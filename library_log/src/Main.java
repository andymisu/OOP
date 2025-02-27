import javax.swing.*;
import java.awt.*;

public class Main {
    static Biblioteca biblioteca = new Biblioteca();
    public static void main(String[] args) {
        Carte book = new Carte("Harap-Alb","Ion Creanga",2020,150,"15/10/2024");
        Revista revista = new Revista("Covrigi", "Marcel Ciolacu",2024,200,"10/01/2025");
        Articol articol = new Articol("Cpp", "Ovidiu Ion", 2021,100,"31/12/2021");
        biblioteca.add_lecture(book);
        biblioteca.add_lecture(revista);
        biblioteca.add_lecture(articol);
        mainPage();
    }

    private static void mainPage(){
        JFrame frame = new JFrame("Actiuni");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 300);

        JPanel panel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton editButton = new JButton("Edit");
        JButton visualizeButton = new JButton("Visualize");


        addButton.addActionListener(e -> {
            addPannel();
        });

        removeButton.addActionListener(e -> {
            removePannel();
        });

        editButton.addActionListener(e -> {

        });

        visualizeButton.addActionListener(e -> {

        });


        frame.setVisible(true);

        panel.add(addButton);
        panel.add(removeButton);
        panel.add(editButton);
        panel.add(visualizeButton);

        frame.add(panel);
    }

    private static void addPannel() {
        JFrame frame = new JFrame("Adăugare");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 500);

        JPanel panel = new JPanel(new GridLayout(7, 1));
        JLabel title = new JLabel("Titlu");
        JLabel author = new JLabel("Autor");
        JLabel year = new JLabel("An");
        JLabel type = new JLabel("Tip");
        JLabel pages = new JLabel("Pagini");
        JLabel date = new JLabel("Data");

        JTextField titleText = new JTextField();
        JTextField authorText = new JTextField();
        JTextField yearText = new JTextField();
        JTextField typeText = new JTextField();
        JTextField pagesText = new JTextField();
        JTextField dateText = new JTextField();

        panel.add(title);
        panel.add(titleText);
        panel.add(author);
        panel.add(authorText);
        panel.add(year);
        panel.add(yearText);
        panel.add(type);
        panel.add(typeText);
        panel.add(pages);
        panel.add(pagesText);
        panel.add(date);
        panel.add(dateText);

        JButton addButton = new JButton("Add");
        panel.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String autor = authorText.getText();
                String titlu = titleText.getText();
                int an = Integer.parseInt(yearText.getText());
                String tip_text = typeText.getText();
                int pagini = Integer.parseInt(pagesText.getText());
                String data = dateText.getText();

                if (tip_text.equalsIgnoreCase("Carte")) {
                    Carte carte = new Carte(titlu, autor, an, pagini, data);
                    biblioteca.add_lecture(carte);
                    JOptionPane.showMessageDialog(frame, "Carte adăugată");
                } else if (tip_text.equalsIgnoreCase("Revista")) {
                    Revista revista = new Revista(titlu, autor, an, pagini, data);
                    biblioteca.add_lecture(revista);
                    JOptionPane.showMessageDialog(frame, "Revistă adăugată");
                } else if (tip_text.equalsIgnoreCase("Articol")) {
                    Articol articol = new Articol(titlu, autor, an, pagini, data);
                    biblioteca.add_lecture(articol);
                    JOptionPane.showMessageDialog(frame, "Articol adăugat");
                } else {
                    JOptionPane.showMessageDialog(frame, "Tip invalid! Introduceți Carte, Revista sau Articol.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Eroare: Introduceți date valide pentru câmpurile numerice (An, Pagini).");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Eroare: " + ex.getMessage());
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }


    private static void removePannel(){
        JFrame frame = new JFrame("Stergere");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 500);

        JPanel panel = new JPanel(new GridLayout(7, 1));
        JLabel title = new JLabel("Titlu");
        JLabel author = new JLabel("Autor");
        JLabel year = new JLabel("An");
        JLabel type = new JLabel("Tip");
        JLabel pages = new JLabel("Pagini");
        JLabel date = new JLabel("Data");

        JTextField titleText = new JTextField();
        JTextField authorText = new JTextField();
        JTextField yearText = new JTextField();
        JTextField typeText = new JTextField();
        JTextField pagesText = new JTextField();
        JTextField dateText = new JTextField();

        panel.add(title);
        panel.add(titleText);
        panel.add(author);
        panel.add(authorText);
        panel.add(year);
        panel.add(yearText);
        panel.add(type);
        panel.add(typeText);
        panel.add(pages);
        panel.add(pagesText);
        panel.add(date);
        panel.add(dateText);

        JButton addButton = new JButton("Add");
        panel.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String autor = authorText.getText();
                String titlu = titleText.getText();
                int an = Integer.parseInt(yearText.getText());
                String tip_text = typeText.getText();
                int pagini = Integer.parseInt(pagesText.getText());
                String data = dateText.getText();

                if (tip_text.equalsIgnoreCase("Carte")) {
                    Carte carte = new Carte(titlu, autor, an, pagini, data);
                    biblioteca.delete_lecture(carte);
                    JOptionPane.showMessageDialog(frame, "Carte stearsa");
                } else if (tip_text.equalsIgnoreCase("Revista")) {
                    Revista revista = new Revista(titlu, autor, an, pagini, data);
                    biblioteca.delete_lecture(revista);
                    JOptionPane.showMessageDialog(frame, "Revistă stearsa");
                } else if (tip_text.equalsIgnoreCase("Articol")) {
                    Articol articol = new Articol(titlu, autor, an, pagini, data);
                    biblioteca.delete_lecture(articol);
                    JOptionPane.showMessageDialog(frame, "Articol sters");
                } else {
                    JOptionPane.showMessageDialog(frame, "Tip invalid! Introduceți Carte, Revista sau Articol.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Eroare: Introduceți date valide pentru câmpurile numerice (An, Pagini).");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Eroare: " + ex.getMessage());
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void editPannel(){
        JFrame frame = new JFrame("Editare");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        JComboBox<String> editare = new JComboBox<>();
        editare.addItem("Ultima Citire");
        editare.addItem("Pagini");
        editare.addItem("An");

        frame.add(editare);

        JTextField edit = new JTextField();
        frame.add(edit);
        frame.setVisible(true);

        JButton editButton = new JButton("Editare");

        frame.add(editButton);

        editButton.addActionListener(e -> {
           String tip = editare.getSelectedItem().toString();
           if(tip.equalsIgnoreCase("Ultima Citire")){
               String data = edit.getText();

           }
        });

    }

    private static void visualizePannel(){

    }

}