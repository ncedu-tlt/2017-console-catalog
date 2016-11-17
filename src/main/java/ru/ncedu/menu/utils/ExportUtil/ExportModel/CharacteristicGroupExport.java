package ru.ncedu.menu.utils.ExportUtil.ExportModel;

import ru.ncedu.menu.models.CharacteristicGroup;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class CharacteristicGroupExport {
    private List<CharacteristicGroup> characteristicGroups;

    private CharacteristicGroupExport(){}
    public CharacteristicGroupExport(List<CharacteristicGroup> characteristicGroups) {
        this.characteristicGroups = characteristicGroups;
    }

    public List<CharacteristicGroup> getCharacteristicGroups() {
        return characteristicGroups;
    }

    @XmlElement(name = "CharacteristicGroup")
    public void setCharacteristicGroups(List<CharacteristicGroup> characteristicGroups) {
        this.characteristicGroups = characteristicGroups;
    }
}
