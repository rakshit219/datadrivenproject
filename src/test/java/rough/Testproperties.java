package rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import utilities.MonitoringMail;
import utilities.TestConfig;

public class Testproperties {

	public static void main(String[] args) throws IOException, AddressException, MessagingException {
		Properties config=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis);
		//System.out.println(config.getProperty("browser"));
		Properties or=new Properties();
		FileInputStream fis2=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
		or.load(fis2);
		
		Date d=new Date();
		System.out.println(d.toString().replace(" ", "_").replace(":","_")+".jpg");
		
		MonitoringMail mail=new MonitoringMail();
		String messageBody=
				InetAddress.getLocalHost().getHostAddress();
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
	}

}
