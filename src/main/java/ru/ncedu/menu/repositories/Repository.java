package ru.ncedu.menu.repositories;

import java.util.List;

public interface Repository<T> {

    /**
     * Returns all objects
     * @return Objects list
     */
    List<T> get();

    /**
     * Adds new object
     * @param object Object to add
     * @return Added object
     */
    T add(T object);

    /**
     * Updates object
     * @param object Object to update
     * @return Updated object
     */
    T update(T object);

    /**
     * Removes object
     * @param object Object to remove
     */
    void remove(T object);

    /**
     * Saves data to file
     */
    void save();

    /**
     * Loads data from file
     */
    void load();

}