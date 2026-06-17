package org.example.personalfinanceapp.controller;

import org.example.personalfinanceapp.dto.DashboardResponseDTO;
import org.example.personalfinanceapp.service.DashBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashBoardService dashBoardService;

    public DashboardController(DashBoardService dashboardService){

        this.dashBoardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getDashboard(Authentication authentication,
                                                             @RequestParam Integer month,
                                                             @RequestParam Integer year){

        String email = authentication.getName();

        DashboardResponseDTO dashBoardResponse = dashBoardService.getDashboard(email, month, year);
        return ResponseEntity.ok(dashBoardResponse);

    }
}
