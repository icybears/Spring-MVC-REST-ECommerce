package ma.pfa.webapp.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.IClientDao;
import ma.pfa.webapp.dao.IUserDao;

import ma.pfa.webapp.model.Client;
import ma.pfa.webapp.model.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class ClientDaoImplTest {

	@Autowired
	private IUserDao userRepo;

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
	@Disabled
	public void testSaveAndFindAll() {

		clientDao.save(cl);
		clientDao.save(cl2);
		assertEquals(2, clientDao.findAll().size());
	}

	@Test
	public void testFindById() {
		int id = clientDao.save(cl);

		assertEquals("Client 1", clientDao.findById(id).getNom());
	}

	@Test
	public void testUpdate() {
		int id = clientDao.save(cl);

		Client clEntity = clientDao.findById(id);
		clEntity.setNom("Updated client");

		assertEquals("Updated client", clientDao.update(clEntity).getNom());
	}

	@Test
	@Rollback(false)
	public void testUser() {
		cl.setEmail("saber@gmail.com");
		cl.setAdresse("Avenue ABC, Agadir");
		cl.setNom("Ibr");
		cl.setPrenom("Saber");
		int id = clientDao.save(cl);
		System.out.println("client id"+id);
		Client clEntity = clientDao.findById(id);
		
		User userEntity = userRepo.findById(1);
		clEntity.setUser(userEntity);
		
		assertEquals("saber",clientDao.update(clEntity).getUser().getName());
		
		

		
	}
}
