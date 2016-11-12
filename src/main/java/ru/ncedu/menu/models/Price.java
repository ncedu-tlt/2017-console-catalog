package ru.ncedu.menu.models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Andrey on 11/12/2016.
 */
public class Price implements Serializable {
    private long marketId;
    private long productId;
    private BigDecimal amount;

    public Price() {
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

    public void setMarketId(long marketId) {
        this.marketId = marketId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
