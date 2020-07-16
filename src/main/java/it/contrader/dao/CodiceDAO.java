package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Codice;

public class CodiceDAO {
	private final String QUERY_ALL = "SELECT * FROM Codice";
	private final String QUERY_CREATE = "INSERT INTO Codice (id, codice) VALUES (?,?)";
	private final String QUERY_READ = "SELECT * FROM Codice WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE Codice SET codice=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM Codice WHERE id=?";
	private final String QUERY_ID = "SELECT id FROM Codice WHERE id=?";
	public CodiceDAO() {}
	public List<Codice> getAll() {
		List<Codice> CodiceList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Codice Codice;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int codice = resultSet.getInt("codice");
				Codice = new Codice(id,codice);
				Codice.setId(id);
				CodiceList.add(Codice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CodiceList;
	}
	public boolean insert(Codice codiceToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setInt(1, codiceToInsert.getId());
			preparedStatement.setInt(2, codiceToInsert.getCodice());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Exeption: " + e);
			return false;
		}

	}
	public Codice read(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int codiceId;
			int codiceAttributo;

			codiceId = resultSet.getInt("id");
			codiceAttributo = resultSet.getInt("codice");
			Codice codice = new Codice(codiceId,codiceAttributo);
			codice.setId(resultSet.getInt("id"));

			return codice;
		} catch (SQLException e) {
			return null;
		}

	}
	
	public boolean update(Codice codiceToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (codiceToUpdate.getId() == 0)
			return false;

		Codice codiceRead = read(codiceToUpdate.getId());
		if (!codiceRead.equals(codiceToUpdate)) {
			try {
				// Fill the oggettoToUpdate object
				if (codiceToUpdate.getCodice() == -1) {
					codiceToUpdate.setCodice(codiceRead.getCodice());
				}

				if (codiceToUpdate.getCodice() == -1) {
					codiceToUpdate.setCodice(codiceRead.getCodice());
				}

				// Update the oggetto
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setInt(1, codiceToUpdate.getCodice());
				preparedStatement.setInt(2, codiceToUpdate.getId());
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

		return false;

	}
	
	public boolean delete(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, id);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
			System.out.println("Messaggio : " + e);
		}
		return false;
	}
	
	public int id(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ID);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int n = resultSet.getInt("id");
			if (n != 0) {
				return n;
			}
			else {
				return -1;
			}

		} catch (SQLException e) {
		}
		return -1;
	}
	

}
