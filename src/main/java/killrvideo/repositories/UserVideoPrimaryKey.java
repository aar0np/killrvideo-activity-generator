package killrvideo.repositories;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class UserVideoPrimaryKey {
	
    @PrimaryKeyColumn(
            name = "userid", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)
    private UUID userid;
    @PrimaryKeyColumn(
            name = "added_date", 
            ordinal = 1, 
            type = PrimaryKeyType.CLUSTERED)
	private Instant addedDate;
    @PrimaryKeyColumn(
            name = "videoid", 
            ordinal = 2, 
            type = PrimaryKeyType.CLUSTERED)
	private UUID videoid;

    public UUID getUserid() {
		return userid;
	}
	public void setUserid(UUID userid) {
		this.userid = userid;
	}
	public Instant getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Instant addedDate) {
		this.addedDate = addedDate;
	}
	public UUID getVideoid() {
		return videoid;
	}
	public void setVideoid(UUID videoid) {
		this.videoid = videoid;
	}
}
