insert into categoria (nome, descricao) values ('Informática', 'Produtos de Informática')

insert into categoria (nome, descricao) values ('Livros','Livros Técnicos')

insert into categoria (nome, descricao) values ('Caderno', 'Apostila')

insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Código Limpo', 'Livro do Autor Robert C. Martin', 87.34, 20, 2);

insert into categoria (nome, descricao) values ('Eletrônicos', 'Produtos eletrônicos');

insert into categoria (nome, descricao) values ('Informática', 'Produtos de informática');

insert into categoria (nome, descricao) values ('Casa', 'Produtos para casa');

insert into categoria (nome, descricao) values ('Esportes', 'Produtos esportivos');

insert into categoria (nome, descricao) values ('Papelaria', 'Produtos de papelaria');

insert into cliente (nome, email, telefone) values ('Ana Clara', 'ana@gmail.com', '14999990001');

insert into cliente (nome, email, telefone) values ('Joao Silva', 'joao@gmail.com', '14999990002');

insert into cliente (nome, email, telefone) values ('Maria Souza', 'maria@gmail.com', '14999990003');

insert into cliente (nome, email, telefone) values ('Carlos Oliveira', 'carlos@gmail.com', '14999990004');

insert into cliente (nome, email, telefone) values ('Beatriz Santos', 'beatriz@gmail.com', '14999990005');

insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Notebook', 'Notebook para uso pessoal', 3500.00, 10, 1);

insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Mouse', 'Mouse sem fio', 80.00, 25, 1);
insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Teclado', 'Teclado USB', 120.00, 20, 2);
insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Cadeira', 'Cadeira para escritório', 650.00, 8, 3);
insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Bola de futebol', 'Bola oficial de futebol', 100.00, 15, 4);

insert into pedido (data, status, valor_total, cliente_id_cliente) values ('2026-09-01 10:00:00', 'Pendente', 3580.00, 1);

insert into pedido (data, status, valor_total, cliente_id_cliente) values ('2026-09-02 11:30:00', 'Pago', 120.00, 2);
insert into pedido (data, status, valor_total, cliente_id_cliente) values ('2026-09-03 14:00:00', 'Pago', 770.00, 3);
insert into pedido (data, status, valor_total, cliente_id_cliente) values ('2026-09-04 16:30:00', 'Pendente', 3500.00, 4);
insert into pedido (data, status, valor_total, cliente_id_cliente) values ('2026-09-05 09:00:00', 'Pago', 100.00, 5);

insert into item_pedido (quantidade, valor_unitario, pedido_id_pedido, produto_id) values (1, 3500.00, 1, 1);
insert into item_pedido (quantidade, valor_unitario, pedido_id_pedido, produto_id) values (1, 120.00, 2, 3);
insert into item_pedido (quantidade, valor_unitario, pedido_id_pedido, produto_id) values (1, 650.00, 3, 4);
insert into item_pedido (quantidade, valor_unitario, pedido_id_pedido, produto_id) values (1, 3500.00, 4, 1);
insert into item_pedido (quantidade, valor_unitario, pedido_id_pedido, produto_id) values (1, 100.00, 5, 5);

insert into pagamento (valor, data, status, tipo, pedido_id_pedido) values (3580.00, '2026-09-01 10:05:00', 'Aprovado', 'Cartao', 1);
insert into pagamento (valor, data, status, tipo, pedido_id_pedido) values (120.00, '2026-09-02 11:35:00', 'Aprovado', 'Pix', 2);
insert into pagamento (valor, data, status, tipo, pedido_id_pedido) values (770.00, '2026-09-03 14:05:00', 'Aprovado', 'Cartao', 3);
insert into pagamento (valor, data, status, tipo, pedido_id_pedido) values (3500.00, '2026-09-04 16:35:00', 'Pendente', 'Boleto', 4);
insert into pagamento (valor, data, status, tipo, pedido_id_pedido) values (100.00, '2026-09-05 09:05:00', 'Aprovado', 'Pix', 5);
