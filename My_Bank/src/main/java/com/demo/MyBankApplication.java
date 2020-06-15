package com.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.demo.dao.ClientRepository;
import com.demo.dao.CompteRepository;
import com.demo.dao.OperationRepository;
import com.demo.entities.Client;
import com.demo.entities.Compte;
import com.demo.entities.CompteCourant;
import com.demo.entities.CompteEpargne;
import com.demo.entities.Retrait;
import com.demo.entities.Versement;
import com.demo.metier.IBanqueMetier;

@SpringBootApplication
public class MyBankApplication  implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;
	
	public static void main(String[] args) {
			SpringApplication.run(MyBankApplication.class, args);
		//ClientRepository clientRepository = context.getBean(ClientRepository.class);
		//clientRepository.save(new Client("Hassan", "hassan@gmail.com"));
		
	}

	@Override
	public void run(String... args) throws Exception {
		Client c1 = clientRepository.save(new Client("Hassan", "hassan@gmail.fr"));
		Client c2 = clientRepository.save(new Client("Ahmed", "ahmed@gmail.fr"));
		
		Compte cp1 = compteRepository.save
				(new CompteCourant("c1", new Date(), 90000, c1, 6000));
		
		Compte cp2 = compteRepository.save
				(new CompteEpargne("c2", new Date(), 6000, c2, 5.5));
		
		operationRepository.save(new Versement(new Date(), 9000, cp1));
		operationRepository.save(new Versement(new Date(), 6000, cp1));
		operationRepository.save(new Versement(new Date(), 2000, cp1));
		operationRepository.save(new Retrait(new Date(), 9000, cp1));
		
		operationRepository.save(new Versement(new Date(), 1000, cp2));
		operationRepository.save(new Versement(new Date(), 11000, cp2));
		operationRepository.save(new Versement(new Date(), 5000, cp2));
		operationRepository.save(new Retrait(new Date(), 2000, cp2));
		
		banqueMetier.verser("c1", 11111);
	}

}
