package com.course.rabbitmqconsumer.entity;

import com.course.rabbitmqconsumer.json.CustomLocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;

public class InvoiceCancelledMessage {

	@JsonDeserialize(using = CustomLocalDateDeserializer.class)
	private LocalDate cancelDate;

	private String invoiceNumber;

	private String reason;

	public InvoiceCancelledMessage() {

	}

	public InvoiceCancelledMessage(LocalDate cancelDate, String invoiceNumber, String reason) {
		super();
		this.cancelDate = cancelDate;
		this.invoiceNumber = invoiceNumber;
		this.reason = reason;
	}

	public LocalDate getCancelDate() {
		return cancelDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public String getReason() {
		return reason;
	}

	public void setCancelDate(LocalDate cancelDate) {
		this.cancelDate = cancelDate;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "InvoiceCancelledMessage [cancelDate=" + cancelDate + ", invoiceNumber=" + invoiceNumber + ", reason="
				+ reason + "]";
	}

}
