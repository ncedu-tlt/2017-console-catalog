package ru.ncedu.menu.utils.exportutil.importutils;

import ru.ncedu.menu.models.CharacteristicValue;
import ru.ncedu.menu.repositories.CharacteristicValueRepository;

import java.util.ArrayList;
import java.util.List;

public class CharacteristicValueImportUtil {
    private List<CharacteristicValue> characteristicsValue;
    private boolean updatingCharacteristicValue;

    public CharacteristicValueImportUtil(List<CharacteristicValue> characteristicsValue, boolean updatingCharacteristicValue) {
        if (characteristicsValue != null) {
            this.characteristicsValue = characteristicsValue;
        } else {
            this.characteristicsValue = new ArrayList<>();
        }
        this.updatingCharacteristicValue = updatingCharacteristicValue;
    }

    public void setInRepository() {
        List<CharacteristicValue> characteristicsValueInRepository = CharacteristicValueRepository.getInstance().get();
        List<CharacteristicValue> updateCharacteristicValue = new ArrayList<>();

        if (!characteristicsValueInRepository.isEmpty()) {
            for (CharacteristicValue characteristicValueInRepository : characteristicsValueInRepository) {
                for (CharacteristicValue characteristicValue : characteristicsValue) {
                    if (characteristicValueInRepository.getValue().equals(characteristicValue.getValue()) &&
                            characteristicValueInRepository.getCharacteristicId() == characteristicValue.getCharacteristicId()) {
                        updateCharacteristicValue.add(characteristicValue);
                    }
                }
            }
            if (!updateCharacteristicValue.isEmpty() && updatingCharacteristicValue) {
                for (CharacteristicValue characteristicValue : updateCharacteristicValue) {
                    CharacteristicValueRepository.getInstance().update(characteristicValue);
                }
            }
            characteristicsValue.removeAll(updateCharacteristicValue);
            if (!characteristicsValue.isEmpty()) {
                for (CharacteristicValue characteristic : characteristicsValue) {
                    CharacteristicValueRepository.getInstance().add(characteristic);
                }
            }
        } else if (characteristicsValueInRepository.isEmpty() && !characteristicsValue.isEmpty()) {
            for (CharacteristicValue characteristicValue : characteristicsValue) {
                CharacteristicValueRepository.getInstance().add(characteristicValue);
            }
        }
    }
}
