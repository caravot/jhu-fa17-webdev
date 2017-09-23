import java.text.ParseException;

public class Destroyer extends Ship {
    private int numberMissile = 0;

    public Destroyer() {
        setType("Destroyer");
    }

    public int getNumberMissile() {
        return numberMissile;
    }

    public void setNumberMissile(int numberMissile) {
        this.numberMissile = Util.validateInteger(numberMissile);
    }

    public void setNumberMissile(String numberMissile) {
        try {
            int num = Integer.parseInt(numberMissile);
            this.numberMissile = Util.validateInteger(num);
        } catch (NumberFormatException nfe) {
            System.out.println("Unable to parse the String " + "\"" + numberMissile + "\" into a valid integer. Setting value to 2.");
            this.numberMissile = 2;
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tNumber of Missiles: " + getNumberMissile();
    }
}