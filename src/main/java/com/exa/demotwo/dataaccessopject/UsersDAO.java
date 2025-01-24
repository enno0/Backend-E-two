package com.exa.demotwo.dataaccessopject;

import com.exa.demotwo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The UsersDAO interface is a Data Access Object (DAO) for the Users entity.
 * It extends JpaRepository to provide CRUD operations and query methods for Users.
 */
@Repository
public interface UsersDAO extends JpaRepository<Users, Long> {
}
