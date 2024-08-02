package com.gl.custommodule.repository.app;

import com.gl.custommodule.model.app.RuleEngineMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleEngineMappingRepository extends JpaRepository<RuleEngineMapping, Long>, JpaSpecificationExecutor<RuleEngineMapping> {
    @Query(value = "select a.id,a.created_on,a.modified_on,a.feature,a.grace_action,a.name,a.post_grace_action,a.rule_order,a.user_type,a.failed_rule_action_grace,a.failed_rule_action_post_grace,a.output,a.rule_message,a.modified_by from feature_rule a left join rule r on r.name=a.name where a.feature = :feature and a.user_type = :user_type and r.state = :state order by a.rule_order", nativeQuery = true)
    public List<RuleEngineMapping> getByFeatureAndUserTypeOrderByRuleOrder(String feature, String user_type, String state);
}
