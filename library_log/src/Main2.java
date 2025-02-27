import javax.swing.*;
import java.awt.*;

public class Main2 {
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

    public static void mainPage() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,300);
        frame.setTitle("Actiuni");

        JPanel panel = new JPanel(new GridLayout(2,2));
        frame.add(panel);

        JButton addButton = new JButton("Add");
        panel.add(addButton);

        JButton removeButton = new JButton("Remove");
        panel.add(removeButton);

        JButton editButton = new JButton("Edit");
        panel.add(editButton);

        JButton viewButton = new JButton("View");
        panel.add(viewButton);

        addButton.addActionListener(e -> {
            addPage();
        });

        removeButton.addActionListener(e -> {
            removePage();
        });

        editButton.addActionListener(e -> {
            editPage();
        });

        viewButton.addActionListener(e -> {
            viewPage();
        });

        frame.setVisible(true);
    }

    static void addPage(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(200,400);
        frame.setTitle("Adaugare");

        JPanel panel = new JPanel(new GridLayout(7,1));
        frame.add(panel);

        JLabel title = new JLabel("Titlu");
        JLabel author = new JLabel("Autor");
        JLabel year = new JLabel("An");
        JLabel genre = new JLabel("Categorie");
        JLabel pages = new JLabel("Pagini");
        JLabel date = new JLabel("Data");

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField pagesField = new JTextField();
        JTextField dateField = new JTextField();

        panel.add(title);
        panel.add(titleField);
        panel.add(author);
        panel.add(authorField);
        panel.add(year);
        panel.add(yearField);
        panel.add(genre);
        panel.add(genreField);
        panel.add(pages);
        panel.add(pagesField);
        panel.add(date);
        panel.add(dateField);
        frame.setVisible(true);

        JButton addButton = new JButton("Adauga");

        panel.add(addButton);
        addButton.addActionListener(e -> {
            try {
                String titlu = titleField.getText();
                String autor = authorField.getText();
                int an = Integer.parseInt(yearField.getText());
                String categorie = genreField.getText();
                int pagini = Integer.parseInt(pagesField.getText());
                String data = dateField.getText();
                if(titlu.isEmpty() || autor.isEmpty() || data.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Completati toate spatiile");
                }else {

                    if (categorie.equalsIgnoreCase("Carte")) {
                        Carte carte = new Carte(titlu, autor, an, pagini, data);
                        biblioteca.add_lecture(carte);
                        JOptionPane.showMessageDialog(null, "Carte adaugata");
                    } else if (categorie.equalsIgnoreCase("Revista")) {
                        Revista revista = new Revista(titlu, autor, an, pagini, data);
                        biblioteca.add_lecture(revista);
                        JOptionPane.showMessageDialog(null, "Revista adaugata");
                    } else if (categorie.equalsIgnoreCase("Articol")) {
                        Articol articol = new Articol(titlu, autor, an, pagini, data);
                        biblioteca.add_lecture(articol);
                        JOptionPane.showMessageDialog(null, "Articol adaugat");
                    } else {
                        JOptionPane.showMessageDialog(null, "Date invalide, alegeti o categorie valida");
                    }
                }
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Data invalide, introduceti numere la campurile pagini si an");
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Eroare : " + ex.getMessage());
            }

        });
    }

    static void removePage(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(200,400);
        frame.setTitle("Stergere");

        JPanel panel = new JPanel(new GridLayout(7,1));
        frame.add(panel);

        JLabel title = new JLabel("Titlu");

        JTextField titleField = new JTextField();


        panel.add(title);
        panel.add(titleField);
        frame.setVisible(true);

        JButton addButton = new JButton("Sterge");

        panel.add(addButton);
        addButton.addActionListener(e -> {
            try {
                String titlu = titleField.getText();
                boolean ok = biblioteca.deleteByTitle(titlu);
                if(ok == false){
                    JOptionPane.showMessageDialog(null,"Lectura nu a fost gasita");
                }else{
                    JOptionPane.showMessageDialog(null,"Lectura stearsa");
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Eroare : " + ex.getMessage());
            }
        });
    }

    static void editPage(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(200,400);
        frame.setTitle("Editare");

        JPanel panel = new JPanel(new GridLayout(5,1));
        frame.add(panel);

        JLabel title = new JLabel("Titlu");

        JTextField titleField = new JTextField();
        JComboBox<String> edit = new JComboBox();
        JTextField editField = new JTextField();

        edit.addItem("An");
        edit.addItem("Categorie");
        edit.addItem("Ultima Citire");

        panel.add(title);
        panel.add(titleField);
        panel.add(editField);
        panel.add(edit);
        frame.setVisible(true);

        JButton addButton = new JButton("Editare");
        panel.add(addButton);
        addButton.addActionListener(e -> {
            try {
                String titlu = titleField.getText();
                boolean ok = false;
                String caz = edit.getSelectedItem().toString();
                String editText = editField.getText();
                if(caz.equalsIgnoreCase("An")){
                    ok = biblioteca.modify(titlu,1,editText);
                }else if(caz.equalsIgnoreCase("Categorie")){
                    ok = biblioteca.modify(titlu,2,editText);
                }else if(caz.equalsIgnoreCase("Ultima Citire")){
                    ok = biblioteca.modify(titlu,3,editText);
                }
                if(ok == false){
                    JOptionPane.showMessageDialog(null,"Lectura nu a fost gasita sau date invalide");
                }else{
                    JOptionPane.showMessageDialog(null,"Lectura modificata");
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Eroare : " + ex.getMessage());
            }
        });
    }

    static void viewPage(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700,400);
        frame.setTitle("View");
        JPanel panel = new JPanel();
        frame.add(panel);
        JTextArea textArea = new JTextArea();
        panel.add(textArea);

        for(Lectura lectura : biblioteca.getLecturi()){
            textArea.append(lectura.toString() + '\n');
        }

        frame.setVisible(true);

    }

}
