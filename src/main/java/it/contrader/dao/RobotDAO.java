package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Oggetto;

public class RobotDAO {
	
	private final String QUERY_CREATECODE = "UPDATE Magazzino SET codice=? WHERE id_oggetto=?";
	private final String QUERY_REMOVECODE = "UPDATE Magazzino SET id_oggetto=NULL, codice=NULL WHERE codice=?";
	private final String QUERY_GETOBJECT = "SELECT Oggetto.* FROM Oggetto JOIN Magazzino ON Magazzino.id_oggetto = Oggetto.id WHERE Magazzino.codice=?";
	private final String QUERY_DELETEOBJECT = "DELETE FROM Oggetto WHERE id=?";

	
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
	
	private boolean removeCode(int codice) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_REMOVECODE);
			preparedStatement.setInt(1, codice);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
		}
		return false;
	}
	
	private int getObject(int codice) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GETOBJECT);
			preparedStatement.setInt(1, codice);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int id = resultSet.getInt("id");
			if (id != 0) {
				return id;
			}
			else {
				return -1;
			}

		} catch (SQLException e) {
			System.out.println("Errore: " + e);
		}
		return -1;
	}
	
	private boolean removeObject(int codice) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			int id = getObject(codice);
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETEOBJECT);
			preparedStatement.setInt(1, id);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
			System.out.println("Errore: " + e);
		}
		return false;
	}
	
	public boolean spedizione(int codice) {
		
		if(removeObject(codice) && removeCode(codice) == true) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
