package ejercicio.mutantes.services.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ejercicio.mutantes.model.DNA;
import ejercicio.mutantes.repository.DNARepository;
import ejercicio.mutantes.services.DNAService;

@Service
public class DNAServiceImpl implements DNAService {
	
	public static final String COUNT_MUTANT = "count_mutant_dna";
	
	public static final String COUNT_HUMAN = "count_human_dna";
	
	public static final String RATIO = "ratio";
	
	@Autowired
	private DNARepository repository;

	@Override
	@Transactional
	public Boolean isMutant(String[] dna) {
		for(String chain : dna){
			if(chain.length() < 4) {
				return Boolean.FALSE;
			}
		}
		Boolean isMutant = Boolean.FALSE;
		Integer numberOfSecuencies = 0;
		Integer totalOfRows = dna.length;
		for(int row = 0; row < totalOfRows; row++) {
			for(int column = 0; column < dna[row].length(); column++) {
				String base = String.valueOf(dna[row].charAt(column));
				if(column < dna[row].length()-3 && hasInlineChain(dna, row, column, base)){
					numberOfSecuencies++;
				}
				if(row < totalOfRows - 3 && hasInlineChain(dna, column, row, base)) {
					numberOfSecuencies++;
				}
				if(row < totalOfRows-3
						&& column < dna[row].length()-3
						&& base.equals(String.valueOf(dna[row+1].charAt(column+1)))
						&& base.equals(String.valueOf(dna[row+2].charAt(column+2)))
						&& base.equals(String.valueOf(dna[row+3].charAt(column+3)))) {
					numberOfSecuencies++;
				}
				Integer diagonalUpIndex = totalOfRows - row - 1;
				if(diagonalUpIndex > 2) {
					base = String.valueOf(dna[diagonalUpIndex].charAt(column));
					if(column < dna[row].length()-3
							&& base.equals(String.valueOf(dna[diagonalUpIndex - 1].charAt(column+1)))
							&& base.equals(String.valueOf(dna[diagonalUpIndex - 2].charAt(column+2)))
							&& base.equals(String.valueOf(dna[diagonalUpIndex - 3].charAt(column+3)))) {
						numberOfSecuencies++;
					}
				}
				if(numberOfSecuencies > 1) {
					isMutant = Boolean.TRUE;
				}
			}
		}
		
		DNA entity = new DNA();
		entity.setSecuence(String.join("-", dna));
		entity.setIsMutant(isMutant);
		repository.saveAndFlush(entity);
		return isMutant;
	}

	private boolean hasInlineChain(String[] dna, int row, int column, String base) {
		return base.equals(String.valueOf(dna[row].charAt(column+1))) 
				&& base.equals(String.valueOf(dna[row].charAt(column+2)))
				&& base.equals(String.valueOf(dna[row].charAt(column+3)));
	}

	@Override
	public Map<String, Float> getStats() {
		Map<String, Float> stats = new HashMap<String, Float>();
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(2);
		float[] mutants = {0};
		float[] humans = {0};
		List<DNA> entries = repository.findAll();
		Integer totalOfEntries = entries.size();
		if(totalOfEntries > 0) {
			entries.stream().forEach(dna -> {
				mutants[0] = dna.getIsMutant()? mutants[0]+1 : mutants[0];
			});
			humans[0] = totalOfEntries - mutants[0];
			stats.put(COUNT_MUTANT, mutants[0]);
			stats.put(COUNT_HUMAN, humans[0]);
			stats.put(RATIO, humans[0] != 0? mutants[0]/humans[0] : 0);
		}
		return stats;
	}
}
