package com.cfar.apis.tddashboardbackend.config;

import com.cfar.apis.tddashboardbackend.client.TDAmeritradeAPIClient;
import com.cfar.apis.tddashboardbackend.model.client.TDAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private static final String AUTH_HEADER = "Authorization";

    @Autowired
    TDAmeritradeAPIClient tdAmeritradeAPIClient;


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (request.getURI().toString() == "https://api.tdameritrade.com/v1/oauth2/token") {
            return execution.execute(request, body);
        }

        HttpHeaders headers = request.getHeaders();

        if (headers.get(AUTH_HEADER) == null && headers.get(AUTH_HEADER).isEmpty()) {
            TDAuthResponse authResponse = tdAmeritradeAPIClient.authorize();
            if (authResponse.getAccessToken() != null && !authResponse.getAccessToken().isEmpty()) {
                headers.set(AUTH_HEADER, authResponse.getAccessToken());
            }
        }

        return execution.execute(request, body);
    }
}
