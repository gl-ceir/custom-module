package com.gl.custommodule.repository.app;

import com.gl.custommodule.model.app.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList,Long>, JpaSpecificationExecutor<BlackList> {
    Optional<BlackList> findTopByImeiLike(String deviceId);
}
