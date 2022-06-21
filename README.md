## 버스 게시판 🚍

#

가져오기

1. rest api rest template으로 http 통신해서 가져오기
   api 더미데이터 넣고 connect
   위도 경도 넣고 connect
2. excel data > db 변환해서 db 넣기
   db connect

뿌리기

0. refleat.js 로 지도 그리기

1. api 가져온거 뿌리기
   마커찍기
   리스트(셀렉트)
2. db 가져온거 그리기 (노선도)

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
