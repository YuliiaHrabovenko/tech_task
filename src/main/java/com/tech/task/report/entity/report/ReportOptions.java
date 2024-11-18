package com.tech.task.report.entity.report;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportOptions {
    private DateGranularity dateGranularity;
    private AsinGranularity asinGranularity;
}
