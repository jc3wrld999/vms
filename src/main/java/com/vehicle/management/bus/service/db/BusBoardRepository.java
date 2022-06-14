package com.vehicle.management.bus.service.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.management.bus.entity.BusStationInfo;

public interface BusBoardRepository extends CrudRepository<BusStationInfo, Integer>{

	@Query(value = "SELECT * FROM BUS_STATION_INFO LIMIT 10", nativeQuery = true)
	@Transactional(readOnly = true)
	List<BusStationInfo> findAll();
}
