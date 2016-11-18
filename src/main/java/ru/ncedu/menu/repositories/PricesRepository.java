package ru.ncedu.menu.repositories;

import ru.ncedu.menu.models.Price;
import ru.ncedu.menu.utils.JSONUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PricesRepository implements Repository<Price> {

    private static final String FILE_NAME = "prices.json";
    private List<Price> prices;
    private static PricesRepository instance;

    private PricesRepository() {
    }

    public static synchronized PricesRepository getInstance() {
        if (instance == null) {
            instance = new PricesRepository();
            instance.load();
        }
        return instance;
    }

    @Override
    public List<Price> get() {
        return prices;
    }


    public Price get(long marketId, long productId) {
        for (Price price : prices) {
            if (price.getMarketId() == marketId && price.getProductId() == productId) {
                return price;
            }
        }
        return null;
    }

    @Override
    public Price add(Price object) {
        if (object == null) {
            return null;
        }
        Price price = new Price(object.getMarketId(), object.getProductId(), object.getAmount());
        prices.add(price);
        return price;
    }

    @Override
    public Price update(Price object) {
        if (object == null) {
            return null;
        }
        Price price = new Price(object.getMarketId(), object.getProductId(), object.getAmount());
        remove(price);

        prices.add(price);
        return price;
    }

    @Override
    public void remove(Price object) {
        if (object == null) {
            return;
        }
        Price price = get(object.getMarketId(), object.getProductId());
        prices.remove(price);
    }

    // TODO: перенесли ошибки из CharacteristicValueRepository
    public void remove(long marketId) {
        List<Price> priceToRemove = new ArrayList<>();

        if (marketId == 0) return;

        for (Price price: prices) {
            if (price.getMarketId() == marketId) {
                priceToRemove.add(price);
            }
        }
        prices.addAll(priceToRemove);
    }

    @Override
    public void save() {
        try {
            JSONUtils.writeToFile(FILE_NAME, prices);
        } catch (IOException IOE) {
            IOE.printStackTrace();
        }
    }

    @Override
    public void load() {
        try {
            prices = JSONUtils.readListFromFile(FILE_NAME, Price.class);
        } catch (IOException e) {
            prices = new ArrayList<>();
            e.printStackTrace();
        }
    }
}
