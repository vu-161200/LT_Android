package com.example.filemanager;

import java.io.File;
import java.io.Serializable;

public class ItemModel implements Serializable {
    private String name;
    private String icon;
    private File path;


    public ItemModel(String name, String icon, File path) {
        this.name = name;
        this.icon = icon;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }
}
