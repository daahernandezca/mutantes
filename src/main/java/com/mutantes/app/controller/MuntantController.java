package com.mutantes.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import com.mutantes.app.database.DnaRepository;
import com.mutantes.app.entities.Dna;

@RestController
@RequestMapping("/mutant")
public class MuntantController {

	@Autowired
	private DnaRepository repository;
	
	@PostMapping
	public ResponseEntity<?> checkMutant( @RequestParam(value="dna") String dnaSeq   ){
		
		try {
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(dnaSeq);
			String[] arr = new String[jsonArray.size()];
			for (int i=0; i<jsonArray.size(); i++) {
				arr[i] = (String) jsonArray.get(i) ;
			}
			
			Dna dna = new Dna(arr);
			boolean isMutant = dna.isMutant();
			repository.addDna(dna);
			
			if(isMutant) {
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
		
	}
}