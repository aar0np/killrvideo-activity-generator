package killrvideo.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import killrvideo.models.VideoPlaybackStats;

public interface VideoPlaybackStatsRepository extends CassandraRepository<VideoPlaybackStats, UUID>{

}
