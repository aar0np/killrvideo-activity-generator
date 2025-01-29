package killrvideo.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class VideoRatingByUserPrimaryKey {

    @PrimaryKeyColumn(
            name = "videoid", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)    
    private UUID videoid;
    @PrimaryKeyColumn(
            name = "userid", 
            ordinal = 1, 
            type = PrimaryKeyType.CLUSTERED)    
    private UUID userid;
    
	public UUID getVideoid() {
		return videoid;
	}
	public void setVideoid(UUID videoid) {
		this.videoid = videoid;
	}
	public UUID getUserid() {
		return userid;
	}
	public void setUserid(UUID userid) {
		this.userid = userid;
	}
}
