package com.store;

import entities.Category;

import java.util.HashMap;

public class CategoriesStore {
    private static CategoriesStore instance = null;
    private final HashMap<String, Category> tagStore = new HashMap<>();

    private CategoriesStore() {
    }

    public static CategoriesStore getInstance() {
        if (instance == null) {
            instance = new CategoriesStore();
        }
        return instance;
    }

    public Category putCategoryIntoStore(String key, Category value) {
        return tagStore.put(value.getId(), value);
    }

    public Category getCategoryFromStore(String key) {
        return tagStore.get(key);
    }
}
