package killrvideo.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;

import killrvideo.models.LatestVideo;

public interface LatestVideosRepository extends CassandraRepository<LatestVideo, LatestVideosPrimaryKey>{

}
