create table client
(
    it_client bigint auto_increment
        primary key,
    reference varchar(8) not null,
    type      varchar(4) null
);

create table energie
(
    it_energie bigint auto_increment
        primary key,
    type       tinyint     not null,
    libellle   varchar(15) null
);

create table energie_configuration
(
    it_energie_configuraiton bigint auto_increment
        primary key,
    seuil_ca                 decimal        not null,
    prix_kwh_ca_inf          decimal(10, 3) not null,
    prix_kwh_ca_sup          decimal(10, 3) null,
    it_energie               bigint         not null,
    type_client              varchar(10)    null,
    constraint energie_configuration__fk
        foreign key (it_energie) references energie (it_energie)
);

create table particuliers
(
    reference_client bigint auto_increment
        primary key,
    civilite         varchar(25) not null,
    nom              varchar(50) not null,
    prenom           varchar(25) not null,
    it_client        bigint      not null,
    constraint particuliers__fk
        foreign key (it_client) references client (it_client)
);

create table professionnel
(
    it_professionnel bigint   auto_increment primary key,
    numero_siret     int      not null,
    raison_sociale   varchar(255) not null,
    chiffre_affaire  bigint       not null,
    it_client        bigint       not null,
    constraint professionnel__fk
        foreign key (it_client) references client (it_client)
);

create table releve
(
    it_client      bigint      not null,
    it_energie     bigint      not null,
    periode        date        not null,
    fractionnement varchar(10) not null,
    consommation   int         null,
    primary key (it_client, it_energie),
    constraint releve__energie_fk
        foreign key (it_energie) references energie (it_energie),
    constraint releve_client_fk
        foreign key (it_client) references client (it_client)
);


INSERT INTO client (it_client, reference, type) VALUES (1, '1234', 'PRO');
INSERT INTO client (it_client, reference, type) VALUES (2, '5678', 'PART');

INSERT INTO energie (it_energie, type, libellle) VALUES (1, 1, 'ELECTRICITE');
INSERT INTO energie (it_energie, type, libellle) VALUES (2, 2, 'GAZ');

INSERT INTO energie_configuration (it_energie_configuraiton, seuil_ca, prix_kwh_ca_inf, prix_kwh_ca_sup, it_energie, type_client) VALUES (1, 0, 0.121, 0.000, 1, 'PART');
INSERT INTO energie_configuration (it_energie_configuraiton, seuil_ca, prix_kwh_ca_inf, prix_kwh_ca_sup, it_energie, type_client) VALUES (2, 0, 0.115, 0.000, 2, 'PART');
INSERT INTO energie_configuration (it_energie_configuraiton, seuil_ca, prix_kwh_ca_inf, prix_kwh_ca_sup, it_energie, type_client) VALUES (3, 1000000, 0.118, 0.114, 1, 'PRO');
INSERT INTO energie_configuration (it_energie_configuraiton, seuil_ca, prix_kwh_ca_inf, prix_kwh_ca_sup, it_energie, type_client) VALUES (4, 1000000, 0.113, 0.111, 2, 'PRO');

INSERT INTO particuliers (reference_client, civilite, nom, prenom, it_client) VALUES (1, 'marie', 'RAKOTO', 'TOKY', 2);

INSERT INTO professionnel (it_professionnel, numero_siret, raison_sociale, chiffre_affaire, it_client) VALUES (1, 123546, 'ORANGE', 500000, 1);

INSERT INTO releve (it_client, it_energie, periode, fractionnement, consommation) VALUES (1, 1, '2023-05-14', 'MENSUEL', 150);
INSERT INTO releve (it_client, it_energie, periode, fractionnement, consommation) VALUES (2, 1, '2023-05-14', 'MENSUEL', 200);
INSERT INTO releve (it_client, it_energie, periode, fractionnement, consommation) VALUES (2, 2, '2023-05-14', 'MENSUEL', 500);

