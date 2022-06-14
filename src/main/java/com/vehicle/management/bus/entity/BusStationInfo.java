package com.vehicle.management.bus.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

@Entity
@Alias("BusStationInfo")
public class BusStationInfo {
	
	private int    route_id;			/*  */
	private String route_name;			/* TBL_DATA_DAILY_${YYYYMMDD} */
	@Id
	private int    station_no;		/* 20 		fixed */
	private int    node_id;		/* 20 		fixed */
	private int    ars_id;		/* 20 		fixed */
	private String station_name;			/* TBL_DATA_DAILY_${YYYYMMDD} */
	private double coordinate_x;
	private double coordinate_y;
}
