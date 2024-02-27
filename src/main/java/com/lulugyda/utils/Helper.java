package com.lulugyda.utils;

import com.lulugyda.models.responses.MovieListResponse;
import com.lulugyda.models.responses.ResultsResponse;
import io.micronaut.security.authentication.Authentication;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Helper {

    public static Integer getUserId(Authentication authentication) {

        Object userIdObject = authentication.getAttributes().get("user-id");
        String userId = String.valueOf(userIdObject);

        return Integer.valueOf(userId);
    }

    public static String convertToHtml(MovieListResponse movieListResponse) {
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<html><body>");


        htmlBuilder.append("<table border=\"1\"><tr><th>ID</th><th>Title</th><th>Above Average</th></tr>");
        List<ResultsResponse> results = movieListResponse.getResults();
        for (ResultsResponse result : results) {
            htmlBuilder.append("<tr>");
            htmlBuilder.append("<td>").append(result.getId()).append("</td>");
            htmlBuilder.append("<td>").append(result.getTitle()).append("</td>");
            htmlBuilder.append("<td>").append(result.isAboveAverage()).append("</td>");
            htmlBuilder.append("</tr>");
        }
        htmlBuilder.append("</table>");

        htmlBuilder.append("<p>Total Pages: ").append(movieListResponse.getTotalPages()).append("</p>");
        htmlBuilder.append("<p>Total Results: ").append(movieListResponse.getTotalResults()).append("</p>");

        htmlBuilder.append("</body></html>");

        return htmlBuilder.toString();
    }
}
