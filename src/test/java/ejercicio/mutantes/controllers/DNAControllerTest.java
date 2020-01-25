package ejercicio.mutantes.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;

import ejercicio.mutantes.services.DNAService;

public class DNAControllerTest {
	
	@InjectMocks
	private DNAController controller;
	
	@Mock
	private DNAService service;
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Test
	public void verifyService( ) {
		assertNotNull(controller.getService());
	}
	
	private Map<String, String[]> createDNA() {
		Map<String, String[]> dna = new HashMap<String, String[]>();
		String[] dnaArray = {};
		dna.put("dna", dnaArray);
		return dna;
	}
	
	@Test
	public void isMutantOk() {
		Map<String, String[]> dna = createDNA();
		when(service.isMutant(any())).thenReturn(Boolean.TRUE);
		ResponseEntity<Object> respose = controller.isMutant(dna);
		assertNotNull(respose);
		assertEquals(respose.getStatusCode(), HttpStatus.OK);
		verify(service).isMutant(any());
	}
	
	@Test
	public void isMutantError() {
		Map<String, String[]> dna = createDNA();
		when(service.isMutant(any())).thenReturn(Boolean.FALSE);
		ResponseEntity<Object> respose = controller.isMutant(dna);
		assertNotNull(respose);
		assertEquals(respose.getStatusCode(), HttpStatus.FORBIDDEN);
		verify(service).isMutant(any());
	}
	
	@Test
	public void isMutantNullDna() {
		when(service.isMutant(any())).thenReturn(Boolean.FALSE);
		ResponseEntity<Object> respose = controller.isMutant(null);
		assertNotNull(respose);
		assertEquals(respose.getStatusCode(), HttpStatus.FORBIDDEN);
		verify(service, never()).isMutant(any());
	}
}
