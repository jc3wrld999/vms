package com.vehicle.management.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.management.data.db.BusBoardMapper;
import com.vehicle.management.entity.BusStationInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BusBoardService {
    
    @Autowired
    private BusBoardMapper busBoardMapper;

    @Transactional
    public List<BusStationInfo> findById(int id) {
        return null;
    }

    @Transactional
    public List<BusStationInfo> findAll(int id) {
        List<BusStationInfo> busBoardList = busBoardMapper.selectStationInfoList();
        return busBoardList;
    }
}
