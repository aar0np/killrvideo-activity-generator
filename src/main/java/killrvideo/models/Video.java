package killrvideo.models;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Table("videos")
public class Video {
	@PrimaryKey("videoid")
    private UUID videoid;
    @Column("added_date")
	private Instant addedDate;
    private String description;
    private String location;
    @Column("location_type")
    private int locationType;
    private String name;
    @Column("preview_image_location")
    private String previewImageLocation;
    private Set<String> tags;
    private UUID userid;
    
    public Video() {
    	this.videoid = UUID.randomUUID();
    }
    
	public UUID getVideoid() {
		return videoid;
	}
	public void setVideoid(UUID videoid) {
		this.videoid = videoid;
	}
	public Instant getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Instant addedDate) {
		this.addedDate = addedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getLocationType() {
		return locationType;
	}
	public void setLocationType(int locationType) {
		this.locationType = locationType;
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
	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	public UUID getUserid() {
		return userid;
	}
	public void setUserid(UUID userid) {
		this.userid = userid;
	}
}
