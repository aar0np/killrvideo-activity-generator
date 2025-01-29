package killrvideo.models;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import killrvideo.repositories.VideoRatingByUserPrimaryKey;

@Table("video_ratings_by_user")
public class VideoRatingByUser {

	@PrimaryKey
	private VideoRatingByUserPrimaryKey key;
	private int rating;
	
	public VideoRatingByUserPrimaryKey getKey() {
		return key;
	}
	public void setKey(VideoRatingByUserPrimaryKey key) {
		this.key = key;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
