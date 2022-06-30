package gui;

import entities.Cliente;
import entities.ClienteDAO;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.List;


// Classe Responsável por gerar minha tela de Cadastro
public class FrameCadastro extends JFrame {

    public FrameCadastro() throws ParseException {
        //  criando e definindo dimensões da janela
        setLayout(new FlowLayout());
        setSize(365, 300);
        setTitle("CADASTRO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criando Blocos de componentes
        JPanel panelButton = new JPanel();
        JPanel panelEmail = new JPanel();
        JPanel panelNome = new JPanel();
        JPanel panelTelefone = new JPanel();
        JPanel panelEndereco = new JPanel();

        // Criando campos para inserção de texto
        JTextField txtNome = new JTextField(15);
        JTextField txtEmail = new JTextField(15);
        JTextField txtTelefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
        JTextField txtEndereco = new JTextField(15);

        // Adicionando os campos nos respectivos blocos
        panelNome.add(new JLabel("Nome:"));
        panelNome.add(txtNome);
        panelEmail.add(new JLabel("Email:"));
        panelEmail.add(txtEmail);
        panelTelefone.add(new JLabel("Telefone: "));
        panelTelefone.add(txtTelefone);
        panelEndereco.add(new JLabel("Endereço: "));
        panelEndereco.add(txtEndereco);

        // Criando botões
        JButton buttonCadastrar = new JButton("Cadastrar");
        JButton buttonFechar = new JButton("Fechar");
        JButton buttonListar = new JButton("Listar Clientes");

        // Ação para o botão de fechar
        buttonFechar.addActionListener((e -> System.exit(0)));

        // Ação para o botão de Cadastro - Cadastrando usuário no banco de dados
        buttonCadastrar.addActionListener(e -> {
            // Gera conexão com o banco de dados
            ClienteDAO dao = new ClienteDAO();
            // Cria um novo cliente que será adicionado ao banco
            Cliente cliente1 = new Cliente();
            // Cliente recebe os parâmetros dos campos de texto inseridos pelo usuário
            cliente1.setNome(txtNome.getText());
            cliente1.setEmail(txtEmail.getText());
            cliente1.setTelefone(txtTelefone.getText());
            cliente1.setEndereco(txtEndereco.getText());
            // Cliente é Cadastrado no Banco
            dao.setCliente(cliente1);
            // Campos de Texto são limpos
            txtNome.setText("");
            txtEmail.setText("");
            txtTelefone.setText("");
            txtEndereco.setText("");
            System.out.println("Cadastrado");

            /* Para confirmar que os dados foram cadastrados no Banco, essa parte do programa imprime todos os clientes
              Cadastrados no Banco de dados
            */
            List<Cliente> clientes = dao.getClientes();
            for(Cliente cliente: clientes){
                System.out.println("------------------------");
                System.out.println("id: "+cliente.getId());
                System.out.println("Nome: "+cliente.getNome());
                System.out.println("Email: "+cliente.getEmail());
                System.out.println("Telefone: "+cliente.getTelefone());
                System.out.println("Endereço: "+cliente.getEndereco());
                System.out.println("Data de Cadastro: "+cliente.getDataCadastro().getTime());
            }
        });

        //ação para botão de listar
        buttonListar.addActionListener(e -> {
            FrameTable f = new FrameTable();
            f.setVisible(true);
            setVisible(false);
                });

        // Adicionando os Botões ao bloco de botões
        panelButton.add(buttonCadastrar);
        panelButton.add(buttonFechar);
        panelButton.add(buttonListar);

        // Adicionando os blocos a tela de Cadastro
        add(panelNome);
        add(panelEmail);
        add(panelTelefone);
        add(panelEndereco);
        add(panelButton);


        setVisible(true);
    }

}
