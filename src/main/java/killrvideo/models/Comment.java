package killrvideo.models;

public class Comment {
    private String commentId;
    private String videoId;
    private String userId;
    private String commentText;
    private String createdDate;

    // Constructor
    public Comment(String commentId, String videoId, String userId, String commentText, String createdDate) {
        this.commentId = commentId;
        this.videoId = videoId;
        this.userId = userId;
        this.commentText = commentText;
        this.createdDate = createdDate;
    }

    // Getters and Setters (Optional for accessing fields)
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}