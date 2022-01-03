package com.cfar.apis.tddashboardbackend.model.client;

import lombok.Data;

@Data
public class InstrumentModel {
    private String assetType;
    private String cusip;
    private String symbol;
}
