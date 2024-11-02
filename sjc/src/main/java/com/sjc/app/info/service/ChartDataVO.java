package com.sjc.app.info.service;

import java.util.List;

import lombok.Data;

@Data
public class ChartDataVO {
    private List<String> categories;
    private List<SeriesVO> series;
    
    @Data
    public static class SeriesVO {
        private String name;
        private List<Double> data;
    }
}