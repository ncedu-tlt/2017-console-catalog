package ru.ncedu.menu.utils.exportutil.importutils;


import ru.ncedu.menu.models.Characteristic;
import ru.ncedu.menu.repositories.CharacteristicRepository;

import java.util.ArrayList;
import java.util.List;

public class CharacteristicImportUtil implements Import {
    private List<Characteristic> characteristics;
    private boolean updatingCharacteristics;

    public CharacteristicImportUtil(List<Characteristic> characteristics, boolean updatingCharacteristics) {
        if (characteristics != null) {
            this.characteristics = characteristics;
        } else {
            this.characteristics = new ArrayList<>();
        }
        this.updatingCharacteristics = updatingCharacteristics;
    }

    public void setInRepository() {
        List<Characteristic> characteristicsInRepository = CharacteristicRepository.getInstance().get();
        List<Characteristic> updateCharacteristic = new ArrayList<>();

        if (!characteristicsInRepository.isEmpty()) {
            for (Characteristic characteristicInRepository : characteristicsInRepository) {
                for (Characteristic caracteristic : characteristics) {
                    if (characteristicInRepository.getId() == caracteristic.getId()) {
                        updateCharacteristic.add(caracteristic);
                    }
                }
            }
            if (!updateCharacteristic.isEmpty() && updatingCharacteristics) {
                for (Characteristic characteristic : updateCharacteristic) {
                    CharacteristicRepository.getInstance().update(characteristic);
                }
            }
            characteristics.removeAll(updateCharacteristic);
            if (!characteristics.isEmpty()) {
                for (Characteristic characteristic : characteristics) {
                    CharacteristicRepository.getInstance().add(characteristic);
                }
            }
        } else if (characteristicsInRepository.isEmpty() && !characteristics.isEmpty()) {
            for (Characteristic characteristic : characteristics) {
                CharacteristicRepository.getInstance().add(characteristic);
            }
        }
    }
}
