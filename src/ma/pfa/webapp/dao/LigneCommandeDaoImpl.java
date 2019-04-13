package ma.pfa.webapp.dao;



import java.util.Set;

import org.springframework.stereotype.Repository;

import ma.pfa.webapp.model.IdLigneCommande;
import ma.pfa.webapp.model.LigneCommande;

@Repository
public class LigneCommandeDaoImpl extends CrudGenericDaoImpl<LigneCommande> implements ILigneCommandeDao{

	
	// retourne l'objet IdLigneCommande 
	public IdLigneCommande saveLigne(LigneCommande lc) {
		
		  return (IdLigneCommande) getCurrentSession().save(lc);	  
	}

	
	public LigneCommande findByIdLigne(IdLigneCommande id) {
		return (LigneCommande) getCurrentSession().get(LigneCommande.class, id);
	}
	
	// retourne 1 par defaut, a ne pas utiliser!
	@Override
	public int save(LigneCommande lc) {
		this.saveLigne(lc);
		return 1;
	}
	


}
