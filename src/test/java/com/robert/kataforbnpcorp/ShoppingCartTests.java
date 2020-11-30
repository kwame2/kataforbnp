package com.robert.kataforbnpcorp;
import com.robert.kataforbnpcorp.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author: ROBERT TOGBE (Senior Developer Tech lead Software Architect for Industry-leading Corp.)
 * Creation Date: 26/11/2020
 */

@SpringBootTest
public class ShoppingCartTests {

    private static Inventory inventory;
    private static ShoppingCart cart;

    @BeforeAll
    public static void setUp() throws Exception {
        Item item1 = new Item("item-1","Effective Java", ItemType.BOOK,BigDecimal.valueOf(4000));
        Item item2 = new Item("item-2","Effective Python", ItemType.BOOK,BigDecimal.valueOf(3500));
        Inventory inventory = new Inventory();
        inventory.add(item1);
        inventory.add(item2);
        cart = new ShoppingCart(inventory);
    }

    @Test
    public void should_add_an_item_to_a_cart() {


        //cart.addItem(new Item("item-1","Effective Java", ItemType.BOOK, BigDecimal.valueOf(4000)));
        cart.addItem(new LineItem(("item-1")));

        int totalItemCount = cart.totalNumberOfItems();

        Assertions.assertEquals(totalItemCount, 1);

    }

    @Test
    public void should_add_multiple_items_to_the_cart() {

        cart.addItem(new LineItem("item-1"));
        cart.addItem(new LineItem("item-2"));

        int totalItemCount = cart.totalNumberOfItems();

        Assertions.assertEquals(totalItemCount, 2);

    }

    @Test
    public void should_add_multiple_quantity_of_same_item_to_the_cart() {

        cart.addItem(new LineItem("item-1", 2));

        int totalItemCount = cart.totalNumberOfItems();

        Assertions.assertEquals(totalItemCount, 2);

    }

    @Test
    public void should_remove_an_item_from_cart() {
        cart.addItem(new LineItem("item-1"));
        cart.addItem(new LineItem("item-2"));

        cart.remove(new LineItem("item-1"));

        int totalItemCount = cart.totalNumberOfItems();

        Assertions.assertEquals(totalItemCount, 1);
    }

    @Test
    public void should_remove_specific_quantity_of_an_item_from_cart() {
        cart.addItem(new LineItem("item-1", 2));
        cart.addItem(new LineItem("item-2", 3));

        cart.remove(new LineItem("item-1" , 2));
        cart.remove(new LineItem("item-2" , 1));

        int totalItemCount = cart.totalNumberOfItems();

        Assertions.assertEquals(totalItemCount, 2);
    }

    @Test
    public void should_view_listing_of_items_in_the_cart() {
        cart.addItem(new LineItem("item-1", 2));
        cart.addItem(new LineItem("item-2", 2));

        List<LineItem> lineItems = cart.listItemsInCart();

        Assertions.assertEquals(lineItems.get(0).totalPrice(), BigDecimal.valueOf(8000));
        Assertions.assertEquals(lineItems.get(1).totalPrice(), BigDecimal.valueOf(7000));
    }
}
