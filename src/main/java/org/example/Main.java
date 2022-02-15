package org.example;

public class Main {

    public static void main(String[] args) {

            while (true) {
                try {
                    App.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("App.run упал нахуй");
                }
                System.out.println("бот упал, щя перезапустим, сек");
            }
    }

}
