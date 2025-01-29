package killrvideo.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;

import killrvideo.models.YoutubeVideo;

public interface YoutubeVideoRepository extends CassandraRepository<YoutubeVideo,YoutubeVideoPrimaryKey>{

}
