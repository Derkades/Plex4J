# Plex4J
A java API for interacting with a Plex Media Server through its web API.

# Maven
This project is not hosted in a maven repository. To use it, manually install it in your own local repository, by cloning the project and running `mvn install`

# Features
* Getting a list of artists/albums/tracks with basic metadata
* Getting a list of libraries with basic metadata

# TODO
* Methods for getting artist (not album artist), track number and year.
* Support for video files

# Example code
[Getting a plex authentication token](https://support.plex.tv/articles/204059436-finding-an-authentication-token-x-plex-token/)

```
Server server = new Server("https://plex.yourdomain.net", token);
System.out.println("This server has " + server.getLibraries().size() + " libraries.");

for (Library library : server.getLibraries()){
	if (library instanceof MusicLibrary){
		MusicLibrary musicLibrary = (MusicLibrary) library;
		System.out.println("This library has " + musicLibrary.getArtists().size() + " artists");
	}
}
```