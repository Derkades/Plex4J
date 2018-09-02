package xyz.derkades.plex4j.library.item;

public class Track extends LibraryItem {
	
	private Album album;
	private String title;
	private String file;
	private String artistName;
	private String year;
	
	public Track(String key, Album album, String title, String file, String artistName, String year){
		super(album.getLibrary(), key);
		
		this.album = album;
		this.title = title;
		this.file = file;
		this.artistName = artistName;
		this.year = year;
	}
	
	public Album getAlbum() {
		return album;
	}
	
	public String getArtistName() {
		return artistName;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getYear() {
		return year;
	}
	
	public String getFile() {
		return file;
	}

}
