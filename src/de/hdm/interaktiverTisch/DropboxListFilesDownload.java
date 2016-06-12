package de.hdm.interaktiverTisch;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxRequestConfig;

import de.hdm.interaktiverTisch.Teilnehmer;

public class DropboxListFilesDownload {
	
	
	public static void main(String[] args) {
		
		try {
			
			DbxRequestConfig config = new DbxRequestConfig("InteraktiverTisch", 
					Locale.getDefault().toString());
			 
			Showcase showcase = new Showcase();
			Teilnehmer t = showcase.t1;
			
			DbxClient client = new DbxClient(config, t.getAccessKey());

			DbxEntry.WithChildren files = client.getMetadataWithChildren("/Gruppenarbeit_User");
			
			for (DbxEntry file : files.children) {
				
				String path = "C://Users/annina/Desktop/Gruppenarbeit_Lokal";
				
				if (file.isFolder() == false) {
					
					File fileDownloaded = new File(path, file.name);
					
					FileOutputStream fileOutputStream = new FileOutputStream(fileDownloaded);
					
					System.out.println("Eine Datei wurde heruntergeladen.");
					
					fileOutputStream.close();
				}
				
				
				else if (file.isFolder() == true) {
					
					File folder = new File(path, file.name);
					folder.mkdir();

					System.out.println("Es wurde ein neuer Unterordner angelegt.");
					
					DbxEntry.WithChildren files2 = client.getMetadataWithChildren("/Gruppenarbeit_User/" + file.name);
					
					String path2 = path + "/" + file.name;
					
					for (DbxEntry file2 : files2.children) {
						
						if (file2.isFile() == true) {
							
							File fileNeu = new File(path2, file2.name);
							
							FileOutputStream fileOutputStream2 = new FileOutputStream(fileNeu);
							
							System.out.println("Die Datei " + file2.name + " wurde dem Unterordner hinzugefügt.");
							
							fileOutputStream2.close();
							
						}
						
						else if (file2.isFolder() == true) {
							File folder2 = new File(path2, file2.name);
							folder2.mkdir();
						}
					}
				}
			} 
			
			System.out.println("Die Dateien aus dem Ordner Gruppenarbeit wurden heruntergeladen.");

		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
