/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmss.zmdm.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ZMF
 */
@Entity
@Table(name = "project_fleet", catalog = "ZMDM", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectFleet.findAll", query = "SELECT p FROM ProjectFleet p")
    , @NamedQuery(name = "ProjectFleet.findById", query = "SELECT p FROM ProjectFleet p WHERE p.id = :id")
    , @NamedQuery(name = "ProjectFleet.findByName", query = "SELECT p FROM ProjectFleet p WHERE p.name = :name")
    , @NamedQuery(name = "ProjectFleet.findByDescription", query = "SELECT p FROM ProjectFleet p WHERE p.description = :description")
    , @NamedQuery(name = "ProjectFleet.findByProfileId", query = "SELECT p FROM ProjectFleet p WHERE p.profileId = :profileId")})
public class ProjectFleet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;
    @Size(max = 45)
    @Column(name = "description", length = 45)
    private String description;
    @Column(name = "profile_id")
    private Integer profileId;

    public ProjectFleet() {
    }

    public ProjectFleet(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectFleet)) {
            return false;
        }
        ProjectFleet other = (ProjectFleet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zmss.zmdm.entity.ProjectFleet[ id=" + id + " ]";
    }
    
}
