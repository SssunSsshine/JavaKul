CREATE SEQUENCE s_profile;

CREATE SEQUENCE s_book;

create table profile (
   	id_profile           	BIGINT               not null DEFAULT (nextval('s_profile')),
   	surname_profile         VARCHAR(100)         not null,
   	name_profile            VARCHAR(100)         not null,
   	birthday_profile        DATE                 not null,
   	phone_profile    		VARCHAR(20)          not null UNIQUE,
   	email_profile     		VARCHAR(100)         not null UNIQUE,
	password_profile		VARCHAR(100)         not null	
);

create table book (
   	id_book           	BIGINT              not null DEFAULT (nextval('s_book')),
   	author				VARCHAR(100)		not null,
	title				VARCHAR(100)		not null,
	year_book			INT					not null,
	id_profile			BIGINT              not null
);

alter table profile
   add constraint PK_PROFILE primary key (id_profile);
   
alter table book
   add constraint PK_book primary key (id_book);

alter table book
   add constraint FK_BOOK_PROFILE foreign key (id_profile)
      references profile (id_profile)
      on delete cascade on update cascade;
	  