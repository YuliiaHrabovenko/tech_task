package com.tech.task.report.service.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.task.report.entity.report.SalesTrafficReportEntity;
import com.tech.task.report.repository.report.SalesTrafficReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalesTrafficReportService {

    private final SalesTrafficReportRepository salesTrafficReportRepository;

    public List<SalesTrafficReportEntity> findStatisticsByDateRange(Date startDate, Date endDate) {
        if (endDate == null) {
            return salesTrafficReportRepository.findStatisticsBySingleDate(startDate);
        }
        return salesTrafficReportRepository.findStatisticsByDateRange(startDate, endDate);
    }

    public List<SalesTrafficReportEntity> findStatisticsByAsin(List<String> asins) {
        return salesTrafficReportRepository.findStatisticsByAsinIn(asins);
    }

    public List<SalesTrafficReportEntity> findAllSalesAndTrafficByDates() {
        return salesTrafficReportRepository.findAllStatisticsByDates();
    }

    public List<SalesTrafficReportEntity> findAllSalesAndTrafficByAsin() {
        return salesTrafficReportRepository.findAllStatisticsByAsins();
    }

    @Scheduled(fixedRate = 60000) // execute every minute
    public void updateStatistics() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("test_report.json");
            SalesTrafficReportEntity newReport = objectMapper.readValue(jsonFile, SalesTrafficReportEntity.class);

            salesTrafficReportRepository.findTopByOrderByIdDesc().ifPresent(reportToUpdate -> {
                if (!newReport.equals(reportToUpdate)) {
                    reportToUpdate.setReportSpecification(newReport.getReportSpecification());
                    reportToUpdate.setSalesAndTrafficByAsin(newReport.getSalesAndTrafficByAsin());
                    reportToUpdate.setSalesAndTrafficByDate(newReport.getSalesAndTrafficByDate());
                    salesTrafficReportRepository.save(reportToUpdate);
                }
            });
        } catch (IOException e) {
            log.error("Error updating statistics: {}", e.getMessage());
        }
    }

    public SalesTrafficReportEntity getAllStatistics() {
        return salesTrafficReportRepository.findTopByOrderByIdDesc().orElse(null);
    }
}
