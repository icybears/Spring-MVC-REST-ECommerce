package ma.pfa.webapp.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.ICooperativeDao;
import ma.pfa.webapp.model.Cooperative;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class CooperativeDaoImplTest {

	@Autowired
	private ICooperativeDao coopDao;
	
	static Cooperative coop;
	
	@Test
	void testSaveAndFindAll() {
		coop = new Cooperative(); 
		coop.setDescription("Cooperative 1");
		coopDao.save(coop);
		coop = new Cooperative(); 
		coop.setDescription("Cooperative 2");
		coopDao.save(coop);
		
		assertEquals(2,coopDao.findAll().size());
	}
	
	@Test
	void testFindByIdAndUpdate() {
		coop = new Cooperative(); 
		coop.setDescription("Cooperative 1");
		int id = coopDao.save(coop);
		
		assertNotNull(coopDao.findById(id));
		
		Cooperative coopEntity = coopDao.findById(id);
		coopEntity.setDescription("Updated Cooperative");
		coopEntity = coopDao.update(coopEntity);
		
		assertEquals("Updated Cooperative",coopEntity.getDescription());
		
	}

}
