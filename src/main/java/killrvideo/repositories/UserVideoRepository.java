package killrvideo.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;

import killrvideo.models.UserVideos;

public interface UserVideoRepository extends CassandraRepository<UserVideos, UserVideoPrimaryKey> {

}
