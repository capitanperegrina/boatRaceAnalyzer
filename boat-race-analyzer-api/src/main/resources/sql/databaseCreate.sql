CREATE TABLE boat (
  id_boat int(11) NOT NULL AUTO_INCREMENT,
  name varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id_boat)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE race (
  id_race int(11) NOT NULL AUTO_INCREMENT,
  name varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id_race)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE leg (
  id_leg int(11) NOT NULL AUTO_INCREMENT,
  id_race int(11) NOT NULL,
  date datetime DEFAULT NULL,
  name varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id_leg),
  KEY fk_id_race (id_race),
  CONSTRAINT fk_id_race FOREIGN KEY (id_race) REFERENCES race (id_race)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE route (
  id_route int(11) NOT NULL AUTO_INCREMENT,
  id_leg int(11) NOT NULL,
  seq int(11) DEFAULT NULL,
  lat1 decimal(15,9) NOT NULL,
  lon1 decimal(15,9) NOT NULL,
  lat2 decimal(15,9) DEFAULT NULL,
  lon2 decimal(15,9) DEFAULT NULL,
  kind int(11) DEFAULT NULL,
  name varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  pass int(11) DEFAULT NULL,
  PRIMARY KEY (id_route),
  KEY fk_route_id_leg (id_leg),
  CONSTRAINT fk_route_id_leg FOREIGN KEY (id_leg) REFERENCES leg (id_leg)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE track (
  id_track int(11) NOT NULL AUTO_INCREMENT,
  id_leg int(11) NOT NULL,
  id_boat int(11) NOT NULL,
  tsp_ini datetime DEFAULT NULL,
  tsp_end datetime DEFAULT NULL,
  PRIMARY KEY (id_track),
  KEY fk_id_leg (id_leg),
  KEY fk_id_boat (id_boat),
  CONSTRAINT fk_id_boat FOREIGN KEY (id_boat) REFERENCES boat (id_boat),
  CONSTRAINT fk_id_leg FOREIGN KEY (id_leg) REFERENCES leg (id_leg)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE trackpoint (
  id_track_point int(11) NOT NULL AUTO_INCREMENT,
  id_track int(11) NOT NULL,
  tsp datetime NOT NULL,
  lat decimal(15,9) NOT NULL,
  lon decimal(15,9) NOT NULL,
  sog decimal(15,9) DEFAULT NULL,
  cog decimal(15,9) DEFAULT NULL,
  PRIMARY KEY (id_track_point),
  KEY fk_id_track (id_track),
  CONSTRAINT fk_id_track FOREIGN KEY (id_track) REFERENCES track (id_track)
) ENGINE=InnoDB AUTO_INCREMENT=467626 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE board (
  id_board int(11) NOT NULL AUTO_INCREMENT,
  id_track int(11) NOT NULL,
  ini int(11) NOT NULL,
  date_ini datetime NOT NULL,
  fin int(11) NOT NULL,
  date_fin datetime NOT NULL,
  PRIMARY KEY (id_board),
  KEY fk_board_id_track (id_track),
  KEY fk_board_ini (ini),
  KEY fk_board_fin (fin),
  CONSTRAINT fk_board_fin FOREIGN KEY (fin) REFERENCES route (id_route),
  CONSTRAINT fk_board_id_track FOREIGN KEY (id_track) REFERENCES track (id_track),
  CONSTRAINT fk_board_ini FOREIGN KEY (ini) REFERENCES route (id_route)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
