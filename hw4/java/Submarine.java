public class Submarine extends Ship {
    private int numberTorpedos;

    public Submarine() {
        setType("Submarine");
    }

    public int getNumberTorpedos() {
        return numberTorpedos;
    }

    public void setNumberTorpedos(int numberTorpedos) {
        if (numberTorpedos > 0) {
            this.numberTorpedos = numberTorpedos;
        } else {
            System.out.println("Only positive whole numbers accepted");
        }
    }

    public void setNumberTorpedos(String numberTorpedos) {
        try {
            this.numberTorpedos = Integer.parseInt(numberTorpedos);
        } catch (NumberFormatException nfe) {
            System.out.println("Unable to parse the String " + "\"" + numberTorpedos + "\" into a valid integer");
            this.numberTorpedos = 2;
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tNumber of Torpedos: " + getNumberTorpedos();
    }
}
