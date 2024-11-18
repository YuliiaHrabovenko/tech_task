package com.tech.task.report.entity.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SalesAndTrafficByDate implements Serializable {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;
}
