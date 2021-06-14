package Entities.Items;

import Entities.Entity;

/**
 * Publiczny interfejs rozszerzający interfejs Entity
 * W interfejsie Item jest deklarowana metoda pickUp() typu void
 */
public interface Item extends Entity {

    void pickUp();

}
