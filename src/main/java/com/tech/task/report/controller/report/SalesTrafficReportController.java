package com.tech.task.report.controller.report;

import com.tech.task.report.entity.report.SalesTrafficReportEntity;
import com.tech.task.report.service.report.SalesTrafficReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalesTrafficReportController {

    private final SalesTrafficReportService salesTrafficReportService;

    @GetMapping("/statisticsByDate")
    public List<SalesTrafficReportEntity> findStatisticsByDateRange(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                 @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return salesTrafficReportService.findStatisticsByDateRange(startDate, endDate);
    }

    @GetMapping("/statisticsByAsin")
    public List<SalesTrafficReportEntity> findStatisticsByAsin(@RequestParam List<String> asins) {
        return salesTrafficReportService.findStatisticsByAsin(asins);
    }

    @GetMapping("/statisticsByAllDates")
    public List<SalesTrafficReportEntity> findAllSalesAndTrafficByDates() {
        return salesTrafficReportService.findAllSalesAndTrafficByDates();
    }

    @GetMapping("/statisticsByAllAsins")
    public List<SalesTrafficReportEntity> findAllSalesAndTrafficByAsin() {
        return salesTrafficReportService.findAllSalesAndTrafficByAsin();
    }

    @GetMapping("/allStatistics")
    public SalesTrafficReportEntity getAllStatistics() {
        return salesTrafficReportService.getAllStatistics();
    }
}
