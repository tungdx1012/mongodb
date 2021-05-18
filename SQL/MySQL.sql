-- delete database Lesson2; 
create database Lesson2;
use Lesson2;
create table account(
	id int primary key auto_increment,
    username varchar(50) unique,
	password varchar(50),
    status tinyint);
create table trainer(
	id int auto_increment PRIMARY KEY,
    name varchar(255),
    account_id int,
    foreign key (account_id) references account(id));
create table horse(
	id int auto_increment primary key,
    name varchar(225),
    foaled datetime);
create table horse_account(
	id int auto_increment primary key,
    horse_id int, 
    account_id int, 
    archive tinyint default 0,
    foreign key (horse_id) references horse(id),
    foreign key (account_id) references account(id)
    );
insert into account value(null,'sa',md5('abc'),'0');
insert into account value(null,'sb',md5('abc'),'1');
insert into account value(null,'sc',md5('abc'),'0');
insert into account value(null,'sd',md5('abc'),'0');
insert into account value(null,'se',md5('abc'),'1');
insert into account value(null,'sr',md5('abc'),'0');

insert into trainer value(null,'Peter',1);
insert into trainer value(null,'Tom',2);
insert into trainer value(null,'Cat',3);
insert into trainer value(null,'CR7',3);
insert into trainer value(null,'DXT',1);

insert into horse value(null,'Horse 1','2000-01-01');
insert into horse value(null,'Horse 2','2005-05-05');
insert into horse value(null,'Horse 3','2010-10-10');
insert into horse value(null,'Horse 4','2010-06-20');
insert into horse value(null,'Horse 3','2010-10-18');
insert into horse value(null,'Horse 6','2010-10-21');
insert into horse value(null,'Horse 4','2008-10-10');
insert into horse value(null,'Horse 1','2010-06-13');
insert into horse value(null,'Horse 7','2011-04-05');
insert into horse value(null,'Horse 10','2012-04-05');

insert into horse_account value(null,1,1,0);
insert into horse_account value(null,2,2,1);
insert into horse_account value(null,3,3,0);
insert into horse_account value(null,7,1,1);
insert into horse_account value(null,8,2,1);
insert into horse_account value(null,1,3,0);
insert into horse_account value(null,3,2,1);
insert into horse_account value(null,6,3,0);


-- select
select * from account;

-- join,inner join, left join
select a.id, t.name, ha.horse_id, a.username, a.passwordflyway_schema_history, h.foaled, a.status, ha.archive 
from account a inner 
join trainer t on a.id = t.account_id 
join horse_account ha on a.id = ha.account_id 
left join horse h on h.id = ha.horse_id;

-- update
UPDATE trainer set name = 'Quang' where id = 10 ;
UPDATE trainer set name = 'ABC' where id = 8 ;
update horse set name = 'Horse 10' where id =11;

-- union & order by
select id from trainer
union
select id from horse
Order By id desc;

-- delete from account --> horse_account + trainer = account
delete from horse_account where account_id=6;
delete from trainer where account_id=2; 
delete from account where id = 1;
-- delete from horse_account
delete from horse_account where id = 12;

-- Ex1 : Find trainer has 3 horse
select t.id, t.name, a.username, a.password
from trainer t join account a on t.account_id=a.id 
join horse_account ha on ha.account_id= a.id 
join horse h on h.id=ha.horse_id 
group by ha.account_id having count(ha.horse_id) > 2;

-- Find horse duplicate
select t.id,t.name, h.name
from trainer t join account a on t.account_id=a.id 
join horse_account ha on ha.account_id= a.id 
join horse h on h.id=ha.horse_id 
group by h.name having count(h.name) >1 ;

-- Find all trainer
select t.id,t.name as Trainer,h.name as Horse,h.id as ID 
from trainer t join account a on t.account_id=a.id 
join horse_account ha on ha.account_id= a.id 
join horse h on h.id=ha.horse_id order by h.name;

-- demo find duplicate
select id, name, Count(name) as Total from horse group by name having count(name) >1 ;

-- Ex2 : Find all horse exist 
select t.id as ID, t.name as Trainer, h.name as Horse, h.id as HorseID
from trainer t join account a on t.account_id=a.id 
join horse_account ha on ha.account_id= a.id 
join horse h on h.id=ha.horse_id
where h.name in 
(select h.name
from trainer t join account a on t.account_id=a.id 
join horse_account ha on ha.account_id= a.id 
join horse h on h.id=ha.horse_id 
group by h.name having count(h.name) >1);


-- Trainer_ID = 1 and Year = 2010
select h.id, h.name, h.foaled
from trainer t join account a on t.account_id=a.id 
join horse_account ha on ha.account_id= a.id 	
join horse h on h.id=ha.horse_id
where t.id = 10 and Year(h.foaled) = 2010;

-- select * from account where id = (select id from account where username = 'se');




