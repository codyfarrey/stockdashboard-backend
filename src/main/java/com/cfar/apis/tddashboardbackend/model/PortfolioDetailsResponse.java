package com.cfar.apis.tddashboardbackend.model;

import lombok.Data;

import java.util.List;

@Data
public class PortfolioDetailsResponse {
    private OverviewModel overview;
    private List<PositionModel> positions;
}
