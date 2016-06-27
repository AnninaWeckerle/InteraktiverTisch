package de.hdm.dropboxConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse, die dem GUI spezielle Methoden bietet, um die anwesenden Nutzer zu identifizieren 
 * und den Down- sowie Upload der Dateien zu steuern.
 */
public class Administration {
	
	
	public static List<String> gebeTeilnehmerliste () {
		
		List<String> teilnehmerliste = new ArrayList<String>();
		
		RemoteDeviceDiscovery rdd = new RemoteDeviceDiscovery();
		
		try {
			rdd.main(null);
		
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		teilnehmerliste = rdd.zeigeTeammitglieder();
		
		System.out.println("Das sind die aktuellen Teilnehmer: ");
		
		for(int i = 0; i < teilnehmerliste.size(); i++) {
			System.out.println(teilnehmerliste.get(i));
		}
			
		return teilnehmerliste;
		}
	
	
	
	public static void upload(String name, String art, String accessKey, String path) {
		
		art = "Upload";
		path = "C://Users/annina/Desktop/Gruppenarbeit_Lokal";
		
//		if (name.equals("Dunja")) {
//			accessKey = "rXYVHDdGNSQAAAAAAAAATiHDfUw7CJa-4m_W0GzivFT9xFhmfkmatFGx1CYXfB4d";
//		}
//		
//		else if (name.equals("Annina")) {
//			accessKey = "RG2gELbsdUAAAAAAAAAK-_nMF3QJ0TuwXhRdjOv1kNJ5C4EhGDrGBdk50t2WhCnL";
//		}
//		
//		else if (name.equals("Nina")) {
//			accessKey = "z9OW9fQQQB0AAAAAAAAgrfuxAaRB0FbzZwrbMTsji_Lrl4VVX8CUrodS8-JrAduP";
//		}
		
		DropboxListFilesUpload upload = new DropboxListFilesUpload(accessKey, path);
		upload.main(null);
	}
	
	
	
	public static void download(String name, String art, String accessKey, String path) {
		
		art = "Download";
		path = "C://Users/annina/Desktop/Gruppenarbeit_Lokal";
		
//		if (name.equals("Dunja")) {
//			accessKey = "rXYVHDdGNSQAAAAAAAAATiHDfUw7CJa-4m_W0GzivFT9xFhmfkmatFGx1CYXfB4d";
//		}
//		
//		else if (name.equals("Annina")) {
//			accessKey = "RG2gELbsdUAAAAAAAAAK-_nMF3QJ0TuwXhRdjOv1kNJ5C4EhGDrGBdk50t2WhCnL";
//		}
//		
//		else if (name.equals("Nina")) {
//			accessKey = "z9OW9fQQQB0AAAAAAAAgrfuxAaRB0FbzZwrbMTsji_Lrl4VVX8CUrodS8-JrAduP";
//		}
		
		DropboxListFilesDownload download = new DropboxListFilesDownload(accessKey, path);
		download.main(null);
	}
}
	
