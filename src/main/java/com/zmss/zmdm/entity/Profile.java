/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmss.zmdm.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ZMF
 */
@Entity
@Table(name = "profile", catalog = "ZMDM", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p")
    , @NamedQuery(name = "Profile.findById", query = "SELECT p FROM Profile p WHERE p.id = :id")
    , @NamedQuery(name = "Profile.findByName", query = "SELECT p FROM Profile p WHERE p.name = :name")
    , @NamedQuery(name = "Profile.findByDescription", query = "SELECT p FROM Profile p WHERE p.description = :description")
    , @NamedQuery(name = "Profile.findByCreatedOn", query = "SELECT p FROM Profile p WHERE p.createdOn = :createdOn")
    , @NamedQuery(name = "Profile.findByUpdatedOn", query = "SELECT p FROM Profile p WHERE p.updatedOn = :updatedOn")
    , @NamedQuery(name = "Profile.findByUpdatedBy", query = "SELECT p FROM Profile p WHERE p.updatedBy = :updatedBy")
    , @NamedQuery(name = "Profile.findBySyncInterval", query = "SELECT p FROM Profile p WHERE p.syncInterval = :syncInterval")
    , @NamedQuery(name = "Profile.findByStatus", query = "SELECT p FROM Profile p WHERE p.status = :status")})
public class Profile implements Serializable {

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
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    @Size(max = 45)
    @Column(name = "updated_by", length = 45)
    private String updatedBy;
    @Column(name = "sync_interval")
    private Integer syncInterval;
    @Size(max = 45)
    @Column(name = "status", length = 45)
    private String status;

    public Profile() {
    }

    public Profile(Integer id) {
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getSyncInterval() {
        return syncInterval;
    }

    public void setSyncInterval(Integer syncInterval) {
        this.syncInterval = syncInterval;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zmss.zmdm.entity.Profile[ id=" + id + " ]";
    }
    
}
