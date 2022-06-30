package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 classe responsável por gerar minhas conexões com o banco de dados.
 As especificações do banco bem como o script sql se encontram na pasta "/sql"
 */
public class ConnectionFactory {
    public Connection getConnection() {
        String url = "jdbc:mysql://localhost/cliente";
        try {
            return DriverManager.getConnection(url, "root", "<SENHA DO BANCO DE DADOS>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
