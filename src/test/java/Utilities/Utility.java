package Utilities;

import java.io.*;
import java.util.Properties;

public class Utility {

    private static Properties prop = new Properties();
	private static String config_filepath = System.getProperty("user.dir")+ "/src/test/resources/config/configuration.properties";
    
    public static void loadPropertyFile() {
    	
    	try {
			prop.load(new FileInputStream(config_filepath));
		} catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
	public static String getProperty(String key) {
		loadPropertyFile();
		if(prop.getProperty(key).equals(null))
		{
			System.out.println("Property not found");
			System.exit(0);
		}
		return prop.getProperty(key);
	}
}
