CREATE TABLE categoria (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE grupo_assignado (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE subtag (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    tag_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE tag (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    categoria_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ticket (
    id BIGINT NOT NULL AUTO_INCREMENT,
    status VARCHAR(255),
    data_e_horario_de_criacao TIMESTAMP,
    tipo VARCHAR(255),
    numero_ticket_segundo_tipo VARCHAR(255),
    titulo VARCHAR(150) NOT NULL,
    reportado_por_id BIGINT NOT NULL,
    reportado_para_id BIGINT NOT NULL,
    grupo_assignado_id BIGINT NOT NULL,
    gerenciado_por_id BIGINT,
    descricao VARCHAR(1000) NOT NULL,
    dados_pessoais VARCHAR(1000) NOT NULL,
    categoria_reportada_id BIGINT NOT NULL,
    categoria_afetada_id BIGINT NOT NULL,
    tag_id BIGINT,
    subtag_id BIGINT,
    solucao VARCHAR(1000),
    solucao_dados_pessoais VARCHAR(1000),
    PRIMARY KEY (id)
);

CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    codigo VARCHAR(255),
    nome VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE usuario_grupo_assignado (
    usuario_id BIGINT NOT NULL,
    grupo_assignado_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, grupo_assignado_id)
);

CREATE TABLE categoria_grupo_assignado (
    grupo_assignado_id BIGINT NOT NULL,
    categoria_id BIGINT NOT NULL,
    PRIMARY KEY (grupo_assignado_id, categoria_id)
);

ALTER TABLE subtag
    ADD CONSTRAINT FK_subtag_tag
    FOREIGN KEY (tag_id)
    REFERENCES tag (id);

ALTER TABLE tag
    ADD CONSTRAINT FK_tag_categoria
    FOREIGN KEY (categoria_id)
    REFERENCES categoria (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_ticket_usuario_reportado_por
    FOREIGN KEY (reportado_por_id)
    REFERENCES usuario (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_ticket_usuario_reportado_para
    FOREIGN KEY (reportado_para_id)
    REFERENCES usuario (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_ticket_grupo_assignado
    FOREIGN KEY (grupo_assignado_id)
    REFERENCES grupo_assignado (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_ticket_usuario_gerenciado_por
    FOREIGN KEY (gerenciado_por_id)
    REFERENCES usuario (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_ticket_categoria_reportada
    FOREIGN KEY (categoria_reportada_id)
    REFERENCES categoria (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_ticket_categoria_afetada
    FOREIGN KEY (categoria_afetada_id)
    REFERENCES categoria (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_ticket_tag
    FOREIGN KEY (tag_id)
    REFERENCES tag (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_ticket_subtag
    FOREIGN KEY (subtag_id)
    REFERENCES subtag (id);

ALTER TABLE usuario_grupo_assignado
    ADD CONSTRAINT FK_usuario_grupo_assignado_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id);

ALTER TABLE usuario_grupo_assignado
    ADD CONSTRAINT FK_usuario_grupo_assignado_grupo_assignado
    FOREIGN KEY (grupo_assignado_id)
    REFERENCES grupo_assignado (id);

ALTER TABLE categoria_grupo_assignado
    ADD CONSTRAINT FK_categoria_grupo_assignado_grupo_assignado
    FOREIGN KEY (grupo_assignado_id)
    REFERENCES grupo_assignado (id);

ALTER TABLE categoria_grupo_assignado
    ADD CONSTRAINT FK_categoria_grupo_assignado_categoria
    FOREIGN KEY (categoria_id)
    REFERENCES categoria (id);