CREATE TABLE 	public.control_acceso_end_point
(
	des_end_point character varying,
	cantidad_acceso integer
);

INSERT INTO public.control_acceso_end_point
(des_end_point,cantidad_acceso)
VALUES('salvarPersonaJuridica',0);

ALTER TABLE control_acceso_end_point add constraint des_end_point UNIQUE (des_end_point);