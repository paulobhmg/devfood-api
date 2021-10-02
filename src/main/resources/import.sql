INSERT INTO kitchens(id, description) VALUES(1, 'Mineira');
INSERT INTO kitchens(id, description) VALUES(2, 'Japonesa');

INSERT INTO restaurants(id, name, delivery_rate, kitchen_id) VALUES(1, 'DevFood', 3.5, 2);
INSERT INTO restaurants(id, name, delivery_rate, kitchen_id) VALUES(2, 'FastTop', 4.88, 1);
INSERT INTO restaurants(id, name, delivery_rate, kitchen_id) VALUES(3, 'ComeComeFood', 3.20, 1);

INSERT INTO states(id, name, sigle) VALUES(1, 'Minas Gerais', 'MG');
INSERT INTO states(id, name, sigle) VALUES(2, 'Rio de Janeiro', 'RJ');
INSERT INTO states(id, name, sigle) VALUES(3, 'Mato Grosso do Sul', 'MS');

INSERT INTO cities(id, name, state_id) VALUES(1, 'Belo Horizonte', 1);
INSERT INTO cities(id, name, state_id) VALUES(2, 'Patos de Minas', 1);
INSERT INTO cities(id, name, state_id) VALUES(3, 'Campo Grande', 3);
INSERT INTO cities(id, name, state_id) VALUES(4, 'Rocinha', 2);
