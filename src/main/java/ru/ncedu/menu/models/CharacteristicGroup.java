package ru.ncedu.menu.models;

import java.io.Serializable;

public class CharacteristicGroup implements Serializable {
    private long id;
    private String name;
    private long orderNumber;

    public CharacteristicGroup() {
    }

    public CharacteristicGroup(String name) {
        this.name = name;
    }

    public CharacteristicGroup(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CharacteristicGroup(String name, long orderNumber) {
        this.name = name;
        this.orderNumber = orderNumber;
    }

    public CharacteristicGroup(long id, String name, long orderNumber) {
        this.id = id;
        this.name = name;
        this.orderNumber = orderNumber;
    }

    public CharacteristicGroup(CharacteristicGroup characteristicGroup) {
        this(characteristicGroup.getId(), characteristicGroup.getName(), characteristicGroup.getOrderNumber());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }
}
