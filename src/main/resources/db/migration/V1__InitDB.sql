
 alter table if exists departments drop constraint departmentToLector;
 alter table if exists lector_department drop constraint toDepartment;
 alter table if exists lector_department drop constraint toLector;

 drop table if exists departments cascade;
 drop table if exists lectors cascade;
 drop table if exists lector_department cascade;

 create table departments (
 id  bigserial not null,
 department_name varchar(255),
 department_head_id int8,
 primary key (id)
 );

 create table lectors (
 id  bigserial not null,
 degree varchar(255),
 first_name varchar(255),
 last_name varchar(255),
 salary float8,
 primary key (id)
 );

 create table lector_department (
 lector_id int8 not null,
 department_id int8 not null
 );

 alter table departments add constraint departmentToLector foreign key (department_head_id) references lectors;
 alter table departments add constraint uniqueDepartmentName unique (department_name);
 alter table lector_department add constraint toDepartment foreign key (department_id) references departments;
 alter table lector_department add constraint toLector foreign key (lector_id) references lectors;
 alter table lector_department add constraint uniqueLectorInDepartment unique (department_id, lector_id);
