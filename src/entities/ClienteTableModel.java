package entities;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;

public class ClienteTableModel extends AbstractTableModel  implements TableModelListener {
    final private List<Cliente> clientes;
    final private List<String> colunas;
    final private ClienteDAO dao;

    public ClienteTableModel(ClienteDAO dao){
        this.dao = dao;
        this.clientes = dao.getClientes();
        colunas = Arrays.asList("Id", "Nome", "Email", "Telefone", "Endereço");
        this.addTableModelListener(this);
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.size();
    }

    public String getColumnName(int i){
        return colunas.get(i);
    }

    public Object getValueAt(int r, int c){
        Cliente cliente = clientes.get(r);
        return switch (c) {
            case 0 -> cliente.getId();
            case 1 -> cliente.getNome();
            case 2 -> cliente.getEmail();
            case 3 -> cliente.getTelefone();
            case 4 -> cliente.getEndereco();
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // O usuário não poderá alterar o id e o nome do cliente
        return columnIndex !=0 && columnIndex !=1;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0 -> cliente.setNome((String) aValue);
            case 1 -> cliente.setEmail((String) aValue);
            case 2 -> cliente.setTelefone((String) aValue);
            case 3 -> cliente.setEndereco((String) aValue);
        }
        fireTableCellUpdated(rowIndex,columnIndex);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int i = e.getFirstRow();
        Cliente cliente = clientes.get(i);
        dao.updateClientes(cliente);
    }
}
