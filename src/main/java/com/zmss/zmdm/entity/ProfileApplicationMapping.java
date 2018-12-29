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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ZMF
 */
@Entity
@Table(name = "profile_application_mapping", catalog = "ZMDM", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfileApplicationMapping.findAll", query = "SELECT p FROM ProfileApplicationMapping p")
    , @NamedQuery(name = "ProfileApplicationMapping.findById", query = "SELECT p FROM ProfileApplicationMapping p WHERE p.id = :id")
    , @NamedQuery(name = "ProfileApplicationMapping.findByProfileId", query = "SELECT p FROM ProfileApplicationMapping p WHERE p.profileId = :profileId")
    , @NamedQuery(name = "ProfileApplicationMapping.findByApplicationId", query = "SELECT p FROM ProfileApplicationMapping p WHERE p.applicationId = :applicationId")})
public class ProfileApplicationMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @Column(name = "application_id")
    private Integer applicationId;

    public ProfileApplicationMapping() {
    }

    public ProfileApplicationMapping(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
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
        if (!(object instanceof ProfileApplicationMapping)) {
            return false;
        }
        ProfileApplicationMapping other = (ProfileApplicationMapping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zmss.zmdm.entity.ProfileApplicationMapping[ id=" + id + " ]";
    }
    
}
