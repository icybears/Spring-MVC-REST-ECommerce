package ma.pfa.webapp.dao.test;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.IEtatDao;
import ma.pfa.webapp.model.Etat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class EtatDaoImplTest {

	@Autowired
	private IEtatDao etDao;
	
	@Test
	void test() {
		Etat etat = new Etat();
		etat.setNom("etat1");
		
		int idEtat = etDao.save(etat);
		Etat etatEntity = etDao.findById(idEtat);
		assertNotNull(etatEntity);
		
	}

}
