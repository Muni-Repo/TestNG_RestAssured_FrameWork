package com.pets.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class Common_Utilities {
	
	//========================================================================================
	//Read the values from the property file
	public static String getpropertyvalue(String keyvalue, String FileName) throws IOException {
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(FileName));
			
		} catch (FileNotFoundException e ) {
			e.printStackTrace();
		}
		return prop.getProperty(keyvalue);
	}
	
	//========================================================================================
	//Loading the files
	public static String generateStringFromResource(String Path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(Path)));
	}
	
	//========================================================================================
	//Update runtime values into properties file
	public static void updateTestData(String Filename, String key, String IDValue) {
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(Filename));
			prop.setProperty(key, IDValue);
			prop.save(new FileOutputStream(Filename), "");
			prop.store(new FileOutputStream(Filename), "");
			prop.clone();		
		} catch (FileNotFoundException e ) {
			e.printStackTrace();
		} catch (IOException e ) {
			e.printStackTrace();
		}	  
	}
	
	//========================================================================================
	
}
