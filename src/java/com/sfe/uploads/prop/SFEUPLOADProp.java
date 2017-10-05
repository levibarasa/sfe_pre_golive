package com.sfe.uploads.prop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SFEUPLOADProp {

    Properties prop;

    public SFEUPLOADProp() {
    }

    public Properties getDBProperty() {
        prop = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            InputStream url = classLoader.getResourceAsStream("com/sfe/uploads/prop/SFEUPLOAD.properties");
            prop.load(url);
        } catch (IOException asd) {
            System.out.println(asd.getMessage());
        }
        return prop;
    }
}
