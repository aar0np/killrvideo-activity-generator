package killrvideo.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import killrvideo.models.Video;

public interface VideoRepository  extends CassandraRepository<Video, UUID>{

}
