INSERT IGNORE INTO borrow_history VALUES (1,1, 1,'2018-03-09','2018-03-19');
INSERT IGNORE INTO borrow_history VALUES (2,3, 2,'2018-05-08', '2018-05-29' );
INSERT IGNORE INTO borrow_history VALUES (3, 3, 1,'2018-06-08', '2018-06-15');
INSERT IGNORE INTO borrow_history VALUES (4,4, 2, '2018-07-06', '2018-07-18');
INSERT IGNORE INTO borrow_history VALUES (5,1, 3,'2018-08-08', '2018-08-15');

INSERT IGNORE INTO users VALUES (1,'aaaa', '$2a$10$hEtMmBeei0mIPOCLn1bHRef8FmbayaN3WGxO5s/nznNDuzJmdAdKa','Betty', 'Davis','1234 Commerce Street 10',  'ROLE_ADMIN','ACTIVE');
INSERT IGNORE INTO users VALUES (2, 'bbbb', '$2a$10$aMal96ZOaOFR9Yph3jY/lOmplEJ6eSWoBlbU9cvLlGvCWnPOLyUfW', 'James', 'Bonds','5654 Cardinal Ave. 3',  'ROLE_USER','ACTIVE');
INSERT IGNORE INTO users VALUES (3, 'cccc','$2a$10$tUWW10Qjyxo0FUIUTFUDj.pzLmpRAYbb0iJNB7OhObN2r5HnLGkES','Eduardo', 'Rodriquez','2693 Lake St. 7',  'ROLE_USER','ACTIVE');
INSERT IGNORE INTO users VALUES (4, 'dddd', '$2a$10$pQm.c/BTZkaP7lbTtnLzYuNwfY7StNaLc.d.BdseMoqt1YktSyQDu','David', 'Schroeder','2749 Blackhawk Trail 5',   'ROLE_USER','ACTIVE');

INSERT IGNORE INTO books VALUES (1, 'Programmieren lernen mit Java', 'Alexander Salvanos','ACTIVE',   'COMPUTER');
INSERT IGNORE INTO books VALUES (2, 'Grundwissen Mathematik', 'Rob Bosch','ACTIVE',   'MATHEMATIK');
INSERT IGNORE INTO books VALUES (3, 'Buchfuehrung Grundlagen', 'Til Thomsen', 'ACTIVE', 'BWL');
INSERT IGNORE INTO books VALUES (4, 'Finanzierung', 'Til Thomsen', 'ACTIVE', 'VWL');
