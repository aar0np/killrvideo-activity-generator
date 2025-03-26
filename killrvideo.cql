CREATE KEYSPACE killrvideo WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

CREATE TABLE killrvideo.users (
    userid uuid PRIMARY KEY,
    created_date timestamp,
    email text,
    firstname text,
    lastname text);

CREATE TABLE killrvideo.user_credentials (
    email text PRIMARY KEY,
    password text,
    userid uuid);

CREATE TABLE killrvideo.latest_videos (
    yyyymmdd text,
    added_date timestamp,
    videoid uuid,
    name text,
    preview_image_location text,
    userid uuid,
    PRIMARY KEY (yyyymmdd, added_date, videoid)
) WITH CLUSTERING ORDER BY (added_date DESC, videoid ASC);

CREATE TABLE killrvideo.comments_by_user (
    userid uuid,
    commentid timeuuid,
    comment text,
    videoid uuid,
    PRIMARY KEY (userid, commentid)
) WITH CLUSTERING ORDER BY (commentid DESC);

CREATE TABLE killrvideo.video_recommendations_by_video (
    videoid uuid,
    userid uuid,
    added_date timestamp static,
    authorid uuid static,
    name text static,
    preview_image_location text static,
    rating float,
    PRIMARY KEY (videoid, userid)
) WITH CLUSTERING ORDER BY (userid ASC);

CREATE TABLE killrvideo.video_ratings (
    videoid uuid PRIMARY KEY,
    rating_counter counter,
    rating_total counter);

CREATE TABLE killrvideo.tags_by_letter (
    first_letter text,
    tag text,
    PRIMARY KEY (first_letter, tag)
) WITH CLUSTERING ORDER BY (tag ASC);

CREATE TABLE killrvideo.video_recommendations (
    userid uuid,
    added_date timestamp,
    videoid uuid,
    authorid uuid,
    name text,
    preview_image_location text,
    rating float,
    PRIMARY KEY (userid, added_date, videoid)
) WITH CLUSTERING ORDER BY (added_date DESC, videoid ASC);

CREATE TABLE killrvideo.videos (
    videoid uuid PRIMARY KEY,
    added_date timestamp,
    description text,
    location text,
    location_type int,
    name text,
    preview_image_location text,
    solr_query text,
    tags set<text>,
    userid uuid);

CREATE TABLE killrvideo.user_videos (
    userid uuid,
    added_date timestamp,
    videoid uuid,
    name text,
    preview_image_location text,
    PRIMARY KEY (userid, added_date, videoid)
) WITH CLUSTERING ORDER BY (added_date DESC, videoid ASC);

CREATE TABLE killrvideo.videos_by_tag (
    tag text,
    videoid uuid,
    added_date timestamp,
    name text,
    preview_image_location text,
    tagged_date timestamp,
    userid uuid,
    PRIMARY KEY (tag, videoid)
) WITH CLUSTERING ORDER BY (videoid ASC);

CREATE TABLE killrvideo.video_playback_stats (
    videoid uuid PRIMARY KEY,
    views counter);

CREATE TABLE killrvideo.youtube_videos (
    sourceid text,
    published_at timestamp,
    youtube_video_id text,
    description text,
    name text,
    used boolean,
    PRIMARY KEY (sourceid, published_at, youtube_video_id)
) WITH CLUSTERING ORDER BY (published_at DESC, youtube_video_id ASC);

CREATE TABLE killrvideo.video_ratings_by_user (
    videoid uuid,
    userid uuid,
    rating int,
    PRIMARY KEY (videoid, userid)
) WITH CLUSTERING ORDER BY (userid ASC);

CREATE TABLE killrvideo.comments_by_video (
    videoid uuid,
    commentid timeuuid,
    comment text,
    userid uuid,
    PRIMARY KEY (videoid, commentid)
) WITH CLUSTERING ORDER BY (commentid DESC);
