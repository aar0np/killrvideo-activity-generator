package killrvideo.repositories;

import java.time.Instant;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class YoutubeVideoPrimaryKey {
    @PrimaryKeyColumn(
            name = "sourceid", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)
    private String sourceid;
    @PrimaryKeyColumn(
            name = "published_at", 
            ordinal = 1, 
            type = PrimaryKeyType.CLUSTERED)
    private Instant publishedAt;
    @PrimaryKeyColumn(
            name = "youtube_video_id", 
            ordinal = 2, 
            type = PrimaryKeyType.CLUSTERED)
    private String youtubeVideoId;
    
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	public Instant getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(Instant publishedAt) {
		this.publishedAt = publishedAt;
	}
	public String getYoutubeVideoId() {
		return youtubeVideoId;
	}
	public void setYoutubeVideoId(String youtubeVideoId) {
		this.youtubeVideoId = youtubeVideoId;
	}
}
