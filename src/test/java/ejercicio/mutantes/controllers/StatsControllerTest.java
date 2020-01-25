package ejercicio.mutantes.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ejercicio.mutantes.services.DNAService;


public class StatsControllerTest {
	
	@InjectMocks
	private StatsController controller;
	
	@Mock
	private DNAService service;
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Test
	public void verifyService( ) {
		assertNotNull(controller.getService());
	}
	
	@Test
	public void testGetStatsOk() {
		when(service.getStats()).thenReturn(new HashMap<String, Float>());
		ResponseEntity<Map<String, Float>> response = controller.getStats();
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		verify(service).getStats();
	}
	
	@Test
	public void testGetStatsFail() {
		when(service.getStats()).thenReturn(null);
		ResponseEntity<Map<String, Float>> response = controller.getStats();
		assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
		verify(service).getStats();
	}

}
