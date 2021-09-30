package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("tam@gmail.com");
		user.setPassword("tam2020");
		user.setFirstName("tram");
		user.setLastName("tita");
		User savedUser = userRepository.save(user);
		User existUser = testEntityManager.find(User.class, savedUser.getId());
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

	}
}
