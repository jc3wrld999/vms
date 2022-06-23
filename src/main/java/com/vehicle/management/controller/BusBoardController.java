package com.vehicle.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vehicle.management.data.BusBoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/bus")
public class BusBoardController {

    // @Value("${bus.api.test.routeId}")
    private static final String TEST_ROUTE_ID = "100100118";

    @Autowired
    private final BusBoardService busBoardService;

    @GetMapping("/index")
    public String getBoardIndex() {
        // int pageSize = 5;
		// Pageable pageable = PageRequest.of(1, pageSize);
        // ldbDataDailyRepository.findAll(pageable);
        return "/bus/index";
    }

    @GetMapping("/list")
    public String getBoardList(@PathVariable(value="routeId") int routeId, Model model) {
        model.addAttribute("resultMap", busBoardService.findAll(routeId));
        System.out.println("/////////////////////" + busBoardService.findAll(routeId).toString() + "/////////////////////");
        return "/bus/list";
    }


    @GetMapping("/map")
    public String getBoardMap() {
        // int pageSize = 5;
		// Pageable pageable = PageRequest.of(1, pageSize);
        // ldbDataDailyRepository.findAll(pageable);
        return "/bus/map";
    }
    @GetMapping("/map2")
    public String getBoardMap2() {
        return "/bus/map2";
    }
    @GetMapping("/map3")
    public String getBoardMap3(@RequestParam(required = false, defaultValue = TEST_ROUTE_ID) int routeId, Model model) {
        model.addAttribute("routeId", routeId);
        return "/bus/map3";
    }
    @GetMapping("/map4")
    public String getBoardMap4() {
        return "/bus/map4";
    }
    @GetMapping("/map5")
    public String getBoardMap5() {
        return "/bus/map5";
    }
    @GetMapping("/iframe2")
    public String getBoardIframe2() {
        // int pageSize = 5;
		// Pageable pageable = PageRequest.of(1, pageSize);
        // ldbDataDailyRepository.findAll(pageable);
        return "/bus/iframe2";
    }
}
