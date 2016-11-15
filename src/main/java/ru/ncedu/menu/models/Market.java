package ru.ncedu.menu.models;

public class Market {

    private long id;
    private String name;

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

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
