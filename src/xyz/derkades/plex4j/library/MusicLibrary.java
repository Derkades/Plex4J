package xyz.derkades.plex4j.library;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xyz.derkades.plex4j.Server;
import xyz.derkades.plex4j.library.item.Artist;

public class MusicLibrary extends Library {

	public MusicLibrary(Server server, int id, String title) {
		super(server, id, title, "artist");
	}
	
	public List<Artist> getArtists() throws MalformedURLException, IOException, SAXException {
		URLConnection connection = new URL
				(String.format("%s/library/sections/%s/all?X-Plex-Token=%s", 
						this.getServer().getBaseUrl(), this.getId(), this.getServer().getToken()))
				.openConnection();

		Document artistsDoc = this.getServer().getDocumentBuilder().parse(connection.getInputStream());
		Node mediaContainer = artistsDoc.getFirstChild();
		NodeList modes = mediaContainer.getChildNodes();
		
		List<Artist> artists = new ArrayList<>();
		
		for (int i = 1; i < modes.getLength(); i++) {
			if (i % 2 == 0) continue;
			
			Node artistNode = modes.item(i);
			
			String type = artistNode.getAttributes().getNamedItem("type").getTextContent();
			
			// TODO Is this check really necessary?
			if (!type.equals("artist")) {
				continue;
			}
			
			String key = artistNode.getAttributes().getNamedItem("key").getTextContent();
			String title = artistNode.getAttributes().getNamedItem("title").getTextContent();
			
			artists.add(new Artist(this, key, title));
		}
		
		return artists;
	}

}
