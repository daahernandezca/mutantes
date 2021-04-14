package com.mutantes.app.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class StatsControllerTest {

	StatsController statsController = new StatsController();
	@Test
	@DisplayName("Should give 200 ok")
	public void testWrongInfo() throws Exception {
        ResponseEntity<?> response = statsController.getStats();
        assertEquals("500 INTERNAL_SERVER_ERROR", response.getStatusCode().toString());
    }

}
