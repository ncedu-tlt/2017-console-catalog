package ru.ncedu.menu.repositories;

import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.models.Characteristic;
import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.utils.JSONUtils;

import java.io.IOException;
import java.util.*;

public class CharacteristicGroupRepository implements Repository<CharacteristicGroup> {


    private static final String FILE_NAME = "characteristicGroup.json";
    private static final long START_ID = 0;

    private static CharacteristicGroupRepository instance;

    private List<CharacteristicGroup> characteristicGroups;

    private CharacteristicGroupRepository() {
    }

    public static synchronized CharacteristicGroupRepository getInstance() {
        if (instance == null) {
            instance = new CharacteristicGroupRepository();
            instance.load();
        }
        return instance;
    }

    @Override
    public List<CharacteristicGroup> get() {
        sortById(characteristicGroups);
        return characteristicGroups;
    }

    public CharacteristicGroup get(long id) {
        for (CharacteristicGroup characteristicGroup : characteristicGroups) {
            if (characteristicGroup.getId() == id) {
                return characteristicGroup;
            }
        }
        return null;
    }

    @Override
    public CharacteristicGroup add(CharacteristicGroup object) {

        if (object == null) return null;

        CharacteristicGroup characteristicGroup = new CharacteristicGroup(object);
        characteristicGroup.setId(generateId());

        characteristicGroups.add(characteristicGroup);
        return characteristicGroup;
    }

    @Override
    public CharacteristicGroup update(CharacteristicGroup object) {
        if (object == null) return null;

        CharacteristicGroup characteristicGroup = new CharacteristicGroup(object);
        remove(characteristicGroup);

        characteristicGroups.add(characteristicGroup);

        return characteristicGroup;
    }

    @Override
    public void remove(CharacteristicGroup object) {
        if (object == null) return;

        CharacteristicGroup characteristicGroup = get(object.getId());
        CharacteristicRepository.getInstance().remove(object.getId());
        characteristicGroups.remove(characteristicGroup);
    }

    @Override
    public void save() {
        try {
            JSONUtils.writeToFile(FILE_NAME, characteristicGroups);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void load() {
        try {
            characteristicGroups = JSONUtils.readListFromFile(FILE_NAME, CharacteristicGroup.class);
        } catch (IOException e) {
            characteristicGroups = new ArrayList<>();
            e.printStackTrace();
        }
    }

    private long generateId() {
        long id = START_ID;
        for (CharacteristicGroup characteristicGroup : characteristicGroups) {
            id = Math.max(id, characteristicGroup.getId());
        }

        return ++id;
    }

    public void sortById(List<CharacteristicGroup> groups) {

        Collections.sort(groups, new Comparator<CharacteristicGroup>() {
            public int compare(CharacteristicGroup obj1, CharacteristicGroup obj2) {
                if (obj1.getId() > obj2.getId()) {
                    return 1;
                } else if (obj1.getId() < obj2.getId()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }

    public void sortByOrderNumber(List<CharacteristicGroup> groups) {

        Collections.sort(groups, new Comparator<CharacteristicGroup>() {
            public int compare(CharacteristicGroup obj1, CharacteristicGroup obj2) {
                if (obj1.getOrderNumber() > obj2.getOrderNumber()) {
                    return 1;
                } else if (obj1.getOrderNumber() < obj2.getOrderNumber()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }

    public List<CharacteristicGroup> getOrdered() {
        sortByOrderNumber(characteristicGroups);
        return characteristicGroups;
    }


    public List<CharacteristicGroup> getForCategory(Category category) {

        List<Characteristic> characteristics = CharacteristicRepository.getInstance().get();
        List<CharacteristicGroup> groups = new ArrayList<>();
        for (Characteristic characteristic : characteristics) {
            if (characteristic.getCategoryId() == category.getId()) {
                groups.add(get(characteristic.getGroupId()));
            }
        }
        sortByOrderNumber(groups);
        return groups;
    }

}