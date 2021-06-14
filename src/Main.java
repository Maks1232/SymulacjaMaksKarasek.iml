import Entities.Characters.Human;
import Entities.Characters.Zombie;
import Entities.Entity;
import Entities.Items.Gun;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Publiczna klasa Main służąca do zadania parametrów początkowych symulacji oraz jej uruchomienia
 * @author Maksymilian Karasek
 * throws InterruptedException jest generowany, gdy wątek zostanie przerwany, gdy czeka, śpi lub jest zajęty
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {

        /**
         * Ustawienia początkowe
         * @param cycles ustawienie liczby cykli symulacji
         * @param list ustawianie typu i liczby obiektów początkowych, które są dodawane do listy a następnie rozmieszczane na mapie
         */
        int cycles=20;
        List<Entity> list= new ArrayList<>();
        list.add(new Zombie(5));
        list.add(new Zombie(5));
        list.add(new Human());
        list.add(new Human());
        list.add(new Human());
        list.add(new Gun());
        list.add(new Gun());

/**
 * Tworzenie obiektu klasy Game
 * @param height wysokość tablicy na której rozegra się akcja
 * @param width szerokość tablicy na której rozegra się akcja
 */
        Game game= new Game(list,10,10);
        game.show();
/**
 * Wywoływanie kolejnych cykli oraz metod wraz z formatowaniem wyświetlania
 */
        for(int i=1;i<cycles+1;i++){
            TimeUnit.SECONDS.sleep(1);
            System.out.println("");
            for(int clear = 0; clear < 1000; clear++)
            {
                System.out.println("\b") ;
            }
            game.tick();
            game.show();
        }
        /**
         * Wywołanie metody save() w celu zapisu danych z każdego etapu symulacji do pliku a.txt
         */
            game.save();
    }
}
