package ru.ncedu.menu.repositories;

import ru.ncedu.menu.models.Characteristic;
import ru.ncedu.menu.utils.JSONUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharacteristicRepository implements Repository<Characteristic> {

    private static final String FILE_NAME = "characteristics.json";
    private static final long START_ID = 0;

    private static CharacteristicRepository instance;

    private List<Characteristic> characteristics;

    private CharacteristicRepository() {}

    public static synchronized CharacteristicRepository getInstance() {
        if (instance == null) {
            instance = new CharacteristicRepository();
            instance.load();
        }
        return instance;
    }

    @Override
    public List<Characteristic> get() {
        return characteristics;
    }

    public Characteristic get(long id) {
        for (Characteristic characteristic : characteristics) {
            if (characteristic.getId() == id) return characteristic;
        }
        return null;
    }

    @Override
    public Characteristic add(Characteristic object) {
        if (object == null) return null;

        Characteristic characteristic = new Characteristic(object);
        characteristic.setId(generateId());

        characteristics.add(characteristic);
        return characteristic;
    }

    @Override
    public Characteristic update(Characteristic object) {
        if (object == null) return null;

        Characteristic characteristic = new Characteristic(object);
        remove(characteristic);

        characteristics.add(characteristic);
        return characteristic;
    }

    @Override
    public void remove(Characteristic object) {
        if (object == null) return;

        Characteristic characteristic = get(object.getId());
        CharacteristicValueRepository.getInstance().remove(object.getId());
        characteristics.remove(characteristic);
    }

    public void remove(long characteristicGroupId) {
        List<Characteristic> characteristicToRemove = new ArrayList<>();

        if (characteristicGroupId == 0) return;

        for (Characteristic characteristic: characteristics) {
            if (characteristic.getGroupId() == characteristicGroupId) {
                characteristicToRemove.add(characteristic);
            }
        }
        characteristics.addAll(characteristicToRemove);
    }

    @Override
    public void save() {
        try {
            JSONUtils.writeToFile(FILE_NAME, characteristics);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        try {
            characteristics = JSONUtils.readListFromFile(FILE_NAME, Characteristic.class);
        } catch (IOException e) {
            characteristics = new ArrayList<>();
            e.printStackTrace();
        }
    }

    private long generateId() {
        long id = START_ID;
        for (Characteristic charasteristic : characteristics) {
            id = Math.max(id, charasteristic.getId());
        }

        return ++id;
    }
}
