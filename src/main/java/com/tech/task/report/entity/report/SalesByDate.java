package com.tech.task.report.entity.report;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SalesByDate implements Serializable {
    private Amount orderedProductSales;
    private Amount orderedProductSalesB2B;
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
    private Amount averageSalesPerOrderItem;
    private Amount averageSalesPerOrderItemB2B;
    private double averageUnitsPerOrderItem;
    private double averageUnitsPerOrderItemB2B;
    private Amount averageSellingPrice;
    private Amount averageSellingPriceB2B;
    private int unitsRefunded;
    private double refundRate;
    private int claimsGranted;
    private Amount claimsAmount;
    private Amount shippedProductSales;
    private int unitsShipped;
    private int ordersShipped;
}
