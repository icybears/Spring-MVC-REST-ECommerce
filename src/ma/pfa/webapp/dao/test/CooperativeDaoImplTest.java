package ma.pfa.webapp.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.ICooperativeDao;
import ma.pfa.webapp.model.Cooperative;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
class CooperativeDaoImplTest {

	@Autowired
	private ICooperativeDao coopDao;
	
	@Test
	@Transactional
	@Rollback(true)
	void testSaveandFindAll() {
		Cooperative coop = new Cooperative(); 
		coop.setDescription("Cooperative 1");
		Cooperative coop2 = new Cooperative(); 
		coop2.setDescription("Cooperative 2");
		
		coopDao.save(coop);
		coopDao.save(coop2);
		
		assertEquals(2,coopDao.findAll().size());
	}

}
