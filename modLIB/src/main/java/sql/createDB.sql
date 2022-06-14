use master

if exists(select name
          from sys.databases
          where name = 'modLIB')
    drop database modLIB

create database modLIB