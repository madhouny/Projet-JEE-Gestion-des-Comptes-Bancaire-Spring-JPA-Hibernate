package com.demo.metier;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.demo.entities.Compte;
import com.demo.entities.Operation;

public interface IBanqueMetier {

	public Compte consulterCompte(String codeCompte);
	public void verser(String codeCompte, double montant);
	public void retirer(String codeCompte, double montant);
	public void virement(String codeCompte1, String codeCompte2, double montant);
	public Page<Operation> listOperation(String codeCompte, int page, int size);
}
