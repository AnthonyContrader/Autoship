package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.contrader.model.Codice;
import it.contrader.utils.ConnectionSingleton;

public  class CodiceDAO implements DAO<Codice> {
private final String QUERY_ALL = " SELECT * FROM Codice ";
private final String QUERY_CREATE = "INSERT INTO Codice (id,otp) VALUES (?,?)";
private final String QUERY_READ = "SELECT * FROM Codice WHERE id=?";
private final String QUERY_UPDATE = "UPDATE user SET id=?, otp=?";
private final String QUERY_DELETE = "DELETE FROM Codice WHERE id=?";

public CodiceDAO() {
	
}
	
	
	public List<Codice> getAll() {
		List<Codice> codiceList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Codice codice;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String otp = resultSet.getString("otp");
				codice = new Codice(id,otp);
				codice.setId(id);
				codiceList.add(codice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codiceList;
	}
	public boolean insert(Codice codiceToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setInt(1, codiceToInsert.getId());
			preparedStatement.setString(2,codiceToInsert.getOtp());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}
	public Codice read(int codiceId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, codiceId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String otp;
            otp = resultSet.getString("otp");
			Codice codice = new Codice (codiceId, otp);
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
				// Fill the userToUpdate object
				if (codiceToUpdate.getOtp() == null || codiceToUpdate.getOtp().equals("")) {
					codiceToUpdate.setOtp(( codiceRead).getOtp());
				}

				
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setInt(1, codiceToUpdate.getId());
				preparedStatement.setString(2, codiceToUpdate.getOtp());
				int a = preparedStatement.executeUpdate();
				if (a > 0)
					return true;
				else
					return false;

			} catch (SQLException e) {
				
			}
			
		}return false;}
			
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
		
		
		
	}
		

	
		
