package com.cfar.apis.tddashboardbackend.client;

import com.cfar.apis.tddashboardbackend.model.client.TDAuthRequest;
import com.cfar.apis.tddashboardbackend.model.client.TDAuthResponse;
import com.cfar.apis.tddashboardbackend.model.client.TDGetAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TDAmeritradeAPIClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${auth.accKey}")
    private String accountKey;

    @Value("${auth.refreshToken}")
    private String refreshToken;

    @Value("${auth.consumerKey}")
    private String consumerKey;

    @Value("${api.tdAmeritradeApi.url}")
    private String baseUrl;

    @Value("${api.tdAmeritradeApi.authenticationEndpoint}")
    private String authEndpoint;

    @Value("${api.tdAmeritradeApi.getAccountsEndpoint}")
    private String getAccountsEndpoint;

    public TDAuthResponse authorize() {
        MultiValueMap<String, String> req = new LinkedMultiValueMap<>();
        req.add("grant_type", "refresh_token");
        req.add("refresh_token", refreshToken);
        req.add("client_id", consumerKey);
        req.add("redirect_uri", "https://127.0.0.1");

        String uri = baseUrl + authEndpoint;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(req, headers);

        ResponseEntity<TDAuthResponse> response = restTemplate.exchange(uri, HttpMethod.POST, entity, TDAuthResponse.class);

        return response.getBody();
    }

    public TDGetAccountResponse getAccount(String authToken) {
        String uri = UriComponentsBuilder.fromUriString(baseUrl + getAccountsEndpoint + accountKey)
                .queryParam("fields", "positions")
                .build()
                .toString();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authToken);

        System.out.println("Calling URI: " + uri);

        ResponseEntity<TDGetAccountResponse> response = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(headers), TDGetAccountResponse.class);


        return response.getBody();
    }

}
