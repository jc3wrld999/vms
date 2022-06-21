package com.vehicle.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.management.data.BusBoardService;
import com.vehicle.management.entity.BusArriveInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rest")
@Component
@PropertySource("classpath:config.properties")
public class BusApiController {

    @Value("${bus.api.test.routeId}")
    private String routeId;

    @Value("${bus.api.test.tmX}")
    private String tmX;

    @Value("${bus.api.test.tmY}")
    private String tmY;

    @Autowired
    private BusBoardService busBoardService;

    /** 위치 기반 정류소 정보 가져오기 */
    @GetMapping("/getStationByPos")
    public ResponseEntity<?> getStationByPos(@RequestParam Map<String, String> paramMap, Model model) {
        System.out.println(paramMap.get("tmX")+"//////////////////////////////////////");
        ResponseEntity<?> busStaionsByPosList = busBoardService.getStationByPos(Double.valueOf(paramMap.get("tmX")), Double.valueOf(paramMap.get("tmY")), Integer.parseInt(paramMap.get("radius")));
        return busStaionsByPosList;
    }

    /** 특정 정류소의 버스 노선 정보 가져오기 */
    @GetMapping("/getRouteByStation/{arsId}")
    public ResponseEntity<?> getRouteByStation(@PathVariable(value="arsId") int arsId, Model model) {
        
        ResponseEntity<?> busRouteByStation = busBoardService.getRouteByStation(arsId);
        return busRouteByStation;
    }

    /** 특정 버스 모든 노선 도착 정보 가져오기 */
    @GetMapping("/getArrInfoByRouteAll/{busRouteId}")
    public ResponseEntity<?> getArrInfoByRouteAll(@PathVariable(value="busRouteId") int busRouteId, Model model) {
        
        ResponseEntity<?> busArriveInfoList = busBoardService.getArrInfoByRouteAll(busRouteId);
        return busArriveInfoList;
    }

    /** 특정 버스 특정 정류장 도착 정보 가져오기 */
    @GetMapping("/getArrInfoByRoute/{busRouteId}")
    public ResponseEntity<?> getArrInfoByRoute(@PathVariable(value="busRouteId") int busRouteId, Model model) {
        
        // ResponseEntity<?> busArriveInfoList = busBoardService.getArrInfoByRouteAll(busRouteId);
        return null;
    }

    /** 특정 버스 노선 좌표 정보 가져오기 */
    @GetMapping("/getRoutePath/{busRouteId}")
    public ResponseEntity<?> getRoutePath(@PathVariable(value="busRouteId") int busRouteId, Model model) {
        
        ResponseEntity<?> busRoutePath = busBoardService.getRoutePath(busRouteId);
        return busRoutePath;
    }



    /**
     * 한 정류소의 특정노선의 도착예정정보를 조회한다. 정류소ID와 노선ID에 해당하는 도착예정정보를 조회한다.
     */
    @GetMapping("/getArrInfoByRoute/{stdId}/{busRouteId}")
    public ResponseEntity<BusArriveInfo> getArrInfoByRouteList(@PathVariable(value="stdId") int stdId, @PathVariable(value="busRouteId") int busRouteId) {
        
        /** serviceKey 인증키       busArriveInfoAuthKey */

        /** stId 정류소 ID      112000001 */

        /** busRouteId 노선 ID      100100118 */
 
        /** ord 정류소 순번         18 */
        // ResponseEntity<?> busArrInfoByRouteList = busBoardService.getArrInfoByRouteList(stdId, busRouteId, );

        
        return null;
    }

    /**
     * 한 정류소의 특정노선의 저상버스 도착예정정보를 조회한다. 정류소ID와 노선ID에 해당하는 저상버스 도착예정정보를 조회한다.
     */
    @GetMapping("/getLowArrInfoByRoute/{busRouteId}")
    public ResponseEntity<BusArriveInfo> getLowArrInfoByRouteList(@PathVariable(value="busRouteId") int busRouteId) {
        
        /** serviceKey 인증키       busArriveInfoAuthKey */

        /** stId 정류소 ID      112000001 */

        /** busRouteId 노선 ID      100100118 */
 
        /** ord 정류소 순번         18 */
        
        return null;
    }

    /**
     * 정류소ID로 저상버스 도착예정정보를 조회한다. 정류소ID에 해당하는 저상버스 도착예정정보 목록을 조회한다.
     */
    @GetMapping("/getLowArrInfoByStId/{busRouteId}")
    public ResponseEntity<BusArriveInfo> getLowArrInfoByStIdList(@PathVariable(value="busRouteId") int busRouteId) {
        
        /** serviceKey 인증키       busArriveInfoAuthKey */

        /** stId 정류소 ID      112000001 */

        
        return null;
    }
}
