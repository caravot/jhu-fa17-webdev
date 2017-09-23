public class Ship implements Contact {
    int length;
    int speed;
    String name;
    String type;

    @Override
    public String toString() {
        return "Type: " + getType() +
                "\n\tName: " + getName() +
                "\n\tLength: " + getLength() +
                "\n\tSpeed: " + getSpeed();
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public void setLength(int i) {
        this.length = Util.validateInteger(i);
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(int i) {
        this.speed = Util.validateInteger(i);
    }

    @Override
    public void setSpeed(String s) {
        try {
            int num = Integer.parseInt(s);
            this.speed = Util.validateInteger(num);
        } catch (NumberFormatException nfe) {
            System.out.println("Unable to parse the String " + "\"" + s + "\" into a valid integer. Setting value to 0.");
            this.speed = 0;
        }
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
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String s) {
        this.type = s;
    }
}
