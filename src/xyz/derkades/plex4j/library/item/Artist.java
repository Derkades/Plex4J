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
import xyz.derkades.plex4j.library.Library;

public class Artist extends LibraryItem {
	
	private String title;
	
	public Artist(Library library, String key, String title){
		super(library, key);
		
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public List<Album> getAlbums() throws SAXException, IOException {
		Server server = this.getLibrary().getServer();
		
		URLConnection connection = new URL(
				String.format("%s%s?X-Plex-Token=%s", 
						server.getBaseUrl(), 
						this.getKey(), 
						server.getToken())
				).openConnection();

		Document albumsDoc = server.getDocumentBuilder().parse(connection.getInputStream());
		Node mediaContainer3 = albumsDoc.getFirstChild();
		NodeList albumNodes = mediaContainer3.getChildNodes();
		
		List<Album> albums = new ArrayList<>();

		for (int i = 1; i < albumNodes.getLength(); i++) {
			if (i % 2 == 0) continue;
			
			Node albumNode = albumNodes.item(i);
				
			String type = albumNode.getAttributes().getNamedItem("type").getTextContent();
				
			if (!type.equals("album")) {
				continue;
			}
				
			String key = albumNode.getAttributes().getNamedItem("key").getTextContent();
			String title = albumNode.getAttributes().getNamedItem("title").getTextContent();
				
			albums.add(new Album(key, title, this));
		}
		
		return albums;
	}

}
