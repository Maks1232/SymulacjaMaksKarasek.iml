package Entities.Characters;

import java.util.concurrent.ThreadLocalRandom;

public class Zombie implements PlayerCharacter {

    public int lifeTime;

    /**
     * Konstruktor z parametrem czasu życia zombie
     *
     * @param lifeTime definiuje ile cykli może przeżyć obiekt typu zombie (czas życia)
     */
    public Zombie(int lifeTime){
        this.lifeTime= lifeTime;
    }

    /**
     * Nadpisana metoda move() implementowana z interfejsu PlayerCharacter
     *
     * @return zwraca liczbę losową na podstawie której wykonywany jest ruch postaci
     */
    @Override
    public Moves move() {

        lifeTime--;
        return Moves.values()[ThreadLocalRandom.current().nextInt(0, 4 )];
    }

    /**
     * Nadpisana metoda draw() implementowana z interfejsu Entity
     *
     * @return zwraca pojedyńczy znak typu String, który jest umieszczany w odpowiednim miejscu na mapie
     */

    @Override
    public String draw() {
        return "Z";
    }
}
