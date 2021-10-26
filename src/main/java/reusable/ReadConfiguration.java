package reusable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfiguration {

	Properties prop=new Properties();
	
	public ReadConfiguration(){
		File src= new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Configuration\\Config.properties");		
		try {
			FileInputStream input=new FileInputStream(src);
			try {
				prop.load(input);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
		
	public String getURL(){
		String url=prop.getProperty("URL");
		return url;
	}
	public String getBrowserName(){
		String browserName=prop.getProperty("BROWSERNAME");
		return browserName;
	}
	public String getChromePath(){
		String chromepath =prop.getProperty("CHROMEEXE");
		return chromepath;
	}
	
}
