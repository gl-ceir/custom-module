package com.gl.custommodule.repository.app;

import com.gl.custommodule.model.app.MobileDeviceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobileDeviceRepoRepository extends JpaRepository<MobileDeviceRepository,Long>, JpaSpecificationExecutor<MobileDeviceRepository> {
    Optional<MobileDeviceRepository> findByDeviceId(String deviceId);
}
