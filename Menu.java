package com.company;

import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    int gameMenu(){
        int choice = 0;
        while(choice < 1 || choice > 3) {
            System.out.println("1) Play a game");
            System.out.println("2) Help");
            System.out.println("3) Exit");
            System.out.print("Please select an option: ");
            choice = input.nextInt();
        }
        return choice;
    }

}
