package de.hdm.dropboxConnection;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;

/**
 * Klasse, die Dateien aus dem lokalen Dropbox-Ordner "Gruppenarbeit_Lokal" auf dem 
 * PC fuer die Gruppenarbeit in den online Dropbox-Ordner des Nutzers hochlaedt.
 * Den Zugang zum spezifischen Nutzerordner erhaelt das Programm ueber den AccessKey 
 * des Nutzers, auf dessen Dropbox zugegriffen werden soll.
 */
public class DropboxListFilesUpload {
	
	static String accessKey;
	static String path;
	
	public DropboxListFilesUpload(String accessKey, String path) {
		this.accessKey = accessKey;
		this.path = path;
	}
	
	public static void main(String[] args) {
		
		try {
			
//			DbxRequestConfig config = new DbxRequestConfig("InteraktiverTisch", 
//					Locale.getDefault().toString());
			
			DbxRequestConfig config = new DbxRequestConfig("FlyingDocs", 
					Locale.getDefault().toString());
			
			DbxClient client = new DbxClient(config, accessKey);
			
			File directory = new File(path);
			File[] filesToUpload = directory.listFiles();
			
			for (File file: filesToUpload) {
				if (file.isFile()) {
					
					File fileNeu = new File(path, file.getName());
					
					FileInputStream fileInputStream = new FileInputStream(fileNeu);

//					client.uploadFile("/Gruppenarbeit_User/" + file.getName(), DbxWriteMode.add(), 
//							file.length(), fileInputStream);
					
					client.uploadFile("/" + file.getName(), DbxWriteMode.add(), 
							file.length(), fileInputStream);

					System.out.println("Eine Datei wurde hochgeladen!");
					
					fileInputStream.close();
				}


				else if (file.isDirectory()) {
					
					String path2 = path + "/" + file.getName();
					
//					client.createFolder("/Gruppenarbeit_User/" + file.getName());

					client.createFolder("/" + file.getName());
					
					System.out.println("Es wurde ein neuer Unterordner angelegt.");
					
					
					File directory2 = new File(path2);
					
					File[] filesToUpload2 = directory2.listFiles();
					System.out.println(filesToUpload2.toString());
					
					for (File file2: filesToUpload2) {
						
						if (file2.isFile() == true) {
						
						File fileNeu2 = new File(path2, file2.getName());
						System.out.println(fileNeu2);
						
						FileInputStream fileInputStream2 = new FileInputStream(fileNeu2);

//						client.uploadFile("/Gruppenarbeit_User/" + file.getName() + "/" + file2.getName(), DbxWriteMode.add(), 
//								file2.length(), fileInputStream2);
						
						client.uploadFile("/" + file.getName() + "/" + file2.getName(), DbxWriteMode.add(), 
								file2.length(), fileInputStream2);

						System.out.println("Eine Datei wurde in den Unterordner hochgeladen!");
						
						fileInputStream2.close();
						}
						
						else if (file2.isDirectory() == true) {
							
//							client.createFolder("/Gruppenarbeit_User/" + file.getName() + "/" + file2.getName());

							client.createFolder("/" + file.getName() + "/" + file2.getName());
							
							System.out.println("Es wurde ein neuer Unterordner angelegt.");
						}
					}
			    }
			}

			System.out.println("Die Dateien aus dem Ordner Gruppenarbeit_Lokal wurden hochgeladen.");
			
			DirectoryAnimation anim = new DirectoryAnimation(path, "UploadNeu");
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
