package jupdate.main;

import jupdate.util.StringUtil;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {

        private static Logger logger = Logger.getLogger(Main.class.getName());

        public static void main(String[] args) throws IOException {
                logger.info(StringUtil.flip("!dlroW olleH"));
        }
}