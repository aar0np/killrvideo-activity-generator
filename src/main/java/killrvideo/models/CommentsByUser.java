package killrvideo.models;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import killrvideo.repositories.CommentsByUserPrimaryKey;

@Table("comments_by_user")
public class CommentsByUser {

	@PrimaryKey
	private CommentsByUserPrimaryKey key;
	private String comment;
    private UUID videoid;
    
	public CommentsByUserPrimaryKey getKey() {
		return key;
	}
	public void setKey(CommentsByUserPrimaryKey key) {
		this.key = key;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public UUID getVideoid() {
		return videoid;
	}
	public void setVideoid(UUID videoid) {
		this.videoid = videoid;
	}
}
