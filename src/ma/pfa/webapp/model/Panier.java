package ma.pfa.webapp.model;

import java.util.HashMap;
import java.util.Map;

import ma.pfa.webapp.model.LigneCommande;
import ma.pfa.webapp.model.Produit;

public class Panier {
	
	public Map<Integer,LigneCommande> items = new HashMap<Integer,LigneCommande>();
	
	public void addItem(Produit produit, int quantite)
	{
		LigneCommande lc = items.get(produit.getId());
		
		if(lc==null){
			lc = new LigneCommande();
			lc.setProduit(produit);
			lc.setQuantite(quantite);
			items.put(produit.getId(), lc);
		}
		
		else
			lc.setQuantite(lc.getQuantite() + quantite);
	}
	
	public void addItem(LigneCommande ligne)
	{
		Produit produit = ligne.getProduit();
		LigneCommande lc = items.get(ligne.getProduit().getId());
		
		if(lc==null){
			lc = ligne;
			
			items.put(produit.getId(), lc);
		}
		
		else
			lc.setQuantite(lc.getQuantite() + ligne.getQuantite());
	}
	
	
	public void removeItem(int idProduit)
	{
		items.remove(idProduit);
	}
	
	public void editQuantiteByProductId(int idProduit, int quantite){
		
		LigneCommande lc = items.get(idProduit);
		
		if(lc != null)
			lc.setQuantite(quantite);
	}

	public Produit findProduitById(int id) {
		return this.findLigneCommandeByProductId(id).getProduit();
	}
	
	public LigneCommande findLigneCommandeByProductId(int id) {
		return items.get(id);
	}
	
	public Map<Integer, LigneCommande> getItems() {
		return items;
	}

	public void setItems(Map<Integer, LigneCommande> items) {
		this.items = items;
	}
	
}
