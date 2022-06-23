package com.vehicle.management.data.db;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.vehicle.management.entity.BusStationInfo;
import com.vehicle.management.entity.RouteId;

@Mapper
@Repository
public interface BusBoardMapper {

	public List<BusStationInfo> selectRoutePathByRouteId(int busRouteId);

	public List<RouteId> selectRouteIdByRouteNm(String routeNm);

}
