package ma.pfa.webapp.dao.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ma.pfa.webapp.dao.IClientDao;
import ma.pfa.webapp.model.Client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class ClientDaoImplTest {

	@Autowired
	private IClientDao clientDao;

	static Client cl;
	 static Client cl2;
	

	@BeforeAll
	static void setUp() throws Exception {
		cl = new Client();
		cl2 = new Client();
		cl.setNom("Client 1");
		cl2.setNom("Client 2");
	}

	@Test
	public void testSaveAndFindAll() {
		
		clientDao.save(cl);
		clientDao.save(cl2);
		assertEquals(2, clientDao.findAll().size());
	}
	
	@Test
	public void testFindById() {
		int id = clientDao.save(cl);
		
		assertEquals("Client 1",clientDao.findById(id).getNom());
	}
	
	@Test 
	public void testUpdate() {
		int id = clientDao.save(cl);
		
		Client clEntity = clientDao.findById(id);
		clEntity.setNom("Updated client");
		
		assertEquals("Updated client",clientDao.update(clEntity).getNom());
	}
}
