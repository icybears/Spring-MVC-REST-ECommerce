package ma.pfa.webapp.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ma.pfa.webapp.dao.IOrigineDao;
import ma.pfa.webapp.model.Origine;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class OrigineDaoImplTest {
	
	@Autowired
	private IOrigineDao origineDao;
	
	static private Origine origineEntity;
	
	
	@Test
	public void testSave() {
		
		Origine org = new Origine();
		org.setNom("Test Origine");
		
		int id = origineDao.save(org);
		
		assertEquals("Test Origine",origineDao.findById(id).getNom());
		
		origineEntity = origineDao.findById(id);
		
	}
	
	@Test
	public void testUpdate() {
		
		Origine org = new Origine();
		org.setNom("Test Origine");
		
		int id = origineDao.save(org);
		
		origineEntity = origineDao.findById(id);
		
		origineEntity.setNom("Updated Test Origine");
		
		origineDao.update(origineEntity);
		
		assertEquals("Updated Test Origine",origineDao.findById(id).getNom());
	}
	
	@Test
	public void testDelete() {
		
		Origine org = new Origine();
		org.setNom("Test Origine");
		
		int id = origineDao.save(org);
		
		origineEntity = origineDao.findById(id);
		
		origineDao.delete(origineEntity);
		
		assertNull(origineDao.findById(id));
	}
	

	@Test
	public void testFindAll() {
		Origine org = new Origine();
		org.setNom("Test Origine 1");
		origineDao.save(org);
		org = new Origine();
		org.setNom("Test Origine 2");
		origineDao.save(org);
		org = new Origine();
		org.setNom("Test Origine 3");
		origineDao.save(org);
		
		assertEquals(3,origineDao.findAll().size());
		
	}

}
