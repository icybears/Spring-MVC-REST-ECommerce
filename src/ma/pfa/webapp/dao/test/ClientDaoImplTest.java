package ma.pfa.webapp.dao.test;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ma.pfa.webapp.dao.IClientDao;
import ma.pfa.webapp.model.Client;
import org.junit.jupiter.api.Test;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })

class ClientDaoImplTest {


	@Autowired
	private IClientDao clientDao;


	@Test
	@Transactional
	@Rollback(true)
	public void testMethod() {
		Client cl = new Client();
		Client cl2 = new Client();
		cl.setNom("Client 1");
		cl2.setNom("Client 2");
		clientDao.save(cl);
		clientDao.save(cl2);
		assertEquals(2,clientDao.findAll().size());
	}
}

