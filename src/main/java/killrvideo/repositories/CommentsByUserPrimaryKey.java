package killrvideo.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class CommentsByUserPrimaryKey {
    @PrimaryKeyColumn(
            name = "userid", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)
    private UUID userid;
    @PrimaryKeyColumn(
            name = "commentid", 
            ordinal = 1, 
            type = PrimaryKeyType.CLUSTERED)
    private UUID commentid;
    
	public UUID getUserid() {
		return userid;
	}
	public void setUserid(UUID userid) {
		this.userid = userid;
	}
	public UUID getCommentid() {
		return commentid;
	}
	public void setCommentid(UUID commentid) {
		this.commentid = commentid;
	}
}
