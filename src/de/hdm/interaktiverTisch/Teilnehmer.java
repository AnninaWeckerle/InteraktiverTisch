package de.hdm.interaktiverTisch;

public class Teilnehmer {
	
	String benutzername;
	String url;
	String accessKey;
	
	public Teilnehmer(String accessKey) {
		this.accessKey = accessKey;
	}
	
	public Teilnehmer(String benutzername, String accessKey) {
		this.benutzername = benutzername;
		this.accessKey = accessKey;
	}
	
//	public Teilnehmer(String benutzername, String url) {
//		this.benutzername = benutzername;
//		this.url = url;
//	}
	
	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
