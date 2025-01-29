package killrvideo.models;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import killrvideo.repositories.LatestVideosPrimaryKey;

@Table("latest_videos")
public class LatestVideo {
	
	@PrimaryKey
	private LatestVideosPrimaryKey key;
	private String name;
    @Column("preview_image_location")
    private String previewImageLocation;
    private UUID userid;
    
	public LatestVideosPrimaryKey getKey() {
		return key;
	}
	public void setKey(LatestVideosPrimaryKey key) {
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
	public UUID getUserid() {
		return userid;
	}
	public void setUserid(UUID userid) {
		this.userid = userid;
	}
}
