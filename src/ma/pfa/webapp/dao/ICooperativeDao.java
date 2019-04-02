package ma.pfa.webapp.dao;

import java.util.Set;

import ma.pfa.webapp.model.Cooperative;
import ma.pfa.webapp.model.Produit;

public interface ICooperativeDao extends ICrudGenericDao<Cooperative>{

	Set<Produit> getProduits(int id);
}
