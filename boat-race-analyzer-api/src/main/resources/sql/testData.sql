drop table  trackpoint;
drop table track;
drop table leg;
drop table boat;
drop table race;
  
CREATE TABLE boat (
    id_boat         INTEGER         NOT NULL AUTO_INCREMENT,
    name            VARCHAR(80),
    PRIMARY KEY(id_boat)
) ENGINE=InnoDB;

CREATE TABLE race (
    id_race         INTEGER         NOT NULL AUTO_INCREMENT,
    name            VARCHAR(80),
    PRIMARY KEY(id_race)
) ENGINE=InnoDB;

CREATE TABLE leg (
    id_leg          INTEGER         NOT NULL AUTO_INCREMENT,
    id_race         INTEGER         NOT NULL,
    date            DATETIME,
    name            VARCHAR(80),
    PRIMARY KEY(id_leg),
    CONSTRAINT fk_id_race FOREIGN KEY (id_race) REFERENCES race(id_race)
) ENGINE=InnoDB;

CREATE TABLE track (
    id_track        INTEGER         NOT NULL AUTO_INCREMENT,
    id_leg          INTEGER         NOT NULL,
    id_boat         INTEGER         NOT NULL,
    PRIMARY KEY(id_track),
    CONSTRAINT fk_id_leg FOREIGN KEY (id_leg) REFERENCES leg(id_leg),
    CONSTRAINT fk_id_boat FOREIGN KEY (id_boat) REFERENCES boat(id_boat)
) ENGINE=InnoDB;

CREATE TABLE trackPoint( 
    id_track_point  INTEGER         NOT NULL AUTO_INCREMENT,
    id_track        INTEGER         NOT NULL,
    tsp             DATETIME        NOT NULL,
    lat             DECIMAL(15,9)   NOT NULL,
    lon             DECIMAL(15,9)   NOT NULL,
    sog             DECIMAL(15,9),
    cog             DECIMAL(15,9),
    PRIMARY KEY(id_track_point),
    CONSTRAINT fk_id_track FOREIGN KEY (id_track) REFERENCES track(id_track)
) ENGINE=InnoDB;


insert into race values (1,'6ª Regata Ría de Pontevedra');
insert into leg values (1,1,'2020-01-25 14:45:00', 'Etapa 1 - Combarro');
insert into boat values (1,'Miña Miniña');
insert into boat values (2,'Cassandra');
insert into boat values (3,'Patela');
insert into boat values (4,'Peregrina');
insert into boat values (5,'Voodoo');
insert into boat values (6,'Atxurri');
insert into boat values (7,'Mou');
insert into track values (1,1,1);
insert into track values (2,1,2);
insert into track values (3,1,3);
insert into track values (4,1,4);
insert into track values (5,1,5);
insert into track values (6,1,6);
insert into track values (7,1,7);
insert into trackpoint values ( 1,  1, '2020-01-25 13:35:10', 42.426544, -8.699434, 2.4, 286 );
insert into trackpoint values ( 2,  1, '2020-01-25 13:35:11', 42.426548, -8.699445, 2.4, 291 );
insert into trackpoint values ( 3,  2, '2020-01-25 13:35:10', 42.426552, -8.699463, 2.4, 284 );
insert into trackpoint values ( 4,  2, '2020-01-25 13:35:11', 42.426556, -8.699475, 2.4, 286 );
insert into trackpoint values ( 5,  3, '2020-01-25 13:35:10', 42.426556, -8.699487, 2.4, 284 );
insert into trackpoint values ( 6,  3, '2020-01-25 13:35:11', 42.426559, -8.699499, 2.4, 285 );
insert into trackpoint values ( 7,  4, '2020-01-25 13:35:10', 42.426556, -8.699511, 2.4, 273 );
insert into trackpoint values ( 8,  4, '2020-01-25 13:35:11', 42.426559, -8.699521, 2.4, 274 );
insert into trackpoint values ( 9,  5, '2020-01-25 13:35:10', 42.426559, -8.699535, 2.4, 284 );
insert into trackpoint values (10,  5, '2020-01-25 13:35:11', 42.426563, -8.699548, 2.4, 284 );
insert into trackpoint values (11,  6, '2020-01-25 13:35:10', 42.426567, -8.699563, 2.3, 290 );
insert into trackpoint values (12,  6, '2020-01-25 13:35:11', 42.426571, -8.699578, 2.3, 291 );
insert into trackpoint values (13,  7, '2020-01-25 13:35:10', 42.426575, -8.69959 , 2.3, 292 );
insert into trackpoint values (14,  7, '2020-01-25 13:35:11', 42.426579, -8.699605, 2.3, 286 );