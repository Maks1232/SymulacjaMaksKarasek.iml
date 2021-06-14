import Entities.Characters.Human;
import Entities.Characters.Moves;
import Entities.Characters.PlayerCharacter;
import Entities.Characters.Zombie;
import Entities.Entity;
import Entities.Items.Item;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Publiczna klasa Game tworząca symulację
 */
public class Game {

    private final GameLogic gameLogic;
    private final Entity[][] board;
        int height;
        int width;
    List<List> results= new ArrayList<>();


    /**
     * Konstruktor wyznaczający mapę oraz rozmieszający w sposób losowy obiekty w fazie początkowej
     * @param entities lista przechowująca obiekty początkowe, zadane w klasie main, mające znaleźć się na mapie
     * @param height przechowuje wartość wysokości mapy
     * @param width przechowuje wartość szerokości mapy
     */
    public Game(List<Entity> entities, int height, int width){

        this.height= height;
        this.width= width;
        this.gameLogic= new GameLogic();
        board= new Entity[height][width];
        for (Entity entity:entities) {
           int x= ThreadLocalRandom.current().nextInt(0,height);
           int y= ThreadLocalRandom.current().nextInt(0,width);
            while(board[x][y]!=null){
                x= ThreadLocalRandom.current().nextInt(0,height);
                y= ThreadLocalRandom.current().nextInt(0,width);
            }
            board[x][y]=entity;
        }
    }

    /**
     * Metoda makeMove definiuje poruszanie postaci
     * @param player obiekt typu Zombie/Human
     * @param x współrzędna wysokości mapy
     * @param y współrzędna szerokości mapy
     * @return zwraca parę liczb typu Integer na podstawie których odbywa się ruch postaci
     */
    private Pair<Integer, Integer> makeMove(PlayerCharacter player, int x, int y){
        Moves move= player.move();
        int newX=x;
        int newY=y;

        switch(move){
            case UP: {
                newY++;
                break;
            }
            case DOWN: {
                newY--;
                break;
            }
            case RIGHT: {
                newX++;
                break;
            }
            case LEFT: {
                newX--;
                break;
            }
        }
        if(newY<0 || newY>=this.height) newY=y;
        if(newX<0 || newX>=this.width) newX=x;
        return new Pair<Integer, Integer>(newX, newY);

    }

    /**
     * Metoda tick wykonuje interakcje na mapie, porusza obiektami
     */
    public void tick(){

        for (int x=0;x<this.width;x++){
            for (int y=0;y<this.height;y++){
                 Entity entity =board[x][y];
                 if(entity==null)
                     continue;
                if (entity instanceof PlayerCharacter) {
                     PlayerCharacter character= (PlayerCharacter)entity;

                     Pair<Integer, Integer> cords= this.makeMove(character,x,y);
                    int newX= cords.getKey();
                    int newY= cords.getValue();
                    if(newX==x && newY==y) continue;
                    MoveResults result = this.gameLogic.checkAction(character, board[newX][newY]);
                    switch(result){
                        case STAY:{
                            continue;

                        }
                        case MOVE:{
                            board[newX][newY]= entity;
                            board[x][y]=null;
                            break;
                        }
                        case KILL_ATTACKER:{
                            board[x][y]=null;
                            break;
                        }
                        case KILL_VICTIM:{
                            board[newX][newY]=null;
                            break;
                        }
                        case PICK_UP_WEAPON:{
                            board[x][y]=null;
                            Entity item= board[newX][newY];
                            Human human = (Human) character;
                            human.item= (Item)item;
                            board[newX][newY]= human;
                            break;
                        }
                        case INFECT_VICTIM:{
                            board[newX][newY]=new Zombie(5);
                            Zombie zombie= (Zombie) character;
                            zombie.lifeTime+= 5;
                            break;
                        }
                        case INFECT_ATTACKER:{
                            board[x][y]=new Zombie(5);
                            Zombie zombie= (Zombie) board[newX][newY];
                            zombie.lifeTime+= 5;
                            break;
                        }
                    }
                }


            }

        }

    }

    /**
     * Metoda show() rysuje mape, postaci, przedmioty oraz zlicza liczbę wszystkich obiektów na mapie i zapisuje do listy "results"
     */
    public void show() {

        int zombiecounter=0;
        int humancounter=0;

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {

                if(board[x][y]==null) System.out.print(".");
                else System.out.print(board[x][y].draw());
                if(board[x][y] instanceof Zombie) zombiecounter++;
                if(board[x][y] instanceof Human) humancounter++;
            }
            System.out.println();
        }
        System.out.println("Liczba zombie: "+ zombiecounter);
        System.out.println("Liczba ludzi: "+ humancounter);

        List<String> results= new ArrayList<>();
        results.add("Zombie: "+ zombiecounter);
        results.add("Human: "+ humancounter);
        this.results.add(results);


    }

    /**
     * Metoda save() wykonuje zapis listy "results" do pliku "a.txt."
     * Generuje wyjątek gdy nie powiedzie się zapis do pliku
     */
    public void save(){
        try{
            FileWriter fw=new FileWriter("a.txt");
            fw.write(String.valueOf(results));
            fw.close();
        }catch(Exception e){System.out.println(e);}
        System.out.println("Data saved!");

    }

}


