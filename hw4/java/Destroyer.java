import java.text.ParseException;

public class Destroyer extends Ship {
    public int numberMissile;

    public int getNumberMissile() {
        return numberMissile;
    }

    public void setNumberMissile(int numberMissile) {
        this.numberMissile = numberMissile;
    }

    public void setNumberMissile(String numberMissile) {
        try {
            this.numberMissile = Integer.parseInt(numberMissile);
        } catch (Exception e) {
            this.numberMissile = 2;
        }
    }
}