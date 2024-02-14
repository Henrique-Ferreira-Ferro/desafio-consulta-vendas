package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {
	
	
	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	
	public Page<SaleMinReportDTO> getReport(String dataIni, String dataFim, String name, Pageable pageable){
		
		    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		    LocalDate localMinDate;
		    LocalDate localMaxDate;

		    if (dataIni == null || dataIni.isEmpty()) {
		        LocalDate localMin = today.minusYears(1L);
		        localMinDate = localMin;
		    } else {
		        localMinDate = LocalDate.parse(dataIni);
		    }

		    if (dataFim == null || dataFim.isEmpty()) {
		        localMaxDate = today;
		    } else {
		        localMaxDate = LocalDate.parse(dataFim);
		    }

		    Page<SaleMinReportDTO> result = repository.searchRel(localMinDate, localMaxDate, name, pageable);
		    return result;
	}
	
	public Page<SaleMinSummaryDTO> getSummary(String dataIni, String dataFim, String name, Pageable pageable){
		
				
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()); 

		LocalDate localMinDate;
		LocalDate localMaxDate;
		
		if(dataIni == null || dataIni.equals("")) {
			localMinDate = today.minusYears(1L);
		}else {
			localMinDate = LocalDate.parse(dataIni);
		}
		
		if(dataFim == null || dataFim.equals("")) {
			localMaxDate = today;
		}else {
			localMaxDate = LocalDate.parse(dataFim);
		}
		
		Page<SaleMinSummaryDTO> result = repository.searchSum(localMinDate, localMaxDate, name, pageable);
		
		return result;
	}
	
	
	
	
}
