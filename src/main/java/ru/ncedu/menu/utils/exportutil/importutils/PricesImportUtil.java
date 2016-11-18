package ru.ncedu.menu.utils.exportutil.importutils;


import ru.ncedu.menu.models.Price;
import ru.ncedu.menu.repositories.PricesRepository;

import java.util.ArrayList;
import java.util.List;

public class PricesImportUtil implements Import{
    private List<Price> prices;
    private boolean updatingPrice;

    public PricesImportUtil(List<Price> prices, boolean updatingPrice) {
        if (prices != null) {
            this.prices = prices;
        } else {
            this.prices = new ArrayList<>();
        }
        this.updatingPrice = updatingPrice;
    }

    public void setInRepository() {
        List<Price> pricesInRepository = PricesRepository.getInstance().get();
        List<Price> updatePrices = new ArrayList<>();

        if (!pricesInRepository.isEmpty()) {
            for (Price priceInRepository : pricesInRepository) {
                for (Price price : prices) {
                    if (priceInRepository.getProductId() == price.getProductId()
                    && priceInRepository.getMarketId() == price.getMarketId()){
                        updatePrices.add(price);
                    }
                }
            }
            if (!updatePrices.isEmpty() && updatingPrice) {
                for (Price price : updatePrices) {
                    PricesRepository.getInstance().update(price);
                }
            }
            prices.removeAll(updatePrices);
            if (!prices.isEmpty()) {
                for (Price price : prices) {
                    PricesRepository.getInstance().add(price);
                }
            }
        } else if (pricesInRepository.isEmpty() && !prices.isEmpty()) {
            for (Price price : prices) {
                PricesRepository.getInstance().add(price);
            }
        }
    }
}
