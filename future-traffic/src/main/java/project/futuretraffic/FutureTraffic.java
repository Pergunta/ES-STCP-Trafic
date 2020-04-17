package project.futuretraffic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FutureTraffic {

	public static void main(String[] args) {
		SpringApplication.run(FutureTraffic.class, args);
	}

}
