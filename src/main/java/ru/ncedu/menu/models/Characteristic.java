package ru.ncedu.menu.models;

import java.io.Serializable;

public class Characteristic implements Serializable {
    private long id;
    private String name;
    private long categoryId;
    private long groupId;

    public Characteristic() {
    }

    public Characteristic(String name, long categoryId, long groupId) {
        this.name = name;
        this.categoryId = categoryId;
        this.groupId = groupId;
    }

    public Characteristic(long id, String name, long categoryId, long groupId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.groupId = groupId;
    }

    public Characteristic(Characteristic characteristic) {
        this(characteristic.getId(), characteristic.getName(),
                characteristic.getCategoryId(), characteristic.getGroupId());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
