package killrvideo.models;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import killrvideo.repositories.CommentsByVideoPrimaryKey;

@Table("comments_by_video")
public class CommentsByVideo {

	@PrimaryKey
	private CommentsByVideoPrimaryKey key;
	private String comment;
    private UUID userid;
    
	public CommentsByVideoPrimaryKey getKey() {
		return key;
	}
	public void setKey(CommentsByVideoPrimaryKey key) {
		this.key = key;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public UUID getUserid() {
		return userid;
	}
	public void setUserid(UUID userid) {
		this.userid = userid;
	}
}
