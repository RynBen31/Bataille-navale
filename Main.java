package com.company;

import java.util.Vector;
import java.util.Scanner;

import static java.lang.System.exit;

//for storing coordinates for debug mode
class Coordinates{
    int x;
    int y;

    Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();
        int choice = menu.gameMenu();
        Game game;
        while(choice > 0 && choice < 4) {
            if (choice == 1) {
                game = new Game();
                game.startGame();
            }
            //help
            else if (choice == 2) {
                Game.Help();
            }
            //exit
            else if (choice == 3) {
                exit(1);
            }
            choice = menu.gameMenu();
        }

    }
}