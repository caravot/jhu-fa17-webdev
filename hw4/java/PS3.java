public class PS3 extends Aircraft {
    int numberEngines;

    public int getNumberEngines() {
        return numberEngines;
    }

    public void setNumberEngines(int numberEngines) {
        this.numberEngines = numberEngines;
    }

    @Override
    public String toString() {
        return "Aircraft Type: PS3\n\t" +
                "Number of Engines: " + getNumberEngines() +
                "\n\t" + super.toString();
    }
}
