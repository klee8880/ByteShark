DROP TABLE repair_line;
DROP TABLE shopping;

CREATE TABLE IF NOT EXISTS shopping (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	car_Initial VARCHAR(4),
	car_Number INTEGER,
	car_Type VARCHAR(1),
	invoice_Number VARCHAR(20),
	invoice_Date DATE,
	detail_Source VARCHAR(2),
	billing_Party VARCHAR(4),
	account_Date DATE,
	arrival_Date DATE,
	repair_Date DATE,
	SPLC INT,
	load_Indicator VARCHAR(1),
	defect_Card VARCHAR(4),
	defect_Date DATE
);

CREATE TABLE IF NOT EXISTS repair_line (

	shopping_ID INT NOT NULL,
	line_number INT NOT NULL,
	location VARCHAR(2),
	quantity INT,
	condition_code INT,
	applied VARCHAR(6),
	Description VARCHAR(50),
	removed VARCHAR(6),
	why_made INT,
	responsability INT,
	labor_charge FLOAT,
	material_charge FLOAT,
	material_sign varchar(1),
	PRIMARY KEY (shopping_ID, line_number),
	FOREIGN KEY (shopping_ID) REFERENCES shopping(id)
);