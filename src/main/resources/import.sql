INSERT INTO cozinhas(id, descricao) VALUES(1, 'Mineira');
INSERT INTO cozinhas(id, descricao) VALUES(2, 'Japonesa');

INSERT INTO restaurantes(id, nome, taxa_de_entrega, cozinha_id) VALUES(1, 'DevFood', 3.5, 2);
INSERT INTO restaurantes(id, nome, taxa_de_entrega, cozinha_id) VALUES(2, 'FastTop', 4.88, 1);
INSERT INTO restaurantes(id, nome, taxa_de_entrega, cozinha_id) VALUES(3, 'ComeComeFood', 3.20, 1);

INSERT INTO estados(id, nome, sigla) VALUES(1, 'Minas Gerais', 'MG');
INSERT INTO estados(id, nome, sigla) VALUES(2, 'Rio de Janeiro', 'RJ');
INSERT INTO estados(id, nome, sigla) VALUES(3, 'Mato Grosso do Sul', 'MS');

INSERT INTO cidades(id, nome, estado_id) VALUES(1, 'Belo Horizonte', 1);
INSERT INTO cidades(id, nome, estado_id) VALUES(2, 'Patos de Minas', 1);
INSERT INTO cidades(id, nome, estado_id) VALUES(3, 'Campo Grande', 3);
INSERT INTO cidades(id, nome, estado_id) VALUES(4, 'Rocinha', 2);
