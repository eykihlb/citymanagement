package main.java.com.mydao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LoginLog {
    private Long recId;

    private String staffId;
    @JsonFormat
    private Date loginTime;

    private String terminalNo;
    @JsonFormat
    private Date idate;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo == null ? null : terminalNo.trim();
    }

    public Date getIdate() {
        return idate;
    }

    public void setIdate(Date idate) {
        this.idate = idate;
    }
}