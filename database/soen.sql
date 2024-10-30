
REVOKE CONNECT ON DATABASE soendb FROM public;


--DROP SCHEMA public;
--CREATE SCHEMA public AUTHORIZATION pg_database_owner;



DROP SEQUENCE IF EXISTS public.administrator_id_seq;

CREATE SEQUENCE public.administrator_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

DROP SEQUENCE IF EXISTS public.booking_id_seq;

CREATE SEQUENCE public.booking_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

DROP SEQUENCE IF EXISTS public.client_id_seq;

CREATE SEQUENCE public.client_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

DROP SEQUENCE IF EXISTS public.guardian_id_seq;

CREATE SEQUENCE public.guardian_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

DROP SEQUENCE IF EXISTS public.instructor_id_seq;

CREATE SEQUENCE public.instructor_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

DROP SEQUENCE IF EXISTS public.location_id_seq;

CREATE SEQUENCE public.location_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

DROP SEQUENCE IF EXISTS public.location_schedule_id_seq;

CREATE SEQUENCE public.location_schedule_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

DROP SEQUENCE IF EXISTS public.offering_id_seq;

CREATE SEQUENCE public.offering_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

DROP SEQUENCE IF EXISTS public.representative_id_seq;

CREATE SEQUENCE public.representative_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

DROP SEQUENCE IF EXISTS public.schedule_id_seq;

CREATE SEQUENCE public.schedule_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

DROP SEQUENCE IF EXISTS public.space_id_seq;

CREATE SEQUENCE public.space_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;



-- public.administrator definition

DROP TABLE IF EXISTS public.administrator;

CREATE TABLE public.administrator (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"name" varchar NOT NULL,
	phone text NOT NULL,
	age int4 NULL,
	CONSTRAINT administrator_pk PRIMARY KEY (id)
);


-- public.guardian definition

DROP TABLE IF EXISTS public.guardian CASCADE;

CREATE TABLE public.guardian (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"name" varchar NOT NULL,
	phone text NOT NULL,
	age int4 NULL,
	CONSTRAINT guardian_pk PRIMARY KEY (id)
);


-- public.client definition

DROP TABLE IF EXISTS public.client CASCADE;

CREATE TABLE public.client (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"name" varchar NOT NULL,
	age int4 NULL,
	phone text NOT NULL,
	CONSTRAINT client_pk PRIMARY KEY (id)
);


-- public.instructor definition

DROP TABLE IF EXISTS public.instructor;

CREATE TABLE public.instructor (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"name" varchar NOT NULL,
	phone text NOT NULL,
	age int4 NULL,
	specializations _text NULL,
	availabilities text NULL,
	CONSTRAINT instructor_pk PRIMARY KEY (id)
);


-- public.representative definition

DROP TABLE IF EXISTS public.representative;

CREATE TABLE public.representative (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	guardian_id int8 NOT NULL,
	client_id int8 NOT NULL,
	relationship varchar NULL,
	CONSTRAINT representative_pk PRIMARY KEY (id),
	CONSTRAINT representative_fk FOREIGN KEY (guardian_id) REFERENCES public.guardian(id) ON DELETE CASCADE,
	CONSTRAINT representative_fk_1 FOREIGN KEY (client_id) REFERENCES public.client(id)
);



-- public.location definition

DROP TABLE IF EXISTS public."location" CASCADE;

CREATE TABLE public."location" (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"name" varchar NULL,
	city varchar NOT NULL,
	CONSTRAINT location_pk PRIMARY KEY (id)
);


-- public.schedule definition

DROP TABLE IF EXISTS public.schedule CASCADE;

CREATE TABLE public.schedule (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	start_date date NULL,
	end_date date NULL,
	start_t time NULL,
	end_t time NULL,
	time_slots _int8 NOT NULL,
	CONSTRAINT schedule_pk PRIMARY KEY (id)
);


-- public.location_schedule definition

DROP TABLE IF EXISTS public.location_schedule CASCADE;

CREATE TABLE public.location_schedule (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	location_id int8 NOT NULL,
	schedule_id int8 NOT NULL,
	CONSTRAINT location_schedule_pk PRIMARY KEY (id),
	CONSTRAINT location_schedule_fk FOREIGN KEY (location_id) REFERENCES public.location(id),
	CONSTRAINT location_schedule_fk_1 FOREIGN KEY (schedule_id) REFERENCES public.schedule(id)
);

-- public."space" definition

DROP TABLE IF EXISTS public."space" CASCADE;

CREATE TABLE public."space" (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"type" varchar NULL,
	location_schedule_id int8 NOT NULL,
	CONSTRAINT space_pk PRIMARY KEY (id),
	CONSTRAINT space_fk FOREIGN KEY (location_schedule_id) REFERENCES public.location_schedule(id)
);


-- public.offering definition

DROP TABLE IF EXISTS public.offering CASCADE;

CREATE TABLE public.offering (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	status varchar NOT NULL DEFAULT 'non-available'::character varying,
	active bool NULL DEFAULT true,
	taken bool NOT NULL DEFAULT false,
	location_schedule_id int8 NOT NULL,
	CONSTRAINT offering_pk PRIMARY KEY (id),
	CONSTRAINT offering_fk FOREIGN KEY (location_schedule_id) REFERENCES public.location_schedule(id)
);

-- public.booking definition

DROP TABLE IF EXISTS public.booking CASCADE;

CREATE TABLE public.booking (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	client_id int8 NOT NULL,
	offering_id int8 NOT NULL,
	status varchar NULL,
	active bool NULL DEFAULT true,
	CONSTRAINT booking_pk PRIMARY KEY (id),
	CONSTRAINT booking_fk FOREIGN KEY (client_id) REFERENCES public.client(id),
	CONSTRAINT booking_fk_1 FOREIGN KEY (offering_id) REFERENCES public.offering(id)
);
    

-- ALTER SEQUENCE IF EXISTS public.administrator_id_seq OWNED BY public.administrator.id;
-- ALTER SEQUENCE IF EXISTS public.booking_id_seq OWNED BY public.booking.id;
-- ALTER SEQUENCE IF EXISTS public.client_id_seq OWNED BY public.client.id;
-- ALTER SEQUENCE IF EXISTS public.guardian_id_seq OWNED BY public.guardian.id;
-- ALTER SEQUENCE IF EXISTS public.instructor_id_seq OWNED BY public.instructor.id;
-- ALTER SEQUENCE IF EXISTS public.location_id_seq OWNED BY public."location".id;
-- ALTER SEQUENCE IF EXISTS public.location_schedule_id_seq OWNED BY public.location_schedule.id;
-- ALTER SEQUENCE IF EXISTS public.offering_id_seq OWNED BY public.offering.id;
-- ALTER SEQUENCE IF EXISTS public.representative_id_seq OWNED BY public.representative.id;
-- ALTER SEQUENCE IF EXISTS public.schedule_id_seq OWNED BY public.schedule.id;
-- ALTER SEQUENCE IF EXISTS public.space_id_seq OWNED BY public."space".id;


INSERT INTO administrator ("name", phone, age) VALUES 
('kristi', '1111111', '88'),
('mehdi', '2222222', '88');
