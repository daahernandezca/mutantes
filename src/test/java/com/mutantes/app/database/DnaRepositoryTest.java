package com.mutantes.app.database;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.mutantes.app.entities.Dna;

class DnaRepositoryTest {


	private DnaRepository dnaRepository = new DnaRepository();
    
	
	@Test
	@DisplayName("Should create dna in db")
	void testCreateElement() throws Exception{
		char[][] seq = {{'A','G'},{'T','G'}}; 
        Dna dna = new Dna(seq);
		dnaRepository.addDna(dna);
	}

	@Test
	@DisplayName("Should create not dna in db")
	void testNotCreateElement() throws Exception{
        Dna dna = new Dna();
		
		Assertions.assertThrows(AmazonDynamoDBException.class, () -> {
			dnaRepository.addDna(dna);
        });
	}
	
	@Test
	@DisplayName("Should give stats")
	void testGetRatio() throws Exception{
        JSONObject obj = dnaRepository.getRatio();
        if (!obj.containsKey("count_mutant_dna"))  fail("Not key found");
        if (!obj.containsKey("count_human_dna"))  fail("Not key found");
        if (!obj.containsKey("ratio"))  fail("Not key found");
	}

	
}
