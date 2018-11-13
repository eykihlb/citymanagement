package main.java.com.mydao.entity;

import java.util.Date;

public class ViolationType {
    private Long recId;

    private String violationtypeCode;

    private String violationtypeName;

    private Date idate;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getViolationtypeCode() {
        return violationtypeCode;
    }

    public void setViolationtypeCode(String violationtypeCode) {
        this.violationtypeCode = violationtypeCode == null ? null : violationtypeCode.trim();
    }

    public String getViolationtypeName() {
        return violationtypeName;
    }

    public void setViolationtypeName(String violationtypeName) {
        this.violationtypeName = violationtypeName == null ? null : violationtypeName.trim();
    }

    public Date getIdate() {
        return idate;
    }

    public void setIdate(Date idate) {
        this.idate = idate;
    }
}