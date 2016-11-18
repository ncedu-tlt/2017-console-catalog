package ru.ncedu.menu.utils.exportutil.importutils;

import ru.ncedu.menu.models.Market;
import ru.ncedu.menu.repositories.MarketRepository;

import java.util.ArrayList;
import java.util.List;

public class MarketImportUtil implements Import {
    private List<Market> markets;
    private boolean updatingMarket;

    public MarketImportUtil(List<Market> markets, boolean updatingMarket) {
        if (markets != null) {
            this.markets = markets;
        } else {
            this.markets = new ArrayList<>();
        }
        this.updatingMarket = updatingMarket;
    }

    public void setInRepository() {
        List<Market> marketsInRepository = MarketRepository.getInstance().get();
        List<Market> updateMarkets = new ArrayList<>();

        if (!marketsInRepository.isEmpty()) {
            for (Market marketInRepository : marketsInRepository) {
                for (Market market : markets) {
                    if (marketInRepository.getId() == market.getId()) {
                        updateMarkets.add(market);
                    }
                }
            }
            if (!updateMarkets.isEmpty() && updatingMarket) {
                for (Market market : updateMarkets) {
                    MarketRepository.getInstance().update(market);
                }
            }
            markets.removeAll(updateMarkets);
            if (!markets.isEmpty()) {
                for (Market market : markets) {
                    MarketRepository.getInstance().add(market);
                }
            }
        } else if (marketsInRepository.isEmpty() && !markets.isEmpty()) {
            for (Market market : markets) {
                MarketRepository.getInstance().add(market);
            }
        }
    }
}
