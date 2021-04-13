package com.mutantes.app.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


public class DnaTest {

	@Test
    @DisplayName("Should create dna")
    public void shouldCreatedna() throws Exception {
		String[] seq = {"ATGCGA","CAGTGC"}; 
        Dna dna = new Dna(seq);
        assertEquals("ATGCGACAGTGC", dna.getDna_id());
    }
	
	@Test
    @DisplayName("Should create dna matrix")
    public void shouldCreateDnaMatrix() throws Exception {
		char[][] seq = {{'A','G'},{'T','G'}}; 
        Dna dna = new Dna(seq);
        assertEquals("AGTG", dna.getDna_id());
    }
	
	@Test
    @DisplayName("Should thrown exception Nucleos")
    public void shouldThrowExceptionNucleos() throws Exception{
		String[] seq = {"ATGCXA","CAGTGC"};
        Assertions.assertThrows(Exception.class, () -> {
        	new Dna(seq);
        });
    }
	
	@Test
	@DisplayName("Should thrown exception empty rows")
    public void shouldThrowExceptionEmpyyRows() throws Exception{
		String[] seq = {""};
        Assertions.assertThrows(Exception.class, () -> {
        	new Dna(seq);
        });
    }
	
	@Test
	@DisplayName("Should thrown exception empty")
    public void shouldThrowExceptionEmpy() throws Exception{
		String[] seq = {};
        Assertions.assertThrows(Exception.class, () -> {
        	new Dna(seq);
        });
    }
	
	@Test
    @DisplayName("Should say human")
    public void shouldSayHuman() throws Exception{
		String[] seq = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CACCTA","TCACTG"};
		Dna dna = new Dna(seq);
		assertFalse(dna.isMutant());
    }
	
	@Test
    @DisplayName("Should say mutant")
    public void shouldSayMutant() throws Exception{
		String[] seq = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Dna dna = new Dna(seq);
		assertTrue(dna.isMutant());
    }
}