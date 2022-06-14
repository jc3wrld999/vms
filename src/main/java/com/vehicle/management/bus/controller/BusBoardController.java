package com.vehicle.management.bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vehicle.management.bus.service.BusBoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BusBoardController {

    @Autowired
    private final BusBoardService busBoardService;

    @GetMapping("/bus/index")
    public String getBoardIndex() {
        // int pageSize = 5;
		// Pageable pageable = PageRequest.of(1, pageSize);
        // ldbDataDailyRepository.findAll(pageable);
        return "/bus/index";
    }

    @GetMapping("/bus/list")
    public String getBoardList(Model model) {
        model.addAttribute("resultMap", busBoardService.findAll(1));
        System.out.println("/////////////////////" + busBoardService.findAll(1).toString() + "/////////////////////");
        return "/bus/list";
    }

    @GetMapping("/bus/map")
    public String getBoardMap() {
        // int pageSize = 5;
		// Pageable pageable = PageRequest.of(1, pageSize);
        // ldbDataDailyRepository.findAll(pageable);
        return "/bus/map";
    }
    @GetMapping("/bus/map2")
    public String getBoardMap2() {
        // int pageSize = 5;
		// Pageable pageable = PageRequest.of(1, pageSize);
        // ldbDataDailyRepository.findAll(pageable);
        return "/bus/iframe";
    }
}
