package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.demo.dao.ClientRepository;
import com.demo.entities.Client;

@SpringBootApplication
public class MyBankApplication  implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	public static void main(String[] args) {
			SpringApplication.run(MyBankApplication.class, args);
		//ClientRepository clientRepository = context.getBean(ClientRepository.class);
		//clientRepository.save(new Client("Hassan", "hassan@gmail.com"));
		
	}

	@Override
	public void run(String... args) throws Exception {
		Client c1 = clientRepository.save(new Client("Hassan", "hassan@gmail.fr"));
		Client c2 = clientRepository.save(new Client("Ahmed", "ahmed@gmail.fr"));
	}

}
