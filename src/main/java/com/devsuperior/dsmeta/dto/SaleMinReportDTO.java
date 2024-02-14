package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleMinReportDTO {
	private Long id;
	private Double amount;
	private LocalDate date;
	private String sale;
	
	public SaleMinReportDTO(Long id,  LocalDate date ,Double amount, String sale) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.sale = sale;
	}
	
	public SaleMinReportDTO(Sale entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
		sale = entity.getSeller().getName();
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}
