package com.tech.task.report.entity.report;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SalesByAsin implements Serializable {
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private Amount orderedProductSales;
    private Amount orderedProductSalesB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
}
