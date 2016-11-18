package ru.ncedu.menu.utils.exportutil.exportmodel;

import ru.ncedu.menu.models.Market;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class MarketExport {
    private List<Market> markets;

    public MarketExport() {
    }

    public List<Market> getMarkets() {
        return markets;
    }

    @XmlElement(name = "Market")
    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

    public MarketExport(List<Market> markets) {

        this.markets = markets;
    }
}
