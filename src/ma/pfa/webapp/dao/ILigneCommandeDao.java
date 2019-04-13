package ma.pfa.webapp.dao;

import java.io.Serializable;

import ma.pfa.webapp.model.IdLigneCommande;
import ma.pfa.webapp.model.LigneCommande;

public interface ILigneCommandeDao extends ICrudGenericDao<LigneCommande>{

	IdLigneCommande saveLigne(LigneCommande lc);
	
	LigneCommande findByIdLigne(IdLigneCommande id);
	
}
