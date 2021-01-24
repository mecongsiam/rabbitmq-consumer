package com.course.rabbitmqconsumer.entity;

import com.course.rabbitmqconsumer.json.CustomLocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;

public class InvoiceRejectedMessage {

	private String invoiceNumber;

	private String reason;

	@JsonDeserialize(using = CustomLocalDateDeserializer.class)
	private LocalDate rejectDate;

	public InvoiceRejectedMessage() {

	}

	public InvoiceRejectedMessage(LocalDate rejectDate, String invoiceNumber, String reason) {
		super();
		this.rejectDate = rejectDate;
		this.invoiceNumber = invoiceNumber;
		this.reason = reason;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public String getReason() {
		return reason;
	}

	public LocalDate getRejectDate() {
		return rejectDate;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setRejectDate(LocalDate rejectDate) {
		this.rejectDate = rejectDate;
	}

	@Override
	public String toString() {
		return "InvoiceRejectedMessage [rejectDate=" + rejectDate + ", invoiceNumber=" + invoiceNumber + ", reason="
				+ reason + "]";
	}

}
