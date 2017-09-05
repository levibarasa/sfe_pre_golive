package com.sfe.prop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SFEProp {

    Properties prop;

    public SFEProp() {
    }

    public Properties getDBProperty() {
        prop = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            InputStream url = classLoader.getResourceAsStream("com/sfe/prop/SFE.properties");
            prop.load(url);
        } catch (IOException asd) {
            System.out.println(asd.getMessage());
        }
        return prop;
    }
}
