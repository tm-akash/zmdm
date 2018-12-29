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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ZMF
 */
@Entity
@Table(name = "district")
@NamedQueries({
    @NamedQuery(name = "District.findAll", query = "SELECT d FROM District d")})
public class District implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "digit_code")
    private Integer digitCode;
    @Lob
    @Size(max = 65535)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "state_digit_code")
    private String stateDigitCode;

    public District() {
    }

    public District(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDigitCode() {
        return digitCode;
    }

    public void setDigitCode(Integer digitCode) {
        this.digitCode = digitCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStateDigitCode() {
        return stateDigitCode;
    }

    public void setStateDigitCode(String stateDigitCode) {
        this.stateDigitCode = stateDigitCode;
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
        if (!(object instanceof District)) {
            return false;
        }
        District other = (District) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zmss.zmdm.entity.District[ id=" + id + " ]";
    }
    
}
