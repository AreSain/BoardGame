package are.example.cardlike;

import org.springframework.boot.SpringApplication;

public class TestCadelikeApplication {

	public static void main(String[] args) {
		SpringApplication.from(CadelikeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
