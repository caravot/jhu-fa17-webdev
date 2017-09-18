public class Aircraft implements Contact {
    int altitude;

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public void setLength(int i) {

    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void setSpeed(int i) {

    }

    @Override
    public void setSpeed(String s) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String s) {

    }

    @Override
    public Contact getType() {
        return null;
    }

    @Override
    public void setType(String s) {

    }
}
