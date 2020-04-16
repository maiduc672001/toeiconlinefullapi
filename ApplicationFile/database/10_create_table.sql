use toeiconlinefull;
CREATE TABLE exerciselisten (
  exerciselistenid bigint NOT NULL PRIMARY KEY auto_increment,
  name VARCHAR(100) NOT NULL,
  createddate TIMESTAMP null,
  modifieddate TIMESTAMP null,
  unique(name)
);
CREATE TABLE exercisequestionlisten (
                                  exercisequestionlistenid bigint NOT NULL PRIMARY KEY auto_increment,
                                  image VARCHAR(255),
                                  audio VARCHAR(255),
                                  question TEXT NOT NULL,
                                  option1 VARCHAR(300) NOT NULL,
                                  option2 VARCHAR(300) NOT NULL,
                                  option3 VARCHAR(300) NOT NULL,
                                  option4 VARCHAR(300) NOT NULL,
                                  correctanswer VARCHAR(10) NOT NULL,
                                  exerciselistenid BIGINT NOT NULL,
                                  createddate TIMESTAMP null,
                                  modifieddate TIMESTAMP null
);

ALTER TABLE listenguideline ADD CONSTRAINT fk_listenguideline_exercise FOREIGN KEY (exerciselistenid) REFERENCES exerciselisten(exerciselistenid) ;