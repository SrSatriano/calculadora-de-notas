import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Calculadora extends JFrame implements ActionListener {

    private JLabel[] labelsNota;
    private JLabel[] labelsPeso;
    private JTextField[] entriesNota;
    private JTextField[] entriesPeso;
    private JButton buttonCalcular;
    private JLabel labelResultado;

    public Calculadora() {
        setTitle("Calculadora de Nota Final");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(3);

        int numCampos = 6;

        labelsNota = new JLabel[numCampos];
        labelsPeso = new JLabel[numCampos];
        entriesNota = new JTextField[numCampos];
        entriesPeso = new JTextField[numCampos];

        for (int i = 0; i < numCampos; i++) {
            labelsNota[i] = new JLabel("Nota " + (i + 1) + ":");
            add(labelsNota[i]);
            entriesNota[i] = new JTextField(10);
            add(entriesNota[i]);

            labelsPeso[i] = new JLabel("Peso da Nota " + (i + 1) + " (em decimal):");
            add(labelsPeso[i]);
            entriesPeso[i] = new JTextField(10);
            add(entriesPeso[i]);
        }

        buttonCalcular = new JButton("Calcular");
        buttonCalcular.addActionListener(this);
        add(buttonCalcular);

        labelResultado = new JLabel();
        add(labelResultado);

        pack();
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonCalcular) {
            int numCampos = labelsNota.length;
            double[] notas = new double[numCampos];
            double[] pesos = new double[numCampos];
            int numNotasPreenchidas = 0;

            for (int i = 0; i < numCampos; i++) {
                String notaStr = entriesNota[i].getText();
                String pesoStr = entriesPeso[i].getText();

                if (!notaStr.isEmpty() && !pesoStr.isEmpty()) {
                    notas[numNotasPreenchidas] = Double.parseDouble(notaStr);
                    pesos[numNotasPreenchidas] = Double.parseDouble(pesoStr);
                    numNotasPreenchidas++;
                }
            }

            if (numNotasPreenchidas > 0) {
                double notaPonderadaTotal = 0;
                double pesoTotal = 0;

                for (int i = 0; i < numNotasPreenchidas; i++) {
                    notaPonderadaTotal += notas[i] * pesos[i];
                    pesoTotal += pesos[i];
                }

                double notaFinal = notaPonderadaTotal;
                if (pesoTotal != 0) {
                    notaFinal /= pesoTotal;
                }

                labelResultado.setText("A nota final do semestre é: " + notaFinal);
            } else {
                labelResultado.setText("Nenhuma nota preenchida.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculadora().setVisible(true);
        });
    }
}
