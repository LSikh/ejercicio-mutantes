package ejercicio.mutantes.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ejercicio.mutantes.services.DNAService;

@Controller
@RequestMapping(value = "/stats")
public class StatsController {
	
	@Autowired
	private DNAService dnaService;
	
	@GetMapping
	public ResponseEntity<Map<String, Float>> getStats() {
		Map<String, Float> stats = getService().getStats();
		HttpStatus status = stats != null? HttpStatus.OK : HttpStatus.BAD_REQUEST;
		return new ResponseEntity<Map<String, Float>>(stats, status);
	}

	public DNAService getService() {
		return dnaService;
	}
}
