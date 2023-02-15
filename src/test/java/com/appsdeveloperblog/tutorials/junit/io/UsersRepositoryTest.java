package com.appsdeveloperblog.tutorials.junit.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.UUID;

@DataJpaTest
public class UsersRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UsersRepository usersRepository;

    @BeforeEach
    void setUp() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setEmail("whatever@test.com");
        userEntity.setEncryptedPassword("12345678");
        userEntity.setFirstName("sergeyu");
        userEntity.setLastName("kargo");
        testEntityManager.persistAndFlush(userEntity);
    }

    @Test
    void testFindByEmail_whenGivenCorrectEmail_returnsUserEntity() {

        UserEntity user = new UserEntity();
        user.setFirstName("Serget");
        user.setLastName("Karga");
        user.setEmail("test@test.com");
        user.setUserId(UUID.randomUUID().toString());
        user.setEncryptedPassword("123456789");

        testEntityManager.persistAndFlush(user);


        UserEntity userEntity =usersRepository.findByEmail(user.getEmail());

        Assertions.assertEquals(user.getEmail(),userEntity.getEmail());

    }

    @Test
    void testtest () {

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setEmail("test@gmail.com");
        userEntity.setEncryptedPassword("12345678");
        userEntity.setFirstName("sergey");
        userEntity.setLastName("kargo");
        testEntityManager.persistAndFlush(userEntity);

        String emailDomainName = "@gmail.com";

        List<UserEntity> u = usersRepository.findUsersWithEmailEndingWith(emailDomainName);

        Assertions.assertEquals(u.size(), 1);
        Assertions.assertTrue(u.get(0).getEmail().endsWith(emailDomainName));
    }
}
