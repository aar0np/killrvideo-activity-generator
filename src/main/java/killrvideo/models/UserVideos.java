package killrvideo.models;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import killrvideo.repositories.UserVideoPrimaryKey;

@Table("user_videos")
public class UserVideos {

	@PrimaryKey
	private UserVideoPrimaryKey key;
    private String name;
    @Column("preview_image_location")
    private String previewImageLocation;
    
	public UserVideoPrimaryKey getKey() {
		return key;
	}
	public void setKey(UserVideoPrimaryKey key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreviewImageLocation() {
		return previewImageLocation;
	}
	public void setPreviewImageLocation(String previewImageLocation) {
		this.previewImageLocation = previewImageLocation;
	}
}
