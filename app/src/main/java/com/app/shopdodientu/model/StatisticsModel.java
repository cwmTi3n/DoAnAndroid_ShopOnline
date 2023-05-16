package com.app.shopdodientu.model;

public class StatisticsModel {

    private int totalOrder;
    private int deliveringOrder;
    private int deliveredOrder;
    private int cancelOrder;
    private int revenue;

    private StatisticsModel(){}

    public StatisticsModel(int totalOrder, int deliveringOrder, int deliveredOrder, int cancelOrder, int revenue) {
        this.totalOrder = totalOrder;
        this.deliveringOrder = deliveringOrder;
        this.deliveredOrder = deliveredOrder;
        this.cancelOrder = cancelOrder;
        this.revenue = revenue;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public int getDeliveringOrder() {
        return deliveringOrder;
    }

    public void setDeliveringOrder(int deliveringOrder) {
        this.deliveringOrder = deliveringOrder;
    }

    public int getDeliveredOrder() {
        return deliveredOrder;
    }

    public void setDeliveredOrder(int deliveredOrder) {
        this.deliveredOrder = deliveredOrder;
    }

    public int getCancelOrder() {
        return cancelOrder;
    }

    public void setCancelOrder(int cancelOrder) {
        this.cancelOrder = cancelOrder;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
}
