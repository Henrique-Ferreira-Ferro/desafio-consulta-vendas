package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinReportDTO>> getReport
	(@RequestParam(value="dataIni", defaultValue = "") String dataIni, @RequestParam(value="dataFim",defaultValue="") String dataFim,@RequestParam(value="name",defaultValue = "") String name, Pageable pageable) {
		Page<SaleMinReportDTO> sale = service.getReport(dataIni, dataFim, name, pageable);
		return ResponseEntity.ok(sale);
 	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleMinSummaryDTO>> getSummary
	(@RequestParam(value="minDate", defaultValue="") String dataIni,@RequestParam(value="maxDate",defaultValue="") String dataFim, @RequestParam(value="name",defaultValue="") String name, Pageable pageable) {
		Page<SaleMinSummaryDTO> sale = service.getSummary(dataIni, dataFim, name, pageable);
		return ResponseEntity.ok(sale);
	}
	
	
}