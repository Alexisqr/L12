import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Dialog4 extends JFrame {
    private String[][] questions = {
            {"2+2=", "4", "2", "3", "1"},
            {"Cat has ", "2 lives", "1 lives", "3 lives", "9 lives"},
            {"What's my favorite color?", "red", "black", "green", "yellow"}

    };

    private int[] correctAnswers = {0, 3, 1, 2, 1};

    private ArrayList<Integer> scores = new ArrayList<>();
    private int startQuestions= 0;

    private JLabel Label;
    private JRadioButton[] var;
    private JButton nextButton;

    public Dialog4() {
        setTitle("Test Program");

        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();

        panel.setLayout(new GridLayout(0, 1));

        Label = new JLabel(questions[startQuestions][0]);
        panel.add(Label);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK)," "));

        ButtonGroup group = new ButtonGroup();
        var = new JRadioButton[questions[startQuestions].length - 1];
        int k = 0;
        for (int i = 1; i < questions[startQuestions].length; i++) {
            var[k] = new JRadioButton(questions[startQuestions][i]);
            group.add(var[k]);
            panel.add(var[k]);
            k++;
        }

        nextButton = new JButton("Далі");

        nextButton.addActionListener(new Listener());
        panel2.add(nextButton);
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK)," "));

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(panel2, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedOption = -1;
            for (int i = 0; i < var.length; i++) {
                if (var[i].isSelected()) {
                    selectedOption = i;
                    break;
                }
            }

            if (selectedOption >= 0) {
                if (selectedOption == correctAnswers[startQuestions]) {
                    scores.add(5);
                } else {
                    scores.add(2);
                }

                startQuestions++;
                int k =0;
                if (startQuestions < questions.length) {
                    Label.setText(questions[startQuestions][0]);
                    for (int i = 1; i < questions[startQuestions].length; i++) {
                        var[k].setText(questions[startQuestions][i]);
                        var[k].setSelected(false);//це метод у Java, який використовується для встановлення вибраного стану прапорця, перемикача
                        k++;
                    }
                } else {
                    double averageScore = 0;
                    for (Integer score : scores) {
                        averageScore += score;
                    }
                    averageScore =  averageScore/scores.size();

                    JOptionPane.showMessageDialog(Dialog4.this,
                            "Ваш результат : " + averageScore,
                            " ",
                            JOptionPane.INFORMATION_MESSAGE);

                    System.exit(0);
                }
            } else {
                JOptionPane.showMessageDialog(Dialog4.this,
                        "Будьласка ввиберіть один валіант.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new Dialog4();
    }
}