package com.lulugyda.controllers;

import com.lulugyda.services.FtpService;
import com.lulugyda.models.enums.AllowedContentType;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.server.types.files.StreamedFile;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;

import static com.lulugyda.utils.Constants.HEADER_X_CORRELATION_ID;
import static com.lulugyda.utils.Helper.getUserId;


@Controller("v1/file")
@Validated
@RequiredArgsConstructor
@Secured(SecurityRule.IS_AUTHENTICATED)
@Introspected
public class FileController {

    private final FtpService ftpService;

    @Post(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA)
    public HttpResponse<String> uploadProfilePic(@Part("file") CompletedFileUpload file,
                                           @Part("uploadPath") String uploadPath,
                                           @Header(HEADER_X_CORRELATION_ID) String correlationId,
                                           Authentication authentication) {


        Integer userId =  getUserId(authentication);

        if (file.getSize() == 0) {
            return HttpResponse.badRequest("No file uploaded.");
        }

        MediaType mediaType = file.getContentType().get();
        if (!AllowedContentType.isAllowed(String.valueOf(mediaType))) {
            return HttpResponse.badRequest("File type not supported. Only png files are allowed.");
        }


        try {

            // upload path is the target directory in the FTP ex: ftp> mkdir uploads -> uploadPath = /uploads/
            ftpService.uploadFile(file.getInputStream(), file.getFilename(), uploadPath, userId);

            return HttpResponse.ok("File uploaded successfully.");
        } catch (Exception e) {
            return HttpResponse.badRequest("Upload failed: " + e.getMessage());
        }
    }

    @Get("/retrieve")
    public HttpResponse<StreamedFile> viewProfilePic(@Header(HEADER_X_CORRELATION_ID) String correlationId,
                                                     Authentication authentication) {
        try {

            Integer userId =  getUserId(authentication);

            String filePath = "uploads/" + userId + ".png";
            InputStream fileStream = ftpService.downloadFile(filePath);

            if (fileStream == null) {
                return HttpResponse.notFound();
            }

            StreamedFile file = new StreamedFile(fileStream, new MediaType(MediaType.IMAGE_PNG));
            return HttpResponse.ok(file);
        } catch (Exception e) {
            return HttpResponse.serverError();
        }
    }



}
