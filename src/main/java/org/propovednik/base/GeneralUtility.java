package org.propovednik.base;

public class GeneralUtility {

    private static String OS = System.getProperty("os.name").toLowerCase();

    /**
     * @return Returns true if operating system is Windows, otherwise return false.
     */
    public static boolean isWindows() {
        System.out.println("CALLED: isWindows()");
        boolean isWindows = (OS.indexOf("win") >= 0);
        System.out.println("isWindows: " + isWindows);
        return isWindows;
    }

    /**
     * @return Returns true if operating system is Mac OS X, otherwise return false.
     */
    public static boolean isMac() {
        System.out.println("CALLED: isMac()");
        boolean isMac = (OS.indexOf("mac") >= 0);
        System.out.println("isMac: " + isMac);
        return isMac;
    }

    /**
     * @return Returns true if operating system is Unix like, otherwise return false.
     */
    public static boolean isUnix() {
        System.out.println("CALLED: isUnix()");
        boolean isUnix = (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
        System.out.println("isUnix: " + isUnix);
        return isUnix;
    }
}
