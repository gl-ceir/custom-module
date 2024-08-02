package com.gl.custommodule.repository.app;

import com.gl.custommodule.model.app.GreyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface GreyListRepository extends JpaRepository<GreyList,Long>, JpaSpecificationExecutor<GreyList> {
    Optional<GreyList> findByActualImei(String deviceId);
}
