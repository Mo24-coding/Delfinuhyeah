package utility;

public class Colors {

    // Funny Funny stuff

    public static String red(String text) {
        return "\u001B[31m" + text + "\u001B[0m";
    }

    public static String blue(String text) {
        return "\u001B[34m" + text + "\u001B[0m";
    }

    public static String green(String text) {
        return "\u001B[32m" + text + "\u001B[0m";
    }

    public static String yellow(String text) {
        return "\u001B[33m" + text + "\u001B[0m";
    }

    public static String cyan(String text) {
        return "\u001B[36m" + text + "\u001B[0m";
    }
}
