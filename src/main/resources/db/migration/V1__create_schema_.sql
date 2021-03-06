CREATE TABLE area_afetada
(
    id                   INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    residencial          VARCHAR(255),
    comercial            VARCHAR(255),
    industrial           VARCHAR(255),
    agricola             VARCHAR(255),
    pecuaria             VARCHAR(255),
    extrativismo_vegetal VARCHAR(255),
    reserva_florestal    VARCHAR(255),
    mineracao            VARCHAR(255),
    turismo_outras       VARCHAR(255),
    ocorrencia_id        INTEGER                                  NOT NULL,
    CONSTRAINT pk_area_afetada PRIMARY KEY (id)
);

CREATE TABLE cobrade
(
    codigo    VARCHAR(255) NOT NULL,
    categoria VARCHAR(255),
    grupo     VARCHAR(255),
    subgrupo  VARCHAR(255),
    tipo      VARCHAR(255),
    subtipo   VARCHAR(255),
    definicao VARCHAR(255),
    CONSTRAINT pk_cobrade PRIMARY KEY (codigo)
);

CREATE TABLE dano_tipos
(
    id        INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    categoria VARCHAR(255),
    descricao VARCHAR(255),
    CONSTRAINT pk_dano_tipos PRIMARY KEY (id)
);

CREATE TABLE danos
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    ocorrencia_id INTEGER                                 NOT NULL,
    latitude      VARCHAR(30)                             NOT NULL,
    longitude     VARCHAR(30)                             NOT NULL,
    CONSTRAINT pk_danos PRIMARY KEY (id)
);

CREATE TABLE danos_ambientais
(
    id                 INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    tipo_id            INTEGER                                  NOT NULL,
    populacao_atingida INTEGER,
    dano_id            BIGINT                                   NOT NULL,
    CONSTRAINT pk_danos_ambientais PRIMARY KEY (id)
);

CREATE TABLE danos_fotos
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name         VARCHAR(255),
    content_type VARCHAR(255),
    size         VARCHAR(255),
    data         OID,
    dano_id      BIGINT                                  NOT NULL,
    CONSTRAINT pk_danos_fotos PRIMARY KEY (id)
);

CREATE TABLE danos_humanos
(
    id             INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    tipo_id        INTEGER                                  NOT NULL,
    numero_pessoas INTEGER,
    dano_id        BIGINT                                   NOT NULL,
    CONSTRAINT pk_danos_humanos PRIMARY KEY (id)
);

CREATE TABLE danos_materiais
(
    id        INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    tipo_id   INTEGER                                  NOT NULL,
    destruido BOOLEAN,
    descricao VARCHAR(255),
    valor     DECIMAL,
    dano_id   BIGINT,
    CONSTRAINT pk_danos_materiais PRIMARY KEY (id)
);

CREATE TABLE instituicao_informante
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    ocorrencia_id INTEGER                                 NOT NULL,
    nome          VARCHAR(255),
    responsavel   VARCHAR(255),
    CONSTRAINT pk_instituicao_informante PRIMARY KEY (id)
);

CREATE TABLE instituicao_informante_telefones
(
    instituicao_informante_id BIGINT NOT NULL,
    telefone                  VARCHAR(20)
);

CREATE TABLE municipios
(
    id   INTEGER NOT NULL,
    nome VARCHAR(255),
    uf   INTEGER NOT NULL,
    CONSTRAINT pk_municipios PRIMARY KEY (id)
);

CREATE TABLE ocorrencia
(
    id                     INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    data                   TIMESTAMP WITHOUT TIME ZONE,
    defesa_civil_informada BOOLEAN,
    sedec_informado        BOOLEAN,
    municipio_id           INTEGER                                  NOT NULL,
    cod_cobrade            VARCHAR(255)                             NOT NULL,
    latitude               VARCHAR(30)                              NOT NULL,
    longitude              VARCHAR(30)                              NOT NULL,
    CONSTRAINT pk_ocorrencia PRIMARY KEY (id)
);

CREATE TABLE ufs
(
    id    INTEGER NOT NULL,
    sigla VARCHAR(255),
    nome  VARCHAR(255),
    CONSTRAINT pk_ufs PRIMARY KEY (id)
);

ALTER TABLE area_afetada
    ADD CONSTRAINT FK_AREA_AFETADA_ON_OCORRENCIA FOREIGN KEY (ocorrencia_id) REFERENCES ocorrencia (id);

ALTER TABLE danos_ambientais
    ADD CONSTRAINT FK_DANOS_AMBIENTAIS_ON_DANO FOREIGN KEY (dano_id) REFERENCES danos (id);

ALTER TABLE danos_ambientais
    ADD CONSTRAINT FK_DANOS_AMBIENTAIS_ON_TIPO FOREIGN KEY (tipo_id) REFERENCES dano_tipos (id);

ALTER TABLE danos_humanos
    ADD CONSTRAINT FK_DANOS_HUMANOS_ON_DANO FOREIGN KEY (dano_id) REFERENCES danos (id);

ALTER TABLE danos_humanos
    ADD CONSTRAINT FK_DANOS_HUMANOS_ON_TIPO FOREIGN KEY (tipo_id) REFERENCES dano_tipos (id);

ALTER TABLE danos_materiais
    ADD CONSTRAINT FK_DANOS_MATERIAIS_ON_DANO FOREIGN KEY (dano_id) REFERENCES danos (id);

ALTER TABLE danos_materiais
    ADD CONSTRAINT FK_DANOS_MATERIAIS_ON_TIPO FOREIGN KEY (tipo_id) REFERENCES dano_tipos (id);

ALTER TABLE danos
    ADD CONSTRAINT FK_DANOS_ON_OCORRENCIA FOREIGN KEY (ocorrencia_id) REFERENCES ocorrencia (id);

ALTER TABLE instituicao_informante
    ADD CONSTRAINT FK_INSTITUICAO_INFORMANTE_ON_OCORRENCIA FOREIGN KEY (ocorrencia_id) REFERENCES ocorrencia (id);

ALTER TABLE municipios
    ADD CONSTRAINT FK_MUNICIPIOS_ON_UF FOREIGN KEY (uf) REFERENCES ufs (id);

ALTER TABLE ocorrencia
    ADD CONSTRAINT FK_OCORRENCIA_ON_COD_COBRADE FOREIGN KEY (cod_cobrade) REFERENCES cobrade (codigo);

ALTER TABLE ocorrencia
    ADD CONSTRAINT FK_OCORRENCIA_ON_MUNICIPIO FOREIGN KEY (municipio_id) REFERENCES municipios (id);

ALTER TABLE danos_fotos
    ADD CONSTRAINT FK_DANOS_FOTOS_ON_DANO FOREIGN KEY (dano_id) REFERENCES danos (id);

ALTER TABLE instituicao_informante_telefones
    ADD CONSTRAINT fk_instituicao_informante_telefones_on_instituicao_informante FOREIGN KEY (instituicao_informante_id) REFERENCES instituicao_informante (id);