package com.vehicle.management.data.api;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
@Component
@PropertySource("classpath:config.properties")
public class BusBoardApi {
    
    /** api 인증키 */ 
    @Value("${bus.api.encoding.auth_key}")
    private String serviceKey;

    /** api path */
    @Value("${bus.api.callback_url}")
    private String callBackUrl;

    /** 
     * 경유노선 전체 정류소 도착예정정보를 조회한다. 노선ID에 해당하는 전체 정류소 도착예정정보 목록을 조회한다.
     */
    public ResponseEntity<?> getArrInfoByRouteAll(int busRouteId) {
            
        String url = callBackUrl + "/arrive/getArrInfoByRouteAll";

        RestTemplate restTemplate = new RestTemplate();
        // HttpHeaders header = new HttpHeaders();
        // header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("serviceKey", serviceKey)
                                                                .queryParam("busRouteId", busRouteId)
                                                                .queryParam("resultType", "json")
                                                                .build(true)
                                                                .toUri();
        
        // ResponseEntity<?> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<String>(header), Object.class);
        // System.out.println(response.getBody());
        ResponseEntity<?> response = restTemplate.getForEntity(uri, String.class);
        // System.out.println(response.getBody());

        return response;
    }
    /**
     * 한 정류소의 모든 노선목록을 조회한다.
     */
    public ResponseEntity<JsonNode> getRouteByStation(int arsId) {
            
        String url = callBackUrl + "/stationinfo/getRouteByStation";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        // Response Header to UTF-8
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("serviceKey", serviceKey)
                                                                .queryParam("arsId", arsId)
                                                                .queryParam("resultType", "json")
                                                                .build(true)
                                                                .toUri();
        
        ResponseEntity<JsonNode> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<String>(header), JsonNode.class);
        
        // HashMap<String, Object> resultMap = response.getBody();
        
        return response;
    }      

    /**
     * 한 정류소의 특정노선의 도착예정정보를 조회한다. 정류소ID와 노선ID에 해당하는 도착예정정보를 조회한다.
     */
    public ResponseEntity<?> getArrInfoByRoute(int busRouteId) {
            
        String url = callBackUrl + "/getArrInfoByRouteAll";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        // Response Header to UTF-8
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("serviceKey", serviceKey)
                                                                .queryParam("busRouteId", busRouteId)
                                                                .queryParam("resultType", "json")
                                                                .build(true)
                                                                .toUri();
        
        ResponseEntity<?> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<String>(header), Object.class);
        // System.out.println(response.getBody());
        // Object response = restTemplate.getForEntity(uri.toUriString(), String.class);
        return response;
    }   

    /**
     * 지정된 좌표와 반경 범위 내의 정류소 목록을 조회한다.
     */
    public ResponseEntity<?> getStationByPos(double tmX, double tmY, int radius) {
        
        String url = callBackUrl + "/stationinfo/getStationByPos";

        RestTemplate restTemplate = new RestTemplate();
        
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("serviceKey", serviceKey)
                                                                .queryParam("tmX", tmX)
                                                                .queryParam("tmY", tmY)
                                                                .queryParam("radius", radius)
                                                                .queryParam("resultType", "json")
                                                                .build(true)
                                                                .toUri();
        
        ResponseEntity<?> response = restTemplate.getForEntity(uri, String.class);
        return response;
    }

    /**
     * 고유번호에 해당하는 경유노선목록을 조회한다.
     */
    public ResponseEntity<?> getRouteByStationList(int arsId) {
        
        String url = callBackUrl + "/stationinfo/getRouteByStation";

        RestTemplate restTemplate = new RestTemplate();
        
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("serviceKey", serviceKey)
                                                                .queryParam("arsId", arsId)
                                                                .queryParam("resultType", "json")
                                                                .build(true)
                                                                .toUri();
        
        ResponseEntity<?> response = restTemplate.getForEntity(uri, String.class);
        return response;
    }

    /**
     * 노선의 지도상 경로를 리턴한다. 아이디에 해당하는 노선의 형상 목록을 조회한다.
     */
    public ResponseEntity<?> getRoutePath(int busRouteId) {
        
        String url = callBackUrl + "/busRouteInfo/getRoutePath";

        RestTemplate restTemplate = new RestTemplate();
        
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("serviceKey", serviceKey)
                                                                .queryParam("busRouteId", busRouteId)
                                                                .queryParam("resultType", "json")
                                                                .build(true)
                                                                .toUri();
        
        ResponseEntity<?> response = restTemplate.getForEntity(uri, String.class);
        return response;
    }
    

}
