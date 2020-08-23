package com.ljw.springboot.thymeleaf.model;

import java.util.Date;

public class User {
    private Integer accountId;

    private String userid;

    private String userPass;

    private String sex;

    private String email;

    private Byte groupId;

    private Integer state;

    private Integer unbanTime;

    private Integer expirationTime;

    private Integer logincount;

    private Date lastlogin;

    private String lastIp;

    private Date birthdate;

    private Byte characterSlots;

    private String pincode;

    private Integer pincodeChange;

    private Integer bankVault;

    private Integer vipTime;

    private Byte oldGroup;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getGroupId() {
        return groupId;
    }

    public void setGroupId(Byte groupId) {
        this.groupId = groupId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUnbanTime() {
        return unbanTime;
    }

    public void setUnbanTime(Integer unbanTime) {
        this.unbanTime = unbanTime;
    }

    public Integer getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Integer expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Integer getLogincount() {
        return logincount;
    }

    public void setLogincount(Integer logincount) {
        this.logincount = logincount;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp == null ? null : lastIp.trim();
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Byte getCharacterSlots() {
        return characterSlots;
    }

    public void setCharacterSlots(Byte characterSlots) {
        this.characterSlots = characterSlots;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode == null ? null : pincode.trim();
    }

    public Integer getPincodeChange() {
        return pincodeChange;
    }

    public void setPincodeChange(Integer pincodeChange) {
        this.pincodeChange = pincodeChange;
    }

    public Integer getBankVault() {
        return bankVault;
    }

    public void setBankVault(Integer bankVault) {
        this.bankVault = bankVault;
    }

    public Integer getVipTime() {
        return vipTime;
    }

    public void setVipTime(Integer vipTime) {
        this.vipTime = vipTime;
    }

    public Byte getOldGroup() {
        return oldGroup;
    }

    public void setOldGroup(Byte oldGroup) {
        this.oldGroup = oldGroup;
    }
}