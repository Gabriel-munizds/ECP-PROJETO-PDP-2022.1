package gui;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

public class FrameInicial extends JFrame {
    public FrameInicial(){
        setLayout(new FlowLayout());
        setSize(365, 300);
        setTitle("TELA INICIAL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelButton = new JPanel();

        JButton buttonCadastrar = new JButton("Cadastrar Cliente");
        JButton buttonListar = new JButton("Listar Clientes");
        JButton buttonFechar = new JButton("Fechar");

        // Ação para o botão de fechar
        buttonFechar.addActionListener((e -> System.exit(0)));

        // Ação para o botão de Listar
        buttonListar.addActionListener(e ->{
            FrameTable f = new FrameTable();
            f.setVisible(true);
            setVisible(false);
                });
        // Ação para o botão de Cadastrar
        buttonCadastrar.addActionListener(e ->{
            try {
                new FrameCadastro();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            setVisible(false);
        });

        // Adicionando os Botões ao bloco de botões
        panelButton.add(buttonCadastrar);
        panelButton.add(buttonListar);
        panelButton.add(buttonFechar);

        // Adicionando os blocos a tela de Cadastro
        add(panelButton);
        setVisible(true);
    }
}
