package ru.ncedu.menu.utils.exportutil;


import ru.ncedu.menu.repositories.*;
import ru.ncedu.menu.utils.exportutil.exportmodel.*;

public class ExportController {
    private ExportController() {
    }

    private ExportUtil exportUtil;

    public static ExportUtil getExportUtil() {
        ExportUtil exportUtil = new ExportUtil();
        exportUtil.setProductExport(new ProductExport(ProductsRepository.getInstance().get()));
        exportUtil.setCategoryExport(new CategoryExport(CategoriesRepository.getInstance().get()));
        exportUtil.setPriceExport(new PriceExport(PricesRepository.getInstance().get()));
        exportUtil.setMarketExport(new MarketExport(MarketRepository.getInstance().get()));
        exportUtil.setCharacteristicValueExport(new CharacteristicValueExport(CharacteristicValueRepository.getInstance().get()));
        exportUtil.setCharacteristicGroupExport(new CharacteristicGroupExport(CharacteristicGroupRepository.getInstance().get()));
        exportUtil.setCharacteristicExport(new CharacteristicExport(CharacteristicRepository.getInstance().get()));
        return exportUtil;
    }
}
