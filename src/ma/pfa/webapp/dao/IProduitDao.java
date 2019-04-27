package ma.pfa.webapp.dao;

import java.util.Set;

import ma.pfa.webapp.model.Produit;


public interface IProduitDao extends ICrudGenericDao<Produit>{

	Set<Produit> getProduitsByMatierePremiere(int id);

}
