package ru.ncedu.menu.models;

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

    public CharacteristicValue(long characteristicId) {
        this.characteristicId = characteristicId;
    }

    public CharacteristicValue(String value) {
        this.value = value;
    }

    public CharacteristicValue(long characteristicId, long productId, String value) {
        this.characteristicId = characteristicId;
        this.productId = productId;
        this.value = value;
    }

    public CharacteristicValue(CharacteristicValue characteristicValue){
        this(characteristicValue.getCharacteristicId(), characteristicValue.getProductId(),
                characteristicValue.getValue());
    }

    public long getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(long characteristicId) {
        this.characteristicId = characteristicId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
