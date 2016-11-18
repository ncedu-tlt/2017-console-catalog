package ru.ncedu.menu.utils.exportutil.importutils;

import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.repositories.CharacteristicGroupRepository;

import java.util.ArrayList;
import java.util.List;

public class CharacteristicGroupImportUtil implements Import {
    private List<CharacteristicGroup> characteristicsGroup;
    private boolean updatingCharacteristicGroup;

    public CharacteristicGroupImportUtil(List<CharacteristicGroup> characteristicsGroup, boolean updatingCharacteristicGroup) {
        if (characteristicsGroup != null) {
            this.characteristicsGroup = characteristicsGroup;
        } else {
            this.characteristicsGroup = new ArrayList<>();
        }
        this.updatingCharacteristicGroup = updatingCharacteristicGroup;
    }

    public void setInRepository() {
        List<CharacteristicGroup> characteristicGroupsInRepository = CharacteristicGroupRepository.getInstance().get();
        List<CharacteristicGroup> updateCharacteristicGroup = new ArrayList<>();

        if (!characteristicGroupsInRepository.isEmpty()) {
            for (CharacteristicGroup characteristicGroupInRepository : characteristicGroupsInRepository) {
                for (CharacteristicGroup characteristicGroup : characteristicsGroup) {
                    if (characteristicGroupInRepository.getId() == characteristicGroup.getId()) {
                        updateCharacteristicGroup.add(characteristicGroup);
                    }
                }
            }
            if (!updateCharacteristicGroup.isEmpty() && updatingCharacteristicGroup) {
                for (CharacteristicGroup characteristicGroup : updateCharacteristicGroup) {
                    CharacteristicGroupRepository.getInstance().update(characteristicGroup);
                }
            }
            characteristicsGroup.removeAll(updateCharacteristicGroup);
            if (!characteristicsGroup.isEmpty()) {
                for (CharacteristicGroup characteristicGroup : characteristicsGroup) {
                    CharacteristicGroupRepository.getInstance().add(characteristicGroup);
                }
            }
        } else if (characteristicGroupsInRepository.isEmpty() && !characteristicsGroup.isEmpty()) {
            for (CharacteristicGroup characteristicGroup : characteristicsGroup) {
                CharacteristicGroupRepository.getInstance().add(characteristicGroup);
            }
        }
    }
}
