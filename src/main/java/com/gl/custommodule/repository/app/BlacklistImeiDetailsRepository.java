package com.gl.custommodule.repository.app;

import com.gl.custommodule.model.app.BlacklistImeiDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlacklistImeiDetailsRepository extends JpaRepository<BlacklistImeiDetails,Long>, JpaSpecificationExecutor<BlacklistImeiDetails> {
    Optional<BlacklistImeiDetails> findTopByDeviceIdLike(String deviceId);
}
