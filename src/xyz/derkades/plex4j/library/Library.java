package xyz.derkades.plex4j.library;

import xyz.derkades.plex4j.Server;

public class Library {
	
	private Server server;
	private String title;
	private int id;
	private String libraryType;
	
	public Library(Server server, int id, String title, String libraryType){
		this.server = server;
		this.title = title;
		this.id = id;
		this.libraryType = libraryType;
	}
	
	public Server getServer() {
		return server;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLibraryType() {
		return libraryType;
	}

}
