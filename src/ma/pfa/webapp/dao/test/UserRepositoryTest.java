package ma.pfa.webapp.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.UserRepository;
import ma.pfa.webapp.model.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepo;
	
	@Test
	void test() {
		assertEquals(2,userRepo.findAll().size());
		Optional<User> userEntity = userRepo.findById(new Long(1));
		assertEquals("saber",userEntity.get().getName());
		assertEquals("saber",userRepo.findByUsername("saber23").get().getName());
	}

}
