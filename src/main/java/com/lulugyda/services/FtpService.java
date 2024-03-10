package com.lulugyda.services;

import jakarta.inject.Singleton;
import org.apache.commons.net.ftp.FTPClient;
import io.micronaut.context.annotation.Value;
import org.apache.commons.net.ftp.FTPReply;


import java.io.IOException;
import java.io.InputStream;


@Singleton
public class FtpService {


    @Value("${ftp.host}")
    private String ftpHost;

    @Value("${ftp.port}")
    private int ftpPort;

    @Value("${ftp.username}")
    private String ftpUsername;

    @Value("${ftp.password}")
    private String ftpPassword;

    private FTPClient configureFtpClient() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(ftpHost, ftpPort);
        ftpClient.login(ftpUsername, ftpPassword);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        return ftpClient;
    }


    public void uploadFile(InputStream inputStream, String fileName, String uploadPath, Integer userId) throws IOException {

        FTPClient ftpClient = configureFtpClient();

        try {

            String fileExtension = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : "";
            String newFilename = userId + fileExtension;
            uploadPath = uploadPath.endsWith("/") ? uploadPath + newFilename : uploadPath + "/" + newFilename;

            boolean result = ftpClient.storeFile(uploadPath, inputStream);
            if (!result) {
                throw new IOException("Could not upload the file to the FTP server.");
            }
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }

    }


    public InputStream downloadFile(String remoteFilePath) throws IOException {
        FTPClient ftpClient = configureFtpClient();
        try {
            InputStream inputStream = ftpClient.retrieveFileStream(remoteFilePath);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositivePreliminary(reply)) {
                ftpClient.completePendingCommand();
                inputStream.close();
                return null;
            }
            return inputStream;
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }
    }


}
