package vmart.application;

public class Main extends Thread {

    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.readFiles();
        shop.preferences();
    }
}
