package com.ljw.springboot.thymeleaf.model;

import java.util.List;

public class EasyuiJson {
    private int total;
    private List rows;

    public EasyuiJson(int total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
