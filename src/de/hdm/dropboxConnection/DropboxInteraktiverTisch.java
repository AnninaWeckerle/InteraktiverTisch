package de.hdm.dropboxConnection;

import java.awt.Desktop;
import java.net.URI;
import java.util.Locale;
import java.util.Scanner;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;


/**
 * Klasse, um Verbindung mit Dropbox herzustellen und den Zugriff der App auf die 
 * persoenliche Dropbox des Nutzers zu genehmigen.
 * Diese Klasse wird derzeit nicht benoetigt, da bei dieser Applikation die Genehmigung
 * nur einmalig stattfindet und im Programm gespeichert wurde (@see Administration.java).
 */
public class DropboxInteraktiverTisch {
	
	public static void main(String[] args) {
		
		try {

				DbxAppInfo appInfo = new DbxAppInfo("eznx3qgarjzrt4v", 	
						"4gmdmgswkkkgugd");
				
				DbxRequestConfig config = new DbxRequestConfig("FlyingDocs", 
						Locale.getDefault().toString());
				
				DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
				
				Desktop.getDesktop().browse(new URI(webAuth.start()));
				
				System.out.println("Bitte geben Sie ihren Code nun unten an.");
				
				Scanner scanner = new Scanner(System.in);
	
				String code2 = scanner.next();
				
				System.out.println();
				System.out.println("Dies ist Ihr Access-Token:");
	
				String accessKey = webAuth.finish(code2).accessToken;
				System.out.println(accessKey);
				
				scanner.close();
			}

			catch (Exception ex){
				ex.printStackTrace();			
			}
	}
}