package gr.sae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(namedQueriesLocation = "classpath:jpa-named-queries.properties")
public class Smdb2Application {

	public static void main(String[] args) {
		SpringApplication.run(gr.sae.Smdb2Application.class, args);
	}

}
