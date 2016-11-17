package ru.ncedu.menu.utils.ExportUtil.ExportModel;

import ru.ncedu.menu.models.Characteristic;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class CharacteristicExport {
    private List<Characteristic> characteristics;

    public CharacteristicExport(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }
    @XmlElement(name = "Characteristic")
    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
}
