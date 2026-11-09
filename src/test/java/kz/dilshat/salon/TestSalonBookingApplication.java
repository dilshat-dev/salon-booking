package kz.dilshat.salon;

import org.springframework.boot.SpringApplication;

public class TestSalonBookingApplication {

	public static void main(String[] args) {
		SpringApplication.from(SalonBookingApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
