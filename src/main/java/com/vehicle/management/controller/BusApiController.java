package com.vehicle.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.management.data.BusBoardService;
import com.vehicle.management.entity.BusArriveInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rest/arrive")
public class BusApiController {

    @Autowired
    private BusBoardService busBoardService;
    
    @GetMapping("/getArrInfoByRouteAll/{busRouteId}") // 100100118
    public ResponseEntity<?> getArrInfoByRouteAllList(@PathVariable(value="busRouteId") int busRouteId) {
        
        ResponseEntity<?> busArriveInfoList = busBoardService.getArrInfoByRouteAllList(busRouteId);
        return busArriveInfoList;
    }

    /**
     * 한 정류소의 특정노선의 도착예정정보를 조회한다. 정류소ID와 노선ID에 해당하는 도착예정정보를 조회한다.
     */
    @GetMapping("/getArrInfoByRoute/{busRouteId}")
    public ResponseEntity<BusArriveInfo> getArrInfoByRouteList(@PathVariable(value="busRouteId") int busRouteId) {
        
        /** serviceKey 인증키       busArriveInfoAuthKey */

        /** stId 정류소 ID      112000001 */

        /** busRouteId 노선 ID      100100118 */
 
        /** ord 정류소 순번         18 */
        
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
