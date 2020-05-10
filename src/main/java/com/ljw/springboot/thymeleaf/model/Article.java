package com.ljw.springboot.thymeleaf.model;

import java.util.Date;

public class Article {
    private Integer aId;

    private String content;

    private Integer titleId;

    private String titlename;

    private Date createdate;
    private Date editdate;
    private Date deletedate;

    private Integer accountId;

    private String typeId;

    private Integer readtimes;

    private Integer collecttimes;

    private Integer status;

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getTitlename() {
        return titlename;
    }

    public void setTitlename(String titlename) {
        this.titlename = titlename == null ? null : titlename.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getDeletedate() {
        return deletedate;
    }

    public void setDeletedate(Date deletedate) {
        this.deletedate = deletedate;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public Integer getReadtimes() {
        return readtimes;
    }

    public void setReadtimes(Integer readtimes) {
        this.readtimes = readtimes;
    }

    public Integer getCollecttimes() {
        return collecttimes;
    }

    public void setCollecttimes(Integer collecttimes) {
        this.collecttimes = collecttimes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getEditdate() {
        return editdate;
    }

    public void setEditdate(Date editdate) {
        this.editdate = editdate;
    }
}