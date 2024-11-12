
REVOKE CONNECT ON DATABASE soendb FROM public;


CREATE TYPE roleType AS enum ('client', 'guardian', 'instructor', 'admin', 'guest');

-- public.user definition

DROP TABLE IF EXISTS public.users;

CREATE TABLE public.users (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	active bool NULL DEFAULT true,
	"name" varchar NOT NULL,
	age int4 NOT NULL,
	phone text NOT NULL,
	"role" roleType NOT NULL DEFAULT 'guest',
	CONSTRAINT user_pk PRIMARY KEY (id)
);

-- public.client definition

DROP TABLE IF EXISTS public.client;

CREATE TABLE public.client (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"role" roleType NOT NULL DEFAULT 'client',
	dependant bool NULL DEFAULT false,
	CONSTRAINT client_pk PRIMARY KEY (id),
	UNIQUE (phone)
) inherits (users);


-- public.administrator definition

DROP TABLE IF EXISTS public.administrator;

CREATE TABLE public.administrator (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"role" roleType NOT NULL DEFAULT 'admin',
	CONSTRAINT administrator_pk PRIMARY KEY (id),
	UNIQUE (phone)
) inherits (users);


-- public.guardian definition

DROP TABLE IF EXISTS public.guardian;

CREATE TABLE public.guardian (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"role" roleType NOT NULL DEFAULT 'guardian',
	CONSTRAINT guardian_pk PRIMARY KEY (id),
	UNIQUE (phone)
) inherits (users);



-- public.instructor definition

DROP TABLE IF EXISTS public.instructor;

CREATE TABLE public.instructor (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"role" roleType NOT NULL DEFAULT 'instructor',
	specializations _text NOT NULL,
	availabilities _text NOT NULL,
	CONSTRAINT instructor_pk PRIMARY KEY (id),
	UNIQUE (phone)
) inherits (users);


-- public.representative definition

DROP TABLE IF EXISTS public.representative;

CREATE TABLE public.representative (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	active bool NULL DEFAULT true,
	guardian_id int8 NOT NULL,
	client_id int8 NOT NULL,
	relationship varchar NULL,
	CONSTRAINT representative_pk PRIMARY KEY (id),
	CONSTRAINT representative_fk FOREIGN KEY (guardian_id) REFERENCES public.guardian(id) ON DELETE CASCADE,
	CONSTRAINT representative_fk_1 FOREIGN KEY (client_id) REFERENCES public.client(id) ON DELETE CASCADE,
	check (guardian_id <> client_id)
);

-- public.location definition

DROP TABLE IF EXISTS public."location" CASCADE;

CREATE TABLE public."location" (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	active bool NULL DEFAULT true,
	"name" varchar NULL,
	"address" text NULL,
	city varchar NOT NULL,
	CONSTRAINT location_pk PRIMARY KEY (id),
	UNIQUE ("name", "address", city)
);



CREATE TYPE day_of_week AS enum (
    'sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat'
);

-- public.schedule definition
DROP TABLE IF EXISTS public.schedule;

CREATE TABLE public.schedule (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	active bool NULL DEFAULT true,
	slot tsrange NOT NULL,
	weekdays _day_of_week NULL,
	CONSTRAINT schedule_pk PRIMARY KEY (id),
	EXCLUDE USING GIST (slot WITH &&)
);

-- public."space" definition

DROP TABLE IF EXISTS public."space";

CREATE TABLE public."space" (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	active bool NULL DEFAULT true,
	"type" varchar NULL,
	location_id int8 NOT NULL,
	schedule_id int8 NOT NULL,
	CONSTRAINT space_pk PRIMARY KEY (id),
	CONSTRAINT space_fk_1 FOREIGN KEY (location_id) REFERENCES public.location(id),
	CONSTRAINT space_fk_2 FOREIGN KEY (schedule_id) REFERENCES public.schedule(id)
);


-- public.offering definition

DROP TABLE IF EXISTS public.offering;

CREATE TABLE public.offering (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	active bool NULL DEFAULT true,
	"status" varchar NOT NULL DEFAULT 'non-available'::character varying,
	taken bool NOT NULL DEFAULT false,
	"type" varchar NOT NULL,
	mode bool NOT NULL DEFAULT false,
	seats int4 NOT NULL DEFAULT 1,
	CONSTRAINT offering_pk PRIMARY KEY (id)
);


-- public.location_schedule definition

DROP TABLE IF EXISTS public.location_schedule;

CREATE TABLE public.location_schedule (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	active bool NULL DEFAULT true,
	location_id int8 NOT NULL,
	schedule_id int8 NOT NULL,
	offering_id int8 NOT NULL,
	CONSTRAINT location_schedule_pk PRIMARY KEY (id),
	CONSTRAINT location_schedule_fk FOREIGN KEY (location_id) REFERENCES public.location(id),
	CONSTRAINT location_schedule_fk_1 FOREIGN KEY (schedule_id) REFERENCES public.schedule(id),
	CONSTRAINT location_schedule_fk_2 FOREIGN KEY (offering_id) REFERENCES public.offering(id),
	UNIQUE (offering_id),
	UNIQUE (location_id, schedule_id)
);

DROP TABLE IF EXISTS public.instructor_offering;

CREATE TABLE public.instructor_offering (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	active bool NULL DEFAULT true,
	instructor_id int8 NOT NULL,
	offering_id int8 NOT NULL,
	CONSTRAINT instructor_offering_pk PRIMARY KEY (id),
	CONSTRAINT instructor_offering_fk FOREIGN KEY (instructor_id) REFERENCES public.instructor(id),
	CONSTRAINT instructor_offering_fk_1 FOREIGN KEY (offering_id) REFERENCES public.offering(id),
	UNIQUE (offering_id)
);


-- public.booking definition

DROP TABLE IF EXISTS public.booking;

CREATE TABLE public.booking (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	active bool NULL DEFAULT true,
	client_id int8 NOT NULL,
	offering_id int8 NOT NULL,
	"status" varchar NULL,
	CONSTRAINT booking_pk PRIMARY KEY (id),
	CONSTRAINT booking_fk FOREIGN KEY (client_id) REFERENCES public.client(id),
	CONSTRAINT booking_fk_1 FOREIGN KEY (offering_id) REFERENCES public.offering(id)
);
    
-- FUNCTIONS

CREATE OR REPLACE FUNCTION dates_overlap(cid bigint, startDate date, endDate date)
  RETURNS setof schedule
  LANGUAGE plpgsql AS
$$
	begin return query
		select s.*
		from schedule s
		join location_schedule ls on s.id = ls.schedule_id 
		join offering o on ls.offering_id = o.id 
		join booking b on o.id = b.offering_id 
		where b.client_id = cid
		and (s.start_date, s.end_date) overlaps (startDate, endDate);
	end;
$$;

CREATE OR REPLACE FUNCTION weekdays_overlap(cid bigint, weekdays _day_of_week)
  RETURNS setof schedule
  LANGUAGE plpgsql AS
$$
	begin return query
		select s.*
		from schedule s
		join location_schedule ls on s.id = ls.schedule_id 
		join offering o on ls.offering_id = o.id 
		join booking b on o.id = b.offering_id 
		where b.client_id = cid
		and weekdays && s.weekdays;
	end;
$$;


-- Test Data

INSERT INTO administrator ("name", phone, age, "role") VALUES 
('kristi', '1', '88', 'admin'),
('mehdi', '2', '88', 'admin');


INSERT INTO client (active, "name", age, phone, "role", dependant) VALUES 
(true, 'k', 20, '1', 'client', false),
(true, 'k1', 20, '2', 'client', false),
(true, 'k2', 12, '3', 'client', true),
(true, 'k3', 14, '4', 'client', true),
(true, 'm', 20, '5', 'client', false),
(true, 'm1', 20, '6', 'client', false),
(true, 'm2', 20, '7', 'client', true);


INSERT INTO guardian (active, "name", age, phone, "role") VALUES 
(true, 'gk', 20, '1', 'guardian'),
(true, 'gm', 20, '2', 'guardian');


INSERT INTO instructor (active, "name", age, phone, "role", specializations, availabilities) VALUES 
(true, 'i1', 20, '1', 'instructor', '{gym, swimming, judo}', '{montreal, laval}'),
(true, 'i2', 20, '2', 'instructor', '{yoga}', '{montreal}'),
(true, 'i3', 20, '3', 'instructor', '{boxing, dance}', '{montreal, laval, brossard}');


INSERT INTO representative (active, guardian_id, client_id, relationship) VALUES 
(true, 1, 3, 'father'),
(true, 1, 4, 'father'),
(true, 2, 7, 'guardian');


INSERT INTO "location" (active, "name", "address", city) VALUES 
(true, 'A building room 1', '1000 St Catherine', 'montreal'),
(true, 'B building room 2', '1001 St Catherine', 'montreal'),
(true, 'C building room 3', '1002 St Catherine', 'montreal'),
(true, 'A building room 1', '1000 street', 'laval'),
(true, 'B building room 2', '1001 street', 'laval'),
(true, 'C building room 3', '1002 street', 'laval'),
(true, 'A building room 1', '1000 street', 'brossard'),
(true, 'B building room 2', '1001 street', 'brossard'),
(true, 'C building room 3', '1002 street', 'brossard');


INSERT INTO schedule (active, slot, weekdays) VALUES 
(true, '[2024-11-01 05:00:00, 2024-11-01 06:00:00)', '{"sun"}'),
(true, '[2024-11-01 06:00:00, 2024-11-01 07:00:00)', '{"sun"}'),
(true, '[2024-11-01 07:00:00, 2024-11-01 08:00:00)', '{"sun"}'),
(true, '[2024-11-01 08:00:00, 2024-11-01 09:00:00)', '{"sun"}'),
(true, '[2024-11-01 09:00:00, 2024-11-01 10:00:00)', '{"sun"}'),
(true, '[2024-11-01 10:00:00, 2024-11-01 11:00:00)', '{"sun"}'),
(true, '[2024-11-01 11:00:00, 2024-11-01 12:00:00)', '{"sun"}'),
(true, '[2024-11-01 12:00:00, 2024-11-01 13:00:00)', '{"sun"}'),
(true, '[2024-11-01 13:00:00, 2024-11-01 14:00:00)', '{"sun"}'),
(true, '[2024-11-01 14:00:00, 2024-11-01 15:00:00)', '{"sun"}'),
(true, '[2024-11-01 15:00:00, 2024-11-01 16:00:00)', '{"sun"}'),
(true, '[2024-11-01 16:00:00, 2024-11-01 17:00:00)', '{"sun"}'),
(true, '[2024-11-01 17:00:00, 2024-11-01 18:00:00)', '{"sun"}'),
(true, '[2024-11-01 18:00:00, 2024-11-01 19:00:00)', '{"sun"}'),
(true, '[2024-11-01 19:00:00, 2024-11-01 20:00:00)', '{"sun"}'),
(true, '[2024-11-01 20:00:00, 2024-11-01 21:00:00)', '{"sun"}'),
(true, '[2024-11-01 21:00:00, 2024-11-01 22:00:00)', '{"sun"}');



INSERT INTO offering (active, "status", taken, "type", mode, seats) VALUES 
(true, 'non-available', false, 'yoga', false, 1),
(true, 'non-available', false, 'yoga', true, 16),
(true, 'non-available', true, 'swimming', false, 0),
(true, 'available', true, 'swimming', false, 1),
(true, 'available', true, 'judo', true, 12);


INSERT INTO location_schedule (active, location_id, schedule_id, offering_id) VALUES 
(true, 1, 1, 3),
(true, 1, 2, 4),
(true, 1, 3, 5);

INSERT INTO instructor_offering (active, instructor_id, offering_id) VALUES 
(true, 1, 3),
(true, 1, 4),
(true, 1, 5);

INSERT INTO booking (active, client_id, offering_id, "status") VALUES 
(true, 1, 3, 'booked');
