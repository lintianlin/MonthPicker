package com.sinfeeloo.monthpicker;

/**
 * @author: mhj
 * @date: 2017/11/6 17:09
 * @desc:
 */

public class DateBean {

    private int id;
    private String name;
    private boolean isSelected = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
