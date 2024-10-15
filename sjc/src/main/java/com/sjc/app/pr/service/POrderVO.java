package com.sjc.app.pr.service;

import lombok.Data;

@Data
public class POrderVO {
    private String porderCode;
    private String startDate;
    private String endDate;
    private String manager;
    private String comm;
    private Integer status;
    private String planCode;
    
}