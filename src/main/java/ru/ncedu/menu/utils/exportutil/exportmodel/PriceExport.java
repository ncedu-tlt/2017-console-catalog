package ru.ncedu.menu.utils.exportutil.exportmodel;

import ru.ncedu.menu.models.Price;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class PriceExport {

    private List<Price> prices;

    private PriceExport(){}

    public PriceExport(List<Price> prices) {
        this.prices = prices;
    }


    public List<Price> getPrices() {
        return prices;
    }

    @XmlElement(name = "Price")
    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
