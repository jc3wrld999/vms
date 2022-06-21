package com.vehicle.management.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.management.data.BusBoardService;
import com.vehicle.management.entity.BusArriveInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rest")
public class BusApiController {

    @Autowired
    private BusBoardService busBoardService;
    
    @GetMapping("/getArrInfoByRouteAll/{busRouteId}") // 100100118
    public Object getArrInfoByRouteAllList(@PathVariable(value="busRouteId") int busRouteId, Model model) {
        
        ResponseEntity<?> busArriveInfoList = busBoardService.getArrInfoByRouteAllList(busRouteId);
        model.addAttribute("resultMap", busArriveInfoList.getBody());
        return busArriveInfoList.getBody();
    }

    @GetMapping("/getRoutePathList/{busRouteId}") // 100100118
    public ResponseEntity<?> getRoutePathList(@PathVariable(value="busRouteId") int busRouteId, Model model) {
        
        ResponseEntity<?> busRoutePathList = busBoardService.getRoutePathList(busRouteId);
        model.addAttribute("resultMap", busRoutePathList.getBody());
        // System.out.println(busRoutePathList.toString());
        return busRoutePathList;
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
