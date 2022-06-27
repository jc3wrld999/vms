package com.vehicle.management.domain;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("BaseApiEntity")
public class BaseApiEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    
    HashMap<String, Object> comMsgHeader;
    HashMap<String, Object> msgHeader;
    HashMap<String, Object> msgBody;

}
