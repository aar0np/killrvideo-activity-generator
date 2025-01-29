package killrvideo.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import killrvideo.models.User;

public interface UsersRepository extends CassandraRepository<User,UUID>{

}
