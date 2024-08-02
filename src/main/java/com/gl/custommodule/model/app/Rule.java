package com.gl.custommodule.model.app;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "rule")
public class Rule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "description", length = 2500)
    private String description;

    @Column(name = "modified_on")
    private LocalDate modifiedOn;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "output")
    private String output;

    @Column(name = "state")
    private String state;

    @Column(name = "modified_by")
    private String modifiedBy;

    public Rule() {
    }

    public Rule(int id, LocalDate createdOn, String description, LocalDate modifiedOn, String name, String output, String state, String modifiedBy) {
        this.id = id;
        this.createdOn = createdOn;
        this.description = description;
        this.modifiedOn = modifiedOn;
        this.name = name;
        this.output = output;
        this.state = state;
        this.modifiedBy = modifiedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDate modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rule{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", description='").append(description).append('\'');
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", name='").append(name).append('\'');
        sb.append(", output='").append(output).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", modifiedBy='").append(modifiedBy).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

