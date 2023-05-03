# Webes ételfutár alkalmazás - FutárFalatok
### A futtatáshoz szükséges:
1. Adatbázis:
	- XAMPP
	- Apache és MySQL indítása a XAMPP felületén
Az adatbázisban szükség van egy új adatgyűjteményre. Ennek a neve "futarfalatok". Emellett néhány adat beszúrása is fontos a működéshez.
Létrehozás:
CREATE DATABASE futarfalatok CHARACTER SET utf8 COLLATE utf8_hungarian_ci;

**USER készítése az adatbázishoz:**
CREATE USER 'zsut2323'@'localhost' IDENTIFIED BY 'NagyTitok123';
REVOKE ALL PRIVILEGES ON *.* FROM 'zsut2323'@'localhost'; GRANT ALL PRIVILEGES ON *.* TO 'zsut2323'@'localhost' REQUIRE NONE WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;

**Adatok beszúrása:**
INSERT INTO `futarfalatok`.`user` (`id`, `email`, `first_name`, `last_name`, `password`, `phone_number`, `role`) VALUES ('4', 'adminandris@mail.hu', 'András', 'Admin', 'password', '06000000007', 'ROLE_ADMIN');

Először egy admin felhasználó, amelynek felhasználóneve "adminandris@mail.hu", jelszava pedig "password". Ezzel lehet elérni néhány műveletet.

**További adatok:**
INSERT INTO `futarfalatok`.`restaurant` (`id`, `delivery_fee`, `name`, `rating`) VALUES ('1', '100', 'Étterem \'A\'', '4.8');
INSERT INTO `futarfalatok`.`restaurant` (`id`, `delivery_fee`, `name`, `rating`) VALUES ('2', '0', 'Étterem \'B\'', '4.3');
INSERT INTO `futarfalatok`.`restaurant` (`id`, `delivery_fee`, `name`, `rating`) VALUES ('3', '150', 'Étterem \'C\'', '4.5');

INSERT INTO `futarfalatok`.`dish` (`id`, `calories`, `carbohydrates`, `course`, `fat`, `name`, `protein`) VALUES ('1', '60', '35', 'Főétel', '35', 'Spagetti', '32');
INSERT INTO `futarfalatok`.`dish` (`id`, `calories`, `carbohydrates`, `course`, `fat`, `name`, `protein`) VALUES ('2', '40', '10', 'Leves', '15', 'Grízgaluska leves', '20');
INSERT INTO `futarfalatok`.`dish` (`id`, `calories`, `carbohydrates`, `course`, `fat`, `name`, `protein`) VALUES ('3', '120', '50', 'Desszert', '23', 'Feketeerdő torta', '30');
INSERT INTO `futarfalatok`.`dish` (`id`, `calories`, `carbohydrates`, `course`, `fat`, `name`, `protein`) VALUES ('4', '65', '42', 'Főétel', '20', 'Rántott halfilé rizzsel', '60');

2. Backend:
	- JDK 17
	- Futtatókörnyezet (IDE), de működik egyszerű parancssorból is
	- Apache Maven 3.8.5

A jdk és a maven beállítása Windows rendszeren a környezeti változóknál történik. A "path" változóhoz szükséges mind a kettő hozzáadása.

 3. Frontend
	- Node.js
	
**Indítás**
Két alkalmazás elindítása szükséges a megfelelő eredmény érdekében. A backend indítható vagy futtatókörnyezetben vagy parancssor esetén a "mvn spring-boot:run" parancs kiadásával. A frontend indításához szintén elegendő a parancssor, lényeg mind a kettő esetén, hogy a *FŐ* könyvtárban legyünk. Ehhez két parancs szükséges. "npm install", amely a szükséges függőségeket betölti a jó működés érdekében, majd az "npm start" kifejezéssel elindul az alkalmazás.
**Fontos, hogy fusson az adatbázis, a backend és csak azután a frontend.**