package com.vehicle.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.management.data.BusBoardService;
import com.vehicle.management.entity.BusStationInfo;
import com.vehicle.management.entity.RouteId;

import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<?> getStationByPos(@RequestParam Map<String, String> paramMap) {
        
        ResponseEntity<?> busStaionsByPosList = busBoardService.getStationByPos(Double.valueOf(paramMap.get("tmX")), Double.valueOf(paramMap.get("tmY")), Integer.parseInt(paramMap.get("radius")));
        return busStaionsByPosList;
    }

    /** 특정 정류소의 버스 노선 정보 가져오기 */
    @GetMapping("/getRouteByStation/{arsId}")
    public ResponseEntity<?> getRouteByStation(@PathVariable(value="arsId") int arsId) {
        
        ResponseEntity<?> busRouteByStation = busBoardService.getRouteByStation(arsId);
        return busRouteByStation;
    }

    /** 특정 버스 모든 노선 도착 정보 가져오기 */
    @GetMapping("/getArrInfoByRouteAll/{busRouteId}")
    public ResponseEntity<?> getArrInfoByRouteAll(@PathVariable(value="busRouteId") int busRouteId) {
        
        ResponseEntity<?> busArriveInfoList = busBoardService.getArrInfoByRouteAll(busRouteId);
        return busArriveInfoList;
    }

    /** 특정 버스 노선 좌표 정보 가져오기 */
    @GetMapping("/getRoutePathByRouteId/{routeId}")
    public List<BusStationInfo> getRoutePathByRouteId(@PathVariable(value="routeId") int routeId) {
        return busBoardService.getRoutePathByRouteId(routeId);
    }

    /** 버스번호 조회 */
    @GetMapping("/getRouteIdByRouteNm/{routeNm}")
    public List<RouteId> getRouteIdByRouteNm(@PathVariable(value="routeNm") String routeNm) {
        return busBoardService.getRouteIdByRouteNm(routeNm);
    }

    /**
     * 한 정류소의 특정노선의 도착예정정보를 조회한다. 정류소ID와 노선ID에 해당하는 도착예정정보를 조회한다.
     */
    // @GetMapping("/getArrInfoByRoute/{stdId}/{busRouteId}")
    // public ResponseEntity<BusArriveInfo> getArrInfoByRouteList(@PathVariable(value="stdId") int stdId, @PathVariable(value="busRouteId") int busRouteId) {
        
    //     /** serviceKey 인증키       busArriveInfoAuthKey */

    //     /** stId 정류소 ID      112000001 */

    //     /** busRouteId 노선 ID      100100118 */
 
    //     /** ord 정류소 순번         18 */
    //     // ResponseEntity<?> busArrInfoByRouteList = busBoardService.getArrInfoByRouteList(stdId, busRouteId, );

        
    //     return null;
    // }

}
