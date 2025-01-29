package killrvideo.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;

import killrvideo.models.VideoRatingByUser;

public interface VideoRatingByUserRepository extends CassandraRepository<VideoRatingByUser, VideoRatingByUserPrimaryKey> {

}
