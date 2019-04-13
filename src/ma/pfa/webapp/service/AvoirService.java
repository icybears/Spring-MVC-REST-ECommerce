package ma.pfa.webapp.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.IAvoirDao;
import ma.pfa.webapp.dao.ILigneCommandeDao;
import ma.pfa.webapp.model.Avoir;
import ma.pfa.webapp.model.CommandeClient;
import ma.pfa.webapp.model.IdLigneCommande;
import ma.pfa.webapp.model.LigneCommande;
import ma.pfa.webapp.model.Produit;

@Transactional
public class AvoirService implements IAvoirService {

	@Autowired
	private ILigneCommandeDao lcDao;
	
	@Autowired
	private IAvoirDao avoirDao;
	
	
	@Override
	public int createAvoir(Produit prodEntity, CommandeClient cmdEntity, int qte) {
		LigneCommande lcEntity = lcDao.findByIdLigne(new IdLigneCommande(prodEntity, cmdEntity));
		Avoir avoir = new Avoir(qte);
		avoir.addLigneCommande(lcEntity);
		
		return avoirDao.save(avoir);
	}

	@Override
	public int createAvoir(IdLigneCommande idLc, int qte) {
		LigneCommande lcEntity = lcDao.findByIdLigne(idLc);
		Avoir avoir = new Avoir(qte);
		avoir.addLigneCommande(lcEntity);
		
		return avoirDao.save(avoir);

	}

	@Override
	public Avoir updateAvoir(IdLigneCommande idLc, int qte) {
		
		Avoir avoir = lcDao.findByIdLigne(idLc).getAvoir();
		avoir.setQuantite(qte);
		
		
		return avoirDao.update(avoir);
	}
	@Override
	public void deleteAvoir(Avoir avoir) {
		
		avoirDao.delete(avoir);
	}
	
	@Override
	public Avoir getById(int idAvoir) {
		return avoirDao.findById(idAvoir);
	}
	
	@Override
	public List<Avoir> getAll(){
		return avoirDao.findAll();
	}
	

}
