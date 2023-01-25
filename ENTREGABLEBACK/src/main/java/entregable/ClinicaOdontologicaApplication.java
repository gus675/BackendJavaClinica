package entregable;

import org.apache.log4j.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args){
;
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);

	}

}
