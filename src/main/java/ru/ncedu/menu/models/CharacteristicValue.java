package ru.ncedu.menu.models;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Created by AlexanderZheleznov on 11.11.2016.
 */
public class CharacteristicValue implements Serializable {
    private long characteristicId;
    private long productId;
    private String value;

    public CharacteristicValue(){
    }

    public CharacteristicValue(String value) {
        this.value = value;
    }

    public CharacteristicValue(long productId, long characteristicId, String value){
        this.productId = productId;
        this.characteristicId = characteristicId;
        this.value = value;
    }

    public CharacteristicValue(CharacteristicValue characteristicValue){
        this(characteristicValue.getProductId(), characteristicValue.getCharacteristicId(), characteristicValue.getValue());
    }

    public long getCharacteristicId() {
        return characteristicId;
    }
    @XmlElement
    public void setCharacteristicId(long characteristicId) {
        this.characteristicId = characteristicId;
    }

    public long getProductId() {
        return productId;
    }
    @XmlElement
    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getValue() {
        return value;
    }
    @XmlElement
    public void setValue(String value) {
        this.value = value;
    }

}
