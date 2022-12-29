package com.ivanosevic.accountspaces.verifications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountVerificationRepository extends JpaRepository<AccountVerification, Integer> {

    @Query("SELECT a FROM AccountVerification a JOIN FETCH a.account as account WHERE a.token = :token ORDER BY a.createdAt DESC")
    Optional<AccountVerification> findCurrentByToken(String token);
}
