-- Inserir dados na tabela Cars (veículos)
INSERT INTO cars (id, model, manufacturer, model_year, fipe_value) VALUES
  (1, 'Civic', 'Honda', 2022, 120000.00),
  (2, 'Corolla', 'Toyota', 2023, 150000.00),
  (3, 'Focus', 'Ford', 2021, 100000.00);

-- Inserir dados na tabela drivers (motoristas)
INSERT INTO drivers (id, document, birthdate) VALUES
  (1, 'ABC123456', '2000-05-15'),
  (2, 'XYZ789012', '1995-06-06'),
  (3, 'DEF345678', '1970-07-27');

-- Inserir dados na tabela customers (clientes)
INSERT INTO customers (id, name, driver_id) VALUES
  (1, 'Leonardo Arruda', 1),
  (2, 'Tatiane Arruda', 2),
  (3, 'Ana Maria Arruda', 3);

-- Inserir dados na tabela Claims (sinistros)
INSERT INTO claims (id, car_id, driver_id, event_date) VALUES
  (1, 1, 1, '2023-03-01 12:00:00'),
  (2, 1, 2, '2023-04-01 12:00:00');

-- Inserir dados na tabela Car_Drivers (Motoristas dos Veículos)
INSERT INTO car_drivers (id, car_id, driver_id, is_main_driver) VALUES
  (1, 1, 1, 1),
  (2, 2, 2, 1),
  (3, 3, 3, 1);
