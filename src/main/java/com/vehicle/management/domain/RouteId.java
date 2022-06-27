package com.vehicle.management.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("RouteId")
public class RouteId implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	private int    route_id;
	private String route_name;
}
