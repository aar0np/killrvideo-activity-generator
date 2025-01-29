package killrvideo.models;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import killrvideo.repositories.YoutubeVideoPrimaryKey;

@Table("youtube_videos")
public class YoutubeVideo {
	@PrimaryKey
	private YoutubeVideoPrimaryKey key;
    private String description;
    private String name;
    private boolean used;
    
	public YoutubeVideoPrimaryKey getSourceid() {
		return key;
	}
	public void setKey(YoutubeVideoPrimaryKey key) {
		this.key = key;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
}
