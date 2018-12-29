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
@Table(name = "change_log", catalog = "ZMDM", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChangeLog.findAll", query = "SELECT c FROM ChangeLog c")
    , @NamedQuery(name = "ChangeLog.findById", query = "SELECT c FROM ChangeLog c WHERE c.id = :id")
    , @NamedQuery(name = "ChangeLog.findByAdminId", query = "SELECT c FROM ChangeLog c WHERE c.adminId = :adminId")
    , @NamedQuery(name = "ChangeLog.findByTableChanged", query = "SELECT c FROM ChangeLog c WHERE c.tableChanged = :tableChanged")
    , @NamedQuery(name = "ChangeLog.findByNarration", query = "SELECT c FROM ChangeLog c WHERE c.narration = :narration")
    , @NamedQuery(name = "ChangeLog.findByUpdatedOn", query = "SELECT c FROM ChangeLog c WHERE c.updatedOn = :updatedOn")
    , @NamedQuery(name = "ChangeLog.findByTableKeyChanged", query = "SELECT c FROM ChangeLog c WHERE c.tableKeyChanged = :tableKeyChanged")})
public class ChangeLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "admin_id")
    private Integer adminId;
    @Size(max = 45)
    @Column(name = "table_changed", length = 45)
    private String tableChanged;
    @Size(max = 45)
    @Column(name = "narration", length = 45)
    private String narration;
    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    @Column(name = "table_key_changed")
    private Integer tableKeyChanged;

    public ChangeLog() {
    }

    public ChangeLog(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getTableChanged() {
        return tableChanged;
    }

    public void setTableChanged(String tableChanged) {
        this.tableChanged = tableChanged;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getTableKeyChanged() {
        return tableKeyChanged;
    }

    public void setTableKeyChanged(Integer tableKeyChanged) {
        this.tableKeyChanged = tableKeyChanged;
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
        if (!(object instanceof ChangeLog)) {
            return false;
        }
        ChangeLog other = (ChangeLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zmss.zmdm.entity.ChangeLog[ id=" + id + " ]";
    }
    
}
