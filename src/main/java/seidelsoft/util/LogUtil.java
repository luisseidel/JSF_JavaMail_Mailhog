package seidelsoft.util;

import java.util.logging.Logger;

public class LogUtil {

    private LogUtil(){}

    public static Logger getLogger(Object obj) {
        return Logger.getLogger(obj.getClass().getName());
    }
}
