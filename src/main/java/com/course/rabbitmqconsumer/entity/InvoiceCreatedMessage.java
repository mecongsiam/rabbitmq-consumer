package com.course.rabbitmqconsumer.entity;

import com.course.rabbitmqconsumer.json.CustomLocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;

public class InvoiceCreatedMessage {

	private double amount;

	@JsonDeserialize(using = CustomLocalDateDeserializer.class)
	private LocalDate createdDate;

	private String currency;

	private String invoiceNumber;

	public InvoiceCreatedMessage() {

	}

	public InvoiceCreatedMessage(double amount, LocalDate createdDate, String currency, String invoiceNumber) {
		super();
		this.amount = amount;
		this.createdDate = createdDate;
		this.currency = currency;
		this.invoiceNumber = invoiceNumber;
	}

	public double getAmount() {
		return amount;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public String getCurrency() {
		return currency;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Override
	public String toString() {
		return "InvoiceCreatedMessage [amount=" + amount + ", createdDate=" + createdDate + ", currency=" + currency
				+ ", invoiceNumber=" + invoiceNumber + "]";
	}

}
