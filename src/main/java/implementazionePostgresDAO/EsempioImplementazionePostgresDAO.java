package implementazionePostgresDAO;

import Database.ConnessioneDatabase;
import dao.EsempioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class EsempioImplementazionePostgresDAO implements EsempioDAO{

	private Connection connection;

	public void EsempioImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void esempioQuery() {
		System.out.println("");
	}



}
