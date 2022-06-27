package com.vehicle.management.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.vehicle.management.repository.api.BusBoardApi;
import com.vehicle.management.repository.db.BusBoardMapper;
import com.vehicle.management.domain.BusStationInfo;
import com.vehicle.management.domain.RouteId;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BusBoardService {
    
    @Autowired
    private BusBoardMapper busBoardMapper;

    @Autowired
    private BusBoardApi busBoardApi;

    @Transactional
    public List<RouteId> getRouteIdByRouteNm(String routeNm) {
        return busBoardMapper.selectRouteIdByRouteNm(routeNm);
    }

    @Transactional
    public List<BusStationInfo> getRoutePathByRouteId(int routeId) {
        return busBoardMapper.selectRoutePathByRouteId(routeId);
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
        // ResponseEntity<JsonNode> response = busBoardApi.getRouteByStation(arsId);
        // JsonNode map = response.getBody();
        // // System.out.println(map.get("msgBody").get("itemList").get(0));
        // System.out.println(map.toString());

        // Gson gson = new Gson();
        // Type type = new TypeToken<List<HashMap<String, Object>>>(){}.getType();
        // List<HashMap<String, Object>> list = gson.fromJson(map.get("msgBody").get("itemList").toString(), type);
        // ListIterator<HashMap<String, Object>> listIterator = list.listIterator();
        // while (listIterator.hasNext()) {
        
        //     System.out.println(listIterator.next().get("busRouteId"));
        // }
        // System.out.print(list.toString());
        // ArrayList <?> arrayList = map.get("msgBody").get("itemList");
        return busBoardApi.getRouteByStation(arsId);
    }


    
}
