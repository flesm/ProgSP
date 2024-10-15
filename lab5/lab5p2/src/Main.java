import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Main extends JFrame {
    private JTextField nameField; // Тэкставае поле для ўводу імя
    private JTextArea biographyArea; // Тэкставая вобласць для аўтабіяграфіі
    private JComboBox<String> genreComboBox; // Выпадаючы спіс для выбару жанру
    private JList<String> categoryList; // Спіс катэгорый
    private DefaultListModel<String> biographyListModel; // Мадэль для спісу аўтабіяграфій

    // Канструктар для стварэння інтэрфейсу
    public Main() {
        setTitle("Міні-бібліятэка аўтабіяграфій"); // Назва акна
        setSize(500, 400); // Памер акна
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Механізм закрыцця акна
        setLocationRelativeTo(null); // Размяшчэнне акна па цэнтры

        // Стварэнне асноўных кампанентаў інтэрфейсу
        nameField = new JTextField(20); // Тэкставае поле для ўводу імя
        biographyArea = new JTextArea(5, 20); // Тэкставая вобласць для ўводу аўтабіяграфіі
        String[] genres = {"Навуковая", "Мастацкая", "Асабістая"}; // Жанры аўтабіяграфій
        genreComboBox = new JComboBox<>(genres); // Выпадаючы спіс для жанраў
        String[] categories = {"Пісьменнікі", "Навукоўцы", "Палітыкі"}; // Катэгорыі
        categoryList = new JList<>(categories); // Спіс катэгорый
        categoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Множны выбар

        biographyListModel = new DefaultListModel<>(); // Мадэль для спісу аўтабіяграфій
        JList<String> biographyList = new JList<>(biographyListModel); // Стварэнне спісу аўтабіяграфій

        // Кнопка для захавання аўтабіяграфіі
        JButton saveButton = new JButton("Захаваць");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBiography();
            }
        });

        // Кнопка для загрузкі аўтабіяграфій з файла
        JButton loadButton = new JButton("Загрузіць");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBiographies();
            }
        });

        // Кнопка для закрыцця акна з пацвярджэннем
        JButton exitButton = new JButton("Выйсці");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Вы сапраўды хочаце закрыць?", "Пацверджанне", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0); // Закрыццё праграмы
                }
            }
        });

        // Панэль для размяшчэння элементаў інтэрфейсу
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));
        panel.add(new JLabel("Імя:")); // Надпіс для імя
        panel.add(nameField);
        panel.add(new JLabel("Жанр:")); // Надпіс для жанру
        panel.add(genreComboBox);
        panel.add(new JLabel("Катэгорыі:")); // Надпіс для катэгорый
        panel.add(new JScrollPane(categoryList));
        panel.add(new JLabel("Аўтабіяграфія:")); // Надпіс для аўтабіяграфіі
        panel.add(new JScrollPane(biographyArea));

        // Дадаем кнопкі ў панэль
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(exitButton);

        // Асноўны макет акна
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(new JScrollPane(biographyList), BorderLayout.EAST); // Спіс для адлюстравання захаваных аўтабіяграфій
    }

    // Метад для захавання аўтабіяграфіі ў файл
    private void saveBiography() {
        String name = nameField.getText(); // Атрымаем імя
        String genre = (String) genreComboBox.getSelectedItem(); // Атрымаем выбраны жанр
        String biography = biographyArea.getText(); // Атрымаем аўтабіяграфію
        ArrayList<String> selectedCategories = new ArrayList<>(categoryList.getSelectedValuesList()); // Атрымаем выбраныя катэгорыі

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

    // Метад для загрузкі аўтабіяграфій з файла
    private void loadBiographies() {
        biographyListModel.clear(); // Ачысцім мадэль
        try (BufferedReader reader = new BufferedReader(new FileReader("biographies.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Імя: ")) {
                    String name = line.substring(5);
                    String genre = reader.readLine().substring(6);
                    biographyListModel.addElement(name + " - " + genre); // Дадаем аўтабіяграфію ў спіс
                }
            }
            JOptionPane.showMessageDialog(this, "Аўтабіяграфіі загружаныя!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Памылка пры загрузцы: " + ex.getMessage(), "Памылка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Галоўны метад для запуску праграмы
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true); // Запуск акна
            }
        });
    }
}
