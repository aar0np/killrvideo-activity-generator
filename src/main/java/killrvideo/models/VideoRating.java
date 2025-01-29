package killrvideo.models;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("video_ratings")
public class VideoRating {
	
	@PrimaryKey
	private UUID videoid;
	@Column("rating_counter")
	private long ratingCounter;
	@Column("rating_total")
	private long ratingTotal;
	
	public UUID getVideoid() {
		return videoid;
	}
	public void setVideoid(UUID videoid) {
		this.videoid = videoid;
	}
	public long getRatingCounter() {
		return ratingCounter;
	}
	public void setRatingCounter(long ratingCounter) {
		this.ratingCounter = ratingCounter;
	}
	public long getRatingTotal() {
		return ratingTotal;
	}
	public void setRatingTotal(long ratingTotal) {
		this.ratingTotal = ratingTotal;
	}
}
