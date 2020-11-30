package com.robert.kataforbnpcorp;

import com.robert.kataforbnpcorp.model.*;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: ROBERT TOGBE (Senior Developer Tech lead Software Architect for Industry-leading Corp.)
 * Creation Date: 26/11/2020
 */


@SpringBootApplication
public class KataForBnpApp implements CommandLineRunner {

    public static void main(String[] args) throws Exception{
        SpringApplication app = new SpringApplication(KataForBnpApp.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    ShoppingCart cart;
    List<Item> catalog = new ArrayList<>();
    private int ch = 0;
    private int ch3 = 0;
    private int ch4 = 0;
    private int ch5 = 0;
    private String str = "";
    @Override
    public void run(String... args) throws Exception {

        Item item1 = new Item("item-1","Effective Java", ItemType.BOOK, BigDecimal.valueOf(4000));
        Item item2 = new Item("item-2","Effective Python", ItemType.BOOK,BigDecimal.valueOf(3500));
        Item item3 = new Item("item-3","Effective PHP", ItemType.BOOK,BigDecimal.valueOf(2500));
        catalog.add(item1);
        catalog.add(item2);
        catalog.add(item3);
        Inventory inventory = new Inventory();
        inventory.add(item1);
        inventory.add(item2);
        inventory.add(item3);
        cart = new ShoppingCart(inventory);
        System.out.println("Here is your catalog: ");
        displayStoreProducts();

        String message1 = "";
        String message2 = "";
        String message3 = "";
        String message4 = "";
        //Scanner input = new Scanner(System.in);
        //System.out.println("Please provide item number; choices are 1, 2, 3");
        char  flag ='y';
        do {
            Scanner input = new Scanner(System.in);
            showMenu();
            ch = Integer.parseInt(input.nextLine());
            switch(ch) {
                case 1:
                    System.out.println("Actually in cart: " + cart.totalNumberOfItems() + " items");
                    System.out.println("Your Cart contains following lines: ");
                    addLineItemWithQuantityToCart(catalog);
                    showCart();
                    //System.out.println("siamo qui -> ch1");
                    break;
                case 2:
                    showCart();
                    System.out.println("provide itemid of the product : ");
                    System.out.println("Provide item-1, item-2, or item-3");
                    String item = input.nextLine();
                    //System.out.println("Here1 " + item);
                    System.out.println("provide number by which you want to increase the quantity: ");
                    int increment = input.nextInt();
                    System.out.println("Here1 " + increment);
                    for(LineItem lineItem : cart.listItemsInCart()) {
                        if(lineItem.getItem().equals(item)) {
                            cart.increaseItemQuantityBy(lineItem, increment);
                        }
                    }
                    System.out.println("******Quantity successfully incremented***************");
                    showCart();
                    //showMenu();
                    System.out.println("siamo qui -> ch2");
                    break;
                case 3:
                    showCart();
                    System.out.println("siamo qui -> ch2 dopo");
                    System.out.println("provide itemid of the product : ");
                    System.out.println("Provide item-1, item-2, or item-3");
                    String itemToIncrease = input.nextLine();
                    System.out.println("Here1 " + itemToIncrease);
                    System.out.println("provide number by which you want to decrease the quantity: ");
                    int decrement = input.nextInt();
                    System.out.println("Here1 " + decrement);
                    for(LineItem lineItem : cart.listItemsInCart()) {
                        if(lineItem.getItem().equals(itemToIncrease)) {
                            cart.reduceItemQuantityBy(lineItem, decrement);
                        }
                    }
                    System.out.println("******Quantity successfully decreased***************");
                    showCart();
                    //showMenu();
                    break;
                case 4:
                    System.out.println("Please Provide itemId of the product to remove from cart" );
                    System.out.println("choose item-1, or item-2, or item-3");
                    String itemid = input.nextLine();
                    LineItem lineItemToRemove = new LineItem(itemid);
                    for(LineItem lineItem : cart.listItemsInCart()) {
                        if(lineItem.getItemId().equals(itemid)) {
                            //cart.remove(lineItem);
                            lineItemToRemove.setItemId(lineItem.getItemId());
                            lineItemToRemove.setQuantity(lineItem.getQuantity());
                        }
                    }
                    cart.remove(lineItemToRemove);
                    System.out.println("========== PRODUCT " + itemid + " SUCCESSFULLY REMOVED FROM CART =========== ");
                    showCart();
                    //showMenu();

                    break;
                case 5:
                    System.out.println("========== TOTAL COST OF CART================= ");
                    System.out.println("****" + cart.totalCostOfCart() + "****************");
                    break;

                default:
                    break;

            }



        }while(flag == 'y');

    }

    private void addLineItemWithQuantityToCart(List<Item> catalog) {

        Scanner input = new Scanner(System.in);
        for(Item item : catalog) {
            String itemId = item.getId();
            System.out.println("please digit the quantity desired for " + itemId);
            int pid_inner_q1 = Integer.parseInt(input.nextLine());
            LineItem lineItem = new LineItem(itemId, pid_inner_q1);
            cart.addItem(lineItem);
            System.out.println("Total Number Of Items actually in cart: " + cart.totalNumberOfItems());
        }
    }

    private void displayStoreProducts() {

        for(Item item: catalog) {
            System.out.print("id: " + item.getId());
            System.out.print(" name: " + item.getName());
            System.out.print(" price: " + item.getPrice());
            System.out.println();
        }
    }
/*
    private void addLineItemToCart() {
        int pid = getUserInput();
        switch (pid) {
            case 1:
                cart.addItem();
        }
    }

    */


    private void showCart() {
        int count = 0;
        System.out.println("==============YOUR CART CONTAINS FOLLOWING LINES =================");
        for(LineItem lineItem : cart.listItemsInCart()) {
            count = count + 1;
            System.out.println( "Line item number: " + count);
            System.out.print("Name: " + lineItem.getName());
            System.out.print("||");
            System.out.print("Price: " + lineItem.getPrice());
            System.out.print("||");
            System.out.print("Quantity: " + lineItem.getQuantity());
            System.out.println(" ");
        }
        System.out.println("==============END OF YOUR CART CONTENT =================");
    }

    private int getUserInput() throws NumberFormatException {
        Scanner in = new Scanner(System.in);
        ch = Integer.parseInt(in.nextLine());
        return ch;
    }

    private String getStringInput() {
        Scanner in = new Scanner(System.in);
        str = in.nextLine();
        return null;
    }

    private void showMenu() {
        System.out.println("==============YOUR MENU =================");
        System.out.println("Please select one of the following options:");
        System.out.println("1. Add Item to cart ");
        System.out.println("2: Increase quantity for item in shopping cart ");
        System.out.println("3: Increase quantity for item in shopping cart ");
        System.out.println("4. Remove To remove Item in cart ");
        System.out.println("5. Get total cost of cart");
        System.out.println("==============END OF MENU =================");
    }





}
