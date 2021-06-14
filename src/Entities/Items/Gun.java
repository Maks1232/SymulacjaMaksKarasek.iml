package Entities.Items;

public class Gun implements Item{
    /**
     * Nadpisana metoda implementowana z interfejsu Entity
     *
     * @return zwraca pojedyńczy znak typu String, który jest umieszczany w odpowiednim miejscu na mapie
     */
    @Override
    public String draw() {
        return "G";
    }

    /**
     * Nadpisana metoda implementowana z interfejsu Item
     */
    @Override
    public void pickUp() {
        System.out.println("Podniesiono broń");

    }
}
