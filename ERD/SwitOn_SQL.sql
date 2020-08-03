create database if not exists switon;
use switon;

SET foreign_key_checks = 0;
drop table if exists lowercategory;
drop table if exists uppercategory;

create table `uppercategory` (
   `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY (`name`)
);

create table `lowercategory` (
   `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `uppercategory_id` int NOT NULL,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`uppercategory_id`) REFERENCES uppercategory (`id`)
);

insert into uppercategory (`name`) values ('웹');
insert into uppercategory (`name`) values ('모바일');
insert into uppercategory (`name`) values ('프로그래밍언어');
insert into uppercategory (`name`) values ('언어');
insert into uppercategory (`name`) values ('AI');
insert into uppercategory (`name`) values ('CS');

insert into lowercategory (`name`, `uppercategory_id`) values ('Django', 1);
insert into lowercategory (`name`, `uppercategory_id`) values ('Spring', 1);
insert into lowercategory (`name`, `uppercategory_id`) values ('Node.js', 1);
insert into lowercategory (`name`, `uppercategory_id`) values ('Vue', 1);
insert into lowercategory (`name`, `uppercategory_id`) values ('React', 1);
insert into lowercategory (`name`, `uppercategory_id`) values ('html/CSS', 1);
insert into lowercategory (`name`, `uppercategory_id`) values ('JS', 1);
insert into lowercategory (`name`, `uppercategory_id`) values ('DB', 1);
insert into lowercategory (`name`, `uppercategory_id`) values ('기타', 1);

insert into lowercategory (`name`, `uppercategory_id`) values ('안드로이드', 2);
insert into lowercategory (`name`, `uppercategory_id`) values ('IOS', 2);
insert into lowercategory (`name`, `uppercategory_id`) values ('플러터', 2);
insert into lowercategory (`name`, `uppercategory_id`) values ('리액트네이티브', 2);
insert into lowercategory (`name`, `uppercategory_id`) values ('기타', 2);

insert into lowercategory (`name`, `uppercategory_id`) values ('파이썬', 3);
insert into lowercategory (`name`, `uppercategory_id`) values ('C', 3);
insert into lowercategory (`name`, `uppercategory_id`) values ('C#', 3);
insert into lowercategory (`name`, `uppercategory_id`) values ('C++', 3);
insert into lowercategory (`name`, `uppercategory_id`) values ('Java', 3);
insert into lowercategory (`name`, `uppercategory_id`) values ('코틀린', 3);
insert into lowercategory (`name`, `uppercategory_id`) values ('스위프트', 3);
insert into lowercategory (`name`, `uppercategory_id`) values ('R', 3);
insert into lowercategory (`name`, `uppercategory_id`) values ('기타', 3);

insert into lowercategory (`name`, `uppercategory_id`) values ('영어', 4);
insert into lowercategory (`name`, `uppercategory_id`) values ('한국어', 4);
insert into lowercategory (`name`, `uppercategory_id`) values ('중국어', 4);
insert into lowercategory (`name`, `uppercategory_id`) values ('일본어', 4);
insert into lowercategory (`name`, `uppercategory_id`) values ('기타', 4);

insert into lowercategory (`name`, `uppercategory_id`) values ('머신러닝', 5);
insert into lowercategory (`name`, `uppercategory_id`) values ('딥러닝', 5);
insert into lowercategory (`name`, `uppercategory_id`) values ('opencv', 5);
insert into lowercategory (`name`, `uppercategory_id`) values ('텐서플로', 5);
insert into lowercategory (`name`, `uppercategory_id`) values ('케라스', 5);
insert into lowercategory (`name`, `uppercategory_id`) values ('기타', 5);

insert into lowercategory (`name`, `uppercategory_id`) values ('자료구조', 6);
insert into lowercategory (`name`, `uppercategory_id`) values ('알고리즘', 6);
insert into lowercategory (`name`, `uppercategory_id`) values ('네트워크', 6);
insert into lowercategory (`name`, `uppercategory_id`) values ('컴퓨터구조', 6);
insert into lowercategory (`name`, `uppercategory_id`) values ('소프트웨어공학', 6);
insert into lowercategory (`name`, `uppercategory_id`) values ('운영체제', 6);
insert into lowercategory (`name`, `uppercategory_id`) values ('기타', 6);

select * from uppercategory;
select * from lowercategory;

drop table if exists user;

create table `user` (
   `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(20),
    `email` varchar(100),
    `password` varchar(100),
    `introduce` varchar(1000),
    `profile_image` varchar(100),
    `uppercategory_id` int,
    `name_legacy` varchar(20),

    PRIMARY KEY (`id`),
    FOREIGN KEY (`uppercategory_id`) REFERENCES uppercategory (`id`)
);

insert into user (`name`, `email`, `password`, `introduce`, `uppercategory_id`)
values ('ssafy', 'ssafy@ssafy.com', '$2a$10$s2evPCMK9MV1iVWU.xzjmOxhsLg2Lw9tHEC3XKsh0IhHPIKjAL.Qu', '서울대학교 6학년 4반 김싸피입니다. 자신있는 것은 파이썬과 롤입니다.', '2');

insert into user (`name`, `email`, `password`, `introduce`)
values ('admin', 'admin@ssafy.com', '$2a$10$cmOX6saYyAyaejxiWmlNoe8llUvr8933LiWJprxz070.Wla7y1.a2', '진정한 강자는 소개를 하지 않는다.');

select * from user;

drop table if exists study;

create table `study` (
   `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `user_id` int NOT NULL,
    `description` varchar(1000),
    `code` varchar(100),
    `isPrivate` boolean NOT NULL default false,
    `isRecruit` boolean NOT NULL default false,
    `start_term` timestamp(6) NOT NULL default '20200720',
    `end_term` timestamp(6) NOT NULL default '20200802',
    `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP,
    `week` int,
    `start_time` varchar(20),
    `end_time` varchar(20),
    `lowercategory_id` int,
    `logo` varchar(100),
    `users_limit` int NOT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY (`name`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY (`lowercategory_id`) REFERENCES `lowercategory` (`id`)
    
);

insert into study (`name`, `user_id`, `description`, `week`, `start_time`, `end_time`, `lowercategory_id`, `users_limit`)
values ('파이썬의 왕', '1', '저그전이 어렵습니다.', 64 + 16 + 4, '14:00', '18:00', 2, 25);

select * from study;

drop table if exists `comment`;
drop table if exists article;
drop table if exists board;

create table `board` (
   `id` int NOT NULL AUTO_INCREMENT,
    `study_id` int NOT NULL,
    `type` int NOT NULL,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`study_id`) REFERENCES study (`id`)
);

insert into board (`study_id`, `type`)
	values (1, 1);
insert into board (`study_id`, `type`)
	values (1, 2);
insert into board (`study_id`, `type`)
	values (1, 3);

create table `article` (
   `id` int NOT NULL AUTO_INCREMENT,
    `title` varchar(100) NOT NULL,
    `content` varchar(5000) NOT NULL,
    `user_id` int NOT NULL,
    `file` varchar(100),
    `board_id` int NOT NULL,
    `created_at` timestamp default current_timestamp,
    `updated_at` timestamp,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`board_id`) REFERENCES `board` (`id`)
);

insert into article (`title`, `content`, `user_id`, `board_id`)
	values ('공지입니다.', '온라인 회식 공지입니다.', 1, 1);
insert into article (`title`, `content`, `user_id`, `board_id`)
	values ('긴급 공지', '늦잠 자지 마세요.', 1, 1);
insert into article (`title`, `content`, `user_id`, `board_id`)
	values ('질문입니다.', '파이선이 맞나요 파이썬이 맞나요', 1, 2);
insert into article (`title`, `content`, `user_id`, `board_id`)
	values ('오늘 점심 무엇인가요?', '궁금합니다.', 2, 2);
insert into article (`title`, `content`, `user_id`, `board_id`, `file`)
	values ('자료입니다.', '귀여운 고양이 사진 공유합니다.', 1, 3, '고양이파일주소');
insert into article (`title`, `content`, `user_id`, `board_id`, `file`)
	values ('집중할 때 듣기 좋은 음악', '듣기 좋은 음악 공유합니다.', 1, 3, '고양이파일주소');

create table `comment` (
   `id` int NOT NULL AUTO_INCREMENT,
    `content` varchar(5000) NOT NULL,
    `user_id` int NOT NULL,
    `article_id` int NOT NULL,
    `created_at` timestamp default current_timestamp,
    `updated_at` timestamp,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
);

insert into `comment` (`content`, `user_id`, `article_id`)
	values ('파이썬 아닐가요?', 2, 2);
insert into `comment` (`content`, `user_id`, `article_id`)
	values ('귀엽고 소중한 자료 감사합니다.', 2, 3);

select * from board;
select * from article;
select * from `comment`;

drop table if exists notice;
drop table if exists `join`;

create table `join` (
   `id` int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL,
    `study_id` int NOT NULL,
    `isComplete` boolean default false,
    `reg_date` timestamp default current_timestamp,
    `isLeader` boolean default false,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY (`study_id`) REFERENCES `study` (`id`)
);

insert into `join` (`user_id`, `study_id`, `isComplete`, `isLeader`)
	values(1, 1, 0, 1);
insert into `join` (`user_id`, `study_id`, `isComplete`)
	values(2, 1, 0);

select * from `article`;

drop table if exists studylike;
drop table if exists articlelike;
drop table if exists commentlike;
drop table if exists articlefav;

create table `studylike` (
   `id` int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL,
    `study_id` int NOT NULL,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY (`study_id`) REFERENCES `study` (`id`)
);

create table `articlelike` (
   `id` int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL,
    `article_id` int NOT NULL,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
);

create table `commentlike` (
   `id` int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL,
    `comment_id` int NOT NULL,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
);

create table `articlefav` (
   `id` int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL,
    `article_id` int NOT NULL,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
);

select * from studylike;
select * from articlelike;
select * from commentlike;
select * from articlefav;
