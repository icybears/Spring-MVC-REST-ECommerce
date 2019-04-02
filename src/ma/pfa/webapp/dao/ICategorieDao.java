package ma.pfa.webapp.dao;

import java.util.Set;

import ma.pfa.webapp.model.Categorie;
import ma.pfa.webapp.model.Produit;

public interface ICategorieDao extends ICrudGenericDao<Categorie>{

	Set<Produit> getProduits(int id);
}
