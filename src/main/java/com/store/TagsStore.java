package com.store;

import entities.Tag;

import java.util.HashMap;

public class TagsStore {
    private static TagsStore instance = null;
    private final HashMap<String, Tag> tagStore = new HashMap<>();

    private TagsStore() {
    }

    public static TagsStore getInstance() {
        if (instance == null) {
            instance = new TagsStore();
        }
        return instance;
    }

    public Tag putTagIntoStore(String key, Tag value) {
        return tagStore.put(value.getId(), value);
    }

    public Tag getTagFromStore(String key) {
        return tagStore.get(key);
    }
}
