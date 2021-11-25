package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/** The driver. */
	// Parametros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "50br@d0@)!%";
	
	/** The erro. */
	private String erro = "Não foi possível realizar a conexão com o Banco de dados, verifique!\n";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.err.println(erro);
			return null;
		}
	}

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	/* CRUD create */
	public void inserirContato(JavaBeans contato) {
		String create = "insert into tbcontato (nomecontato, fonecontato, emailcontato) values (?,?,?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// preparar a query para execução no DB
			PreparedStatement pst = con.prepareStatement(create);
			// passando os paramentros (?) para a query
			pst.setString(1, contato.getNomeContato());
			pst.setString(2, contato.getFoneContato());
			pst.setString(3, contato.getEmailContato());
			// executar a query
			pst.executeUpdate();
			// encerrar a conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Lista contato.
	 *
	 * @return the array list
	 */
	/* CRUD read */
	public ArrayList<JavaBeans> listaContato() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String listar = "select * from tbcontato order by nomecontato";
		try {
			// abrir a conexão
			Connection con = conectar();
			// preparar a query para execução no DB
			PreparedStatement pst = con.prepareStatement(listar);
			ResultSet rs = pst.executeQuery();
			// o laço abaixo será executado enquanto houver dados
			while (rs.next()) {
				String idcontato = rs.getString(1);
				String nomecontato = rs.getString(2);
				String fonecontato = rs.getString(3);
				String emailcontato = rs.getString(4);
				// populando o array list
				contatos.add(new JavaBeans(idcontato, nomecontato, fonecontato, emailcontato));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	/* CRUD selecionar contato */
	public void selecionarContato(JavaBeans contato) {
		String selecionar = "select * from tbcontato where idcontato= ?";
		try {
			// abrir a conexão
			Connection con = conectar();
			// preparar a query para execução no DB
			PreparedStatement pst = con.prepareStatement(selecionar);
			// passando os paramentros (?) para a query
			pst.setString(1, contato.getIdContato());
			// executar a query
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				contato.setIdContato(rs.getString(1));
				contato.setNomeContato(rs.getString(2));
				contato.setFoneContato(rs.getString(3));
				contato.setEmailContato(rs.getString(4));
			}
			// encerrar a conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Update contato.
	 *
	 * @param contato the contato
	 */
	/* CRUD update */
	public void updateContato(JavaBeans contato) {
		String update = "update tbcontato set " + "nomecontato=?, fonecontato=?, emailcontato=? " + "where idcontato=?";
		try {
			// abrir a conexão
			Connection con = conectar();
			// preparar a query para execução no DB
			PreparedStatement pst = con.prepareStatement(update);
			// passando os paramentros (?) para a query
			pst.setString(1, contato.getNomeContato());
			pst.setString(2, contato.getFoneContato());
			pst.setString(3, contato.getEmailContato());
			pst.setString(4, contato.getIdContato());
			// executar a query
			pst.executeUpdate();
			// encerrar a conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Deleta contato.
	 *
	 * @param contato the contato
	 */
	/* CRUD DELETE */
	public void deletaContato(JavaBeans contato) {
		String deleta = "delete from tbcontato where idcontato=?";
		try {
			// abrir a conexão
			Connection con = conectar();
			// preparar a query para execução no DB
			PreparedStatement pst = con.prepareStatement(deleta);
			// passando os paramentros (?) para a query
			pst.setString(1, contato.getIdContato());
			// executar a query
			pst.executeUpdate();
			// encerrar a conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}