package de.hdm.interaktiverTisch;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxRequestConfig;

public class DropboxListFilesDownload {
	
	public static void main(String[] args) {
		
		try {
			
			DbxRequestConfig config = new DbxRequestConfig("InteraktiverTisch", 
					Locale.getDefault().toString());
			
			DbxClient client = new DbxClient(config, "RG2gELbsdUAAAAAAAAAK0sKSIFQ0eO_D39GKnGfCdROqeQ8erYFewAcCXT-p3RJ6");
			
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
					
					String path2 = path + "/" + file.name;
					
					File directory = new File(path2);
					
					File[] filesToDownload = directory.listFiles();
					System.out.println(filesToDownload.toString());
					
					for (File file2 : filesToDownload) {
						System.out.println("Hallo");
						
						File fileNeu = new File(path2, file2.getName());
						System.out.println(file2.getName());
						
						FileOutputStream fileOutputStream2 = new FileOutputStream(fileNeu);
						
						System.out.println("Es wurde eine Datei dem Unterordner hinzugefügt.");
						
						fileOutputStream2.close();
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
