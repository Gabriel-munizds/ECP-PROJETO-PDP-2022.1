package gui;

import entities.ClienteDAO;
import entities.ClienteTableModel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;


public class FrameTable extends JFrame {
    //tela de listagem dos clientes
    public FrameTable(){
        super("CLIENTES");
        ClienteDAO dao = new ClienteDAO();
        ClienteTableModel tm = new ClienteTableModel(dao);
        JTable t = new JTable(tm);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(t);
        add(scroll);
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton buttonCadastrar = new JButton("Cadastrar Novo Cliente");
        buttonCadastrar.addActionListener(e -> {
            try {
                new FrameCadastro();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            setVisible(false);
        });


        add(buttonCadastrar, BorderLayout.SOUTH);
    }

}
