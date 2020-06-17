public class Score {
    private static Score s = null;

    private Score() {

    }

    public static synchronized Score getImplementation() {
        if (s == null)
            s = new Score();

        return s;
    }

    public void addScore(String name, int points) {

    }
}
