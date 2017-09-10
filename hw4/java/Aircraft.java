public class Aircraft implements Contact {
    int length;
    int speed;
    String name;
    Contact type;
    int altitude;

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return "Aircraft name\n\t" + getName() +
                "\n\tLength of: " + getLength() +
                " with a speed of " + getSpeed() +
                " with an altitude of " + getAltitude();
//                " and is of type " + getType().toString();
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public void setLength(int i) {
        this.length = i;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(int i) {
        this.speed = i;
    }

    @Override
    public void setSpeed(String s) {
        this.speed = Integer.parseInt(s);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String s) {
        this.name = s;
    }

    @Override
    public Contact getType() {
        return this.type;
    }

    @Override
    public void setType(String s) {

    }
}
