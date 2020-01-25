package ejercicio.mutantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Mutantes {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Mutantes.class, args);
	}
}
