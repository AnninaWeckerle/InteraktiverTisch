package de.hdm.dropboxConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.bluetooth.*;

/**
 * Diese Klasse sucht nach sichtbaren Bluetooth-Geraeten in der Umgebung. Diese listet
 * er namentlich auf, sodass die Nutzer durch ihren oeffentlichen Bluetooth-Namen identifiziert
 * werden koennen.
 */
public class RemoteDeviceDiscovery {
	
	 public static final Vector/*<RemoteDevice>*/ devicesDiscovered = new Vector();
	 public static final List<String> teilnehmerliste = new ArrayList<String>();
	 
	 public static List<String> zeigeTeammitglieder() {
	    return teilnehmerliste;
     }
	 
	 
	    public static void main(String[] args) throws IOException, InterruptedException {
	    	
	        final Object inquiryCompletedEvent = new Object();
	        devicesDiscovered.clear();
	        
	        DiscoveryListener listener = new DiscoveryListener() {
	        	
	            public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
	                System.out.println("Device " + btDevice.getBluetoothAddress() + " found");
	                
	                devicesDiscovered.addElement(btDevice);
	                
	                try {
	                    System.out.println("     name " + btDevice.getFriendlyName(false));
	                    
	                    String benutzername = btDevice.getFriendlyName(false);
	                    
	                    teilnehmerliste.add(benutzername);
	                    
	                } catch (IOException cantGetDeviceName) {
	                }
	            }
	          

				public void inquiryCompleted(int discType) {
	                System.out.println("Device Inquiry completed!");
	                
	                synchronized(inquiryCompletedEvent){
	                    inquiryCompletedEvent.notifyAll();
	                }
	            }
	            
	            public void serviceSearchCompleted(int transID, int respCode) {
	            }
	            
	            public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
	            }
	        };
	        
	        
	        synchronized(inquiryCompletedEvent) {
	        	
	            boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent()
	            		.startInquiry(DiscoveryAgent.GIAC, listener);
	            
	            if (started) {
	                System.out.println("wait for device inquiry to complete...");
	                inquiryCompletedEvent.wait();
	                System.out.println(devicesDiscovered.size() +  " device(s) found");
	            }
	        }
	    }
}