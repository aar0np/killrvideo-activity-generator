package killrvideo.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import killrvideo.models.CommentsByVideo;

public interface CommentsByVideoRepository extends CassandraRepository<CommentsByVideo, CommentsByVideoPrimaryKey> {

	@Query("SELECT now() FROM system.local")
	UUID getTimeUUID();
}
