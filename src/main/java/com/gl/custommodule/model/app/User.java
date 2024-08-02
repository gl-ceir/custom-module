package com.gl.custommodule.model.app;

import javax.persistence.*;
import java.sql.Timestamp;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private int id;

    @Column(name = "created_on", nullable = false, updatable = false)
    private Timestamp createdOn;

    @Column(name = "current_status")
    private Integer currentStatus;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_on", nullable = false)
    private Timestamp modifiedOn;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "password")
    private String password;

    @Column(name = "password_date", nullable = false)
    private Timestamp passwordDate;

    @Column(name = "previous_status")
    private Integer previousStatus;

    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "remark")
    private String remark;

    @Column(name = "user_language")
    private String userLanguage;

    @Column(name = "username")
    private String username;

    @Column(name = "user_type_id")
    private Integer userTypeId;

    @Column(name = "approved_by")
    private String approvedBy;

}

