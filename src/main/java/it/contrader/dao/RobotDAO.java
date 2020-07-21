package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Oggetto;
import it.contrader.model.Magazzino;


public class RobotDAO {
	
	private final String QUERY_CREATECODE = "UPDATE Magazzino SET otp=? WHERE id_oggetto=?";
	private final String QUERY_REMOVECODE = "UPDATE Magazzino SET otp=NULL WHERE codice=?";
	private final String QUERY_REMOVEOBJECT = "DELETE FROM Oggetto JOIN Magazzino ON Magazzino.id_oggetto = Oggetto.id WHERE Magazzino.id=?";
     
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
	
	public boolean DelateCode(int codice) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_REMOVECODE);
			preparedStatement.setInt(1, codice);
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
	
	
	public boolean RemoveObject(int codice) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_REMOVEOBJECT);
			preparedStatement.setInt(1, codice);
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
