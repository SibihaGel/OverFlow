package com.javamentor.qa.platform.dao.abstracts.repository;


import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByEmail(String email);
  List<User> findByEmailStartingWith(String string);


}
