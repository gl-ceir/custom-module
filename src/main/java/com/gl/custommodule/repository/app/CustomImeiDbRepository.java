package com.gl.custommodule.repository.app;

import com.gl.custommodule.model.app.CustomImeiDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface CustomImeiDbRepository extends JpaRepository<CustomImeiDb,Long>, JpaSpecificationExecutor<CustomImeiDb> {
    Optional<CustomImeiDb> findTopByImei(String deviceId);
}
