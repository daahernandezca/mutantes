package com.mutantes.app.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class MutantControllerTest {

	private MutantController mutantcontroller = new MutantController();
	
	
	@Test
	@DisplayName("Should give internal server error for wrong info")
	public void testWrongInfo() throws Exception {
        ResponseEntity<?> response = mutantcontroller.checkMutant("");
        assertEquals("500 INTERNAL_SERVER_ERROR", response.getStatusCode().toString());
    }
	
	@Test
	@DisplayName("Should give internal server error for worng dna")
	public void testWrongDna() throws Exception {
		ResponseEntity<?> response = mutantcontroller.checkMutant("[]");
        assertEquals("500 INTERNAL_SERVER_ERROR", response.getStatusCode().toString());
    }
	
	@Test
	@DisplayName("Should give internal server error for wrong dna lenght")
	public void testWrongDnaLength() throws Exception {
		ResponseEntity<?> response = mutantcontroller.checkMutant("[\"\"]");
        assertEquals("500 INTERNAL_SERVER_ERROR", response.getStatusCode().toString());
    }
	
	@Test
	@DisplayName("Should give internal server error for wrong dna nucleos")
	public void testWrongDnaNuckeos() throws Exception {
		ResponseEntity<?> response = mutantcontroller.checkMutant("[\"ATXCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]");
        assertEquals("500 INTERNAL_SERVER_ERROR", response.getStatusCode().toString());
    }
	
	@Test
	@DisplayName("Test Mutant")
	public void testMutant() throws Exception {
        ResponseEntity<?> response = mutantcontroller.checkMutant("[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]");
        assertEquals("500 INTERNAL_SERVER_ERROR", response.getStatusCode().toString());
    }
}
