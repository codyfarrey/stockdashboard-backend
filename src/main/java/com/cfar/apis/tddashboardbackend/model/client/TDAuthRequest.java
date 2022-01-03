package com.cfar.apis.tddashboardbackend.model.client;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TDAuthRequest {
    private String grantType;
    private String refreshToken;
    private String clientId;
    private String redirectUri;
}
