package de.hdm.interaktiverTisch;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;

public class DropboxListFilesUpload {
	
	public static void main(String[] args) {
		
		try {
			
			DbxRequestConfig config = new DbxRequestConfig("InteraktiverTisch", 
					Locale.getDefault().toString());
			
			DbxClient client = new DbxClient(config, 
					"RG2gELbsdUAAAAAAAAAK0sKSIFQ0eO_D39GKnGfCdROqeQ8erYFewAcCXT-p3RJ6");
			
			String path = "C://Users/annina/Desktop/Gruppenarbeit_Lokal";
			
			
			File directory = new File(path);
			File[] filesToUpload = directory.listFiles();
			
			for (File file: filesToUpload) {
				if (file.isFile()) {
					
					File fileNeu = new File(path, file.getName());
					
					FileInputStream fileInputStream = new FileInputStream(fileNeu);

					client.uploadFile("/Gruppenarbeit_User/" + file.getName(), DbxWriteMode.add(), 
							file.length(), fileInputStream);

					System.out.println("Eine Datei wurde hochgeladen!");
					
					fileInputStream.close();
				}


				else if (file.isDirectory()) {
					
					String path2 = path + "/" + file.getName();
					
					client.createFolder("/Gruppenarbeit_User/" + file.getName());

					System.out.println("Es wurde ein neuer Unterordner angelegt.");
					
					
					File directory2 = new File(path2);
					
					File[] filesToUpload2 = directory2.listFiles();
					System.out.println(filesToUpload2.toString());
					
					for (File file2: filesToUpload2) {
						
						File fileNeu2 = new File(path2, file2.getName());
						System.out.println(fileNeu2);
						
						FileInputStream fileInputStream2 = new FileInputStream(fileNeu2);

						client.uploadFile("/Gruppenarbeit_User/" + file.getName() + "/" + file2.getName(), DbxWriteMode.add(), 
								file2.length(), fileInputStream2);

						System.out.println("Eine Datei wurde in den Unterordner hochgeladen!");
						
						fileInputStream2.close();
					}
			    }
			}

			System.out.println("Die Dateien aus dem Ordner Gruppenarbeit_Lokal wurden hochgeladen.");
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
