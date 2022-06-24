package com.vehicle.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/bus")
public class BusBoardController {

    private static final String TEST_ROUTE_ID = "100100118";

    @GetMapping("/index")
    public String getBoardIndex() {
        
        return "/bus/index";
    }

    @GetMapping("/routeInfo")
    public String getBoardMap3(@RequestParam(required = false, defaultValue = TEST_ROUTE_ID) int routeId, Model model) {

        model.addAttribute("routeId", routeId);
        
        return "/bus/routeInfo";
    }

    @GetMapping("/stationInfo")
    public String getBoardMap5() {

        return "/bus/stationInfo";
    }

}
