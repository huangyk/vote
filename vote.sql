create database vote default character set utf8 collate utf8_bin;

use vote;

create table if not exists userinfo(
	usid int(11) primary key auto_increment,
	uname varchar(100) not null unique,
	pwd varchar(100) not null
) ENGINE=InnoDB auto_increment=101 default charset=utf8 collate=utf8_bin;


create table if not exists topics(
	tid varchar(100) primary key,
	tname varchar(200) not null unique,
	types int default 1,
	usid int(11),
	sdate datetime,
	edate datetime,
	usids text,
	constraint FK_topics_usid foreign key(usid) references userinfo(usid)
) ENGINE=InnoDB default charset=utf8 collate=utf8_bin;

create table if not exists topicItem(
	ino int(11) primary key auto_increment,
	tid varchar(100),
	iname varchar(200) not null,
	nums int(11),
	constraint FK_topicItem_tid foreign key(tid) references topics(tid)
) ENGINE=InnoDB auto_increment=1 default charset=utf8 collate=utf8_bin;

