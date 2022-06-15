package com.vehicle.management.data.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.vehicle.management.entity.BusArriveInfo;

@Repository
public class BusBoardApi {
    
        
    /** api 인증키 */ 
    @Value("#{configProp['bus.arrive.info.auth_key']}")
    private String busArriveInfoAuthKey;

    /** api path */
    @Value("#{configProp['bus.arrive.info.path']}")
    private String busArriveInfoPath;

    /** 
     * 경유노선 전체 정류소 도착예정정보를 조회한다. 노선ID에 해당하는 전체 정류소 도착예정정보 목록을 조회한다.
     */
    public ResponseEntity<BusArriveInfo> getArrInfoByRouteAllList(int busRouteId) {
            
        // HashMap<String, Object> result = new HashMap<String, Object>();        

        // ResponseEntity<Object> resultMap = new ResponseEntity<BusArriveInfo>(null, null, 200);

        // String url = busArriveInfoPath + "getArrInfoByRouteAll";

        // try {

        //     RestTemplate restTemplate = new RestTemplate();
            
        //     HttpHeaders header = new HttpHeaders();
        //     HttpEntity<?> entity = new HttpEntity<>(header);

        //     UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

        //     resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);

        //     String path = busArriveInfoPath 
        //             + "/getArrInfoByRouteAll?serviceKey="
        //             + busArriveInfoAuthKey
        //             + "&busRouteId="
        //             + busRouteId;
        //     URL url = new URL(path);
        //     HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //     urlConnection.setRequestMethod("GET");
        //     urlConnection.connect();

        //     BufferedInputStream bi = new BufferedInputStream(urlConnection.getInputStream());
        //     BufferedReader br = new BufferedReader(new InputStreamReader(bi, "UTF-8"));
        //     String returnLine;
        //     while((returnLine = br.readLine()) != null) {
        //         result.append(returnLine);
        //     }

        //     JSONObject jsonObject = XML.toJSONObject(result.toString());
        //     jsonPrintString = jsonObject.toString();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        return null;
    }
}
