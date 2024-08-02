package com.gl.custommodule.repository.app;

import com.gl.custommodule.model.app.ExceptionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExceptionListRepository extends JpaRepository<ExceptionList,Long>, JpaSpecificationExecutor<ExceptionList> {
    Optional<ExceptionList> findByActualImei(String deviceId);
}
