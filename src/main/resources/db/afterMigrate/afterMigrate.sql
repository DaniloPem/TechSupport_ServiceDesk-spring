INSERT INTO categoria (id, nome) VALUES (1, 'Hardware') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO categoria (id, nome) VALUES (2, 'Software') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO categoria (id, nome) VALUES (3, 'Rede') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO categoria (id, nome) VALUES (4, 'Suporte Técnico') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO categoria (id, nome) VALUES (5, 'Outros') ON DUPLICATE KEY UPDATE id=id;

INSERT INTO grupo_assignado (id, nome) VALUES (1, 'Grupo de Hardware') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO grupo_assignado (id, nome) VALUES (2, 'Grupo de Software') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO grupo_assignado (id, nome) VALUES (3, 'Grupo de Rede') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO grupo_assignado (id, nome) VALUES (4, 'Grupo de Suporte Técnico') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO grupo_assignado (id, nome) VALUES (5, 'Grupo Geral') ON DUPLICATE KEY UPDATE id=id;

INSERT INTO tag (id, nome, categoria_id) VALUES (1, 'Hardware', 1) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO tag (id, nome, categoria_id) VALUES (2, 'Software', 2) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO tag (id, nome, categoria_id) VALUES (3, 'Rede', 3) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO tag (id, nome, categoria_id) VALUES (4, 'Suporte', 4) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO tag (id, nome, categoria_id) VALUES (5, 'Outro', 5) ON DUPLICATE KEY UPDATE id=id;

INSERT INTO subtag (id, nome, tag_id) VALUES (1, 'Impressora', 1) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO subtag (id, nome, tag_id) VALUES (2, 'Monitor', 1) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO subtag (id, nome, tag_id) VALUES (3, 'Sistema Operacional', 2) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO subtag (id, nome, tag_id) VALUES (4, 'Aplicativo', 2) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO subtag (id, nome, tag_id) VALUES (5, 'Roteador', 3) ON DUPLICATE KEY UPDATE id=id;

INSERT INTO usuario (id, codigo, nome, email, telefone) VALUES (1, 'USER001', 'João da Silva', '[joao.silva@email.com]', '123456789') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO usuario (id, codigo, nome, email, telefone) VALUES (2, 'USER002', 'Maria Mussato', '[maria.mussato@mail.com]', '987654321') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO usuario (id, codigo, nome, email, telefone) VALUES (3, 'USER003', 'Pedro Correia', '[pedro.correia@email.com]', '123456789') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO usuario (id, codigo, nome, email, telefone) VALUES (4, 'USER004', 'Ana Garcia', '[ana.garcia@email.com]', '987654321') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO usuario (id, codigo, nome, email, telefone) VALUES (5, 'USER005', 'Bruno Souza', '[bruno.souza@email.com]', '123456789') ON DUPLICATE KEY UPDATE id=id;

INSERT INTO usuario_grupo_assignado (usuario_id, grupo_assignado_id) VALUES (1, 1) ON DUPLICATE KEY UPDATE grupo_assignado_id=grupo_assignado_id;
INSERT INTO usuario_grupo_assignado (usuario_id, grupo_assignado_id) VALUES (2, 2) ON DUPLICATE KEY UPDATE grupo_assignado_id=grupo_assignado_id;
INSERT INTO usuario_grupo_assignado (usuario_id, grupo_assignado_id) VALUES (3, 3) ON DUPLICATE KEY UPDATE grupo_assignado_id=grupo_assignado_id;
INSERT INTO usuario_grupo_assignado (usuario_id, grupo_assignado_id) VALUES (4, 4) ON DUPLICATE KEY UPDATE grupo_assignado_id=grupo_assignado_id;
INSERT INTO usuario_grupo_assignado (usuario_id, grupo_assignado_id) VALUES (5, 5) ON DUPLICATE KEY UPDATE grupo_assignado_id=grupo_assignado_id;

INSERT INTO categoria_grupo_assignado (grupo_assignado_id, categoria_id) VALUES (1, 1) ON DUPLICATE KEY UPDATE grupo_assignado_id=grupo_assignado_id;
INSERT INTO categoria_grupo_assignado (grupo_assignado_id, categoria_id) VALUES (2, 2) ON DUPLICATE KEY UPDATE grupo_assignado_id=grupo_assignado_id;
INSERT INTO categoria_grupo_assignado (grupo_assignado_id, categoria_id) VALUES (3, 3) ON DUPLICATE KEY UPDATE grupo_assignado_id=grupo_assignado_id;
INSERT INTO categoria_grupo_assignado (grupo_assignado_id, categoria_id) VALUES (4, 4) ON DUPLICATE KEY UPDATE grupo_assignado_id=grupo_assignado_id;
INSERT INTO categoria_grupo_assignado (grupo_assignado_id, categoria_id) VALUES (5, 5) ON DUPLICATE KEY UPDATE grupo_assignado_id=grupo_assignado_id;