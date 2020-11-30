package com.robert.kataforbnpcorp.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Author: ROBERT TOGBE (Senior Developer Tech lead Software Architect for Industry-leading Corp.)
 * Creation Date: 26/11/2020
 */

public class ShoppingCart {
    private final Inventory inventory;
    // A best practice is that you should not expose the data structure.
    private List<LineItem> itemsInCart = new ArrayList<>();
    
    public ShoppingCart(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addItem(LineItem lineItem) {
        Item item = inventory.get(lineItem.getItemId());
        lineItem.setName(item.getName());
        lineItem.setPrice(item.getPrice());

        this.itemsInCart.add(lineItem);
    }

    public void increaseItemQuantityBy(LineItem lineItem, int quantity) {
        lineItem.increaseQuantityBy(quantity);

    }

    public void reduceItemQuantityBy(LineItem lineItem, int quantity) {
        lineItem.reduceQuantityBy(quantity);
    }

    public int totalNumberOfItems() {

        int totalItem = 0;
                for(LineItem lineItem : itemsInCart) {
                    totalItem += lineItem.getQuantity();
                }
        return totalItem;
    }

    public  BigDecimal totalCostOfCart() {
        BigDecimal totalAmounts = new BigDecimal("0");
        for(LineItem lineItem : itemsInCart) {
            BigDecimal amount = lineItem.getPrice().multiply(new BigDecimal(lineItem.getQuantity()));
            totalAmounts = totalAmounts.add(amount);
        }
        return totalAmounts;
    }

    /**
     * This is wrong - Réfléchissons y un peu
    public void remove(LineItem lineItem) {
        this.itemsInCart.remove(lineItem);

    }
     */

    public void remove(LineItem lineItemToRemove) {
        boolean deleteLineItem = false;
        for(LineItem itemInCart : itemsInCart) {
            if(Objects.equals(itemInCart.getItemId(), lineItemToRemove.getItemId())) {
                if(lineItemToRemove.getQuantity() == itemInCart.getQuantity()) {
                    deleteLineItem = true;
                } else {
                    itemInCart.reduceQuantityBy(lineItemToRemove.getQuantity());
                }
            }
        }
        if(deleteLineItem) {
            this.itemsInCart.remove(lineItemToRemove);
        }

    }


    public List<LineItem> listItemsInCart() {

        return Collections.unmodifiableList(this.itemsInCart);

    }
}
