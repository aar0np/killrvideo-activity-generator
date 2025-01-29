package killrvideo.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class CommentsByVideoPrimaryKey {
    @PrimaryKeyColumn(
            name = "videoid", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)
    private UUID videoid;
    @PrimaryKeyColumn(
            name = "commentid", 
            ordinal = 1, 
            type = PrimaryKeyType.CLUSTERED)
    private UUID commentid;
    
	public UUID getVideoid() {
		return videoid;
	}
	public void setVideoid(UUID videoid) {
		this.videoid = videoid;
	}
	public UUID getCommentid() {
		return commentid;
	}
	public void setCommentid(UUID commentid) {
		this.commentid = commentid;
	}
}
