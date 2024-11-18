package com.tech.task.report.entity.report;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Amount implements Serializable {
    private double amount;
    private String currencyCode;
}
