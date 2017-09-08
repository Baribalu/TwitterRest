﻿CREATE TABLE users(
	id SERIAL UNIQUE NOT NULL,
	username VARCHAR(50),
	password VARCHAR(50),
	full_name VARCHAR(200),
	PRIMARY KEY(id)
);

CREATE TABLE messages(
	id SERIAL UNIQUE NOT NULL,
	content VARCHAR(500),
	id_user INTEGER REFERENCES users(id) ON DELETE CASCADE,
	PRIMARY KEY(id)
);

CREATE TABLE friends(
	id SERIAL UNIQUE NOT NULL,
	id_user INTEGER REFERENCES users(id) ON DELETE CASCADE,
	id_friend INTEGER REFERENCES users(id) ON DELETE CASCADE,
	PRIMARY KEY(id)
);