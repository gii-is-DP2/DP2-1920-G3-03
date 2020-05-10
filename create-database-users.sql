drop database if exists `yogogym`;
create database `yogogym`;

drop user if exists 'yogogym'@'localhost';
create user 'yogogym'@'localhost' identified with mysql_native_password by 'yogogym';
revoke all privileges, grant option from 'yogogym'@'localhost';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `yogogym`.* to 'yogogym'@'localhost';