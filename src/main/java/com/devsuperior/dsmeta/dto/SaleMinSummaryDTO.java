package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleMinSummaryDTO {
	
	private Double Total;
	private String seller;
	
	public SaleMinSummaryDTO(Double amount, String sale) {
		this.Total = amount;
		this.seller = sale;
	}
	
	public SaleMinSummaryDTO(Sale entity) {
		this.Total = entity.getAmount();
		this.seller = entity.getSeller().getName();
	}

	public Double getAmount() {
		return Total;
	}

	public void setAmount(Double amount) {
		this.Total = amount;
	}

	public String getSale() {
		return seller;
	}

	public void setSale(String sale) {
		this.seller = sale;
	}
	
	
	
	
}
