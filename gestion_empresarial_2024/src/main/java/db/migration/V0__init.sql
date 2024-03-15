--
-- PostgreSQL database dump
--

-- Dumped from database version 12.17
-- Dumped by pg_dump version 12.17

-- Started on 2024-01-27 19:34:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 242 (class 1255 OID 17467)
-- Name: validaidpersona(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.validaidpersona() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE existe integer;

BEGIN

	existe = (SELECT COUNT(1) FROM persona_fisica WHERE id = NEW.persona_id);
	IF (existe <= 0) THEN
		existe = (select COUNT(1) FROM persona_juridica WHERE id = NEW.persona_id);
		IF (existe <= 0) THEN
			RAISE EXCEPTION 'No fue encontrada el Id de Persona';
		end IF;
	end if;
	
	RETURN new;
end;
$$;


ALTER FUNCTION public.validaidpersona() OWNER TO postgres;

--
-- TOC entry 243 (class 1255 OID 17482)
-- Name: validaidpersonacuentapagar(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.validaidpersonacuentapagar() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE existe integer;

BEGIN

	existe = (SELECT COUNT(1) FROM persona_fisica WHERE id = NEW.persona_proveedor_id);
	IF (existe <= 0) THEN
		existe = (select COUNT(1) FROM persona_juridica WHERE id = NEW.persona_proveedor_id);
		IF (existe <= 0) THEN
			RAISE EXCEPTION 'No fue encontrada el Id de Persona';
		end IF;
	end if;
	
	RETURN new;
end;
$$;


ALTER FUNCTION public.validaidpersonacuentapagar() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16943)
-- Name: acceso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.acceso (
    id bigint NOT NULL,
    descripcion_acceso character varying(255) NOT NULL
);


ALTER TABLE public.acceso OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16948)
-- Name: categoria_producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria_producto (
    id bigint NOT NULL,
    descripcion_categoria character varying(255) NOT NULL
);


ALTER TABLE public.categoria_producto OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16953)
-- Name: cuenta_pagar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cuenta_pagar (
    id bigint NOT NULL,
    des_cuenta_pagar character varying(255) NOT NULL,
    fecha_pago date,
    fecha_vencimiento date NOT NULL,
    status_cuenta_pagar character varying(255) NOT NULL,
    valor_descuento numeric(19,2),
    valor_total numeric(19,2) NOT NULL,
    persona_id bigint NOT NULL,
    persona_proveedor_id bigint NOT NULL
);


ALTER TABLE public.cuenta_pagar OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16961)
-- Name: cuenta_recibir; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cuenta_recibir (
    id bigint NOT NULL,
    des_cuenta_recibir character varying(255) NOT NULL,
    fecha_pago date,
    fecha_vencimiento date NOT NULL,
    status_cuenta_recibir character varying(255) NOT NULL,
    valor_descuento numeric(19,2),
    valor_total numeric(19,2) NOT NULL,
    persona_id bigint NOT NULL
);


ALTER TABLE public.cuenta_recibir OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16969)
-- Name: cupon_descuento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cupon_descuento (
    id bigint NOT NULL,
    descripcion_cupon character varying(255) NOT NULL,
    valido_hasta date,
    valor_descuento numeric(19,2),
    valor_porcentaje numeric(19,2)
);


ALTER TABLE public.cupon_descuento OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 17250)
-- Name: direccion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.direccion (
    id bigint NOT NULL,
    barrio character varying(255) NOT NULL,
    ciudad character varying(255) NOT NULL,
    complemento character varying(255),
    departamento character varying(255) NOT NULL,
    des_direccion character varying(255) NOT NULL,
    municipio character varying(255) NOT NULL,
    tipo_direccion character varying(255) NOT NULL,
    persona_id bigint NOT NULL
);


ALTER TABLE public.direccion OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 17240)
-- Name: evaluacion_producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.evaluacion_producto (
    id bigint NOT NULL,
    des_evaluacion character varying(255) NOT NULL,
    nota integer NOT NULL,
    persona_id bigint NOT NULL,
    producto_id bigint NOT NULL
);


ALTER TABLE public.evaluacion_producto OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 17283)
-- Name: factura_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.factura_compra (
    id bigint NOT NULL,
    descripcion_factura_compra character varying(255),
    fecha_compra date NOT NULL,
    nro_factura_compra character varying(255) NOT NULL,
    serie_factura_compra character varying(255) NOT NULL,
    valor_descuento numeric(19,2),
    valor_impuesto numeric(19,2),
    valor_total numeric(19,2) NOT NULL,
    cuenta_pagar_id bigint NOT NULL,
    persona_id bigint NOT NULL
);


ALTER TABLE public.factura_compra OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16990)
-- Name: factura_item_producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.factura_item_producto (
    id bigint NOT NULL,
    cantidad double precision NOT NULL,
    factura_compra_id bigint NOT NULL,
    producto_id bigint NOT NULL
);


ALTER TABLE public.factura_item_producto OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 17301)
-- Name: factura_venta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.factura_venta (
    id bigint NOT NULL,
    fecha_factura_venta date NOT NULL,
    nro_factura_venta character varying(255) NOT NULL,
    pdf text NOT NULL,
    serie_factura_venta character varying(255) NOT NULL,
    tipo character varying(255) NOT NULL,
    xml text NOT NULL,
    vnt_cmpr_tnda_vrtl_id bigint NOT NULL
);


ALTER TABLE public.factura_venta OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16995)
-- Name: forma_pago; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.forma_pago (
    id bigint NOT NULL,
    descripcion_forma_pago character varying(255) NOT NULL
);


ALTER TABLE public.forma_pago OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 17000)
-- Name: imagen_producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.imagen_producto (
    id bigint NOT NULL,
    imagen_miniatura text NOT NULL,
    imagen_original text NOT NULL,
    producto_id bigint NOT NULL
);


ALTER TABLE public.imagen_producto OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 17268)
-- Name: item_venta_tienda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_venta_tienda (
    id bigint NOT NULL,
    cantidad double precision NOT NULL,
    producto_id bigint NOT NULL,
    vnt_cmpr_tnda_vrtl_id bigint NOT NULL
);


ALTER TABLE public.item_venta_tienda OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 17008)
-- Name: marca_producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.marca_producto (
    id bigint NOT NULL,
    descripcion_marca character varying(255) NOT NULL
);


ALTER TABLE public.marca_producto OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 17450)
-- Name: persona_fisica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persona_fisica (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    telefono character varying(255) NOT NULL,
    fecha_nacimiento date NOT NULL,
    nro_identificacion character varying(255) NOT NULL,
    primer_apellido character varying(255) NOT NULL,
    primer_nombre character varying(255) NOT NULL,
    segundo_apellido character varying(255),
    segundo_nombre character varying(255),
    tipo_identificacion character varying(255) NOT NULL
);


ALTER TABLE public.persona_fisica OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 17458)
-- Name: persona_juridica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persona_juridica (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    telefono character varying(255) NOT NULL,
    categoria character varying(255),
    nit character varying(255) NOT NULL,
    nombre_fantacia character varying(255),
    nro_verificacion character varying(255) NOT NULL,
    razon_social character varying(255) NOT NULL
);


ALTER TABLE public.persona_juridica OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 17364)
-- Name: producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.producto (
    id bigint NOT NULL,
    activo boolean NOT NULL,
    alerta_cantidad_inventario boolean,
    alerta_cantidad_maximo_inventario integer NOT NULL,
    alerta_cantidad_minimo_inventario integer NOT NULL,
    altura double precision NOT NULL,
    cantidad_clique integer,
    cantidad_inventario integer NOT NULL,
    descripcion_producto text NOT NULL,
    largo double precision NOT NULL,
    link_youtube character varying(255),
    nombre_producto character varying(255) NOT NULL,
    peso double precision NOT NULL,
    precio_venta numeric(19,2) NOT NULL,
    profundidad double precision NOT NULL,
    tipo_unidad character varying(255) NOT NULL
);


ALTER TABLE public.producto OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 17052)
-- Name: seq_acceso; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_acceso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_acceso OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 17054)
-- Name: seq_categoria_producto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_categoria_producto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_categoria_producto OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 17056)
-- Name: seq_cuenta_pagar; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_cuenta_pagar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_cuenta_pagar OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 17058)
-- Name: seq_cuenta_recibir; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_cuenta_recibir
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_cuenta_recibir OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 17060)
-- Name: seq_cupon_descuento; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_cupon_descuento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_cupon_descuento OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17062)
-- Name: seq_direccion; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_direccion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_direccion OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 17233)
-- Name: seq_evaluacion_producto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_evaluacion_producto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_evaluacion_producto OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17064)
-- Name: seq_factura_compra; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_factura_compra
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_factura_compra OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17066)
-- Name: seq_factura_item_producto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_factura_item_producto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_factura_item_producto OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 17132)
-- Name: seq_factura_venta; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_factura_venta
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_factura_venta OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17068)
-- Name: seq_forma_pago; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_forma_pago
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_forma_pago OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17070)
-- Name: seq_imagen_producto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_imagen_producto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_imagen_producto OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 17213)
-- Name: seq_item_venta_tienda; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_item_venta_tienda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_item_venta_tienda OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 17072)
-- Name: seq_marca_producto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_marca_producto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_marca_producto OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 17074)
-- Name: seq_persona; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_persona
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_persona OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 17076)
-- Name: seq_producto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_producto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_producto OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 17134)
-- Name: seq_status_rastreo; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_status_rastreo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_status_rastreo OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 17078)
-- Name: seq_usuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_usuario OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 17136)
-- Name: seq_vnt_cmpr_tnda_vrtl; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_vnt_cmpr_tnda_vrtl
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_vnt_cmpr_tnda_vrtl OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 17119)
-- Name: status_rastreo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status_rastreo (
    id bigint NOT NULL,
    ciudad character varying(255) NOT NULL,
    departamento character varying(255) NOT NULL,
    empresa_distribucion character varying(255) NOT NULL,
    municipio character varying(255) NOT NULL,
    vnt_cmpr_tnda_vrtl_id bigint NOT NULL
);


ALTER TABLE public.status_rastreo OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 17392)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id bigint NOT NULL,
    fecha_actual_password date NOT NULL,
    login character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    empresa_id bigint NOT NULL,
    persona_id bigint NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 17045)
-- Name: usuarios_acceso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios_acceso (
    usuario_id bigint NOT NULL,
    acceso_id bigint NOT NULL
);


ALTER TABLE public.usuarios_acceso OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 17405)
-- Name: vnt_cmpr_tnda_vrtl; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vnt_cmpr_tnda_vrtl (
    id bigint NOT NULL,
    dias_entrega integer NOT NULL,
    fecha_entrega date,
    fecha_venta date NOT NULL,
    valor_descuento numeric(19,2),
    valor_flete numeric(19,2) NOT NULL,
    valor_total numeric(19,2) NOT NULL,
    cupon_descuento_id bigint,
    direccion_cobro_id bigint NOT NULL,
    direccion_entrega_id bigint NOT NULL,
    factura_venta_id bigint NOT NULL,
    forma_pago_id bigint NOT NULL,
    persona_id bigint NOT NULL
);


ALTER TABLE public.vnt_cmpr_tnda_vrtl OWNER TO postgres;

--
-- TOC entry 3018 (class 0 OID 16943)
-- Dependencies: 202
-- Data for Name: acceso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3019 (class 0 OID 16948)
-- Dependencies: 203
-- Data for Name: categoria_producto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3020 (class 0 OID 16953)
-- Dependencies: 204
-- Data for Name: cuenta_pagar; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3021 (class 0 OID 16961)
-- Dependencies: 205
-- Data for Name: cuenta_recibir; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3022 (class 0 OID 16969)
-- Dependencies: 206
-- Data for Name: cupon_descuento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3049 (class 0 OID 17250)
-- Dependencies: 233
-- Data for Name: direccion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3048 (class 0 OID 17240)
-- Dependencies: 232
-- Data for Name: evaluacion_producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.evaluacion_producto VALUES (1, 'AAAAA', 5, 1, 1);


--
-- TOC entry 3051 (class 0 OID 17283)
-- Dependencies: 235
-- Data for Name: factura_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3023 (class 0 OID 16990)
-- Dependencies: 207
-- Data for Name: factura_item_producto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3052 (class 0 OID 17301)
-- Dependencies: 236
-- Data for Name: factura_venta; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3024 (class 0 OID 16995)
-- Dependencies: 208
-- Data for Name: forma_pago; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3025 (class 0 OID 17000)
-- Dependencies: 209
-- Data for Name: imagen_producto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3050 (class 0 OID 17268)
-- Dependencies: 234
-- Data for Name: item_venta_tienda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3026 (class 0 OID 17008)
-- Dependencies: 210
-- Data for Name: marca_producto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3056 (class 0 OID 17450)
-- Dependencies: 240
-- Data for Name: persona_fisica; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.persona_fisica VALUES (1, 'emermar27@hotmail.com', '3117397474', '1973-11-27', '4616818', 'Martinez', 'Viveros', 'Ermelson', '', 'C');


--
-- TOC entry 3057 (class 0 OID 17458)
-- Dependencies: 241
-- Data for Name: persona_juridica; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3053 (class 0 OID 17364)
-- Dependencies: 237
-- Data for Name: producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.producto VALUES (1, true, true, 100, 2, 10, 0, 10, 'Guadaña', 120, 'ffffddddd', 'Guadaña', 450, 1200000.00, 15, '1');


--
-- TOC entry 3042 (class 0 OID 17119)
-- Dependencies: 226
-- Data for Name: status_rastreo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3054 (class 0 OID 17392)
-- Dependencies: 238
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3027 (class 0 OID 17045)
-- Dependencies: 211
-- Data for Name: usuarios_acceso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3055 (class 0 OID 17405)
-- Dependencies: 239
-- Data for Name: vnt_cmpr_tnda_vrtl; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 212
-- Name: seq_acceso; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_acceso', 1, false);


--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 213
-- Name: seq_categoria_producto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_categoria_producto', 1, false);


--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 214
-- Name: seq_cuenta_pagar; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_cuenta_pagar', 1, false);


--
-- TOC entry 3066 (class 0 OID 0)
-- Dependencies: 215
-- Name: seq_cuenta_recibir; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_cuenta_recibir', 1, false);


--
-- TOC entry 3067 (class 0 OID 0)
-- Dependencies: 216
-- Name: seq_cupon_descuento; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_cupon_descuento', 1, false);


--
-- TOC entry 3068 (class 0 OID 0)
-- Dependencies: 217
-- Name: seq_direccion; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_direccion', 1, false);


--
-- TOC entry 3069 (class 0 OID 0)
-- Dependencies: 231
-- Name: seq_evaluacion_producto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_evaluacion_producto', 1, false);


--
-- TOC entry 3070 (class 0 OID 0)
-- Dependencies: 218
-- Name: seq_factura_compra; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_factura_compra', 1, false);


--
-- TOC entry 3071 (class 0 OID 0)
-- Dependencies: 219
-- Name: seq_factura_item_producto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_factura_item_producto', 1, false);


--
-- TOC entry 3072 (class 0 OID 0)
-- Dependencies: 227
-- Name: seq_factura_venta; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_factura_venta', 1, false);


--
-- TOC entry 3073 (class 0 OID 0)
-- Dependencies: 220
-- Name: seq_forma_pago; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_forma_pago', 1, false);


--
-- TOC entry 3074 (class 0 OID 0)
-- Dependencies: 221
-- Name: seq_imagen_producto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_imagen_producto', 1, false);


--
-- TOC entry 3075 (class 0 OID 0)
-- Dependencies: 230
-- Name: seq_item_venta_tienda; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_item_venta_tienda', 1, false);


--
-- TOC entry 3076 (class 0 OID 0)
-- Dependencies: 222
-- Name: seq_marca_producto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_marca_producto', 1, false);


--
-- TOC entry 3077 (class 0 OID 0)
-- Dependencies: 223
-- Name: seq_persona; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_persona', 1, false);


--
-- TOC entry 3078 (class 0 OID 0)
-- Dependencies: 224
-- Name: seq_producto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_producto', 1, false);


--
-- TOC entry 3079 (class 0 OID 0)
-- Dependencies: 228
-- Name: seq_status_rastreo; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_status_rastreo', 1, false);


--
-- TOC entry 3080 (class 0 OID 0)
-- Dependencies: 225
-- Name: seq_usuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_usuario', 1, false);


--
-- TOC entry 3081 (class 0 OID 0)
-- Dependencies: 229
-- Name: seq_vnt_cmpr_tnda_vrtl; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_vnt_cmpr_tnda_vrtl', 1, false);


--
-- TOC entry 2817 (class 2606 OID 16947)
-- Name: acceso acceso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acceso
    ADD CONSTRAINT acceso_pkey PRIMARY KEY (id);


--
-- TOC entry 2819 (class 2606 OID 16952)
-- Name: categoria_producto categoria_producto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_producto
    ADD CONSTRAINT categoria_producto_pkey PRIMARY KEY (id);


--
-- TOC entry 2821 (class 2606 OID 16960)
-- Name: cuenta_pagar cuenta_pagar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta_pagar
    ADD CONSTRAINT cuenta_pagar_pkey PRIMARY KEY (id);


--
-- TOC entry 2823 (class 2606 OID 16968)
-- Name: cuenta_recibir cuenta_recibir_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta_recibir
    ADD CONSTRAINT cuenta_recibir_pkey PRIMARY KEY (id);


--
-- TOC entry 2825 (class 2606 OID 16973)
-- Name: cupon_descuento cupon_descuento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupon_descuento
    ADD CONSTRAINT cupon_descuento_pkey PRIMARY KEY (id);


--
-- TOC entry 2843 (class 2606 OID 17257)
-- Name: direccion direccion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.direccion
    ADD CONSTRAINT direccion_pkey PRIMARY KEY (id);


--
-- TOC entry 2841 (class 2606 OID 17244)
-- Name: evaluacion_producto evaluacion_producto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evaluacion_producto
    ADD CONSTRAINT evaluacion_producto_pkey PRIMARY KEY (id);


--
-- TOC entry 2847 (class 2606 OID 17290)
-- Name: factura_compra factura_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_compra
    ADD CONSTRAINT factura_compra_pkey PRIMARY KEY (id);


--
-- TOC entry 2827 (class 2606 OID 16994)
-- Name: factura_item_producto factura_item_producto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_item_producto
    ADD CONSTRAINT factura_item_producto_pkey PRIMARY KEY (id);


--
-- TOC entry 2849 (class 2606 OID 17308)
-- Name: factura_venta factura_venta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_venta
    ADD CONSTRAINT factura_venta_pkey PRIMARY KEY (id);


--
-- TOC entry 2829 (class 2606 OID 16999)
-- Name: forma_pago forma_pago_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forma_pago
    ADD CONSTRAINT forma_pago_pkey PRIMARY KEY (id);


--
-- TOC entry 2831 (class 2606 OID 17007)
-- Name: imagen_producto imagen_producto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagen_producto
    ADD CONSTRAINT imagen_producto_pkey PRIMARY KEY (id);


--
-- TOC entry 2845 (class 2606 OID 17272)
-- Name: item_venta_tienda item_venta_tienda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venta_tienda
    ADD CONSTRAINT item_venta_tienda_pkey PRIMARY KEY (id);


--
-- TOC entry 2833 (class 2606 OID 17012)
-- Name: marca_producto marca_producto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca_producto
    ADD CONSTRAINT marca_producto_pkey PRIMARY KEY (id);


--
-- TOC entry 2857 (class 2606 OID 17457)
-- Name: persona_fisica persona_fisica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona_fisica
    ADD CONSTRAINT persona_fisica_pkey PRIMARY KEY (id);


--
-- TOC entry 2859 (class 2606 OID 17465)
-- Name: persona_juridica persona_juridica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona_juridica
    ADD CONSTRAINT persona_juridica_pkey PRIMARY KEY (id);


--
-- TOC entry 2851 (class 2606 OID 17371)
-- Name: producto producto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id);


--
-- TOC entry 2839 (class 2606 OID 17126)
-- Name: status_rastreo status_rastreo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_rastreo
    ADD CONSTRAINT status_rastreo_pkey PRIMARY KEY (id);


--
-- TOC entry 2835 (class 2606 OID 17049)
-- Name: usuarios_acceso uk_fne0e41l1rm9ee64vl1wwg2m7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acceso
    ADD CONSTRAINT uk_fne0e41l1rm9ee64vl1wwg2m7 UNIQUE (acceso_id);


--
-- TOC entry 2837 (class 2606 OID 17051)
-- Name: usuarios_acceso unique_acceso_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acceso
    ADD CONSTRAINT unique_acceso_user UNIQUE (usuario_id, acceso_id);


--
-- TOC entry 2853 (class 2606 OID 17399)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2855 (class 2606 OID 17409)
-- Name: vnt_cmpr_tnda_vrtl vnt_cmpr_tnda_vrtl_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vnt_cmpr_tnda_vrtl
    ADD CONSTRAINT vnt_cmpr_tnda_vrtl_pkey PRIMARY KEY (id);


--
-- TOC entry 2877 (class 2620 OID 17471)
-- Name: cuenta_pagar validaidpersonainsertar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonainsertar BEFORE INSERT ON public.cuenta_pagar FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2881 (class 2620 OID 17473)
-- Name: cuenta_recibir validaidpersonainsertar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonainsertar BEFORE INSERT ON public.cuenta_recibir FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2885 (class 2620 OID 17475)
-- Name: direccion validaidpersonainsertar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonainsertar BEFORE INSERT ON public.direccion FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2883 (class 2620 OID 17469)
-- Name: evaluacion_producto validaidpersonainsertar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonainsertar BEFORE INSERT ON public.evaluacion_producto FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2887 (class 2620 OID 17477)
-- Name: factura_compra validaidpersonainsertar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonainsertar BEFORE INSERT ON public.factura_compra FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2889 (class 2620 OID 17479)
-- Name: usuario validaidpersonainsertar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonainsertar BEFORE INSERT ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2891 (class 2620 OID 17481)
-- Name: vnt_cmpr_tnda_vrtl validaidpersonainsertar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonainsertar BEFORE INSERT ON public.vnt_cmpr_tnda_vrtl FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2879 (class 2620 OID 17484)
-- Name: cuenta_pagar validaidpersonainsertarcuentapagar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonainsertarcuentapagar BEFORE INSERT ON public.cuenta_pagar FOR EACH ROW EXECUTE FUNCTION public.validaidpersonacuentapagar();


--
-- TOC entry 2876 (class 2620 OID 17470)
-- Name: cuenta_pagar validaidpersonaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonaupdate BEFORE UPDATE ON public.cuenta_pagar FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2880 (class 2620 OID 17472)
-- Name: cuenta_recibir validaidpersonaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonaupdate BEFORE UPDATE ON public.cuenta_recibir FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2884 (class 2620 OID 17474)
-- Name: direccion validaidpersonaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonaupdate BEFORE UPDATE ON public.direccion FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2882 (class 2620 OID 17468)
-- Name: evaluacion_producto validaidpersonaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonaupdate BEFORE UPDATE ON public.evaluacion_producto FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2886 (class 2620 OID 17476)
-- Name: factura_compra validaidpersonaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonaupdate BEFORE UPDATE ON public.factura_compra FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2888 (class 2620 OID 17478)
-- Name: usuario validaidpersonaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonaupdate BEFORE UPDATE ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2890 (class 2620 OID 17480)
-- Name: vnt_cmpr_tnda_vrtl validaidpersonaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonaupdate BEFORE UPDATE ON public.vnt_cmpr_tnda_vrtl FOR EACH ROW EXECUTE FUNCTION public.validaidpersona();


--
-- TOC entry 2878 (class 2620 OID 17483)
-- Name: cuenta_pagar validaidpersonaupdatecuentapagar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validaidpersonaupdatecuentapagar BEFORE UPDATE ON public.cuenta_pagar FOR EACH ROW EXECUTE FUNCTION public.validaidpersonacuentapagar();


--
-- TOC entry 2863 (class 2606 OID 17100)
-- Name: usuarios_acceso acceso_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acceso
    ADD CONSTRAINT acceso_fk FOREIGN KEY (acceso_id) REFERENCES public.acceso(id);


--
-- TOC entry 2869 (class 2606 OID 17291)
-- Name: factura_compra cuenta_pagar_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_compra
    ADD CONSTRAINT cuenta_pagar_fk FOREIGN KEY (cuenta_pagar_id) REFERENCES public.cuenta_pagar(id);


--
-- TOC entry 2871 (class 2606 OID 17425)
-- Name: vnt_cmpr_tnda_vrtl cupon_descuento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vnt_cmpr_tnda_vrtl
    ADD CONSTRAINT cupon_descuento_fk FOREIGN KEY (cupon_descuento_id) REFERENCES public.cupon_descuento(id);


--
-- TOC entry 2872 (class 2606 OID 17430)
-- Name: vnt_cmpr_tnda_vrtl direccion_cobro_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vnt_cmpr_tnda_vrtl
    ADD CONSTRAINT direccion_cobro_fk FOREIGN KEY (direccion_cobro_id) REFERENCES public.direccion(id);


--
-- TOC entry 2873 (class 2606 OID 17435)
-- Name: vnt_cmpr_tnda_vrtl direccion_entrega_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vnt_cmpr_tnda_vrtl
    ADD CONSTRAINT direccion_entrega_fk FOREIGN KEY (direccion_entrega_id) REFERENCES public.direccion(id);


--
-- TOC entry 2860 (class 2606 OID 17296)
-- Name: factura_item_producto facctura_compra_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_item_producto
    ADD CONSTRAINT facctura_compra_fk FOREIGN KEY (factura_compra_id) REFERENCES public.factura_compra(id);


--
-- TOC entry 2874 (class 2606 OID 17440)
-- Name: vnt_cmpr_tnda_vrtl factura_venta_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vnt_cmpr_tnda_vrtl
    ADD CONSTRAINT factura_venta_fk FOREIGN KEY (factura_venta_id) REFERENCES public.factura_venta(id);


--
-- TOC entry 2875 (class 2606 OID 17445)
-- Name: vnt_cmpr_tnda_vrtl forma_pago_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vnt_cmpr_tnda_vrtl
    ADD CONSTRAINT forma_pago_fk FOREIGN KEY (forma_pago_id) REFERENCES public.forma_pago(id);


--
-- TOC entry 2866 (class 2606 OID 17372)
-- Name: evaluacion_producto producto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evaluacion_producto
    ADD CONSTRAINT producto_fk FOREIGN KEY (producto_id) REFERENCES public.producto(id);


--
-- TOC entry 2861 (class 2606 OID 17377)
-- Name: factura_item_producto producto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_item_producto
    ADD CONSTRAINT producto_fk FOREIGN KEY (producto_id) REFERENCES public.producto(id);


--
-- TOC entry 2862 (class 2606 OID 17382)
-- Name: imagen_producto producto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagen_producto
    ADD CONSTRAINT producto_fk FOREIGN KEY (producto_id) REFERENCES public.producto(id);


--
-- TOC entry 2867 (class 2606 OID 17387)
-- Name: item_venta_tienda producto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venta_tienda
    ADD CONSTRAINT producto_fk FOREIGN KEY (producto_id) REFERENCES public.producto(id);


--
-- TOC entry 2864 (class 2606 OID 17400)
-- Name: usuarios_acceso usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acceso
    ADD CONSTRAINT usuario_fk FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- TOC entry 2870 (class 2606 OID 17410)
-- Name: factura_venta vnt_cmpr_tnda_vrtl_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_venta
    ADD CONSTRAINT vnt_cmpr_tnda_vrtl_fk FOREIGN KEY (vnt_cmpr_tnda_vrtl_id) REFERENCES public.vnt_cmpr_tnda_vrtl(id);


--
-- TOC entry 2868 (class 2606 OID 17415)
-- Name: item_venta_tienda vnt_cmpr_tnda_vrtl_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venta_tienda
    ADD CONSTRAINT vnt_cmpr_tnda_vrtl_fk FOREIGN KEY (vnt_cmpr_tnda_vrtl_id) REFERENCES public.vnt_cmpr_tnda_vrtl(id);


--
-- TOC entry 2865 (class 2606 OID 17420)
-- Name: status_rastreo vnt_cmpr_tnda_vrtl_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_rastreo
    ADD CONSTRAINT vnt_cmpr_tnda_vrtl_fk FOREIGN KEY (vnt_cmpr_tnda_vrtl_id) REFERENCES public.vnt_cmpr_tnda_vrtl(id);


-- Completed on 2024-01-27 19:34:23

--
-- PostgreSQL database dump complete
--

