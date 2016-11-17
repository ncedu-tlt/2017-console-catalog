package ru.ncedu.menu.models;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.math.BigDecimal;

public class Price implements Serializable {
    private long marketId;
    private long productId;
    private BigDecimal amount;

    private Price() {}

    private Price (BigDecimal amount){
        this.amount = amount;
    }

    public Price(long marketId, long productId, BigDecimal amount) {
        this.marketId = marketId;
        this.productId = productId;
        this.amount = amount;
    }


    public long getMarketId() {
        return marketId;
    }

    public long getProductId() {
        return productId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    @XmlElement
    public void setMarketId(long marketId) {
        this.marketId = marketId;
    }
    @XmlElement
    public void setProductId(long productId) {
        this.productId = productId;
    }
    @XmlElement
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
