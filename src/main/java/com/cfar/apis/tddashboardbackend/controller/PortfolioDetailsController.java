package com.cfar.apis.tddashboardbackend.controller;

import com.cfar.apis.tddashboardbackend.model.PortfolioDetailsResponse;
import com.cfar.apis.tddashboardbackend.service.PortfolioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortfolioDetailsController {
    @Autowired
    PortfolioDetailsService portfolioDetailsService;

    @GetMapping("portfolio")
    public ResponseEntity<PortfolioDetailsResponse> getPortfolioDetails() {
        return ResponseEntity.ok()
            .body(portfolioDetailsService.getPortfolioDetails());
    }
}
