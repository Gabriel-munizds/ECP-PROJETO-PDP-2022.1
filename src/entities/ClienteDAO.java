package entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// classe Data Access Object
public class ClienteDAO {
    Connection connection;

    // Construtor com conexão ao banco de dados
    public ClienteDAO(){
       this.connection = new ConnectionFactory().getConnection();
    }

    // Método que envia dados para o meu Banco de dados
    public void setCliente(Cliente cliente){
        String sql = "insert into clientes"	+
                "(nome, email, endereco, telefone, dataCadastro)"+
                "values	(?, ?, ?, ?, ?)";
        try	{
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            //setando os valores
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());
            stmt.setDate(5, new Date(Calendar.getInstance().getTimeInMillis()));

            //executa
            stmt.execute();
            stmt.close();
        }  catch (SQLException e)	{
            throw new RuntimeException(e);
        }

    }

    //Método que retorna os dados do meu Banco de Dados
    public List<Cliente> getClientes() {
        try {
            List<Cliente> clienteList = new ArrayList<Cliente>();
            PreparedStatement stmt = this.connection.prepareStatement("select * from clientes");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));

                Calendar date = Calendar.getInstance();
                date.setTime(rs.getDate("dataCadastro"));
                cliente.setDataCadastro(date);

                clienteList.add(cliente);
            }
            rs.close();
            stmt.close();
            return clienteList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // método para dar update na minha tabela do banco de dados
    public void updateClientes(Cliente cliente){
        String sql = "update clientes set email=?, telefone=?, endereco=? where nome=?";
        try	{
            //prepared statement para update
            PreparedStatement stmt = connection.prepareStatement(sql);

            //setando os valores
            stmt.setString(1, cliente.getEmail());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getNome());

            //executa
            stmt.execute();
            stmt.close();
        }  catch (SQLException e)	{
            throw new RuntimeException(e);
        }

    }
}
