package com.vehicle.management.repository.db;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.vehicle.management.domain.BusStationInfo;
import com.vehicle.management.domain.RouteId;

@Mapper
@Repository
public interface BusBoardMapper {

	public List<BusStationInfo> selectRoutePathByRouteId(int busRouteId);

	public List<RouteId> selectRouteIdByRouteNm(String routeNm);

}
