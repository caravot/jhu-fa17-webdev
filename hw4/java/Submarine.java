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

    @Override
    public String toString() {
        return "Ship Type: Submarine\n\t" +
                "Number of Torpedos: " + getNumberTorpedos() +
                "\n\t" + super.toString();
    }
}
