package de.hdm.interaktiverTisch;

import java.awt.Desktop;
import java.net.URI;
import java.util.Locale;
import java.util.Scanner;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;


public class DropboxInteraktiverTisch {

	public static void main(String[] args) {
		
		try {
			DbxAppInfo appInfo = new DbxAppInfo("sgohupmfu5dj12d", 	
					"dtc9l0jpu7r30rw");
			
			DbxRequestConfig config = new DbxRequestConfig("InteraktiverTisch", 
					Locale.getDefault().toString());
			
			DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
			
			Desktop.getDesktop().browse(new URI(webAuth.start()));
			
			System.out.println("Bitte geben Sie ihren Code nun unten an.");
			
			Scanner scanner = new Scanner(System.in);

			String code = scanner.next();
			
			System.out.println();
			System.out.println("Dies ist Ihr Acces-Token:");
			System.out.println(webAuth.finish(code).accessToken);
			
			scanner.close();
		}
		
		catch (Exception ex){
			ex.printStackTrace();			
		}
	}

}