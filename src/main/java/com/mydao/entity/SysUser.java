package main.java.com.mydao.entity;

import java.util.Date;

public class SysUser {
    private Long id;

    private String loginName;

    private String loginPass;

    private String realName;

    private String staffId;

    private String staffName;

    private Byte sex;

    private Byte identitycardTypeId;

    private String identitycardNo;

    private String birthday;

    private String eduLevel;

    private String mobile;

    private String cellPhone;

    private String email;

    private Byte isOnjob;

    private Byte status;

    private String updateStaffid;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass == null ? null : loginPass.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Byte getIdentitycardTypeId() {
        return identitycardTypeId;
    }

    public void setIdentitycardTypeId(Byte identitycardTypeId) {
        this.identitycardTypeId = identitycardTypeId;
    }

    public String getIdentitycardNo() {
        return identitycardNo;
    }

    public void setIdentitycardNo(String identitycardNo) {
        this.identitycardNo = identitycardNo == null ? null : identitycardNo.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel == null ? null : eduLevel.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone == null ? null : cellPhone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getIsOnjob() {
        return isOnjob;
    }

    public void setIsOnjob(Byte isOnjob) {
        this.isOnjob = isOnjob;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getUpdateStaffid() {
        return updateStaffid;
    }

    public void setUpdateStaffid(String updateStaffid) {
        this.updateStaffid = updateStaffid == null ? null : updateStaffid.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}