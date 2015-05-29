CONNECT di18
CREATE SCHEMA IF NOT EXISTS uber
  AUTHORIZATION di18;
  
  
CREATE TABLE uber.activities
(
  name character varying(100) NOT NULL,
  kg59 double precision NOT NULL,
  kg70 double precision NOT NULL,
  kg81 double precision NOT NULL,
  kg92 double precision NOT NULL,
  CONSTRAINT activities_pkey PRIMARY KEY (name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE uber.activities
  OWNER TO di18;
  
  
CREATE TABLE uber.bmi
(
  "Date" date NOT NULL DEFAULT ('now'::text)::date,
  bmi double precision NOT NULL,
  user_iduser integer NOT NULL,
  idbmi bigserial NOT NULL,
  CONSTRAINT bmi_pkey PRIMARY KEY (idbmi),
  CONSTRAINT bmi_user_iduser_fkey FOREIGN KEY (user_iduser)
      REFERENCES uber."user" (iduser) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE uber.bmi
  OWNER TO di18;
  
 CREATE TABLE uber.intake
(
  amount double precision NOT NULL,
  "Datetime" timestamp with time zone NOT NULL DEFAULT now(),
  user_iduser integer NOT NULL,
  idintake bigserial NOT NULL,
  idfood integer NOT NULL,
  CONSTRAINT intake_pkey PRIMARY KEY (idintake),
  CONSTRAINT intake_idfood_fkey FOREIGN KEY (idfood)
      REFERENCES uber.stdfood (idfood) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT intake_user_iduser_fkey FOREIGN KEY (user_iduser)
      REFERENCES uber."user" (iduser) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE uber.intake
  OWNER TO di18;v

  CREATE TABLE uber.stdfood
(
  calorie double precision NOT NULL,
  amount double precision,
  unit character varying(15),
  protein double precision,
  carbon double precision,
  fat double precision,
  iduser integer,
  name character varying(100) NOT NULL,
  idfood serial NOT NULL,
  CONSTRAINT stdfood_pkey PRIMARY KEY (idfood)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE uber.stdfood
  OWNER TO di18;
  
  CREATE TABLE uber.usage
(
  amount double precision NOT NULL,
  "Date" date NOT NULL DEFAULT ('now'::text)::date,
  user_iduser integer NOT NULL,
  activities_name character varying(100) NOT NULL,
  idusage bigserial NOT NULL,
  CONSTRAINT usage_pkey PRIMARY KEY (idusage),
  CONSTRAINT usage_activities_name_fkey FOREIGN KEY (activities_name)
      REFERENCES uber.activities (name) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT usage_user_iduser_fkey FOREIGN KEY (user_iduser)
      REFERENCES uber."user" (iduser) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE uber.usage
  OWNER TO di18;
  
  CREATE TABLE uber."user"
(
  iduser integer NOT NULL DEFAULT nextval('"Users_uid_seq"'::regclass),
  surname character varying(45) NOT NULL,
  firstname character varying(45) NOT NULL,
  "Length" double precision,
  username character varying(45) NOT NULL UNIQUE,
  email character varying(45) NOT NULL UNIQUE,
  password character varying(160) NOT NULL,
  gender character varying(1),
  age integer,
  CONSTRAINT user_pkey PRIMARY KEY (iduser) 
)
WITH (
  OIDS=FALSE
);
ALTER TABLE uber."user"
  OWNER TO di18;

  CREATE TABLE uber.weight
(
  weight double precision NOT NULL,
  "Date" date NOT NULL DEFAULT ('now'::text)::date,
  user_iduser integer NOT NULL,
  width double precision,
  idweight bigserial NOT NULL,
  CONSTRAINT weight_pkey PRIMARY KEY (idweight),
  CONSTRAINT weight_user_iduser_fkey FOREIGN KEY (user_iduser)
      REFERENCES uber."user" (iduser) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE uber.weight
  OWNER TO di18;
  
  CREATE TABLE uber.fat
  (
  fatpercentage double precision NOT NULL,
  "Date" date NOT NULL DEFAULT ('now'::text)::date,
  user_iduser integer NOT NULL,
  idfat bigserial NOT NULL,
  CONSTRAINT fat_pkey PRIMARY KEY (idfat),
  CONSTRAINT fat_user_iduser_fkey FOREIGN KEY (user_iduser)
	REFERENCES uber."user" (iduser) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
 OIDS = FALSE
);
ALTER TABLE uber.fat
	OWNER TO di18;
  
