package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {

    private final NandGate[] nandGates;
    private final SignalEmitter[] emitters;

    XorGate() {
        super(2);
        nandGates = new NandGate[4];
        nandGates[0] = new NandGate();
        nandGates[1] = new NandGate();
        nandGates[2] = new NandGate();
        nandGates[3] = new NandGate();
        emitters = new SignalEmitter[2];
    }

    public boolean read() {
        // Creating middle nandGate
        nandGates[1].connect(0, emitters[0]);
        nandGates[1].connect(1, emitters[1]);

        nandGates[0].connect(0, emitters[0]);
        nandGates[0].connect(1, nandGates[1]);

        nandGates[2].connect(0, nandGates[1]);
        nandGates[2].connect(1, emitters[1]);

        // Final nandGate
        nandGates[3].connect(0, nandGates[0]);
        nandGates[3].connect(1, nandGates[2]);

        return nandGates[3].read();
    }

    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        emitters[inputPin] = emitter;
    }

}
