package ejercicio.mutantes.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ejercicio.mutantes.services.DNAService;

@Controller
@RequestMapping(value = "/mutant")
public class DNAController {

	@Autowired
	private DNAService dnaService;

	@PostMapping
	public ResponseEntity<Object> isMutant(@RequestBody Map<String, String[]> dna) {
		HttpStatus httpStatus = HttpStatus.FORBIDDEN;
		if (dna != null) {
			Object key = dna.keySet().toArray()[0];
			String[] dnaArray = dna.get(key);
			httpStatus = getService().isMutant(dnaArray) ? HttpStatus.OK : httpStatus;
		}
		return ResponseEntity.status(httpStatus).build();
	}

	public DNAService getService() {
		return dnaService;
	}
}
