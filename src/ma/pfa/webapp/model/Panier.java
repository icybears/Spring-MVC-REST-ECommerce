package ma.pfa.webapp.model;

import java.util.HashMap;
import java.util.Map;

public class Panier {

	private Map<Integer, LigneCommande> items;
	
	public Panier() {
		items = new HashMap<Integer, LigneCommande>();
	}

	
	public void addItems(Produit produit, int qte) {
		LigneCommande lc = items.get(produit.getId());
		
		if(lc == null) {
			lc = new LigneCommande();
			lc.setProduit(produit);
			lc.setQuantite(qte);
			items.put(produit.getId(), lc);
			
		} else {
			lc.setQuantite(lc.getQuantite() + qte);
		}

	}
	
	public void removeItem(int idProduit) {
		if(items.containsKey(idProduit))
			items.remove(idProduit);
	}

	public Map<Integer, LigneCommande> getItems() {
		return items;
	}

	public void setItems(Map<Integer, LigneCommande> items) {
		this.items = items;
	}
  
	

}