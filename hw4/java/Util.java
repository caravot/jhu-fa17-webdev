public class Util {
    public static int validateInteger(int n) {
        if (n > 0) {
            return n;
        }

        System.out.println("Only positive whole numbers accepted. Setting to value to 0.");
        return 0;
    }
}
