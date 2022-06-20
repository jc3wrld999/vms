package com.vehicle.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vehicle.management.data.BusBoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/bus")
public class BusBoardController {

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
    public String getBoardList(Model model) {
        model.addAttribute("resultMap", busBoardService.findAll(1));
        System.out.println("/////////////////////" + busBoardService.findAll(1).toString() + "/////////////////////");
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
        // int pageSize = 5;
		// Pageable pageable = PageRequest.of(1, pageSize);
        // ldbDataDailyRepository.findAll(pageable);
        return "/bus/map2";
    }
    @GetMapping("/iframe2")
    public String getBoardIframe2() {
        // int pageSize = 5;
		// Pageable pageable = PageRequest.of(1, pageSize);
        // ldbDataDailyRepository.findAll(pageable);
        return "/bus/iframe2";
    }
}
