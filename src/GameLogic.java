import Entities.Characters.Human;
import Entities.Characters.PlayerCharacter;
import Entities.Characters.Zombie;
import Entities.Entity;
import Entities.Items.Item;

/**
 * Publiczna klasa GameLogic odpowiada za logikę gry, definiuje interakcje między postaciami podczas rozgrywki
 */
public class GameLogic {
    /**
     * Metoda checkAction sprawdza stan postaci podczas każdego cyklu, definiuje co powinno wydarzyć się podczas spotkania
     *
     * @param attacker postać typu Zombie/Human
     * @param victim postać typu Zombie/Human
     *
     * @return zwraca interakcje jaka ma się wykonać
     */
    public MoveResults checkAction(PlayerCharacter attacker, Entity victim) {

        if (victim == null) return MoveResults.MOVE;

        if (attacker instanceof Zombie) {
            Zombie zombie = (Zombie) attacker;
            if (zombie.lifeTime == 0) return MoveResults.KILL_ATTACKER;
            if (victim instanceof Item) return MoveResults.MOVE;
            if (victim instanceof Zombie) return MoveResults.STAY;
            if (victim instanceof Human) {
                Human human = (Human) victim;
                if (human.item != null) return MoveResults.KILL_ATTACKER;
                else return MoveResults.INFECT_VICTIM;
            }


        }
        if (attacker instanceof Human) {
            if (victim instanceof Item) return MoveResults.PICK_UP_WEAPON;
            if (victim instanceof Zombie) {
                Human human = (Human) attacker;
                if (human.item != null) return MoveResults.KILL_VICTIM;
                else return MoveResults.INFECT_ATTACKER;

            }
            if (victim instanceof Human) return MoveResults.STAY;
        }


    return MoveResults.STAY;
    }
}
