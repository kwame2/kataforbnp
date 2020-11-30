package com.robert.kataforbnpcorp.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Author: ROBERT TOGBE (Senior Developer Tech lead Software Architect for Industry-leading Corp.)
 * Creation Date: 26/11/2020
 */


public class LineItem {

    private String itemId;
    private String name;
    private BigDecimal price;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }



    public LineItem(String itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public LineItem(String itemId) {
        this(itemId, 1);
    }

    public String getItem() {
        return itemId;
    }

    public void setItem(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return quantity == lineItem.quantity &&
                Objects.equals(itemId, lineItem.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, quantity);
    }

    public void reduceQuantityBy(int quantityToReduceBy) {
        this.quantity -= quantityToReduceBy;
    }

    public void increaseQuantityBy(int quantityToReduceBy) {
        this.quantity += quantityToReduceBy;
    }


    public BigDecimal totalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
