package killrvideo.repositories;

import killrvideo.models.CommentsByUser;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

public interface CommentsByUserRepository extends CassandraRepository<CommentsByUser, CommentsByUserPrimaryKey>{
	
	@Query("SELECT now() FROM system.local")
	UUID getTimeUUID();
}
