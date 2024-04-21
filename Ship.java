package com.company;

public class Ship {
    //All ship types as enums
    //BATTLESHIP (7, 9, 1, "Battleship"),
    //CRUISER (5, 4, 1, "Cruiser"),
    //DESTROYER (3, 1, 1, "Destroyer"),
    //SUBMARINE (1, 1, 1, "Submarine");

    //properties for ships
    public final int length; //ship length
    public final int damage; //ship damage
    public final int points; //points for ship
    public final String name; //name of ship
    public boolean hit; //if the ship is hit or not

    //constructor for the enum class
    public Ship(int length, int damage, int points, String name){
        this.length = length;
        this.damage = damage;
        this.points = points;
        this.name = name;
        this.hit = false;
    }

}

class BattleShip extends Ship{
    BattleShip(){
        super(7, 9, 1, "Battleship");
    }
}

class Cruiser extends Ship{
    Cruiser(){
        super(5, 4, 1, "Cruiser");
    }
}

class Destroyer extends Ship{
    Destroyer(){
        super(3, 1, 1, "Destroyer");
    }
}

class Submarine extends Ship{
    Submarine(){
        super(1, 1, 1, "Submarine");
    }
}