package com.vehicle.management.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("BusArriveInfo")
public class BusArriveInfo implements Serializable {
	
    private static final long serialVersionUID = 1L;
    

}
