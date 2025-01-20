package com.exa.demotwo.dataaccessopject;

import com.exa.demotwo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends JpaRepository<Users, Long> {
}
