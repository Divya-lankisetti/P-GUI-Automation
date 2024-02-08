package config;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileManager {

	private static Properties elementsProperties;
	 

	public static void assignFileName(String fileToBeUsedToFindSomething) {
		try {
			elementsProperties = new Properties();
			FileInputStream fileInputStream = new FileInputStream("src/test/resources/locatorsProp/"+fileToBeUsedToFindSomething);
			elementsProperties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
			// Handle the exception appropriately (e.g., log it, throw a custom exception, etc.)
		}
	}
	public static String getLocator(String key) {
		return elementsProperties.getProperty(key);
	}

	




}