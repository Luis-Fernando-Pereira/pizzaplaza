--
-- PostgreSQL database dump
--

\restrict wQ9cgZPEDN1mrhrfNtF5AM62KvGFoAYYf0n2ux9Jsl2Fg3E5vd9dCGV9a694K7p

-- Dumped from database version 16.13 (Ubuntu 16.13-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 16.13 (Ubuntu 16.13-0ubuntu0.24.04.1)

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
-- Name: order_service; Type: SCHEMA; Schema: -; Owner: fernando
--

CREATE SCHEMA order_service;


ALTER SCHEMA order_service OWNER TO fernando;

--
-- Name: product_service; Type: SCHEMA; Schema: -; Owner: fernando
--

CREATE SCHEMA product_service;


ALTER SCHEMA product_service OWNER TO fernando;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: costumer; Type: TABLE; Schema: order_service; Owner: fernando
--

CREATE TABLE order_service.costumer (
    oid character varying(36) NOT NULL,
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    cpf character varying(14),
    name character varying(100) NOT NULL,
    user_oid character varying(38)
);


ALTER TABLE order_service.costumer OWNER TO fernando;

--
-- Name: order; Type: TABLE; Schema: order_service; Owner: fernando
--

CREATE TABLE order_service."order" (
    oid character varying(36) NOT NULL,
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    status character varying(20) NOT NULL,
    total_price numeric(38,2) NOT NULL,
    costumer_oid character varying(36),
    CONSTRAINT order_status_check CHECK (((status)::text = ANY ((ARRAY['RECEIVED'::character varying, 'PREPARING'::character varying, 'LEFT_FOR_DELIVERY'::character varying, 'DELIVERED'::character varying])::text[])))
);


ALTER TABLE order_service."order" OWNER TO fernando;

--
-- Name: pizza; Type: TABLE; Schema: order_service; Owner: fernando
--

CREATE TABLE order_service.pizza (
    oid character varying(36) NOT NULL,
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    size character varying(10) NOT NULL,
    unit_price numeric(38,2) NOT NULL,
    order_oid character varying(36) NOT NULL,
    CONSTRAINT pizza_size_check CHECK (((size)::text = ANY ((ARRAY['SMALL'::character varying, 'MEDIUM'::character varying, 'LARGE'::character varying, 'GIANT'::character varying])::text[])))
);


ALTER TABLE order_service.pizza OWNER TO fernando;

--
-- Name: pizza_flavor_snapshot; Type: TABLE; Schema: order_service; Owner: fernando
--

CREATE TABLE order_service.pizza_flavor_snapshot (
    oid character varying(36) NOT NULL,
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    description character varying(255),
    flavor_oid character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    price numeric(38,2) NOT NULL,
    pizza_oid character varying(36) NOT NULL
);


ALTER TABLE order_service.pizza_flavor_snapshot OWNER TO fernando;

--
-- Name: category; Type: TABLE; Schema: product_service; Owner: fernando
--

CREATE TABLE product_service.category (
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    oid character varying(36) NOT NULL,
    description character varying(100) NOT NULL,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255)
);


ALTER TABLE product_service.category OWNER TO fernando;

--
-- Name: flavor; Type: TABLE; Schema: product_service; Owner: fernando
--

CREATE TABLE product_service.flavor (
    price numeric(38,2) NOT NULL,
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    oid character varying(36) NOT NULL,
    name character varying(50) NOT NULL,
    description text,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255)
);


ALTER TABLE product_service.flavor OWNER TO fernando;

--
-- Name: flavor_category; Type: TABLE; Schema: product_service; Owner: fernando
--

CREATE TABLE product_service.flavor_category (
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    category_oid character varying(36) NOT NULL,
    flavor_oid character varying(36) NOT NULL,
    oid character varying(36) NOT NULL,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255)
);


ALTER TABLE product_service.flavor_category OWNER TO fernando;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: fernando
--

CREATE TABLE public.admin (
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    oid character varying(36) NOT NULL,
    user_id character varying(36) NOT NULL,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    user_oid character varying(36) NOT NULL
);


ALTER TABLE public.admin OWNER TO fernando;

--
-- Name: category; Type: TABLE; Schema: public; Owner: fernando
--

CREATE TABLE public.category (
    oid character varying(36) NOT NULL,
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    description character varying(100) NOT NULL
);


ALTER TABLE public.category OWNER TO fernando;

--
-- Name: client; Type: TABLE; Schema: public; Owner: fernando
--

CREATE TABLE public.client (
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    oid character varying(36) NOT NULL,
    user_id character varying(36),
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    user_oid character varying(36)
);


ALTER TABLE public.client OWNER TO fernando;

--
-- Name: flavor; Type: TABLE; Schema: public; Owner: fernando
--

CREATE TABLE public.flavor (
    oid character varying(36) NOT NULL,
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    name character varying(50) NOT NULL,
    category_oid character varying(36),
    price double precision NOT NULL
);


ALTER TABLE public.flavor OWNER TO fernando;

--
-- Name: flavor_category; Type: TABLE; Schema: public; Owner: fernando
--

CREATE TABLE public.flavor_category (
    oid character varying(36) NOT NULL,
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    category_oid character varying(36) NOT NULL,
    flavor_oid character varying(36) NOT NULL
);


ALTER TABLE public.flavor_category OWNER TO fernando;

--
-- Name: pizza; Type: TABLE; Schema: public; Owner: fernando
--

CREATE TABLE public.pizza (
    oid character varying(36) NOT NULL,
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    size character varying(10) NOT NULL,
    order_oid character varying(36),
    CONSTRAINT pizza_size_check CHECK (((size)::text = ANY ((ARRAY['SMALL'::character varying, 'MEDIUM'::character varying, 'LARGE'::character varying, 'GIANT'::character varying])::text[])))
);


ALTER TABLE public.pizza OWNER TO fernando;

--
-- Name: pizza_flavor; Type: TABLE; Schema: public; Owner: fernando
--

CREATE TABLE public.pizza_flavor (
    oid character varying(36) NOT NULL,
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    flavor_oid character varying(36),
    pizza_oid character varying(36)
);


ALTER TABLE public.pizza_flavor OWNER TO fernando;

--
-- Name: pizzaplaza_user; Type: TABLE; Schema: public; Owner: fernando
--

CREATE TABLE public.pizzaplaza_user (
    authenticated boolean NOT NULL,
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    oid character varying(36) NOT NULL,
    cpf character varying(255),
    email character varying(255) NOT NULL,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    name character varying(255),
    password character varying(255) NOT NULL
);


ALTER TABLE public.pizzaplaza_user OWNER TO fernando;

--
-- Name: seller; Type: TABLE; Schema: public; Owner: fernando
--

CREATE TABLE public.seller (
    changed_at timestamp(6) without time zone,
    created_at timestamp(6) without time zone,
    oid character varying(36) NOT NULL,
    user_id character varying(36) NOT NULL,
    id_user_who_changed character varying(255),
    id_user_who_created character varying(255),
    user_oid character varying(36) NOT NULL
);


ALTER TABLE public.seller OWNER TO fernando;

--
-- Data for Name: costumer; Type: TABLE DATA; Schema: order_service; Owner: fernando
--

COPY order_service.costumer (oid, changed_at, created_at, id_user_who_changed, id_user_who_created, cpf, name, user_oid) FROM stdin;
476d2bed-1a7b-4946-8bc6-3c9e3b3be4b7	\N	2026-06-02 21:04:48.066	\N	\N	USER_CPF	USER_NAME	USER_OID
415aa5a9-0430-4850-bb37-1b7dea9e743d	\N	2026-06-07 19:24:14.613	\N	\N	USER_CPF	USER_NAME	USER_OID
\.


--
-- Data for Name: order; Type: TABLE DATA; Schema: order_service; Owner: fernando
--

COPY order_service."order" (oid, changed_at, created_at, id_user_who_changed, id_user_who_created, status, total_price, costumer_oid) FROM stdin;
5fc3cc5d-d18c-41f3-8d1a-b7cf787248be	\N	2026-06-02 21:04:48.063	\N	\N	RECEIVED	80.00	476d2bed-1a7b-4946-8bc6-3c9e3b3be4b7
be137d90-cbcd-4bce-adec-be0566b25b22	\N	2026-06-07 19:24:14.611	\N	\N	RECEIVED	160.00	415aa5a9-0430-4850-bb37-1b7dea9e743d
\.


--
-- Data for Name: pizza; Type: TABLE DATA; Schema: order_service; Owner: fernando
--

COPY order_service.pizza (oid, changed_at, created_at, id_user_who_changed, id_user_who_created, size, unit_price, order_oid) FROM stdin;
3a6df0ea-b93c-4da0-a3de-b38117f8d674	\N	2026-06-02 21:04:48.072	\N	\N	LARGE	80.00	5fc3cc5d-d18c-41f3-8d1a-b7cf787248be
4c9bf649-38c2-48bf-a553-62fe8ee4b681	\N	2026-06-07 19:24:14.626	\N	\N	LARGE	80.00	be137d90-cbcd-4bce-adec-be0566b25b22
94aafee9-62db-406b-aea4-46d2f3f1eb8b	\N	2026-06-07 19:24:14.627	\N	\N	SMALL	80.00	be137d90-cbcd-4bce-adec-be0566b25b22
\.


--
-- Data for Name: pizza_flavor_snapshot; Type: TABLE DATA; Schema: order_service; Owner: fernando
--

COPY order_service.pizza_flavor_snapshot (oid, changed_at, created_at, id_user_who_changed, id_user_who_created, description, flavor_oid, name, price, pizza_oid) FROM stdin;
bfbf1267-d7b7-442e-9812-32c7ff74d5eb	\N	2026-06-02 21:04:48.072	\N	\N	Pizza de frango vivo com catupiry. Brincadeira kkkkk o frango morreu afogado em catupiry	8c9f3cef-db12-4242-a031-736a556d21b3	Frango com catupiry	80.00	3a6df0ea-b93c-4da0-a3de-b38117f8d674
707cbab5-a549-41c0-a062-ed388165fd2e	\N	2026-06-07 19:24:14.626	\N	\N	Pizza de frango vivo com catupiry. Brincadeira kkkkk o frango morreu afogado em catupiry	8c9f3cef-db12-4242-a031-736a556d21b3	Frango com catupiry	80.00	4c9bf649-38c2-48bf-a553-62fe8ee4b681
697bd521-713f-4453-a2c5-f4f604eaf01d	\N	2026-06-07 19:24:14.626	\N	\N	Pizza de calabresa com muita calabresa, compre, é boa 👍	a274b1e6-ba03-432c-8157-2028b45bf4df	Calabresa	80.00	4c9bf649-38c2-48bf-a553-62fe8ee4b681
7d0d80b6-7e88-448d-bf98-56486b2234c8	\N	2026-06-07 19:24:14.626	\N	\N	Pizza de 4 queijos porque faltou o quinto	122cc7d8-aa81-4202-b3c9-7346e4b7f023	4 Queijos	80.00	4c9bf649-38c2-48bf-a553-62fe8ee4b681
2e830eb6-1311-4fc1-8ebe-e1b35ccfeee2	\N	2026-06-07 19:24:14.627	\N	\N	Pizza de frango vivo com catupiry. Brincadeira kkkkk o frango morreu afogado em catupiry	8c9f3cef-db12-4242-a031-736a556d21b3	Frango com catupiry	80.00	94aafee9-62db-406b-aea4-46d2f3f1eb8b
fc85eb0e-c97d-4a7c-91a0-c884c71513f3	\N	2026-06-07 19:24:14.627	\N	\N	Sequestramos ela direto de Portugal, aproveite enquanto está fresquinha!	b078578b-53b5-4834-a395-d447e33f7c4e	Portuguesa	80.00	94aafee9-62db-406b-aea4-46d2f3f1eb8b
6a23c6e7-ef32-4518-a6c8-a14f9d558224	\N	2026-06-07 19:24:14.627	\N	\N	Pizza fresca, tão fresca que o bacon é removido do suino ainda vivo para dar aquele gostin de bacon fresco na pizza	58e3a9d8-ddde-4c2e-86a6-493d440a3b52	Bacon	80.00	94aafee9-62db-406b-aea4-46d2f3f1eb8b
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: product_service; Owner: fernando
--

COPY product_service.category (changed_at, created_at, oid, description, id_user_who_changed, id_user_who_created) FROM stdin;
2026-05-31 19:57:00.816	2026-05-25 20:44:27.03	d4c7550d-7f8e-4213-b26e-25a2bab486e0	Salgada	\N	\N
2026-05-31 19:58:58.344	2026-05-25 21:03:11.244	34f73124-7f73-47e5-bb35-6aea90294797	Acebolada	\N	\N
\.


--
-- Data for Name: flavor; Type: TABLE DATA; Schema: product_service; Owner: fernando
--

COPY product_service.flavor (price, changed_at, created_at, oid, name, description, id_user_who_changed, id_user_who_created) FROM stdin;
80.00	2026-05-31 19:39:03.312	2026-05-25 21:20:41.179	8c9f3cef-db12-4242-a031-736a556d21b3	Frango com catupiry	Pizza de frango vivo com catupiry. Brincadeira kkkkk o frango morreu afogado em catupiry	\N	\N
80.00	\N	2026-05-31 19:40:11.838	b078578b-53b5-4834-a395-d447e33f7c4e	Portuguesa	Sequestramos ela direto de Portugal, aproveite enquanto está fresquinha!	\N	\N
80.00	\N	2026-05-31 19:43:34.627	58e3a9d8-ddde-4c2e-86a6-493d440a3b52	Bacon	Pizza fresca, tão fresca que o bacon é removido do suino ainda vivo para dar aquele gostin de bacon fresco na pizza	\N	\N
80.00	2026-05-31 19:59:09.196	2026-05-25 21:16:55.374	122cc7d8-aa81-4202-b3c9-7346e4b7f023	4 Queijos	Pizza de 4 queijos porque faltou o quinto	\N	\N
80.00	2026-05-31 19:59:21.365	2026-05-25 21:21:02.292	a274b1e6-ba03-432c-8157-2028b45bf4df	Calabresa	Pizza de calabresa com muita calabresa, compre, é boa 👍	\N	\N
\.


--
-- Data for Name: flavor_category; Type: TABLE DATA; Schema: product_service; Owner: fernando
--

COPY product_service.flavor_category (changed_at, created_at, category_oid, flavor_oid, oid, id_user_who_changed, id_user_who_created) FROM stdin;
\N	2026-05-25 21:16:55.375	d4c7550d-7f8e-4213-b26e-25a2bab486e0	122cc7d8-aa81-4202-b3c9-7346e4b7f023	1f5d8618-e579-4bbe-954b-51b221959fa7	\N	\N
\N	2026-05-25 21:20:41.179	d4c7550d-7f8e-4213-b26e-25a2bab486e0	8c9f3cef-db12-4242-a031-736a556d21b3	d674c53d-c414-4523-990a-692c5870c7a5	\N	\N
\N	2026-05-25 21:21:02.292	d4c7550d-7f8e-4213-b26e-25a2bab486e0	a274b1e6-ba03-432c-8157-2028b45bf4df	4bba00f0-4371-4485-b75b-7b2c3421b501	\N	\N
\N	2026-05-31 19:40:11.846	34f73124-7f73-47e5-bb35-6aea90294797	b078578b-53b5-4834-a395-d447e33f7c4e	50e14511-6823-43c6-adfa-4b3a0c63010e	\N	\N
\N	2026-05-31 19:43:34.628	34f73124-7f73-47e5-bb35-6aea90294797	58e3a9d8-ddde-4c2e-86a6-493d440a3b52	a6930122-2db9-4d7f-b7d2-4362d49825a3	\N	\N
\N	2026-05-31 19:59:09.194	34f73124-7f73-47e5-bb35-6aea90294797	122cc7d8-aa81-4202-b3c9-7346e4b7f023	8642ae5e-ff40-4a49-ae43-77c1f82cc828	\N	\N
\N	2026-05-31 19:59:21.364	34f73124-7f73-47e5-bb35-6aea90294797	a274b1e6-ba03-432c-8157-2028b45bf4df	89eec0c9-8daa-40b6-afc9-5b8427831995	\N	\N
\.


--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: fernando
--

COPY public.admin (changed_at, created_at, oid, user_id, id_user_who_changed, id_user_who_created, user_oid) FROM stdin;
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: fernando
--

COPY public.category (oid, changed_at, created_at, id_user_who_changed, id_user_who_created, description) FROM stdin;
0cf640e4-2fa2-41ec-9dc1-197ce812fc69	\N	2026-05-04 11:16:14.101	\N	\N	Pizza de 4 Queijos
e5c57902-c444-415f-96fc-34fbd32a7fc0	\N	2026-05-15 20:18:11.625	\N	\N	Frango
\.


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: fernando
--

COPY public.client (changed_at, created_at, oid, user_id, id_user_who_changed, id_user_who_created, user_oid) FROM stdin;
\N	2026-05-03 16:10:19.801	14ab1bdc-93fc-4ecf-bc9c-e70b29ecc585	c423ee85-8479-4566-b6eb-d76b36f8e0f2	\N	\N	\N
\.


--
-- Data for Name: flavor; Type: TABLE DATA; Schema: public; Owner: fernando
--

COPY public.flavor (oid, changed_at, created_at, id_user_who_changed, id_user_who_created, name, category_oid, price) FROM stdin;
c45deffe-0cc2-494c-8226-4b3028016718	\N	2026-05-15 20:11:05.863	\N	\N	Frango com bacon e catupiry	\N	90
\.


--
-- Data for Name: flavor_category; Type: TABLE DATA; Schema: public; Owner: fernando
--

COPY public.flavor_category (oid, changed_at, created_at, id_user_who_changed, id_user_who_created, category_oid, flavor_oid) FROM stdin;
4e37e6a5-7a60-417b-913d-52728e8a8479	\N	2026-05-15 20:47:24.729	\N	\N	0cf640e4-2fa2-41ec-9dc1-197ce812fc69	c45deffe-0cc2-494c-8226-4b3028016718
\.


--
-- Data for Name: pizza; Type: TABLE DATA; Schema: public; Owner: fernando
--

COPY public.pizza (oid, changed_at, created_at, id_user_who_changed, id_user_who_created, size, order_oid) FROM stdin;
\.


--
-- Data for Name: pizza_flavor; Type: TABLE DATA; Schema: public; Owner: fernando
--

COPY public.pizza_flavor (oid, changed_at, created_at, id_user_who_changed, id_user_who_created, flavor_oid, pizza_oid) FROM stdin;
\.


--
-- Data for Name: pizzaplaza_user; Type: TABLE DATA; Schema: public; Owner: fernando
--

COPY public.pizzaplaza_user (authenticated, changed_at, created_at, oid, cpf, email, id_user_who_changed, id_user_who_created, name, password) FROM stdin;
f	\N	2026-05-03 16:10:19.796	c423ee85-8479-4566-b6eb-d76b36f8e0f2	104.790.659-77	client@email.com	\N	\N	Teste	$2a$10$Uu2uakkgeZWVpu6imKGQSeNpKVS68AAA7AnfTe2qjaxNFs3VSp4y2
\.


--
-- Data for Name: seller; Type: TABLE DATA; Schema: public; Owner: fernando
--

COPY public.seller (changed_at, created_at, oid, user_id, id_user_who_changed, id_user_who_created, user_oid) FROM stdin;
\.


--
-- Name: costumer costumer_pkey; Type: CONSTRAINT; Schema: order_service; Owner: fernando
--

ALTER TABLE ONLY order_service.costumer
    ADD CONSTRAINT costumer_pkey PRIMARY KEY (oid);


--
-- Name: order order_pkey; Type: CONSTRAINT; Schema: order_service; Owner: fernando
--

ALTER TABLE ONLY order_service."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (oid);


--
-- Name: pizza_flavor_snapshot pizza_flavor_snapshot_pkey; Type: CONSTRAINT; Schema: order_service; Owner: fernando
--

ALTER TABLE ONLY order_service.pizza_flavor_snapshot
    ADD CONSTRAINT pizza_flavor_snapshot_pkey PRIMARY KEY (oid);


--
-- Name: pizza pizza_pkey; Type: CONSTRAINT; Schema: order_service; Owner: fernando
--

ALTER TABLE ONLY order_service.pizza
    ADD CONSTRAINT pizza_pkey PRIMARY KEY (oid);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: product_service; Owner: fernando
--

ALTER TABLE ONLY product_service.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (oid);


--
-- Name: flavor_category flavor_category_pkey; Type: CONSTRAINT; Schema: product_service; Owner: fernando
--

ALTER TABLE ONLY product_service.flavor_category
    ADD CONSTRAINT flavor_category_pkey PRIMARY KEY (oid);


--
-- Name: flavor flavor_pkey; Type: CONSTRAINT; Schema: product_service; Owner: fernando
--

ALTER TABLE ONLY product_service.flavor
    ADD CONSTRAINT flavor_pkey PRIMARY KEY (oid);


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (oid);


--
-- Name: admin admin_user_id_key; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_user_id_key UNIQUE (user_id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (oid);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (oid);


--
-- Name: client client_user_id_key; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_user_id_key UNIQUE (user_id);


--
-- Name: flavor_category flavor_category_pkey; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.flavor_category
    ADD CONSTRAINT flavor_category_pkey PRIMARY KEY (oid);


--
-- Name: flavor flavor_pkey; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.flavor
    ADD CONSTRAINT flavor_pkey PRIMARY KEY (oid);


--
-- Name: pizza_flavor pizza_flavor_pkey; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.pizza_flavor
    ADD CONSTRAINT pizza_flavor_pkey PRIMARY KEY (oid);


--
-- Name: pizza pizza_pkey; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.pizza
    ADD CONSTRAINT pizza_pkey PRIMARY KEY (oid);


--
-- Name: pizzaplaza_user pizzaplaza_user_cpf_key; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.pizzaplaza_user
    ADD CONSTRAINT pizzaplaza_user_cpf_key UNIQUE (cpf);


--
-- Name: pizzaplaza_user pizzaplaza_user_email_key; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.pizzaplaza_user
    ADD CONSTRAINT pizzaplaza_user_email_key UNIQUE (email);


--
-- Name: pizzaplaza_user pizzaplaza_user_pkey; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.pizzaplaza_user
    ADD CONSTRAINT pizzaplaza_user_pkey PRIMARY KEY (oid);


--
-- Name: seller seller_pkey; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.seller
    ADD CONSTRAINT seller_pkey PRIMARY KEY (oid);


--
-- Name: seller seller_user_id_key; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.seller
    ADD CONSTRAINT seller_user_id_key UNIQUE (user_id);


--
-- Name: admin ukdl000g6g7srfous47myxkj7ti; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT ukdl000g6g7srfous47myxkj7ti UNIQUE (user_oid);


--
-- Name: client ukqtfxxotstd65kt21njc5ty9l9; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT ukqtfxxotstd65kt21njc5ty9l9 UNIQUE (user_oid);


--
-- Name: seller uktke0dhcci5rw7r60yw51i67bh; Type: CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.seller
    ADD CONSTRAINT uktke0dhcci5rw7r60yw51i67bh UNIQUE (user_oid);


--
-- Name: order fk9bjd9khyqju8vsnp1n8sqii49; Type: FK CONSTRAINT; Schema: order_service; Owner: fernando
--

ALTER TABLE ONLY order_service."order"
    ADD CONSTRAINT fk9bjd9khyqju8vsnp1n8sqii49 FOREIGN KEY (costumer_oid) REFERENCES order_service.costumer(oid);


--
-- Name: pizza fknsg9u0ni91ymvw6evshjrbrmw; Type: FK CONSTRAINT; Schema: order_service; Owner: fernando
--

ALTER TABLE ONLY order_service.pizza
    ADD CONSTRAINT fknsg9u0ni91ymvw6evshjrbrmw FOREIGN KEY (order_oid) REFERENCES order_service."order"(oid);


--
-- Name: pizza_flavor_snapshot fksxc2ok93gs1fon7ca4df47rfg; Type: FK CONSTRAINT; Schema: order_service; Owner: fernando
--

ALTER TABLE ONLY order_service.pizza_flavor_snapshot
    ADD CONSTRAINT fksxc2ok93gs1fon7ca4df47rfg FOREIGN KEY (pizza_oid) REFERENCES order_service.pizza(oid);


--
-- Name: flavor_category fk7d8e6l1jemymxg2pwngmly3dd; Type: FK CONSTRAINT; Schema: product_service; Owner: fernando
--

ALTER TABLE ONLY product_service.flavor_category
    ADD CONSTRAINT fk7d8e6l1jemymxg2pwngmly3dd FOREIGN KEY (category_oid) REFERENCES product_service.category(oid);


--
-- Name: flavor_category fkby444mj3lek9g04nc5he94e8k; Type: FK CONSTRAINT; Schema: product_service; Owner: fernando
--

ALTER TABLE ONLY product_service.flavor_category
    ADD CONSTRAINT fkby444mj3lek9g04nc5he94e8k FOREIGN KEY (flavor_oid) REFERENCES product_service.flavor(oid);


--
-- Name: pizza_flavor fk1chvdkwki35uoquy74wtp6p5q; Type: FK CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.pizza_flavor
    ADD CONSTRAINT fk1chvdkwki35uoquy74wtp6p5q FOREIGN KEY (flavor_oid) REFERENCES public.flavor(oid);


--
-- Name: seller fk2kscixaf238ugf33kyfql8cv5; Type: FK CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.seller
    ADD CONSTRAINT fk2kscixaf238ugf33kyfql8cv5 FOREIGN KEY (user_oid) REFERENCES public.pizzaplaza_user(oid);


--
-- Name: admin fk3exow6pisc2nx81g4oc4qoqo4; Type: FK CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT fk3exow6pisc2nx81g4oc4qoqo4 FOREIGN KEY (user_oid) REFERENCES public.pizzaplaza_user(oid);


--
-- Name: flavor_category fk7d8e6l1jemymxg2pwngmly3dd; Type: FK CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.flavor_category
    ADD CONSTRAINT fk7d8e6l1jemymxg2pwngmly3dd FOREIGN KEY (category_oid) REFERENCES public.category(oid);


--
-- Name: pizza_flavor fkbdxerdb5vve2ikccpq9qt6y60; Type: FK CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.pizza_flavor
    ADD CONSTRAINT fkbdxerdb5vve2ikccpq9qt6y60 FOREIGN KEY (pizza_oid) REFERENCES public.pizza(oid);


--
-- Name: flavor_category fkby444mj3lek9g04nc5he94e8k; Type: FK CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.flavor_category
    ADD CONSTRAINT fkby444mj3lek9g04nc5he94e8k FOREIGN KEY (flavor_oid) REFERENCES public.flavor(oid);


--
-- Name: client fkhjeccwn4u3pfwbxnmfc2n3a6n; Type: FK CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT fkhjeccwn4u3pfwbxnmfc2n3a6n FOREIGN KEY (user_id) REFERENCES public.pizzaplaza_user(oid);


--
-- Name: seller fkkr4dgxxo7rav2xpetu01f71pq; Type: FK CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.seller
    ADD CONSTRAINT fkkr4dgxxo7rav2xpetu01f71pq FOREIGN KEY (user_id) REFERENCES public.pizzaplaza_user(oid);


--
-- Name: flavor fkl9xgedem136stoy5csf556lxj; Type: FK CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.flavor
    ADD CONSTRAINT fkl9xgedem136stoy5csf556lxj FOREIGN KEY (category_oid) REFERENCES public.category(oid);


--
-- Name: admin fklxjaw0el77c0tqrt3a6ao3vns; Type: FK CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT fklxjaw0el77c0tqrt3a6ao3vns FOREIGN KEY (user_id) REFERENCES public.pizzaplaza_user(oid);


--
-- Name: client fkp6gwwqfgy62mgt2ogwajobtdi; Type: FK CONSTRAINT; Schema: public; Owner: fernando
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT fkp6gwwqfgy62mgt2ogwajobtdi FOREIGN KEY (user_oid) REFERENCES public.pizzaplaza_user(oid);


--
-- PostgreSQL database dump complete
--

\unrestrict wQ9cgZPEDN1mrhrfNtF5AM62KvGFoAYYf0n2ux9Jsl2Fg3E5vd9dCGV9a694K7p

