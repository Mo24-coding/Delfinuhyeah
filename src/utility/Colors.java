package utility;

// Funny Funny stuff - colors
public class Colors {


    // Bunch of color methods for changing string text colors

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

    public static String magenta(String text) {
        return "\u001B[35m" + text + "\u001B[0m";
    }

    public static String bold(String text) {
        return "\u001B[1m" + text + "\u001B[0m";
    }
}
