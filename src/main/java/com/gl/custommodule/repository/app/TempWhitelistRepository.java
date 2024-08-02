package com.gl.custommodule.repository.app;

import com.gl.custommodule.model.app.TempWhitelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TempWhitelistRepository extends JpaRepository<TempWhitelist,Long>, JpaSpecificationExecutor<TempWhitelist> {
    Optional<TempWhitelist> findByActualImei(String deviceId);
}
