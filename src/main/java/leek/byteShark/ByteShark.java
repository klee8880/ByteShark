package leek.byteShark;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import leek.byteShark.model.database.RepairLine;
import leek.byteShark.model.database.RepairLineRepository;
import leek.byteShark.model.database.Shopping;
import leek.byteShark.model.database.ShoppingRepository;

@SpringBootApplication
public class ByteShark {

    public static void main(String[] args) {
        SpringApplication.run(ByteShark.class, args);
    }
    
    @Bean
    public CommandLineRunner databaseTest (ShoppingRepository shoppings, RepairLineRepository datalines) {
		return (args) -> {
			
			//Shopping table test
			System.out.println("---TEST SHOPPPING DATA---");
			for (Shopping event : shoppings.findAll()) {
				System.out.println(event.toString());
		    }
			
			//Date Table test
			System.out.println("---DATA TABLE TEST---");
			for (RepairLine line: datalines.findAll()) {
				System.out.println(line.toString());
			}
			
		};
    	
    }

}
