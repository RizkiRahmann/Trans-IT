create database trans_it_db;
use trans_it_db;

show tables;
select * from m_role;
select * from m_user_credential;
select * from m_user_credential_roles;
select * from m_destination;
select * from user;
select * from m_image;
select * from m_hotel;
select * from t_log;
select * from t_purchase;
select * from m_bus;
select * from m_payment;

drop table t_log;
drop table t_purchase;
drop table user;
drop table m_image