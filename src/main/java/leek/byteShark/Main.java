package leek.byteShark;

import java.io.IOException;
import java.util.List;

import javax.swing.event.TableModelListener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import leek.byteShark.controller.CommandManager;
import leek.byteShark.controller.TableListener;
import leek.byteShark.model.CRBDataModel.CRBBase;
import leek.byteShark.model.CRBDataModel.CRBData;
import leek.byteShark.view.BRCPanel.BRCPanel;
import leek.byteShark.view.BRCPanel.HomeWindow;
import leek.byteShark.view.interfaces.IHomeWindow;

@SpringBootApplication
public class Main {

	private static String TESTFILE= "./InvoiceTestData.txt"; 
	
	public static void main(String[] args) throws IOException {
		
		//TODO: TEST FILE INPUT
		List<CRBBase> brc = CommandManager.importNewBRC(TESTFILE);
		List<CRBData> generalLines = CommandManager.extractDataLines(brc);
				
		//Start Event Handlers
		CommandManager manager = new CommandManager(generalLines);
		TableModelListener tableListener = new TableListener(manager);
				
		//assemble the UI
		BRCPanel brcTable = new BRCPanel(tableListener);
		IHomeWindow homeWindow = new HomeWindow(brcTable);
				
		//Controller -> UI connections
		manager.addUI(brcTable);
		manager.connectEvents(homeWindow);
				
		//TODO: TEST ADD DATA TO MODEL
		CommandManager.pushDataToTable(brc, brcTable);
		
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			/*
			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
			*/
		};
	}
}
