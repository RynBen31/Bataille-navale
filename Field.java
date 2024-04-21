package com.company;
import java.util.Random;

//class for the field which contains grids
public class Field {
    // create instance of Random class
    Random rand = new Random();
    Grid field[][] = new Grid[15][15]; //creating a 15 x 15 grid field

    //setting up the grids for field
    //constructor
    Field(){
        //initializing the field
        for (int i=0; i<15; i++){
            for(int j=0; j<15;j++){
                field[i][j] = new Grid(i, j);
            }
        }

        //total occupied coordinates
        //7 + 10 + 9 + 4  = 30

        //placing the ships
        //1 battleship
        placingShips(new BattleShip());
        //2 cruisers
        placingShips(new Cruiser());
        placingShips(new Cruiser());
        //3 destroyer
        placingShips(new Destroyer());
        placingShips(new Destroyer());
        placingShips(new Destroyer());
        //4 submarine
        placingShips(new Submarine());
        placingShips(new Submarine());
        placingShips(new Submarine());
        placingShips(new Submarine());

    }

    //========= for printing grid ==========
    public void printField(Grid field[][]){
        //for printing coordinate numbers horizontally
        for (int l=0; l<15; l++ ){
            if (l == 0)
                System.out.print("  ");
            if(l > 9){
                System.out.print(l + " ");
            }
            else
                System.out.print(" " + l + " ");

        }
        System.out.println();
        for (int i=0; i<15; i++){
            System.out.println("  _____________________________________________");

            for (int j=0; j<15; j++){
                if( j == 0) {
                    if(i<10) {
                        System.out.print(i + " ");
                    }
                    else{
                        System.out.print(i);
                    }
                }
                if (field[i][j].hit == false)
                    System.out.print("| |"); //if not hit
                else if(field[i][j].hit == true){
                    if(field[i][j].ship != null)
                        System.out.print("|x|"); //if hit and ship there
                    else System.out.print("|H|"); // if hit and no ship there
                }
            }
            System.out.println(); // for next line
        }
    }

    //========== for ship placement by computer ==============
    //place ships randomly done by computer
    public void placingShips(Ship ship){

        boolean shipPlaced = false;
        int x, y; //random coordinates for ship placing
        int horizontal; //if 0 then place vertical else if 1 place horizontal
        while (!shipPlaced){
            x = rand.nextInt(15);
            y = rand.nextInt(15);
            horizontal = rand.nextInt(2);

            //check if spot is empty to place the ship
            if(this.field[x][y].ship == null){
                //case for if ship length is 1 then place directly on the spot
                if (ship.length == 1){
                    this.field[x][y].ship = ship;
                    shipPlaced = true;
                }
                //case for length more than 1
                else{
                    //check if to place ship horizontally
                    if(horizontal == 1){
                        //first check horizontal
                       if (checkHorizontalPlacement(x, y, ship.length)){
                           placeShip(x, y, ship.length, "horizontal", ship);
                           shipPlaced = true;
                       }
                       //if no then place vertical
                       else if (checkVerticalPlacement(x, y, ship.length)){
                           placeShip(x, y, ship.length, "vertical", ship);
                           shipPlaced = true;
                       }
                    }
                    //check to place vertical
                    else{
                        //first check for vertical placement
                        if (checkVerticalPlacement(x, y, ship.length)){
                            placeShip(x, y, ship.length, "vertical", ship);
                            shipPlaced = true;
                        }
                        //then for horizontal placement
                        else if (checkHorizontalPlacement(x, y, ship.length)){
                            placeShip(x, y, ship.length, "horizontal", ship);
                            shipPlaced = true;
                        }
                    }
                }
            }
        }

    }

    //check for horizontal placement of ship
    public boolean checkHorizontalPlacement(int x, int y, int length){
        boolean check = true; //default
        if(y + (length - 1) < 15) {
            for (int i = y; i < (y + length); i++) {
                if (this.field[x][i].ship != null) {
                    check = false;
                    break;
                }
            }
        }
        else {check = false;}
        return check;
    }

    //check for vertical placement of ship
    public boolean checkVerticalPlacement(int x, int y, int length){
        boolean check = true; //default
        if(x + (length - 1) < 15) {
            for (int i = x; i < (x + length); i++) {
                if (this.field[i][y].ship != null) {
                    check = false;
                    break;
                }
            }
        }
        else {check = false;}
        return check;
    }

    //placing ship to begin the game
    public void placeShip(int x, int y, int length, String placement, Ship ship){
        if (placement.equalsIgnoreCase("horizontal")){
            for (int i = y; i < (y + length); i++){
                this.field[x][i].ship = ship;
            }
        }
        else{
            for (int i = x; i < (x + length); i++){
                this.field[i][y].ship = ship;
            }
        }

    }

    //========== for players hitting ==============
    //checking coordinates
    public boolean validCoordinates(int x, int y){
        if((x >= 0 && x < 15) && (y >= 0 && y < 15)){
            return true;
        }
        return false;
    }

    //check if grid is already hit
    public boolean checkAlreadyHit(int x, int y){
        if(this.field[x][y].hit == true){
            return true;
        }

        return false;
    }

    //check if there is ship on the coordinate
    public Ship checkShipOnGrid(int x, int y){
            return this.field[x][y].ship;
    }

    public void hitShip(int x, int y){
        this.field[x][y].ship.hit = true;
        this.field[x][y].hit = true;
    }



}

//each block on battleField
class Grid{
    int x; //x coordinate
    int y; //y coordinate
    Ship ship; //holding the ship
    boolean hit; // to check if already hit

    //constructor
    Grid(int x, int y){
        this.x = x;
        this.y = y;
        this.ship = null; //default
        this.hit = false;
    }

    //setters
    public void setShip(Ship ship){
        this.ship = ship;
    }

}