import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Main extends JFrame {
    private JTextField nameField;
    private JTextArea biographyArea;
    private JComboBox<String> genreComboBox;
    private JList<String> categoryList;
    private DefaultListModel<String> biographyListModel;

    public Main() {
        setTitle("Міні-бібліятэка аўтабіяграфій");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        nameField = new JTextField(20);
        biographyArea = new JTextArea(5, 20);
        String[] genres = {"Навуковая", "Мастацкая", "Асабістая"};
        genreComboBox = new JComboBox<>(genres);
        String[] categories = {"Пісьменнікі", "Навукоўцы", "Палітыкі"};
        categoryList = new JList<>(categories);
        categoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        biographyListModel = new DefaultListModel<>();
        JList<String> biographyList = new JList<>(biographyListModel);

        JButton saveButton = new JButton("Захаваць");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBiography();
            }
        });

        JButton loadButton = new JButton("Загрузіць");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBiographies();
            }
        });

        JButton exitButton = new JButton("Выйсці");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Вы сапраўды хочаце закрыць?", "Пацверджанне", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));
        panel.add(new JLabel("Імя:"));
        panel.add(nameField);
        panel.add(new JLabel("Жанр:"));
        panel.add(genreComboBox);
        panel.add(new JLabel("Катэгорыі:"));
        panel.add(new JScrollPane(categoryList));
        panel.add(new JLabel("Аўтабіяграфія:"));
        panel.add(new JScrollPane(biographyArea));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(exitButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(new JScrollPane(biographyList), BorderLayout.EAST);
    }

    private void saveBiography() {
        String name = nameField.getText();
        String genre = (String) genreComboBox.getSelectedItem();
        String biography = biographyArea.getText();
        ArrayList<String> selectedCategories = new ArrayList<>(categoryList.getSelectedValuesList());

        if (name.isEmpty() || biography.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Імя і аўтабіяграфія не могуць быць пустымі!", "Памылка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("biographies.txt", true))) {
            writer.write("Імя: " + name + "\n");
            writer.write("Жанр: " + genre + "\n");
            writer.write("Катэгорыі: " + selectedCategories + "\n");
            writer.write("Аўтабіяграфія: " + biography + "\n");
            writer.write("------------------------\n");
            biographyListModel.addElement(name + " - " + genre); // Дадаем у спіс
            JOptionPane.showMessageDialog(this, "Аўтабіяграфія захаваная!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Памылка пры захаванні: " + ex.getMessage(), "Памылка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadBiographies() {
        biographyListModel.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("biographies.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Імя: ")) {
                    String name = line.substring(5);
                    String genre = reader.readLine().substring(6);
                    biographyListModel.addElement(name + " - " + genre);
                }
            }
            JOptionPane.showMessageDialog(this, "Аўтабіяграфіі загружаныя!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Памылка пры загрузцы: " + ex.getMessage(), "Памылка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
