package com.vehicle.management.controller;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.management.entity.BusArriveInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rest/arrive")
public class BusApiController {

    /** api 인증키 */ 
    @Value("#{configProp['bus.arrive.info.auth_key']}")
    private String busArriveInfoAuthKey;

    /** api path */
    @Value("#{configProp['bus.arrive.info.path']}")
    private String busArriveInfoPath;
    
    /** 
     * 경유노선 전체 정류소 도착예정정보를 조회한다. 노선ID에 해당하는 전체 정류소 도착예정정보 목록을 조회한다.
     */
    @GetMapping("/getArrInfoByRouteAll/{busRouteId}")
    public ResponseEntity<BusArriveInfo> getArrInfoByRouteAllList(@PathVariable(value="busRouteId") int busRouteId) {
        
        //
        try {
            String path = busArriveInfoPath + "/getArrInfoByRouteAll?serviceKey=" 
                                            + busArriveInfoAuthKey;
            URL url = new URL(busArriveInfoPath);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 한 정류소의 특정노선의 도착예정정보를 조회한다. 정류소ID와 노선ID에 해당하는 도착예정정보를 조회한다.
     */
    @GetMapping("/getArrInfoByRoute/{busRouteId}")
    public ResponseEntity<BusArriveInfo> getArrInfoByRouteList(@PathVariable(value="busRouteId") int busRouteId) {
        
        /** serviceKey 인증키       */

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
        
        /** serviceKey 인증키       */

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
        
        /** serviceKey 인증키       */

        /** stId 정류소 ID      112000001 */

        
        return null;
    }
}
