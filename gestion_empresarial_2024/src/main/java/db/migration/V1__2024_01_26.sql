select constraint_name from information_schema.constraint_column_usage where table_name = 'usuarios_acceso' and column_name = 'acceso_id';
 
alter table usuarios_acceso drop CONSTRAINT "uk_fne0e41l1rm9ee64vl1wwg2m7";