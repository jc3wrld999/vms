// Map Access Token
mapboxgl.accessToken =
  "pk.eyJ1IjoiamMzd3JsZDk5OSIsImEiOiJjbDRtdGV3ODEwMm9wM2ttZXpjZHRnZHFhIn0.3WMaZZTngmkKBFLQY1IgcQ"; // 계정에 있는 Public Access Token 입력

var currentMarkers = []; // Marker
var currentRoute = [];
const initX = 126.886199;
const initY = 37.476106;

if($('#ssIFrame_google').length) {
  var google_sandbox = $('#ssIFrame_google').attr('sandbox')
  google_sandbox += ' allow-modals'
  $('#ssIFrame_google').attr('sandbox', google_sandbox)
}

// Map Init
var map = new mapboxgl.Map({
  container: "map",
  style: "mapbox://styles/mapbox/streets-v9",
  center: [initX, initY],
  zoom: 14,
});

// This will let you use the .remove() function later on
if (!("remove" in Element.prototype)) {
  Element.prototype.remove = function () {
    if (this.parentNode) {
      this.parentNode.removeChild(this);
    }
  };
}

/* 현재 좌표 가져오기 */
function getLocation(callback) {

  if ("geolocation" in navigator) {
    navigator.geolocation.getCurrentPosition((position) => {
      // map 이동
      map.flyTo({
        center: [position.coords.longitude, position.coords.latitude],
        essential: true,
        zoom: 14,
      });
      if (arguments.length > 0) {
        return callback(position.coords.longitude, position.coords.latitude);
      }
    });
    callback(initX, initY);
  } else {
    callback(initX, initY);
  }
}

/* 좌표로 정류장 가져오기 */
function fetchStationByPos(long, lat) {

  if(long == null | lat == null) {
    long = map.getCenter().lng
    lat = map.getCenter().lat
  }
  $.ajax({
    url: "/api/rest/getStationByPos",
    data: { tmX: long, tmY: lat, radius: 1000 },
    type: "get",
    dataType: "JSON",
    success: function (r) {
      getStationByPos(r["msgBody"]["itemList"]);
    },
    error: function (e) {
      console.log(e);
    },
  });
}

/* 좌표로 정류장 가져온거 마커로 뿌리기 */
function getStationByPos(data) {
  for (i = 0; i < data.length; i++) {
    var currentFeature = data[i];
    var el = document.createElement("div");
    el.className = "bus_marker";
    el.setAttribute("stationId", currentFeature["stationId"]);
    el.setAttribute("arsId", currentFeature["arsId"]);
    el.setAttribute("stationNm", currentFeature["stationNm"]);

    // Marker 생성
    var marker1 = new mapboxgl.Marker(el)
      .setLngLat([currentFeature["gpsX"], currentFeature["gpsY"]])
      .setPopup(
        new mapboxgl.Popup({ offset: 5 }) // add popups
          .setHTML("<h4>" + currentFeature["stationNm"] + "</h4>")
      )
      .addTo(map);
    currentMarkers.push(marker1);
    // Marker Click Event
    el.addEventListener("click", (e) => {
      fetchStaionsByPosList(
        e.target.getAttribute("arsId"),
        e.target.getAttribute("stationNm")
      );
    });
  }
}

// Marker 삭제
function removeMarker() {
  if (currentMarkers !== null) {
    for (var i = currentMarkers.length - 1; i >= 0; i--) {
      currentMarkers[i].remove();
    }
  }
}

/* 특정 정류장 버스 조회 */
function fetchStaionsByPosList(arsId, stationNm) {
  $.ajax({
    url: "/api/rest/getRouteByStation/" + arsId,
    type: "get",
    dataType: "JSON",
    success: function (r) {
      getRouteByStation(r["msgBody"]["itemList"], stationNm);
    },
    error: function (e) {
      console.log(e);
    },
  });
}

/* 특정 정류장 버스정보 리스트에 뿌리기 */
function getRouteByStation(data, stationNm) {
  var listings = document.getElementById("listings");
  listings.innerHTML = "";
  document.getElementById("heading-num").innerHTML = stationNm;
  for (i = 0; i < data.length; i++) {
    var currentFeature = data[i];

    var listing = listings.appendChild(document.createElement("div"));
    listing.className = "item";
    listing.id = "listing-" + i;

    var link = listing.appendChild(document.createElement("a"));
    link.href =
      'javascript:location.href="/bus/routeInfo?routeId=' +
      currentFeature["busRouteId"] +
      '"';
    link.className = "title";
    link.dataPosition = i;
    link.innerHTML = currentFeature["busRouteNm"];

    var details = listing.appendChild(document.createElement("div"));
    details.innerHTML = currentFeature["stBegin"];
    details.innerHTML += " &middot; " + currentFeature["stEnd"];
  }
  
}

/* 특정 버스 노선도 조회 */
function fetchRoutePath(routeId) {
  $.ajax({
    url: "/api/rest/getRoutePathByRouteId/" + routeId,
    type: "get",
    success: function (data) {
      getRoutePath(data);
    },
    error: function (e) {
      console.log(e);
    },
  });
}

/* 특정 버스 노선도 지도에 그리기 */
function getRoutePath(data) {
  currentRoute = data.msgBody.itemList;
  document.getElementById("heading-num").innerHTML =
  currentRoute[0]["busRouteNm"] + "번 버스 노선도";
  const copy = [];
  
  for (i = 0; i < currentRoute.length; i++) {
    var arr = [currentRoute[i]["gpsX"], currentRoute[i]["gpsY"]];
    copy.push(arr);
  }

  map.on("load", () => {
    map.addSource("route", {
      type: "geojson",
      data: {
        type: "Feature",
        properties: {},
        geometry: {
          type: "LineString",
          coordinates: copy,
        },
      },
    });
    map.addLayer({
      id: "route",
      type: "line",
      source: "route",
      layout: {
        "line-join": "round",
        "line-cap": "round",
      },
      paint: {
        "line-color": "#FF6319",
        "line-width": 4,
      },
    });
  });
  // map 이동
  flyto(currentRoute[0]["gpsX"], currentRoute[0]["gpsY"], 12);
}

/* 특정 버스 노선별 도착정보 조회 */
function fetchArrInfoByRouteAll(routeId) {
  $.ajax({
    url: "/api/rest/getArrInfoByRouteAll/" + routeId,
    type: "get",
    data: { idArr: "test" },
    dataType: "JSON",
    success: function (data) {
      getArrInfoByRouteAll(data["msgBody"]["itemList"]);
    },
    error: function (e) {
      console.log(e);
    },
  });
}

/* 특정 버스 노선별 도착정보 리스트에 뿌리기 */
function getArrInfoByRouteAll(data) {
  // console.log(data);
  for (i = 0; i < data.length; i++) {
    var currentFeature = data[i];
    var prop = currentFeature.properties;
    var listings = document.getElementById("listings");
    var listing = listings.appendChild(document.createElement("div"));
    listing.className = "item";
    listing.id = "listing-" + i;

    var link = listing.appendChild(document.createElement("a"));
    link.href = "javascript:flytoByArsId("+currentFeature["arsId"]+",'"+currentFeature["stNm"]+"')";
    link.className = "title";
    link.dataPosition = i;
    link.innerHTML = currentFeature["stNm"];

    var details = listing.appendChild(document.createElement("div"));
    details.innerHTML = currentFeature["arrmsg1"];
    details.innerHTML += " &middot; " + currentFeature["arrmsg2"];
  }
}

/* 버스번호로 조회 */
function searchRouteBtn(routNm) {

}

/* move map center */
function flyto(x, y, _zoom) {
  map.flyTo({
    center: [x, y],
    essential: true,
    zoom: _zoom,
  });
}

function flytoByArsId(arsId, stNm) {
  removeMarker();
  // const array1 = currentRoute.map(target => target.genre[0]);
  var _curr = [];
  currentRoute.forEach(function(item, index) { 
    if(item.arsId = arsId && item.stationNm == stNm) {
      _curr.push(item.gpsX);
      _curr.push(item.gpsY);
    }
  });
  flyto(_curr[0], _curr[1], 14);

  var el = document.createElement("div");
  el.className = "bus_marker";
  // el.setAttribute("stationId", currentFeature["stationId"]);
  el.setAttribute("arsId", arsId);
  el.setAttribute("stationNm", stNm);
  // Marker 생성
  var marker1 = new mapboxgl.Marker(el)
  .setLngLat([_curr[0], _curr[1]])
  .setPopup(
    new mapboxgl.Popup({ offset: 5 }) // add popups
      .setHTML("<h4>" + stNm + "</h4>")
  )
  .addTo(map);
  currentMarkers.push(marker1);
  // console.log(array)
  // var _curr = currentRoute.filter(function(e){
  //     return e.arsId === arsId;
  // });
}