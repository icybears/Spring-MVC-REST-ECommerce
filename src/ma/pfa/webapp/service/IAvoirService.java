package ma.pfa.webapp.service;

import java.util.List;

import ma.pfa.webapp.model.Avoir;
import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.IdLigneCommande;
import ma.pfa.webapp.model.Produit;

public interface IAvoirService {
	int createAvoir(Produit prod, CommandeClient cmd, int qte);
	int createAvoir(IdLigneCommande idLc, int qte);
	Avoir updateAvoir(IdLigneCommande idLc, int qte);
	Avoir getById(int idAvoir);
	List<Avoir> getAll();
	void deleteAvoir(Avoir avoir);
}
