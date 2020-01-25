package ejercicio.mutantes.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ejercicio.mutantes.model.DNA;
import ejercicio.mutantes.repository.DNARepository;
import ejercicio.mutantes.services.impl.DNAServiceImpl;

public class DNAServiceImplTest {
	
	@InjectMocks
	private DNAServiceImpl service;
	
	@Mock
	private DNARepository repository;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	private String[] createMutantArray() {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		return dnaArray;
	}
	
	private String[] createNotMutantArray() {
		String[] dnaArray = {"ATGCGA","CAGTAC","TTATGT","AGAATG","CCTATA","TCACTG"};
		return dnaArray;
	}
	
	@Test
	public void testIsMutantOk() {
		String[] dna = createMutantArray();
		assertTrue(service.isMutant(dna));
		verify(repository).saveAndFlush(any());
	}
	
	@Test
	public void testIsMutantOkWithDiagonalUp() {
		String[] dna = {"AAAAGA","CAGCGC","TTATGT","AGTAGG","CTCCTA","TCACTG"};
		assertTrue(service.isMutant(dna));
		verify(repository).saveAndFlush(any());
	}
	
	@Test
	public void testIsMutantFail() {
		String[] dna = createNotMutantArray();
		assertFalse(service.isMutant(dna));
		verify(repository).saveAndFlush(any());
	}
	
	@Test
	public void testInvalidChain() {
		String[] dna =  {"ATG"};
		assertFalse(service.isMutant(dna));
		verify(repository, never()).saveAndFlush(any());
	}
	
	@Test
	public void testGetStats() {
		DNA dna1 = new DNA();
		dna1.setIsMutant(Boolean.TRUE);
		DNA dna2 = new DNA();
		dna2.setIsMutant(Boolean.TRUE);
		DNA dna3 = new DNA();
		dna3.setIsMutant(Boolean.FALSE);
		List<DNA> list = Arrays.asList(dna1, dna2, dna3);
		when(repository.findAll()).thenReturn(list);
		Map<String, Float> stats = service.getStats();
		assertTrue(stats.get(DNAServiceImpl.COUNT_MUTANT).equals(2f));
		assertTrue(stats.get(DNAServiceImpl.COUNT_HUMAN).equals(1f));
	}
}
