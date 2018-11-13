package main.java.com.mydao.entity;

import java.util.Date;

public class Violation {
    private Long recId;

    private Integer violationAttribute;

    private String psersonName;

    private String personPhone;

    private String identyCode;

    private String facialFeature;

    private String plateNo;

    private String violationAddr;

    private String addrLng;

    private String addrLat;

    private Date violationTime;

    private String terminalNo;

    private String violationtypeCode;

    private String faceImg;

    private String fullImg;

    private String violationDetail;

    private String proessResult;

    private String processStaffId;

    private Date idate;

    private String violationtypeName;

    public String getViolationtypeName() {
        return violationtypeName;
    }

    public void setViolationtypeName(String violationtypeName) {
        this.violationtypeName = violationtypeName;
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public Integer getViolationAttribute() {
        return violationAttribute;
    }

    public void setViolationAttribute(Integer violationAttribute) {
        this.violationAttribute = violationAttribute;
    }

    public String getPsersonName() {
        return psersonName;
    }

    public void setPsersonName(String psersonName) {
        this.psersonName = psersonName == null ? null : psersonName.trim();
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone == null ? null : personPhone.trim();
    }

    public String getIdentyCode() {
        return identyCode;
    }

    public void setIdentyCode(String identyCode) {
        this.identyCode = identyCode == null ? null : identyCode.trim();
    }

    public String getFacialFeature() {
        return facialFeature;
    }

    public void setFacialFeature(String facialFeature) {
        this.facialFeature = facialFeature == null ? null : facialFeature.trim();
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo == null ? null : plateNo.trim();
    }

    public String getViolationAddr() {
        return violationAddr;
    }

    public void setViolationAddr(String violationAddr) {
        this.violationAddr = violationAddr == null ? null : violationAddr.trim();
    }

    public String getAddrLng() {
        return addrLng;
    }

    public void setAddrLng(String addrLng) {
        this.addrLng = addrLng == null ? null : addrLng.trim();
    }

    public String getAddrLat() {
        return addrLat;
    }

    public void setAddrLat(String addrLat) {
        this.addrLat = addrLat == null ? null : addrLat.trim();
    }

    public Date getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(Date violationTime) {
        this.violationTime = violationTime;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo == null ? null : terminalNo.trim();
    }

    public String getViolationtypeCode() {
        return violationtypeCode;
    }

    public void setViolationtypeCode(String violationtypeCode) {
        this.violationtypeCode = violationtypeCode == null ? null : violationtypeCode.trim();
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg == null ? null : faceImg.trim();
    }

    public String getFullImg() {
        return fullImg;
    }

    public void setFullImg(String fullImg) {
        this.fullImg = fullImg == null ? null : fullImg.trim();
    }

    public String getViolationDetail() {
        return violationDetail;
    }

    public void setViolationDetail(String violationDetail) {
        this.violationDetail = violationDetail == null ? null : violationDetail.trim();
    }

    public String getProessResult() {
        return proessResult;
    }

    public void setProessResult(String proessResult) {
        this.proessResult = proessResult == null ? null : proessResult.trim();
    }

    public String getProcessStaffId() {
        return processStaffId;
    }

    public void setProcessStaffId(String processStaffId) {
        this.processStaffId = processStaffId == null ? null : processStaffId.trim();
    }

    public Date getIdate() {
        return idate;
    }

    public void setIdate(Date idate) {
        this.idate = idate;
    }
}