public class Submarine extends Ship {
    public int numberTorpedos;

    public int getNumberTorpedos() {
        return numberTorpedos;
    }

    public void setNumberTorpedos(int numberMissile) {
        this.numberTorpedos = numberMissile;
    }

    public void setNumberTorpedos(String numberTorpedos) {
        try {
            this.numberTorpedos = Integer.parseInt(numberTorpedos);
        } catch (Exception e) {
            this.numberTorpedos = 2;
        }
    }
}
