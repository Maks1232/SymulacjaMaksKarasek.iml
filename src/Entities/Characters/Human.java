package Entities.Characters;

import Entities.Items.Item;

import java.util.concurrent.ThreadLocalRandom;

public class Human implements PlayerCharacter {

    public Item item= null;

    /**
     * Nadpisana metoda move() implementowana z interfejsu PlayerCharacter
     *
     * @return zwraca liczbę losową na podstawie której wykonywany jest ruch postaci
     */
    @Override
    public Moves move() {
        return Moves.values()[ThreadLocalRandom.current().nextInt(0, 4)];
    }

    /**
     * Nadpisana metoda draw() implementowana z interfejsu Entity
     *
     * @return zwraca pojedyńczy znak typu String, który jest umieszczany w odpowiednim miejscu na mapie
     */
    @Override
    public String draw() {
        return "H";
    }
}
