package kz.dilshat.salon_booking;

import org.springframework.boot.SpringApplication;

public class TestSalonBookingApplication {

	public static void main(String[] args) {
		SpringApplication.from(SalonBookingApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
