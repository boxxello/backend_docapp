package com.docapp.Utils;

import java.io.IOException;
import java.util.logging.Logger;

public class PropsNotFoundException extends IOException {
    static java.util.logging.Logger logger = Logger.getLogger(PropsNotFoundException.class.getName());

    public PropsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropsNotFoundException(Throwable cause) {
        super(cause);
    }

    public PropsNotFoundException(String s) {
        super(s);
    }
    public PropsNotFoundException() {
        super("Erore, props email non trovate");
    }
}
