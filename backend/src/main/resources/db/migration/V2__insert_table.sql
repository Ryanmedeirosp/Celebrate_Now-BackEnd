-- Populando a tabela address
INSERT INTO address (cep, state, city, district, street, house_number)
VALUES 
('12345678', 'SP', 'São Paulo', 'Centro', 'Rua das Flores', '123'),
('87654321', 'RJ', 'Rio de Janeiro', 'Copacabana', 'Avenida Atlântica', '456'),
('45678123', 'MG', 'Belo Horizonte', 'Savassi', 'Rua Pernambuco', '789'),
('65432987', 'BA', 'Salvador', 'Pelourinho', 'Ladeira do Carmo', '321');

-- Populando a tabela ceremonialist
INSERT INTO ceremonialist (name, email, password, document, birthday, phone, id_address)
VALUES 
('Ana Paula', 'ana.paula@email.com', 'senha123', '12345678901234', '1990-05-15', '1191234-5678', 1),
('Carlos Silva', 'carlos.silva@email.com', 'senha456', '56789012345678', '1985-09-25', '2197654-3210', 2);

-- Populando a tabela client
INSERT INTO client (name, email, password, cpf, birthday, phone, id_cerimonialist, id_address)
VALUES 
('Mariana Souza', 'mariana.souza@email.com', 'cliente123', '12345678901', '1995-07-20', '3198877-6655', 1, 3),
('João Pedro', 'joao.pedro@email.com', 'cliente456', '98765432100', '1992-11-10', '1199988-7766', 2, 4);

-- Populando a tabela supplier
INSERT INTO supplier (name, email, cnpj, phone, service_type, description, id_cerimonialist, id_address)
VALUES 
('Buffet Delícia', 'contato@buffetdelicia.com', '12345678000199', '1190000-1234', 'Buffet', 'Serviço completo de buffet para eventos.', 1, 1),
('Decoração & Cia', 'contato@decoracaocia.com', '98765432000188', '2195555-4321', 'Decoração', 'Decoração personalizada para casamentos.', 2, 2);

-- Populando a tabela budget
INSERT INTO budget (buget_date, id_supplier, id_client)
VALUES 
('2024-01-15', 1, 1),
('2024-02-20', 2, 2);

-- Populando a tabela item
INSERT INTO item (price, title, description, id_budget)
VALUES 
(5000.00, 'Buffet Completo', 'Buffet com entradas, prato principal e sobremesa.', 1),
(3000.00, 'Decoração Floral', 'Decoração com flores naturais para salão principal.', 2);

-- Populando a tabela contract
INSERT INTO contract (contract_number, pdf, id_budget)
VALUES 
('550e8400-e29b-41d4-a716-446655440000', 'buffet_contract_ana.pdf', 1),
('660f8400-e29b-11d4-a716-556655440111', 'decor_contract_joao.pdf', 2);
