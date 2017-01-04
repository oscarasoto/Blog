package us.oscarsoto;

/**
 * @author oscarsoto on 1/4/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */
public class Dice {
    private int value;

    public int getValue() {
        return value;
    }

    Dice (){
        this.value = rollDice();
    }

   int rollDice () {
        return (int) (Math.random() * 6) + 1;
    }

}
