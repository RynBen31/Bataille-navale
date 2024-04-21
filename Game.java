package com.company;
import java.util.Scanner;
import java.util.Vector;

public class Game {
    Scanner input = new Scanner(System.in); //input scanner object
    Player player;
    Field field;
    int highscore;

    public void startGame(){
        player = initializeGame();
        playGame(player, field);
    }

    public Player initializeGame(){
        System.out.println("Enter the player name\n");
        String name = input.nextLine();
        System.out.println("Do you want to play in debug mode (press \"1\" for debugMode and other than \"1\" for elsewise)\n");
        int choice = input.nextInt();
        Player player = new Player(name, (choice == 1 ? true: false) );
        return player;
    }

    public boolean checkWin(Grid field[][]){
        boolean win = true;
        for (int i=0; i<15; i++){
            for (int j=0; j<15; j++){
                if((field[i][j].ship != null) && (field[i][j].hit == false)){
                    win = false;
                    break;
                }
            }
        }
        return win;
    }
    public void playGame(Player player, Field field){
        field = new Field();
        int x,y;
        Ship ship;

        //check for debugMode
        if(player.getDebugMode() == true){
            debugMode(field);
        }

        //starting game details
        System.out.println("Player Name: " + player.getName() + ",  Turns Left: " + player.getTurns() + ",  Points: " + player.getPoints());
        while (player.getWin() == false){
            System.out.println("Enter the coordinates from 15 x 15 grid to shoot missile (eg. (x, y))");
            System.out.println("Enter x coordinate");
            x = input.nextInt();
            System.out.println("Enter y coordinate");
            y = input.nextInt();

            //checking if coordinates are within the board
            if (field.validCoordinates(x, y)){
                //check if the coordinate is already hit by the user
                if(field.checkAlreadyHit(x, y)){
                    System.out.println("The coordinates are already hit by you ! \nChoose some other coordinates\n");
                }
                else{
                    //if player misses the ship
                    if(field.checkShipOnGrid(x, y) == null){
                        field.field[x][y].hit = true;
                        System.out.println("You missed the shot !!");
                    }
                    //if player hits the ship
                    else{
                        ship = field.checkShipOnGrid(x, y);
                        field.hitShip(x, y);
                        player.setPoints(ship.points + this.player.getPoints());//add points
                        System.out.println("HIT THE TARGET !!");
                        System.out.println("Ship: " + ship.name + ",   Points: " + ship.points);
                        ship = null;
                    }

                    //print grid field
                    field.printField(field.field);

                    //after 30 turns check for win since only after that a player can win as
                    //all ship lengths sum upto thirty
                    if(player.getTurns() >= 29){
                        player.setWin(checkWin(field.field));
                    }

                    player.setTurns(player.getTurns()+1);//increment turn
                }
            }
            else{
                System.out.println("Not valid coordinates !!");
            }
            //show game details
            if(player.getWin() == true){
                System.out.println("You won the Game !!");
                //System.out.println("Your Score: " + player.getPoints());
                System.out.println("Your Turns : " + player.getTurns());
                break;
            }
            else{
                System.out.println("Player Name: " + player.getName() + ",  Turn: " + player.getTurns());
            }
        }
        if(player.getTurns() == 0){
            System.out.println("You lost the game !!");
        }

    }


    //for debug mode
    public void debugMode(Field field){
        Vector<Coordinates> cruiserCoord = new Vector<Coordinates>();
        Vector<Coordinates> battleshipCoord = new Vector<Coordinates>();
        Vector<Coordinates> submarineCoord = new Vector<Coordinates>();
        Vector<Coordinates> destroyerCoord = new Vector<Coordinates>();

        //temp coordinates
        for(int i=0; i<15; i++){
            for(int j=0; j<15; j++){
                if(field.field[i][j].ship != null) {
                    if (field.field[i][j].ship.name.equalsIgnoreCase("Cruiser")) {
                        Coordinates xy = new Coordinates(i, j);
                        cruiserCoord.add(xy);
                    } else if (field.field[i][j].ship.name.equalsIgnoreCase("Battleship")) {
                        Coordinates xy = new Coordinates(i, j);
                        battleshipCoord.add(xy);
                    } else if (field.field[i][j].ship.name.equalsIgnoreCase("Submarine")) {
                        Coordinates xy = new Coordinates(i, j);
                        submarineCoord.add(xy);
                    } else if (field.field[i][j].ship.name.equalsIgnoreCase("Destroyer")) {
                        Coordinates xy = new Coordinates(i, j);
                        destroyerCoord.add(xy);
                    }
                }
            }
        }

        //print out for debug mode
        System.out.println("FOR DEBUG MODE SHIP POSITIONS");

        System.out.print("Cruiser     ");
        for(int i=0; i<cruiserCoord.size(); i++){
            System.out.print("(" + cruiserCoord.get(i).x + "," + cruiserCoord.get(i).y + ")");
        }
        System.out.println();
        System.out.print("Battleship           ");
        for(int i=0; i<battleshipCoord.size(); i++){
            System.out.print("(" + battleshipCoord.get(i).x + "," + battleshipCoord.get(i).y + ")");
        }
        System.out.println();
        System.out.print("Submarine            ");
        for(int i=0; i<submarineCoord.size(); i++){
            System.out.print("(" + submarineCoord.get(i).x + "," + submarineCoord.get(i).y + ")");
        }
        System.out.println();
        System.out.print("Destroyer            ");
        for(int i=0; i<destroyerCoord.size(); i++){
            System.out.print("(" + destroyerCoord.get(i).x + "," + destroyerCoord.get(i).y + ")");
        }
        System.out.println();

    }

    //help in game
    public static void Help(){
        System.out.println("*********************************");
        System.out.println("Game Rules are as follow \n");
        System.out.println("This is a ship destroying game between human player and computer");
        System.out.println("There is a 15x15 grid on which ships are to be placed either horizontally or vertically.\n." +
                "Each player has a fleet of 10 ships: 1 battleship, 2 cruisers, 3 destroyers, 4 submarines.\n" +
                "Human player starts the game followed by each player gets the turn alternatively.\n" +
                "In the turn either player can move his ship with one box or hit the opponent player.\n" +
                "Once the ship is hit it can't be moved anymore.\n" +
                "Following are the ship details format: shipName(number of ships, length of ship, damage of ship)\n" +
                "Battleship(1, 7, 9), Cruiser(2, 5, 4), Destroyer(3, 3, 1), Submarine(4, 1, 1)\n" +
                "Grid description:\n" +
                "\"| |\" Empty grid means no hit on that place\n" +
                "\"|H|\" on grid means hit but no ship on that place\n" +
                " \"|x|\" on grid means hit and ship was on that place\n");
    }

}
