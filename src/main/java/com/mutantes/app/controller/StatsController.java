package com.mutantes.app.controller;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutantes.app.database.DnaRepository;

@RestController
@RequestMapping("/stats")
public class StatsController {
	@Autowired
	private DnaRepository repository;
	
	@GetMapping
	public ResponseEntity<?> getStats(){
		
		try {
			repository.getRatio();
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
		
	}
}
