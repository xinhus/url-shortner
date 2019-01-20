CREATE TABLE url
(
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  short_url varchar(50) NOT NULL,
  original_url text NOT NULL,
  created_at datetime DEFAULT now() NOT NULL
);
CREATE UNIQUE INDEX url_short_url_uindex ON url (short_url);