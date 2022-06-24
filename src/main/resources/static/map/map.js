// Map Access Token
mapboxgl.accessToken =
  "pk.eyJ1IjoiamMzd3JsZDk5OSIsImEiOiJjbDRtdGV3ODEwMm9wM2ttZXpjZHRnZHFhIn0.3WMaZZTngmkKBFLQY1IgcQ"; // 계정에 있는 Public Access Token 입력

// This will let you use the .remove() function later on
if (!("remove" in Element.prototype)) {
  Element.prototype.remove = function () {
    if (this.parentNode) {
      this.parentNode.removeChild(this);
    }
  };
}

// Map Init
var map = new mapboxgl.Map({
  container: "map",
  style: "mapbox://styles/mapbox/streets-v9",
  center: [126.886199, 37.476106],
  zoom: 12,
});

// Marker
var currentMarkers = [];

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
        callback(position.coords.longitude, position.coords.latitude);
      }
    });
  }
}

/* 좌표로 정류장 가져오기 */
function fetchStationByPos(long, lat) {
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
  document.getElementById("heading-num").innerHTML =
    data[0]["route_name"] + "번 버스 노선도";
  const copy = [];
  for (i = 0; i < data.length; i++) {
    var arr = [data[i]["coordinate_x"], data[i]["coordinate_y"]];
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
  console.log(data);
  for (i = 0; i < data.length; i++) {
    var currentFeature = data[i];
    var prop = currentFeature.properties;
    var listings = document.getElementById("listings");
    var listing = listings.appendChild(document.createElement("div"));
    listing.className = "item";
    listing.id = "listing-" + i;

    var link = listing.appendChild(document.createElement("a"));
    link.href = "#";
    link.className = "title";
    link.dataPosition = i;
    link.innerHTML = currentFeature["stNm"];

    var details = listing.appendChild(document.createElement("div"));
    details.innerHTML = currentFeature["arrmsg1"];
    details.innerHTML += " &middot; " + currentFeature["arrmsg2"];
  }
}
