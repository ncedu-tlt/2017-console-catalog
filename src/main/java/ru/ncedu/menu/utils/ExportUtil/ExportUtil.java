package ru.ncedu.menu.utils.ExportUtil;

import ru.ncedu.menu.models.Characteristic;
import ru.ncedu.menu.utils.ExportUtil.ExportModel.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExportCatalog")
public class ExportUtil {
    private ProductExport productExport;
    private CategoryExport categoryExport;
    private PriceExport priceExport;
    private MarketExport marketExport;
    private CharacteristicValueExport characteristicValueExport;
    private CharacteristicGroupExport characteristicGroupExport;
    private CharacteristicExport characteristicExport;

    public CharacteristicExport getCharacteristicExport() {
        return characteristicExport;
    }
    @XmlElement(name = "Characteristics")
    public void setCharacteristicExport(CharacteristicExport characteristicExport) {
        this.characteristicExport = characteristicExport;
    }

    public CharacteristicGroupExport getCharacteristicGroupExport() {
        return characteristicGroupExport;
    }
    @XmlElement(name = "CharacteristicGroups")
    public void setCharacteristicGroupExport(CharacteristicGroupExport characteristicGroupExport) {
        this.characteristicGroupExport = characteristicGroupExport;
    }

    public CharacteristicValueExport getCharacteristicValueExport() {
        return characteristicValueExport;
    }
    @XmlElement(name = "CharacteristicValues")
    public void setCharacteristicValueExport(CharacteristicValueExport characteristicValueExport) {
        this.characteristicValueExport = characteristicValueExport;
    }

    public MarketExport getMarketExport() {
        return marketExport;
    }

    @XmlElement(name = "Markets")
    public void setMarketExport(MarketExport marketExport) {
        this.marketExport = marketExport;
    }

    public PriceExport getPriceExport() {
        return priceExport;
    }

    @XmlElement(name = "Prices")
    public void setPriceExport(PriceExport priceExport) {
        this.priceExport = priceExport;
    }

    public CategoryExport getCategoryExport() {
        return categoryExport;
    }

    @XmlElement(name = "Categories")
    public void setCategoryExport(CategoryExport categoryExport) {
        this.categoryExport = categoryExport;
    }

    public ProductExport getProductExport() {
        return productExport;
    }

    @XmlElement(name = "Products")
    public void setProductExport(ProductExport productExport) {
        this.productExport = productExport;
    }
}
