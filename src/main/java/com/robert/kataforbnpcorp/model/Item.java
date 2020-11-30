package com.robert.kataforbnpcorp.model;

import java.math.BigDecimal;

/**
 * Author: ROBERT TOGBE (Senior Developer Tech lead Software Architect for Industry-leading Corp.)
 * Creation Date: 26/11/2020
 */


public class Item {

    private final String id;
    private final String name;
    private final ItemType itemType;
    private final BigDecimal price;

    public Item(String id, String name, ItemType itemType, BigDecimal price) {

        this.id = id;
        this.name = name;
        this.itemType = itemType;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public BigDecimal getPrice() {
        return price;
    }






}
