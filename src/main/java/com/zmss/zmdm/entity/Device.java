/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmss.zmdm.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author ZMF
 */
@Entity
@Table(name = "device")
@NamedQueries({
    @NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")
    , @NamedQuery(name = "Device.findById", query = "SELECT d FROM Device d WHERE d.id = :id")
    , @NamedQuery(name = "Device.findByImei", query = "SELECT d FROM Device d WHERE d.imei = :imei")
    , @NamedQuery(name = "Device.findByProjectFleetId", query = "SELECT d FROM Device d WHERE d.projectFleetId = :projectFleetId")
    , @NamedQuery(name = "Device.findByOwnerName", query = "SELECT d FROM Device d WHERE d.ownerName = :ownerName")
    , @NamedQuery(name = "Device.findByLatitude", query = "SELECT d FROM Device d WHERE d.latitude = :latitude")
    , @NamedQuery(name = "Device.findByLongitude", query = "SELECT d FROM Device d WHERE d.longitude = :longitude")
    , @NamedQuery(name = "Device.findByLastSync", query = "SELECT d FROM Device d WHERE d.lastSync = :lastSync")
    , @NamedQuery(name = "Device.findByState", query = "SELECT d FROM Device d WHERE d.state = :state")
    , @NamedQuery(name = "Device.findByDistrict", query = "SELECT d FROM Device d WHERE d.district = :district")
    , @NamedQuery(name = "Device.findByStatus", query = "SELECT d FROM Device d WHERE d.status = :status")
    , @NamedQuery(name = "Device.findBySyncStatus", query = "SELECT d FROM Device d WHERE d.syncStatus = :syncStatus")
    , @NamedQuery(name = "Device.findByDeviceModel", query = "SELECT d FROM Device d WHERE d.deviceModel = :deviceModel")
    , @NamedQuery(name = "Device.findByOsVersion", query = "SELECT d FROM Device d WHERE d.osVersion = :osVersion")
    , @NamedQuery(name = "Device.findByDeviceToken", query = "SELECT d FROM Device d WHERE d.deviceToken = :deviceToken")
    , @NamedQuery(name = "Device.findByOsApiLevel", query = "SELECT d FROM Device d WHERE d.osApiLevel = :osApiLevel")
    , @NamedQuery(name = "Device.findByInstalledApp", query = "SELECT d FROM Device d WHERE d.installedApp = :installedApp")
    , @NamedQuery(name = "Device.findByUninstalledApp", query = "SELECT d FROM Device d WHERE d.uninstalledApp = :uninstalledApp")})
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 15)
    @Column(name = "imei")
    private String imei;
    @Column(name = "project_fleet_id")
    private Integer projectFleetId;
    @Size(max = 45)
    @Column(name = "owner_name")
    private String ownerName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Column(name = "last_sync")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSync;
    @Size(max = 45)
    @Column(name = "state")
    private String state;
    @Size(max = 45)
    @Column(name = "district")
    private String district;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @Size(max = 45)
    @Column(name = "sync_status")
    private String syncStatus;
    @Size(max = 45)
    @Column(name = "device_model")
    private String deviceModel;
    @Size(max = 45)
    @Column(name = "os_version")
    private String osVersion;
    @Size(max = 450)
    @Column(name = "device_token")
    private String deviceToken;
    @Column(name = "os_api_level")
    private Float osApiLevel;
    @Size(max = 500)
    @Column(name = "installed_app")
    private String installedApp;
    @Size(max = 500)
    @Column(name = "uninstalled_app")
    private String uninstalledApp;

    public Device() {
    }

    public Device(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getProjectFleetId() {
        return projectFleetId;
    }

    public void setProjectFleetId(Integer projectFleetId) {
        this.projectFleetId = projectFleetId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Date getLastSync() {
        return lastSync;
    }

    public void setLastSync(Date lastSync) {
        this.lastSync = lastSync;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Float getOsApiLevel() {
        return osApiLevel;
    }

    public void setOsApiLevel(Float osApiLevel) {
        this.osApiLevel = osApiLevel;
    }

    public String getInstalledApp() {
        return installedApp;
    }

    public void setInstalledApp(String installedApp) {
        this.installedApp = installedApp;
    }

    public String getUninstalledApp() {
        return uninstalledApp;
    }

    public void setUninstalledApp(String uninstalledApp) {
        this.uninstalledApp = uninstalledApp;
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
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zmss.zmdm.entity.Device[ id=" + id + " ]";
    }
    
}
