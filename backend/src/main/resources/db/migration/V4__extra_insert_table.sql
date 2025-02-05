-- Adicionando um novo endereço para o novo cliente
INSERT INTO address (cep, state, city, district, street, house_number)
VALUES 
('11122234', 'SP', 'São Paulo', 'Moema', 'Alameda das Rosas', '56');

-- Adicionando um novo cliente para o Ceremonialista de ID 1
INSERT INTO client (name, email, password, cpf, birthday, phone, id_cerimonialist, id_address)
VALUES 
('Mariana Souza', 'mariana.souza@email.com', 'cliente123', '22334455667', '1992-06-25', '1192233-4455', 1, 6);

-- Adicionando um orçamento para o novo cliente
INSERT INTO budget (buget_date, id_supplier, id_client)
VALUES 
('2024-04-15', 2, 4); -- Cliente de ID 4 (Mariana Souza)

-- Adicionando um item ao orçamento do novo cliente
INSERT INTO item (price, title, description, id_budget)
VALUES 
(8500.00, 'Pacote Premium de Casamento', 'Inclui buffet, decoração, DJ e fotografia.', 4);
