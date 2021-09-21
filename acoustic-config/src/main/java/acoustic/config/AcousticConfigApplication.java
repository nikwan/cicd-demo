package acoustic.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class AcousticConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcousticConfigApplication.class, args);
	}

}
