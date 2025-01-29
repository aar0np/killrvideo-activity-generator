package killrvideo.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import killrvideo.models.VideoPlaybackStats;

public interface VideoPlaybackStatsRepository extends CassandraRepository<VideoPlaybackStats, UUID>{

	@Query("UPDATE video_playback_stats SET views = views + ?0 WHERE videoid = ?1")
	void updatePlaybackStats(long views, UUID videoid);
}
