package com.strendent.tutorsu.Models;


public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    private int icon;

    public int getIcon() {
        return icon;
    }

    public NavDrawerItem() {

    }

    public NavDrawerItem(String title, boolean showNotify, int icon) {

        this.title = title;
        this.showNotify = showNotify;
        this.icon = icon;
    }


    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
