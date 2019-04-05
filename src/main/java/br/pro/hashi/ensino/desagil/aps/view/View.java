package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class View extends JPanel implements ActionListener {

    private final JComboBox<Gate> menu;
    private GateView gateview;

    // O construtor recebe uma lista de calculadoras, que
    // devem ser adicionadas ao menu. O menu consegue mostrar
    // os nomes das calculadoras graças ao método toString.
    public View(LinkedList<Gate> model) {
        menu = new JComboBox<>();
        for (Gate g : model) {
            menu.addItem(g);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(menu);
        addGateView(0);

        menu.addActionListener(this);
    }

    // Este método é responsável por adicionar a este JPanel a
    // representação gráfica da calculadora identificada por
    // um índice. Isso consiste em três passos simples.
    private void addGateView(int index) {

        // 1. Usar o índice para pegar a calculadora do menu.
        Gate gate= menu.getItemAt(index);

        // 2. Construir a representação gráfica a partir dela.
        gateview = new GateView(gate);

        // 3. Adicionar essa representação gráfica no JPanel.
        add(gateview);
    }

    // O que esta componente deve fazer quando o usuário
    // selecionar um item no menu? Bem, ela deve...
    @Override
    public void actionPerformed(ActionEvent event) {

        // ...tirar a atual representação gráfica de calculadora...
        remove(gateview);

        // ...descobrir qual é o índice da calculadora selecionada...
        int index = menu.getSelectedIndex();

        // ...e usar o método acima para adicionar a nova.
        addGateView(index);

        // Mantenha esta linha, mas não precisa entendê-la.
        // É necessária para evitar bugs em alguns sistemas.
        ((JFrame) SwingUtilities.getRoot(this)).pack();
    }
}
