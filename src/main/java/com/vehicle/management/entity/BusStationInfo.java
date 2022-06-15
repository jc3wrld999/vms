package com.vehicle.management.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("BusStationInfo")
public class BusStationInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int    route_id;
	private String route_name;	
	private int    station_no;
	private int    node_id;	
	private int    ars_id;
	private String station_name;
	private double coordinate_x;
	private double coordinate_y;
}
