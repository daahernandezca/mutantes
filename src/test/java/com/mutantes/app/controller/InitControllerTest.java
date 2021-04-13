package com.mutantes.app.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;


public class InitControllerTest{

	@Autowired
	InitController initController;
	
	
	@Test
    @DisplayName("Test Spring @Autowired Integration")
    public void testGet() {
        assertEquals("Hello JUnit 5", initController.init());
    }
}
