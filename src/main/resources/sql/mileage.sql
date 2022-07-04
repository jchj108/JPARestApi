CREATE TABLE User (
	user_id	VARCHAR(30)	NOT NULL,
	point	BIGINT	NULL	DEFAULT 0
);

CREATE TABLE Review_Photo (
	photo_id	VARCHAR(30)	NOT NULL,
	review_id	VARCHAR(30)	NOT NULL
);

CREATE TABLE Review (
	review_id	VARCHAR(30)	NOT NULL,
	place_id	VARCHAR(30)	NOT NULL,
	user_id	VARCHAR(30)	NOT NULL,
	content	VARCHAR(3000)	NULL,
	is_deleted	INTEGER	NULL	DEFAULT 0
);

CREATE TABLE Place (
	place_id	VARCHAR(30)	NOT NULL,
	review_count	INTEGER	NULL	DEFAULT 0
);

CREATE TABLE Mileage_History (
	mileage_history_id	BIGINT	NOT NULL,
	user_id	VARCHAR(30)	NOT NULL,
	review_id	VARCHAR(30)	NULL,
	created_date	DATETIME	NULL,
	type	VARCHAR(5)	NULL,
	point_history	BIGINT	NULL,
	point	BIGINT	NULL
);

ALTER TABLE User ADD CONSTRAINT PK_USER PRIMARY KEY (
	user_id
);

ALTER TABLE Review_Photo ADD CONSTRAINT PK_REVIEW_PHOTO PRIMARY KEY (
	photo_id
);

ALTER TABLE Review ADD CONSTRAINT PK_REVIEW PRIMARY KEY (
	review_id
);

ALTER TABLE Place ADD CONSTRAINT PK_PLACE PRIMARY KEY (
	place_id
);

ALTER TABLE Mileage_History ADD CONSTRAINT PK_MILEAGE_HISTORY PRIMARY KEY (
	history_id
);

CREATE INDEX userIdx
ON USER(user_id);

CREATE INDEX reviewPhotoIdx
ON Review_Photo(photo_id);

CREATE INDEX reviewIdx
ON Review(review_id);

CREATE INDEX placeIdx
ON Place(place_id);

CREATE INDEX mileageHistoryIdx
ON Mileage_History(mileage_history_id);

