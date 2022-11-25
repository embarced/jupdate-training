package jupdate.main;

import jupdate.util.StringUtil;

import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        logger.info(StringUtil.flip("!dlroW olleH"));
        throw new RuntimeException("testen der Versionsnummer");
    }
}