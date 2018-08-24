package xyz.derkades.plex4j.library.item;

public class Track extends LibraryItem {
	
	private Album album;
	private String title;
	private String file;
	
	public Track(String key, Album album, String title, String file){
		super(album.getLibrary(), key);
		
		this.album = album;
		this.title = title;
		this.file = file;
	}
	
	public Album getAlbum() {
		return album;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getFile() {
		return file;
	}

}
