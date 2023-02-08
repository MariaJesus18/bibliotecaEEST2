package database;

import java.sql.*;

public class conexaoMySQL {

    // informacoes do local onde o banco esta
    public static String URL = "jdbc:mysql://localhost:3306/bibliotecaeest";
    public static String USER = "root";
    public static String PASSWORD = "mjsm2004";

    private Connection dbconn = null;
    // private ResultSet resultsql = null;

    public static Connection createConnectionToMySQL() throws Exception { // metodo que estabelece a conexao com bd

        Connection dbconn = DriverManager.getConnection(URL, USER, PASSWORD);

        return dbconn;
    }

}
