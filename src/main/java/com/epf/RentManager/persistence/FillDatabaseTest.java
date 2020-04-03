package com.epf.RentManager.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.DeleteDbFiles;


public class FillDatabaseTest {


    public static void main(String[] args) throws Exception {
        try {
            DeleteDbFiles.execute("~", "RentManagerDatabaseTest", true);
            insertWithPreparedStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private static void insertWithPreparedStatement() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement createPreparedStatement = null;

        List<String> createTablesQueries = new ArrayList<String>();
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Client(id INT primary key auto_increment, client_id INT, nom VARCHAR(100), prenom VARCHAR(100), email VARCHAR(100), naissance DATETIME)");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Vehicle(id INT primary key auto_increment, constructeur VARCHAR(100), modele VARCHAR (100), nb_places TINYINT(255))");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Reservation(id INT primary key auto_increment, client_id INT, foreign key(client_id) REFERENCES Client(id), vehicle_id INT, foreign key(vehicle_id) REFERENCES Vehicle(id), debut DATETIME, fin DATETIME)");

        try {
            connection.setAutoCommit(false);

            for (String createQuery : createTablesQueries) {
            	createPreparedStatement = connection.prepareStatement(createQuery);
	            createPreparedStatement.executeUpdate();
	            createPreparedStatement.close();
            }

            // Remplissage de la base avec des Vehicules et des Clients
            Statement stmt = connection.createStatement();
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Renault', 'Clio', 5)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Telsa', 'Model X', 4)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Peugeot', '208', 5)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Alfa Romeo', 'Guilietta', 5)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Seat', 'Ibiza', 5)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Aston Martin', 'DBS Superleggera', 2)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Renault', 'Espace', 7)");     
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Mini', 'Cooper', 4)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Nissan', 'Qashqai', 5)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('BMW', 'Série 3 Cabriolet', 4)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Renault', 'Twingo', 4)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Audi', 'A8', 5)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Smart', 'Fortwo', 2)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Porsche', 'Cayenne', 5)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Toyota', 'Auris', 5)");
            
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Dupont', 'Jean', 'jean.dupont@email.com', '1988-01-22')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Morin', 'Sabrina', 'sabrina.morin@email.com', '1967-05-28')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Afleck', 'Steeve', 'steeve.afleck@email.com', '1984-03-14')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Rousseau', 'Jacques', 'jacques.rousseau@email.com', '1976-09-18')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Durant', 'Jules', 'jules.durant@email.com', '1991-08-07')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Smith', 'Emma', 'emma.smith@email.com', '1993-08-31')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Martin', 'Aurélie', 'aurelie.martin@email.com', '1990-11-05')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Leroux', 'Marc', 'marc.leroux@email.com', '1962-06-13')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Benoit', 'Michel', 'michel.benoit@email.com', '1972-04-21')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Morel', 'Alix', 'alix.morel@email.com', '1980-02-17')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Giraud', 'Armand', 'armand.giraud@email.com', '1983-07-27')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Dufour', 'Irène', 'irene.dufour@email.com', '1969-10-11')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Masson', 'Mathilde', 'mathilde.masson@email.com', '1978-12-03')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Lambert', 'Raphaël', 'raphael.lambert@email.com', '1983-05-01')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Goff', 'Clémence', 'clemence.goff@email.com', '1984-10-16')");
            
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(13, 12, '1997-07-27', '1997-07-31')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(10, 8, '1998-03-13', '1998-03-19')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(10, 8, '1998-03-21', '1998-03-27')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(13, 2, '2001-02-21', '2001-02-26')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(15, 6, '2002-04-11', '2002-04-16')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(12, 7, '2004-08-03', '2004-08-09')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(11, 11, '2005-10-04', '2005-10-06')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(1, 6, '2006-04-11', '2006-04-16')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(4, 5, '2006-11-10', '2006-11-16')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(7, 9, '2008-05-18', '2008-05-24')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(15, 13, '2010-12-12', '2010-12-18')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(8, 7, '2011-04-05', '2011-04-11')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(5, 8, '2012-11-08', '2012-11-14')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(6, 6, '2013-02-21', '2013-02-23')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(2, 14, '2013-08-05', '2013-08-11')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(9, 15, '2014-08-03', '2014-08-09')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(3, 7, '2015-06-04', '2015-06-10')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(14, 10, '2016-05-12', '2016-05-18')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(12, 4, '2016-06-19', '2016-06-23')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(15, 3, '2017-02-15', '2017-02-21')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(5, 13, '2017-03-17', '2017-03-22')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(3, 12, '2018-05-05', '2018-05-11')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(4, 15, '2018-05-27', '2018-05-30')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(9, 10, '2018-07-14', '2018-07-20')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(12, 9, '2018-11-10', '2018-11-16')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(11, 1, '2019-01-03', '2019-01-09')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(11, 5, '2019-01-9', '2019-01-13')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(14, 10, '2019-05-08', '2019-05-12')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(10, 3, '2019-05-12', '2019-05-16')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(6, 1, '2019-06-11', '2019-06-14')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(4, 15, '2019-07-14', '2019-07-18')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(2, 4, '2019-08-01', '2019-08-06')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(9, 2, '2019-10-03', '2019-10-09')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(14, 5, '2019-10-08', '2019-10-14')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(6, 14, '2019-11-07', '2019-11-12')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(7, 4, '2019-12-08', '2019-12-14')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(8, 2, '2019-12-22', '2019-12-28')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(2, 1, '2019-12-30', '2020-01-02')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(7, 14, '2020-01-09', '2020-01-15')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(5, 9, '2020-01-23', '2020-01-25')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(1, 11, '2020-02-03', '2020-02-05')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(8, 13, '2020-02-15', '2020-02-19')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(3, 11, '2020-02-17', '2020-02-22')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(1, 3, '2020-03-04', '2020-03-08')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(13, 12, '2020-03-10', '2020-03-16')");
            
            connection.commit();
            System.out.println("Success!");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}