package com.vehicle.management.data;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vehicle.management.data.api.BusBoardApi;
import com.vehicle.management.data.db.BusBoardMapper;
import com.vehicle.management.entity.BusStationInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BusBoardService {
    
    @Autowired
    private BusBoardMapper busBoardMapper;

    @Autowired
    private BusBoardApi busBoardApi;

    @Transactional
    public List<BusStationInfo> findById(int id) {
        return null;
    }

    @Transactional
    public List<BusStationInfo> findAll(int routeId) {
        List<BusStationInfo> busBoardList = busBoardMapper.selectStationInfoList(routeId);
        return busBoardList;
    }

    public ResponseEntity<?> getArrInfoByRouteAll(int busRouteId) {
        return busBoardApi.getArrInfoByRouteAll(busRouteId);
    }

    public ResponseEntity<?> getRoutePath(int busRouteId) {
        return busBoardApi.getRoutePath(busRouteId);
    }

    public ResponseEntity<?> getStationByPos(double tmX, double tmY, int radius) {
        return busBoardApi.getStationByPos(tmX, tmY, radius);
    }

    public ResponseEntity<JsonNode> getRouteByStation(int arsId) {
        ResponseEntity<JsonNode> response = busBoardApi.getRouteByStation(arsId);
        JsonNode map = response.getBody();
        System.out.println("//////////////////////////////////////////////");
        // System.out.println(map.get("msgBody").get("itemList").get(0));
        System.out.println(map.toString());

        Gson gson = new Gson();
        Type type = new TypeToken<List<HashMap<String, Object>>>(){}.getType();
        List<HashMap<String, Object>> list = gson.fromJson(map.get("msgBody").get("itemList").toString(), type);
        ListIterator<HashMap<String, Object>> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
        
            System.out.println(listIterator.next().get("busRouteId"));
        }
        System.out.print(list.toString());
        // ArrayList <?> arrayList = map.get("msgBody").get("itemList");
        return busBoardApi.getRouteByStation(arsId);
    }

    // public ResponseEntity<?> getStationByPos(double parseDouble, int parseInt, double parseDouble2) {
    //     return null;
    // }

    
}
