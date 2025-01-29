package killrvideo.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import killrvideo.models.VideoRating;

public interface VideoRatingRepository extends CassandraRepository<VideoRating, UUID> {

}
