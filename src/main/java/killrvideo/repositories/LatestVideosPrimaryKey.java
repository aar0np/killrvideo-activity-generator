package killrvideo.repositories;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class LatestVideosPrimaryKey {
    @PrimaryKeyColumn(
            name = "yyyymmdd", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)
    private String yyyymmdd;
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
    
	public String getYyyymmdd() {
		return yyyymmdd;
	}
	public void setYyyymmdd(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd;
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
