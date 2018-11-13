CREATE TABLE entry(
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  description VARCHAR(50) NOT NULL,
  date_due DATE NOT NULL,
  date_payment DATE,
  amount DECIMAL(10,2) NOT NULL,
  note VARCHAR(100),
  type VARCHAR(20) NOT NULL,
  id_category BIGINT(20) NOT NULL,
  id_person BIGINT(20) NOT NULL,
  FOREIGN KEY (id_category) REFERENCES category(id),
  FOREIGN KEY (id_person) REFERENCES person(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'REVENUE', 1, 1);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'EXPENDITURE', 2, 2);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Top Club', '2017-06-10', null, 120, null, 'REVENUE', 3, 3);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'REVENUE', 3, 4);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('DMAE', '2017-06-10', null, 200.30, null, 'EXPENDITURE', 3, 5);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'REVENUE', 4, 6);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Bahamas', '2017-06-10', null, 500, null, 'REVENUE', 1, 7);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'EXPENDITURE', 4, 8);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Despachante', '2017-06-10', null, 123.64, 'Multas', 'EXPENDITURE', 3, 9);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'REVENUE', 5, 10);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Café', '2017-06-10', null, 8.32, null, 'EXPENDITURE', 1, 5);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'EXPENDITURE', 5, 4);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Instrumentos', '2017-06-10', null, 1040.32, null, 'EXPENDITURE', 4, 3);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'EXPENDITURE', 4, 2);
INSERT INTO entry(description, date_due, date_payment, amount, note, type, id_category, id_person ) VALUES ('Lanche', '2017-06-10', null, 10.20, null, 'EXPENDITURE', 4, 1);