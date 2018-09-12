package xyz.derkades.plex4j.library.item;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xyz.derkades.plex4j.Server;

public class Album extends LibraryItem {
	
	String title;
	Artist artist;
	
	public Album(String key, String title, Artist artist){
		super(artist.getLibrary(), key);
		
		this.title = title;
		this.artist = artist;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Artist getArtist() {
		return artist;
	}
	
	public List<Track> getTracks() throws IOException, SAXException {
		Server server = this.getLibrary().getServer();

		String url = String.format("%s%s?X-Plex-Token=%s", server.getBaseUrl(), this.getKey(), server.getToken());
		
		URLConnection connection = new URL(
				url)
				.openConnection();
			
		Document doc = server.getDocumentBuilder().parse(connection.getInputStream());
		Node mediaContainer = doc.getFirstChild();
		NodeList nodes = mediaContainer.getChildNodes();
			
		List<Track> tracks = new ArrayList<>();
		
		for (int i = 1; i < nodes.getLength(); i++) {
			if (i % 2 == 0) continue;
				
			Node trackNode = nodes.item(i);
				
			String type = trackNode.getAttributes().getNamedItem("type").getTextContent();
				
			if (!type.equals("track")) {
				continue;
			}
			
			String key = trackNode.getAttributes().getNamedItem("key").getTextContent();
			String title = trackNode.getAttributes().getNamedItem("title").getTextContent();
			
			String artist;
			try {
				artist = trackNode.getAttributes().getNamedItem("originalTitle").getTextContent();
			} catch (NullPointerException e){
				artist = "";
			}
			
			String year;
			try {
				year = trackNode.getAttributes().getNamedItem("year").getTextContent();
			} catch (NullPointerException e){
				year = "";
			}

			@SuppressWarnings("unused") String track = null; // TODO HOW DOES ONE GET TRACK NUMBER??
			
				
			//<Track><Media><Part file="">
			String file = trackNode.getChildNodes().item(1)
					.getChildNodes().item(1)
					.getAttributes().getNamedItem("file").getTextContent();
				
			tracks.add(new Track(key, this, title, file, artist, year));
		}
		
		return tracks;
	}

}
