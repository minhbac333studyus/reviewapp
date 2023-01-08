package com.rp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rp.data.Author;

import lombok.NoArgsConstructor;
@NoArgsConstructor
public class ResponseModel<T> extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940474490545877283L; 

	void setDataForResponse(Page<T> page, List<T> result ) {
		this.put("result", result);
		this.put("currentPage", page.getNumber());
		this.put("totalItems", page.getTotalElements());
		this.put("totalPages", page.getTotalPages()); 
	}
}
