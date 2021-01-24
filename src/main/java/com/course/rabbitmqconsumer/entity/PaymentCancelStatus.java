package com.course.rabbitmqconsumer.entity;

public class PaymentCancelStatus {

    private boolean status;

    private String invoiceNumber;

    public PaymentCancelStatus(boolean status, String invoiceNumber) {
        this.status = status;
        this.invoiceNumber = invoiceNumber;
    }

    public PaymentCancelStatus() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
