package com.vehicle.management.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.management.bus.entity.BusStationInfo;
import com.vehicle.management.bus.service.db.BusBoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BusBoardService {
    
    @Autowired
    private BusBoardRepository busBoardRepository;

    @Transactional
    public List<BusStationInfo> findById(int id) {
        return null;
    }

    @Transactional
    public List<BusStationInfo> findAll(int id) {
        List<BusStationInfo> busBoardList = busBoardRepository.findAll();
        return busBoardList;
    }
}
