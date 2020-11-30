package com.robert.kataforbnpcorp.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: ROBERT TOGBE (Senior Developer Tech lead Software Architect for Industry-leading Corp.)
 * Creation Date: 26/11/2020
 */


public class Inventory {

    private Map<String, Item> items = new HashMap<>();

    public void add(Item item) {
        items.put(item.getId(),item);
    }

    public Item get(String id) {
        return items.get(id);
    }
}
