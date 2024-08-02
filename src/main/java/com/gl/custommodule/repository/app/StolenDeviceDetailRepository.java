package com.gl.custommodule.repository.app;

import com.gl.custommodule.model.app.StolenDeviceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StolenDeviceDetailRepository extends JpaRepository<StolenDeviceDetail,Long>, JpaSpecificationExecutor<StolenDeviceDetail> {
    Optional<StolenDeviceDetail> findByImei(String deviceId);
}
