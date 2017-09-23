public class PS3 extends Aircraft {
    int numberEngines;

    public PS3() {
        setType("PS3");
    }

    public int getNumberEngines() {
        return numberEngines;
    }

    public void setNumberEngines(int numberEngines) {
        this.numberEngines = Util.validateInteger(numberEngines);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tNumber of Engines: " + getNumberEngines();
    }
}
