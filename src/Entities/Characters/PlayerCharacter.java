package Entities.Characters;

import Entities.Entity;

/**
 * Interfejs PlayerCharacter rozszerza interfejs Entity
 * W interfejsie PlayerCharacter jest deklarowana metoda move() typu Moves
 */

public interface PlayerCharacter extends Entity {
     Moves move();
}
