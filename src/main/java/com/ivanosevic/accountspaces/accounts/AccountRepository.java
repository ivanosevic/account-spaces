package com.ivanosevic.accountspaces.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.accountRoles as ar LEFT JOIN FETCH ar.role WHERE a.email = :emailAddress")
    Optional<Account> findAccountDetailsByEmail(String emailAddress);

    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.accountRoles as ar LEFT JOIN FETCH ar.role WHERE a.id = :id")
    Optional<Account> findAccountDetailsById(Integer id);

    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByIdNoRelations(Integer id);
}