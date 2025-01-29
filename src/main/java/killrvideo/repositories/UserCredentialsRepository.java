package killrvideo.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;

import killrvideo.models.UserCredentials;

public interface UserCredentialsRepository extends CassandraRepository<UserCredentials, String> {

}
