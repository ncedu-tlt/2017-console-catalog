package ru.ncedu.menu.repositories;

import ru.ncedu.menu.models.CharacteristicValue;
import ru.ncedu.menu.utils.JSONUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexanderZheleznov on 12.11.2016.
 * Создаёт репозиторий в JSON формате.
 * Сохжраняют такие сущности как:
 */
public class CharacteristicValueRepository implements Repository<CharacteristicValue>{

    private static final String FILE_NAME = "characteristicValues.json";
    private static final long START_ID = 1;

    private static CharacteristicValueRepository instance;

    private List <CharacteristicValue> characteristicValues;

    private CharacteristicValueRepository(){
    }

    public static synchronized CharacteristicValueRepository getInstance(){
        if(instance == null){
            instance = new CharacteristicValueRepository();
            instance.load();
        }
        return instance;
    }

    @Override
    public List<CharacteristicValue> get() {
        return characteristicValues;
    }

    public CharacteristicValue get(long id_characteristic){
        for(CharacteristicValue value : characteristicValues){
            if(value.getCharacteristicId() == id_characteristic){
                return value;
            }
        }
        return null;
    }

    @Override
    public CharacteristicValue add(CharacteristicValue object) {
        if (object == null)
            return null;

        CharacteristicValue characteristicValue = new CharacteristicValue(object);
        characteristicValue.setCharacteristicId(generateId());
        characteristicValue.setProductId(generateId());

        characteristicValues.add(characteristicValue);
        return characteristicValue;
    }

    @Override
    public CharacteristicValue update(CharacteristicValue object) {
        if(object == null)
            return null;

        CharacteristicValue characteristicValue = new CharacteristicValue(object);
        remove(characteristicValue);

        characteristicValues.add(characteristicValue);
        return characteristicValue;
    }

    @Override
    public void remove(CharacteristicValue object) {
        if(object == null)
            return;

        CharacteristicValue characteristicValue = get(object.getCharacteristicId());

        characteristicValues.remove(characteristicValue);

    }

    @Override
    public void save() {
        try {
            JSONUtils.writeToFile(FILE_NAME, characteristicValues);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        try {
            characteristicValues = JSONUtils.readListFromFile(FILE_NAME, CharacteristicValue.class);
        } catch (IOException e) {
            characteristicValues = new ArrayList<>();
            e.printStackTrace();
        }
    }

    private long generateId() {
        long id = START_ID;
        for (CharacteristicValue value : characteristicValues) {
            id = Math.max(id, value.getCharacteristicId());
        }

        return ++id;
    }
}
