package killrvideo.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import killrvideo.models.VideoRating;

public interface VideoRatingRepository extends CassandraRepository<VideoRating, UUID> {

	@Query("UPDATE video_ratings SET rating_counter = rating_counter + ?0,"
			+ " rating_total = rating_total + ?1"
			+ " WHERE videoid = ?2")
	void updateRating(long rating_counter, long rating_total, UUID videoid);
}
