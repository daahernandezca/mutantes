package com.mutantes.app.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitControllerTest {

	private InitController initController = new InitController(); 
	
	@Test
	@DisplayName("Should create dna")
	void checkInitialTest() {
		String hello = initController.init();		
		assertEquals("This a api for mercado libre Test", hello);
	}

}
