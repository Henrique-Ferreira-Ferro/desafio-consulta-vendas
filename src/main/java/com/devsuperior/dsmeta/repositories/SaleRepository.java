package com.devsuperior.dsmeta.repositories;


import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleMinReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	
	/*
	 * SELECT
		tb_sales.id, tb_sales.date,tb_sales.amount,
		tb_seller.name 
		FROM tb_sales 
		INNER JOIN tb_seller on tb_sales.seller_id = tb_seller.id
		WHERE date BETWEEN '2022-05-01' AND '2022-05-31'
		AND tb_seller.name LIKE '%Odinson%'
		ORDER BY tb_sales.amount
		;
	 */
	
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinReportDTO(obj.id, obj.date, obj.amount, obj.seller.name) "
	        + "FROM Sale obj "
	        + "WHERE obj.date BETWEEN :dataIni AND :dataFim "
	        + "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%',:name, '%')) "
	        + "ORDER BY obj.amount ")
	Page<SaleMinReportDTO> searchRel(LocalDate dataIni, LocalDate dataFim, String name, Pageable pageable);

	
	/*
	 * SELECT tb_seller.name as Seller, SUM(tb_sales.amount) as Total FROM tb_sales
		INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id
		WHERE date BETWEEN '2022-01-01' AND '2022-06-30'
		GROUP BY tb_seller.name
		ORDER BY tb_seller.name;
	 */
	
	//Double amount, LocalDate date, String sale
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinSummaryDTO( SUM(obj.amount),obj.seller.name) "
			+ "FROM Sale obj "
			+ "WHERE obj.date BETWEEN :dataIni AND :dataFim "
			+ "AND obj.seller.name LIKE CONCAT('%',:name,'%') "
			+ "GROUP BY obj.seller.name "
			+ "ORDER BY obj.seller.name")
	Page<SaleMinSummaryDTO> searchSum(LocalDate dataIni, LocalDate dataFim, String name, Pageable pageable);
	
	
	
	
	
	
}
