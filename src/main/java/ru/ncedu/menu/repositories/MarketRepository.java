package ru.ncedu.menu.repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ru.ncedu.menu.models.Market;
import ru.ncedu.menu.utils.JSONUtils;

public class MarketRepository implements Repository<Market> {

    private static final String FILE_NAME = "markets.json";
    private List<Market> markets;
    private static final long START_ID = 0;

    private static MarketRepository instance;

    private MarketRepository() { }


    public static synchronized MarketRepository getInstance() {
        if (instance == null) {
            instance = new MarketRepository();
            instance.load();
        }
        return instance;
    }

    @Override
    public List<Market> get() {
        return markets;
    }

    public Market get(long id) {
        for (Market market : markets) {
            if (market.getId() == id) {
                return market;
            }
        }
        return null;
    }

    @Override
    public Market add(Market object) {
        if (object == null) return null;

        Market market = new Market(object);
        market.setId(makeNewId());

        markets.add(market);

        return market;


    }

    @Override
    public Market update(Market object) {
        if (object == null) return null;

        Market market = new Market(object);
        remove(market);

        markets.add(market);

        return market;

    }

    @Override
    public void remove(Market object) {
        if (object == null) return;

        Market market = get(object.getId());
        PricesRepository.getInstance().remove(object.getId());
        markets.remove(market);
    }


    @Override
    public void save() {
        try {
            JSONUtils.writeToFile(FILE_NAME, markets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        try {
            markets = JSONUtils.readListFromFile(FILE_NAME, Market.class);
        } catch (IOException e) {
            markets = new ArrayList<>();
            e.printStackTrace();
        }
    }

    private long makeNewId() {
        long id = START_ID;
        for (Market market : markets) {
            id = Math.max(id, market.getId());
        }

        return ++id;
    }
}