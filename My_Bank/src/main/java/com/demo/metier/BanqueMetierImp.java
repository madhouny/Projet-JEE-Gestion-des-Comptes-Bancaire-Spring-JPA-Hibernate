package com.demo.metier;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.CompteRepository;
import com.demo.dao.OperationRepository;
import com.demo.entities.Compte;
import com.demo.entities.CompteCourant;
import com.demo.entities.Operation;
import com.demo.entities.Retrait;
import com.demo.entities.Versement;

@Service
@Transactional
public class BanqueMetierImp implements IBanqueMetier {
	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCompte) {
		Compte cp = compteRepository.findById(codeCompte).orElse(null);

		if (cp == null) {
			throw new RuntimeException("Compte introuvale");
		}

		return cp;
	}

	@Override
	public void verser(String codeCompte, double montant) {

		Compte cp = consulterCompte(codeCompte);
		Versement v = new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde() + montant);
		compteRepository.save(cp);
	}

	@Override
	public void retirer(String codeCompte, double montant) {
		Compte cp = consulterCompte(codeCompte);
		double faciliesPaiement = 0;
		if (cp instanceof CompteCourant)
			faciliesPaiement = ((CompteCourant) cp).getDecouvert();
		if (cp.getSolde() + faciliesPaiement < montant)
			throw new RuntimeException("Solde insuffisant");

		Retrait r = new Retrait(new Date(), montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde() - montant);
		compteRepository.save(cp);

	}

	@Override
	public void virement(String codeCompte1, String codeCompte2, double montant) {
		
		retirer(codeCompte1, montant);
		verser(codeCompte2, montant);

	}

	@Override
	public Page<Operation> listOperation(String codeCompte, int page, int size) {
		
		return operationRepository.listOperation(codeCompte,  PageRequest.of(page, size));
	}

}
