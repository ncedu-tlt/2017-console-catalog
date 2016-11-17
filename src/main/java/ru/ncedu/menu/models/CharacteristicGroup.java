package ru.ncedu.menu.models;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class CharacteristicGroup implements Serializable {
    private long id;
    private String name;
    private long orderNumber = 0;

    public CharacteristicGroup() {
    }

    public CharacteristicGroup(String name) {
        this.name = name;
    }

    public CharacteristicGroup(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CharacteristicGroup(long id, String name, long orderNumber) {
        this.id = id;
        this.name = name;
        this.orderNumber = orderNumber;
    }

    public CharacteristicGroup(CharacteristicGroup characteristicGroup) {
        this(characteristicGroup.getId(), characteristicGroup.getName());
    }

    public String getName() {
        return name;
    }

    public String getName(long id) {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
    @XmlElement
    public void setId(long id) {
        this.id = id;
    }
    @XmlElement
    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }
}
