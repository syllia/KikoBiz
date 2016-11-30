package com.investMessage.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User findByUserNameAndPassWordIgnoreCase(String userName, String passWord);
}
