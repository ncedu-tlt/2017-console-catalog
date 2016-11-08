package ru.ncedu.menu.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONUtils {

    private static final String FILES_PATH = "out" + File.separator;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> readListFromFile(String fileName, Class<T> tClass) throws IOException {

        File file = getFile(fileName);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, tClass));
    }

    public static void writeToFile(String fileName, Object object) throws IOException {
        File file = getFile(fileName);
        objectMapper.writeValue(file, object);
    }

    private static File getFile(String fileName) {
        File file = new File(FILES_PATH + fileName);
        file.getParentFile().mkdirs();

        return file;
    }

}