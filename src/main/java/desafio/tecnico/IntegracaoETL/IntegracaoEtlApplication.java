package desafio.tecnico.IntegracaoETL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class IntegracaoEtlApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegracaoEtlApplication.class, args);
	}

}
