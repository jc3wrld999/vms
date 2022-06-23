## 서울시 버스 노선 정보 사이트

#

`지도 이동시 재검색`

![map_marker1](https://user-images.githubusercontent.com/61821641/175223494-299a7a9c-73b4-4ca9-aebb-48b81ca89632.gif)

`마커 클릭시 정류장 도착버스 정보`

![map_marker2](https://user-images.githubusercontent.com/61821641/175223574-7a02f06f-f16a-45a5-8fe6-034ee9fefa2e.gif)

`버스 클릭시 버스 노선도와 도착 정보`

![map_lineString2](https://user-images.githubusercontent.com/61821641/175224631-384cb64a-a641-4294-81e4-5c2bdd431e11.gif)

#

## api 분석

> 특정 좌표 반경 정류소 조회

```
@endpoint
/stationinfo/getStationByPos

@param
tmX
tmY
radius
```

![image](https://user-images.githubusercontent.com/61821641/174729683-0b15b71a-d305-47ed-9755-5796037c2a1c.png)

> 특정 정류장 버스 조회

```
@endpoint
/stationinfo/getRouteByStation

@param
arsId 정류소id

@return
busRouteId busid
busRouteNm bus번호
busRouteType 노선유형 (1:공항, 2:마을, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용)
stBegin 기점
stEnd 종점
nextBus 다음도착버스 시간
firstBusTm 첫차시간
lastBusTm 막차시간
```

![image](https://user-images.githubusercontent.com/61821641/174707694-2e26e058-2029-4362-b0b0-b9458502550e.png)

> 특정 버스 노선 좌표 조회

```
@endpoint
/busRouteInfo/getRoutePath

@param
busRouteId 버스 id

@return
gpsX 좌표X (WGS84)
posX 좌표X (GRS80)
```

![image](https://user-images.githubusercontent.com/61821641/174707913-57d0c267-1251-4558-aad0-d9011f2b9a10.png)

> 특정 버스 노선 이름 조회

```
@endpoint
/arrive/getArrInfoByRouteAll

@param
busRouteId 버스id

@return
stId 정류소 id
stNm 정류소 이름
arsId  정류소 번호
rtNm 버스 번호
mkTm 제공시간
term 배차간격
```

![image](https://user-images.githubusercontent.com/61821641/174710018-a2173c30-47b7-4b5e-8254-97b1efeab894.png)

#

## References

- api
  - [서울시 버스도착정보조회 api](https://www.data.go.kr/data/15000314/openapi.do)
  - [버스별 도착 정보](http://api.bus.go.kr/contents/sub02/getArrInfoByRoute.html)
  - [노선 정보](http://api.bus.go.kr/contents/sub02/getBusRouteList.html)
  - [정류소 정보](http://api.bus.go.kr/contents/sub02/getStationByName.html)
- library
  - [leaflet](https://leafletjs.com/)
  - [leaflet](https://developers.arcgis.com/esri-leaflet/samples/)
- 예제
  - [Leaflet 예제](https://bryceyangs.github.io/study/2021/04/04/Library-Leaflet/)
  - [버스도착정보 조회 웹페이지 예제](https://doqtqu.tistory.com/245)
- etc
  - [국가지점번호](https://ch1517.github.io/CountryBranchCode/)
- resource
  - [아이콘](https://www.flaticon.com/search?word=bus)
