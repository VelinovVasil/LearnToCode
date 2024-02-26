package org.example.events.npmg;


import org.example.events.npmg.models.Role.ERole;
import org.example.events.npmg.models.Role.Role;
import org.example.events.npmg.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Server {

	@Value("${openai.key}")
	private String openaiApiKey;

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

	@Bean
	public CommandLineRunner initializeRoles(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.count() == 0) {
				for (ERole role : ERole.values())
					roleRepository.save(new Role(role));
			}
		};
	}

	@Bean
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(((request, body, execution) -> {
			request.getHeaders().add("Authorization",
					"Bearer " + openaiApiKey);
			return execution.execute(request, body);
		}));
		return restTemplate;
	}
}
