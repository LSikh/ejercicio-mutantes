package ejercicio.mutantes.services;

import java.util.Map;

public interface DNAService {
	
	Boolean isMutant(String[] dna);

	Map<String, Float> getStats();

}
