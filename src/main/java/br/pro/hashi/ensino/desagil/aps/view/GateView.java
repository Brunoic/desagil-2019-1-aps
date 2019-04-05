package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A classe JPanel representa uma das componentes mais
// simples da Swing. A função dela é simplesmente ser
// um contêiner para colocar outras componentes dentro.
// A razão de implementar ActionListener está mais abaixo.
public class GateView extends JPanel implements ActionListener {

    // A ideia é que essa componente gráfica represente
    // uma calculadora específica. Essa calculadora que
    // está sendo representada é guardada como atributo.
    private final Gate gate;

    // A classe JTextField representa um campo de texto.
    private final JCheckBox[] inputs;
    private final JCheckBox output;
    private final Switch[] sws;

    public GateView(Gate gate) {
        this.gate = gate;
        int g_size = gate.getInputSize();


        JLabel entrada_label;
        if (g_size>1) {
            entrada_label = new JLabel("Entradas");
        }
        else{
            entrada_label = new JLabel("Entrada");
        }

        inputs = new JCheckBox[g_size];
        sws = new Switch[g_size];
        output = new JCheckBox();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(entrada_label);

        for (int i=0; i<g_size; i++){
            inputs[i] = new JCheckBox();
            add(inputs[i]);
            inputs[i].addActionListener(this);


            sws[i] = new Switch();
            gate.connect(i, sws[i]);
        }

        JLabel saida_label = new JLabel("Saída");
        add(saida_label);
        add(output);
        // O último campo de texto não pode ser editável, pois é
        // só para exibição. Logo, configuramos como desabilitado.
        output.setEnabled(false);

        // Update é o método que definimos abaixo para atualizar o
        // último campo de acordo com os valores dos primeiros.
        // Precisamos chamar esse método no final da construção
        // para garantir que a interface não nasce inconsistente.
        update();
    }

    private void update() {
//        for (int i=0; i<inputs.length; i++){
//            sws[i].turnOff();
//            if (inputs[i].isSelected()){
//                sws[i].turnOn();
//            }
//        }

        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].isSelected()) {
                sws[i].turnOn();
            } else {
                sws[i].turnOff();
            }
        }
        output.setSelected(gate.read());

//        output.setSelected(gate.read());
    }

    // O que esta componente deve fazer quando o usuário der um
    // Enter depois de digitar? Apenas chamar o update, é claro!
    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
}
