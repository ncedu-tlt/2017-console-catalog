package ru.ncedu.menu.models;

import javax.xml.bind.annotation.XmlElement;

public class Market {

    private long id;
    private String name;
    private long priceId;

    public Market() { }

    public Market(String name) {
        this.name = name;
    }

    public Market(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Market(Market market) {

        this(market.getId(), market.getName());
    }

    public long getId() {
        return id;
    }
    @XmlElement
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public long getPriceId() {
        return priceId;
    }
    @XmlElement
    public void setPriceId(long priceId) {
        this.priceId = priceId;
    }
}
