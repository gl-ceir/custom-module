package com.gl.custommodule.repository.app;

import com.gl.custommodule.model.app.CustomImeiFailedRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CustomImeiFailedRequestsRepository extends JpaRepository<CustomImeiFailedRequests,Long>, JpaSpecificationExecutor<CustomImeiFailedRequests> {
    Optional<CustomImeiFailedRequests> findTopByImei(String deviceId);
}
