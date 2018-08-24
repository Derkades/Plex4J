package xyz.derkades.plex4j.library.item;

import xyz.derkades.plex4j.library.Library;

public abstract class LibraryItem {
	
	private String key;
	private Library library;
	
	public LibraryItem(Library library, String key){
		this.library = library;
		this.key = key;
	}
	
	public Library getLibrary() {
		return library;
	}
	
	public String getKey() {
		return key;
	}

}
