package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Oggetto;

public class RobotDAO {
	
	private final String QUERY_CREATECODE = "UPDATE Magazzino SET coidce=? FROM Magazzino JOIN Oggetto ON Magazzino.id_oggetto = Oggetto.id WHERE Oggetto.id=?";
	private final String QUERY_REMOVECODE = "UPDATE Magazzino SET coidce=NULL WHERE codice=?";
	private final String QUERY_REMOVEOBJECT = "DELETE FROM Oggetto JOIN Magazzino ON Magazzino.id_oggetto = Oggetto.id WHERE Magazzino.codice=?";

	public boolean createCode(int codice, int oggetto) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_CREATECODE);
			preparedStatement.setInt(1, codice);
			preparedStatement.setInt(2, oggetto);
			int a = preparedStatement.executeUpdate();
			if (a > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			System.out.println("Errore: " + e);
			return false;
		}
	}
	
}
