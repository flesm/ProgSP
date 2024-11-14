import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListManagementApp extends JFrame {

    private JTable table;
    private JCheckBox selectOddRowsCheckBox;
    private JCheckBox selectEvenRowsCheckBox;
    private Choice evenRowsChoice;

    public ListManagementApp() {
        setTitle("Кіраванне спісам");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Назва"};
        Object[][] data = {
                {1, "Элемент 1"},
                {2, "Элемент 2"},
                {3, "Элемент 3"},
                {4, "Элемент 4"},
                {5, "Элемент 5"},
                {6, "Элемент 6"},
                {7, "Элемент 7"},
                {8, "Элемент 8"},
                {9, "Элемент 9"},
                {10, "Элемент 10"}
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane, BorderLayout.CENTER);

        selectOddRowsCheckBox = new JCheckBox("Выбраць няцотныя радкі");
        selectEvenRowsCheckBox = new JCheckBox("Выбраць цотныя радкі");

        evenRowsChoice = new Choice();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(selectOddRowsCheckBox);
        panel.add(selectEvenRowsCheckBox);
        panel.add(evenRowsChoice);

        add(panel, BorderLayout.SOUTH);

        selectOddRowsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectOddRowsCheckBox.isSelected()) {
                    for (int i = 0; i < table.getRowCount(); i++) {
                        if ((int) table.getValueAt(i, 0) % 2 != 0) {
                            table.addRowSelectionInterval(i, i);
                        }
                    }
                } else {
                    table.clearSelection();
                }
            }
        });

        selectEvenRowsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evenRowsChoice.removeAll();
                if (selectEvenRowsCheckBox.isSelected()) {
                    for (int i = 0; i < table.getRowCount(); i++) {
                        if ((int) table.getValueAt(i, 0) % 2 == 0) {
                            evenRowsChoice.add((String) table.getValueAt(i, 1));
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ListManagementApp().setVisible(true);
            }
        });
    }
}
