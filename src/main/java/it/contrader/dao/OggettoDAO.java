package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Oggetto;
public class OggettoDAO {
	private final String QUERY_ALL = "SELECT * FROM Oggetto";
	private final String QUERY_CREATE = "INSERT INTO Oggetto (nome, dimensione) VALUES (?,?)";
	private final String QUERY_READ = "SELECT * FROM Oggetto WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE Oggetto SET nome=?, dimensione=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM Oggetto WHERE id=?";
	private final String QUERY_ID = "SELECT id FROM Oggetto WHERE id=?";
	private final String QUERY_DIMENSIONE = "SELECT dimensione FROM Oggetto WHERE id=?";
	private final String QUERY_ALLINCELL = "SELECT * FROM Oggetto JOIN Magazzino ON Magazzino.id_oggetto = Oggetto.id WHERE Magazzino.codice IS NULL";
	
	public OggettoDAO() {}
	public List<Oggetto> getAll() {
		List<Oggetto> OggettosList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Oggetto Oggetto;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				int dimensione = resultSet.getInt("dimensione");
				Oggetto = new Oggetto(nome,dimensione);
				Oggetto.setId(id);
				OggettosList.add(Oggetto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return OggettosList;
	}
	public boolean insert(Oggetto oggettoToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, oggettoToInsert.getNome());
			preparedStatement.setInt(2, oggettoToInsert.getDimensione());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Exeption: " + e);
			return false;
		}

	}
	public Oggetto read(int oggettoId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, oggettoId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String nome;
			int dimensione;

			nome = resultSet.getString("nome");
			dimensione = resultSet.getInt("dimensione");
			Oggetto oggetto = new Oggetto(nome, dimensione);
			oggetto.setId(resultSet.getInt("id"));

			return oggetto;
		} catch (SQLException e) {
			return null;
		}

	}
	
	public boolean update(Oggetto oggettoToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (oggettoToUpdate.getId() == 0)
			return false;

		Oggetto oggettoRead = read(oggettoToUpdate.getId());
		if (!oggettoRead.equals(oggettoToUpdate)) {
			try {
				// Fill the oggettoToUpdate object
				if (oggettoToUpdate.getNome() == null || oggettoToUpdate.getNome().equals("")) {
					oggettoToUpdate.setNome(oggettoRead.getNome());
				}

				if (oggettoToUpdate.getDimensione() == -1) {
					oggettoToUpdate.setDimensione(oggettoRead.getDimensione());
				}

				// Update the oggetto
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, oggettoToUpdate.getNome());
				preparedStatement.setInt(2, oggettoToUpdate.getDimensione());
				preparedStatement.setInt(3, oggettoToUpdate.getId());
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
	
	public int dimensione(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DIMENSIONE);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int n = resultSet.getInt("dimensione");
			if (n != 0) {
				return n;
			}
			else {
				return -1;
			}

		} catch (SQLException e) {
			System.out.println("Errore: " + e);
		}
		return -1;
	}
	
	public List<Oggetto> getAllInCell() {
		List<Oggetto> OggettosList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALLINCELL);
			Oggetto Oggetto;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				int dimensione = resultSet.getInt("dimensione");
				Oggetto = new Oggetto(nome,dimensione);
				Oggetto.setId(id);
				OggettosList.add(Oggetto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return OggettosList;
	}
}
