package com.vehicle.management.entity;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("BaseApiVO")
public class BaseApiVO implements Serializable {

	private static final long serialVersionUID = 1L;
    
    HashMap<String, Object> comMsgHeader;
    HashMap<String, Object> msgHeader;
    HashMap<String, Object> msgBody;

}
