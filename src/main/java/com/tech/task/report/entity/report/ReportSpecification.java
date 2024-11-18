package com.tech.task.report.entity.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReportSpecification implements Serializable {
    private String reportType;
    private ReportOptions reportOptions;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataEndTime;
    private List<String> marketplaceIds;
}
