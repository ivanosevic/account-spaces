package com.ivanosevic.accountspaces.logs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Integer> {

    Page<ActivityLog> findByAccountId(Integer accountId, Pageable pageable);
}