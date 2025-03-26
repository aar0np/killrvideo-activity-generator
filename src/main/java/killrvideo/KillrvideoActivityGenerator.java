package killrvideo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import killrvideo.models.Comment;
import killrvideo.models.CommentsByUser;
import killrvideo.models.CommentsByVideo;
import killrvideo.models.LatestVideo;
import killrvideo.models.UserCredentials;
import killrvideo.models.UserVideos;
import killrvideo.models.User;
import killrvideo.models.Video;
import killrvideo.models.VideoPlaybackStats;
import killrvideo.models.VideoRating;
import killrvideo.models.VideoRatingByUser;
import killrvideo.models.YoutubeVideo;
import killrvideo.repositories.CommentsByUserPrimaryKey;
import killrvideo.repositories.CommentsByUserRepository;
import killrvideo.repositories.CommentsByVideoPrimaryKey;
import killrvideo.repositories.CommentsByVideoRepository;
import killrvideo.repositories.LatestVideosPrimaryKey;
import killrvideo.repositories.LatestVideosRepository;
import killrvideo.repositories.UserCredentialsRepository;
import killrvideo.repositories.UserVideoPrimaryKey;
import killrvideo.repositories.UserVideoRepository;
import killrvideo.repositories.UsersRepository;
import killrvideo.repositories.VideoPlaybackStatsRepository;
import killrvideo.repositories.VideoRatingByUserPrimaryKey;
import killrvideo.repositories.VideoRatingByUserRepository;
import killrvideo.repositories.VideoRatingRepository;
import killrvideo.repositories.VideoRepository;
import killrvideo.repositories.YoutubeVideoRepository;

@SpringBootApplication
public class KillrvideoActivityGenerator {
	
	private static final long TEN_MINUTES = 600L;
	private static final long ONE_SECOND = 1000L;
	
	private static BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
	private static List<User> users;
	private static List<Video> videos;
	private static List<YoutubeVideo> youtubeVideos;
	private static List<Comment> randomComments;
	private static Random random = new Random();
	
	private UsersRepository usersRep;
	private static CommentsByVideoRepository commentsByVidRep;
	private static CommentsByUserRepository commentsByUsrRep;
	private static LatestVideosRepository latestVidRep;
	private static UserCredentialsRepository userCredRep;
	private static UserVideoRepository userVidRep;
	private static VideoPlaybackStatsRepository videoViewsRep;
	private static VideoRepository videoRep;
	private static VideoRatingRepository videoRatingRep;
	private static VideoRatingByUserRepository videoRatingBUsrRep;
	private static YoutubeVideoRepository youtubeVideoRep;
	
	public static void main(String[] args) {
		SpringApplication.run(KillrvideoActivityGenerator.class, args);
	}
	
	@Bean
	public CommandLineRunner clr(UsersRepository usersRep, VideoRepository videoRep,
			YoutubeVideoRepository youtubeVideoRep, UserCredentialsRepository userCredRep,
			CommentsByVideoRepository commentsByVidRep, CommentsByUserRepository commentsByUsrRep,
			UserVideoRepository userVidRep, LatestVideosRepository latestVidRep,
			VideoRatingRepository videoRatingRep, VideoRatingByUserRepository videoRatingBUsrRep,
			VideoPlaybackStatsRepository videoViewsRep) {

		// repositories
		this.commentsByUsrRep = commentsByUsrRep;
		this.commentsByVidRep = commentsByVidRep;
		this.latestVidRep = latestVidRep;
		this.userCredRep = userCredRep;
		this.usersRep = usersRep;
		this.userVidRep = userVidRep;
		this.videoViewsRep = videoViewsRep;
		this.videoRatingRep = videoRatingRep;
		this.videoRatingBUsrRep = videoRatingBUsrRep;
		this.videoRep = videoRep;
		this.youtubeVideoRep = youtubeVideoRep;
		
		// get all users
		users = this.usersRep.findAll();
		
		// get all videos
		youtubeVideos = this.youtubeVideoRep.findAll();
		videos = this.videoRep.findAll();
		
		addSampleVideos(25);
		
		// get all comments
		randomComments = parseCommentsCSV("csv/random_video_comments.csv");
		
		// generate latest videos
		generateLatestVideoList();
		
		//schedules:
		long totalTime = 0L;
		
		while (true) {
			long oneSecondStart = System.nanoTime();
			
			if (totalTime >= TEN_MINUTES) {
				totalTime = 0L;
				addSampleVideos();
				//- every 10 minutes
			}

			if (Math.floorMod(totalTime,60) == 0) {
				addSampleComment();
				//- every 1 minute
			}
			
			if (Math.floorMod(totalTime,20) == 0) {
				addSampleRating();
				//- every 20 seconds
			}
			
			addSampleVideoView();
			//- every 1 seconds
			
			long timeSpent = (System.nanoTime() - oneSecondStart) / 1000000;
			
			if ((ONE_SECOND - timeSpent) > 0) {
				try {
					Thread.sleep(ONE_SECOND - timeSpent);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// slowing it down
			try {
				Thread.sleep(ONE_SECOND*5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			totalTime++;
		}
	}
	
	private static void addSampleVideos() {
		addSampleVideos(10);
	}
	
	private static void addSampleVideos(int maxCount) {
		
		int currentCount = 0;
		
		for (YoutubeVideo ytVideo : youtubeVideos) {
			
			if (!ytVideo.isUsed()) {
				User rUser = getRandomUser();
				
				Video video = mapYoutubeVideo(ytVideo);
				video.setUserid(rUser.getUserid());
				videoRep.save(video);
				
				UserVideos rUserVideo = mapVideoToUserVid(video, rUser.getUserid());
				userVidRep.save(rUserVideo);
				
				// add to list
				videos.add(video);
				
				// flip "used" to true; save
				ytVideo.setUsed(true);
				youtubeVideoRep.save(ytVideo);
				
				System.out.printf("video %s added!\n", video.getVideoid().toString());
				currentCount++;
			}

			if (currentCount >= maxCount) {
				break;
			}
		}
	}
	
	private static void addSampleComment() {

		// comment
		int commentCount = randomComments.size();
		int randomCommentIndex = random.nextInt(0, commentCount);
		Comment comment = randomComments.get(randomCommentIndex);
		UUID commentID = commentsByUsrRep.getTimeUUID();
		
		// video
		Video video = getRandomVideo();
		
		// user
		User user = getRandomUser();
		
		// build data entities
		CommentsByVideo commentBV = new CommentsByVideo();
		CommentsByVideoPrimaryKey commentBVKey = new CommentsByVideoPrimaryKey();
		commentBVKey.setCommentid(commentID);
		commentBVKey.setVideoid(video.getVideoid());
		commentBV.setKey(commentBVKey);
		commentBV.setComment(comment.getCommentText());
		commentBV.setUserid(user.getUserid());
		
		CommentsByUser commentBU = new CommentsByUser();
		CommentsByUserPrimaryKey commentBUKey = new CommentsByUserPrimaryKey();
		commentBUKey.setCommentid(commentID);
		commentBUKey.setUserid(user.getUserid());
		commentBU.setKey(commentBUKey);
		commentBU.setComment(comment.getCommentText());
		commentBU.setVideoid(video.getVideoid());

		commentsByVidRep.save(commentBV);
		commentsByUsrRep.save(commentBU);
		
		System.out.printf("comment %s added from user %s!\n", commentID.toString(), user.getUserid());
	}
	
	private static void addSampleRating() {
		
		User user = getRandomUser();
		Video video = getRandomVideo();
		int intRating = random.nextInt(5) + 1;

		videoRatingRep.updateRating(1L, intRating, video.getVideoid());
		
		// rating by user
		VideoRatingByUser ratingByUser = new VideoRatingByUser();
		VideoRatingByUserPrimaryKey key = new VideoRatingByUserPrimaryKey();
		key.setUserid(user.getUserid());
		key.setVideoid(video.getVideoid());
		ratingByUser.setKey(key);
		ratingByUser.setRating(intRating);
		videoRatingBUsrRep.save(ratingByUser);
		
		System.out.printf("rating for video %s added from user %s!\n", video.getVideoid(), user.getUserid());
	}
	
	private static void addSampleVideoView() {

		Video video = getRandomVideo();
		
		videoViewsRep.updatePlaybackStats(1L,video.getVideoid());
		
		System.out.printf("view for video %s added!\n", video.getVideoid());
		
	}
	
	private static void generateLatestVideoList() {
		
		// TRUNCATE
		latestVidRep.deleteAll();
		
		Video[] array = new Video[videos.size()];
		
		int arrayCounter = 0;
		for (Video video : videos) {
			array[arrayCounter] = video;
			arrayCounter++;
		}
		
		// bubble sort
		boolean flippedAPair = false;
		
		int index = 0;
		while (flippedAPair || index < videos.size() - 1) {
			
			int compared = array[index].getAddedDate()
					.compareTo(array[index + 1].getAddedDate());
			if (compared > 0) {
				Video temp = array[index];
				array[index] = array[index + 1];
				array[index + 1] = temp;
				flippedAPair = true;
			}
			
			index++;
			
			// If we get to the end of the array and haven't flipped a pair,
			// then array is "sorted" and the loop will naturally break.
			// Otherwise, use this if check to reset index and flippedAPair
			if (index >= (array.length - 1) && flippedAPair) {
				index = 0;
				flippedAPair = false;
			}
		}
		
		// latest video list maxes-out at 10
		int counter = 0;
		for (Video vid : array) {
			
			LatestVideo latest = new LatestVideo();
			LatestVideosPrimaryKey key = new LatestVideosPrimaryKey();
			key.setAddedDate(Instant.now());
			key.setVideoid(vid.getVideoid());
			key.setYyyymmdd(getDateString());
			latest.setKey(key);
			latest.setName(vid.getName());
			latest.setPreviewImageLocation(vid.getPreviewImageLocation());
			latest.setUserid(vid.getUserid());
			
			latestVidRep.save(latest);
			
			counter++;
			
			if (counter > 9) {
				break;
			}
		}
	}

	private static String getDateString() {
		// get date string
		ZonedDateTime zdt = ZonedDateTime.now();
		StringBuilder today = new StringBuilder();
		today.append(zdt.getYear());
		if (zdt.getMonthValue() < 10) {
			today.append("0");
		}
		today.append(zdt.getMonthValue());
		today.append(zdt.getDayOfMonth());

		return today.toString();
	}
	
	private static User getRandomUser() {
		
		long usersCount = users.size();
		long randomUserIndex = random.nextLong(0, usersCount);
		
		User rando = users.get((int) randomUserIndex);
		checkForCredentials(rando);
		
		return rando;
	}
	
	private static Video getRandomVideo() {
		int videoCount = videos.size();
		int randomVideoIndex = random.nextInt(0, videoCount);
		Video video = videos.get(randomVideoIndex);
		
		return video;
	}
	
	private static void checkForCredentials(User user) {
		
		List<String> emails = new ArrayList<>();
		emails.add(user.getEmail());
		
		if (userCredRep.findAllById(emails).isEmpty()) {
			UserCredentials creds = new UserCredentials();
			
			creds.setEmail(user.getEmail());
			creds.setUserid(user.getUserid());
			
			String name = user.getFirstname() + user.getLastname();
			creds.setPassword(genPassword(name));
			
			userCredRep.save(creds);
		}
	}
	
	private static String genPassword(String text) {
		return pEncoder.encode(text);
	}
	
	private static Video mapYoutubeVideo(YoutubeVideo ytVideo) {
		Video returnVal = new Video();
		
		returnVal.setAddedDate(Instant.now());
		returnVal.setDescription(ytVideo.getDescription());
		returnVal.setName(ytVideo.getName());
		returnVal.setUserid(getRandomUser().getUserid());
		
		return returnVal;
	}
	
	private static UserVideos mapVideoToUserVid(Video video, UUID userId) {
		UserVideos returnVal = new UserVideos();
		
		UserVideoPrimaryKey userVidPK = new UserVideoPrimaryKey();
		userVidPK.setAddedDate(Instant.now());
		userVidPK.setVideoid(video.getVideoid());
		userVidPK.setUserid(userId);
		
		returnVal.setKey(userVidPK);
		returnVal.setName(video.getName());
		returnVal.setPreviewImageLocation(video.getPreviewImageLocation());
		
		return returnVal;
	}
	
    private static List<Comment> parseCommentsCSV(String filePath) {
        List<Comment> comments = new ArrayList<>();
        String line;
        String delimiter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read the header line
            br.readLine();

            // Read the rest of the file
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(delimiter, -1);

                // Handle fields safely, assuming the CSV format matches the schema
                String commentId = fields[0];
                String videoId = fields[1];
                String userId = fields[2];
                String commentText = fields[3];
                String createdDate = fields[4];

                // Create a Comment object and add it to the list
                Comment comment = new Comment(commentId, videoId, userId, commentText, createdDate);
                comments.add(comment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return comments;
    }
}
