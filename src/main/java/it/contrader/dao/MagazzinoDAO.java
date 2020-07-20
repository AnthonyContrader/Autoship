package it.contrader.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;

import it.contrader.model.Magazzino;

public class MagazzinoDAO {
	private final String QUERY_ALL = "SELECT * FROM Magazzino WHERE cancellato=0";
	private final String QUERY_CREATE = "INSERT INTO Magazzino (id_oggetto, capienza,cancellato) VALUES (?,?,0)";
	private final String QUERY_READ = "SELECT * FROM Magazzino WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE Magazzino SET id_oggetto=?, capienza=? WHERE id=?";
	private final String QUERY_DELETE = "UPDATE Magazzino SET cancellato=1 WHERE id=?";
	private final String QUERY_OGGETTO = "SELECT id_oggetto FROM Magazzino WHERE id=?";
	private final String QUERY_REMOVEOGGETTO = "UPDATE Magazzino SET id_oggetto=NULL WHERE id_oggetto=?";	
	
	public MagazzinoDAO() {

	}
	
	public List<Magazzino> getAll() {
		List<Magazzino> MagazzinosList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Magazzino Magazzino;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int oggetto = resultSet.getInt("id_oggetto");
				int capienza = resultSet.getInt("capienza");
				Magazzino = new Magazzino(oggetto, capienza);
				Magazzino.setId(id);
				MagazzinosList.add(Magazzino);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return MagazzinosList;
	}
	
	public boolean insert(Magazzino magazzinoToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			if(magazzinoToInsert.getId_oggetto() == 0) {
				preparedStatement.setObject(1, null);
			}
			else {
				preparedStatement.setInt(1, magazzinoToInsert.getId_oggetto());
			}
			preparedStatement.setInt(2, magazzinoToInsert.getCapienza());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Errore: " + e);
			return false;
		}

	}
	public Magazzino read(int magazzinoId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, magazzinoId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int oggetto, capienza;

			oggetto = resultSet.getInt("id_oggetto");
			capienza = resultSet.getInt("capienza");
			Magazzino magazzino = new Magazzino(oggetto, capienza);
			magazzino.setId(resultSet.getInt("id"));

			return magazzino;
		} catch (SQLException e) {
			return null;
		}

	}
	
	public boolean update(Magazzino magazzinoToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (magazzinoToUpdate.getId() == 0)
			return false;

		Magazzino magazzinoRead = read(magazzinoToUpdate.getId());
		if (!magazzinoRead.equals(magazzinoToUpdate)) {
			try {
				// Fill the magazzinoToUpdate object
				if (magazzinoToUpdate.getId_oggetto() == -1)  {
					magazzinoToUpdate.setId_oggetto(magazzinoRead.getId_oggetto());
				}

				if (magazzinoToUpdate.getCapienza() == -1) {
					magazzinoToUpdate.setCapienza(magazzinoRead.getCapienza());
				}

				

				// Update the magazzino
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				if(magazzinoToUpdate.getId_oggetto() == 0) {
					if(magazzinoRead.getId_oggetto() > 0) {
						preparedStatement.setInt(1, magazzinoRead.getId_oggetto());
					}
					else {
						preparedStatement.setObject(1, null);
					}
				}
				else {
					preparedStatement.setInt(1, magazzinoToUpdate.getId_oggetto());
				}
				preparedStatement.setInt(2, magazzinoToUpdate.getCapienza());
				preparedStatement.setInt(3, magazzinoToUpdate.getId());
				int a = preparedStatement.executeUpdate();
				if (a > 0)
					return true;
				else
					return false;

			} catch (SQLException e) {
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
		}
		return false;
	}
	
	public int checkOggetto(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			int oggetto;
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_OGGETTO);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			oggetto = resultSet.getInt("id_oggetto");
			if(oggetto > 0) {
				return oggetto;
			}
			else {
				return -1;
			}
		} catch (SQLException e) {
		}
		return -1;	
	}
	
	public boolean removeOggetto(int oggetto) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
					
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_REMOVEOGGETTO);
			preparedStatement.setInt(1, oggetto);
			int a = preparedStatement.executeUpdate();
			if (a > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
		}
		return false;	
	}
	
	public boolean updateOggetto(int oggetto) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
					
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_REMOVEOGGETTO);
			preparedStatement.setInt(1, oggetto);
			int a = preparedStatement.executeUpdate();
			if (a > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
		}
		return false;	
	}
}
