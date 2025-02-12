-- Adicionando um novo endereço para o novo cliente
INSERT INTO address (cep, state, city, district, street, house_number)
VALUES 
('11122233', 'SP', 'São Paulo', 'Moema', 'Alameda dos Anjos', '55');

-- Adicionando um novo cliente para o Ceremonialista de ID 1
INSERT INTO client (name, email, password, cpf, birthday, phone, id_cerimonialist, id_address)
VALUES 
('Fernando Lima', 'fernando.lima@email.com', 'cliente789', '11223344556', '1990-03-15', '1191122-3344', 1, 5);

-- Adicionando um orçamento para o novo cliente
INSERT INTO budget (buget_date, id_supplier, id_client)
VALUES 
('2024-03-10', 1, 3); -- Cliente de ID 3 (Fernando Lima)

-- Adicionando um item ao orçamento do novo cliente
INSERT INTO item (price, title, description, id_budget)
VALUES 
(7000.00, 'Pacote Completo de Casamento', 'Inclui buffet, decoração e DJ.', 3);

-- Adicionando um contrato para o orçamento do novo cliente
INSERT INTO contract (contract_number, pdf, signed, id_budget)
VALUES 
('770a8400-e29b-51d4-a716-667755440222', 'wedding_package_contract.pdf', false, 3);