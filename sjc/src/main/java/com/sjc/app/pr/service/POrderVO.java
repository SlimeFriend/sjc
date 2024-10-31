package com.sjc.app.pr.service;

import lombok.Data;

@Data
public class POrderVO {
    private String porderCode;
    private String startDate;
    private String porderDate;
    private String manager;
    private String comm;
    private String status;
    private String planCode;
    private String userName;
}