import javax.swing.*;
import java.awt.*;

public class Main {
    static Garaj garaj = new Garaj();
    public static void main(String[] args) {
        Masina masina = new Masina("NT25MTH","Renaul","Clio","10/10/2024","13/01/2025",4);
        Motocicleta motocicleta = new Motocicleta("CJ26MMM","Kawasaki", "Ninja","12/12/2024","11/01/2025",10);
        Tractor Tractor = new Tractor("VS01ROU","Aro","Roma","10/10/2024","13/01/2025",5 );
        garaj.addVehicul(masina);
        garaj.addVehicul(motocicleta);
        garaj.addVehicul(Tractor);
        mainPage();
    }

    public static void mainPage(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(300,300);
        JPanel panel = new JPanel(new GridLayout(2,2));
        frame.add(panel);

        JButton addbutton = new JButton("Add");
        panel.add(addbutton);
        JButton removebutton = new JButton("Remove");
        panel.add(removebutton);
        JButton modifybutton = new JButton("Modify");
        panel.add(modifybutton);
        JButton Viewbutton = new JButton("View");
        panel.add(Viewbutton);

        addbutton.addActionListener(e -> {
            addPage();
        });

        removebutton.addActionListener(e -> {
            removePage();
        });

        modifybutton.addActionListener(e -> {
            modifyPage();
        });

        Viewbutton.addActionListener(e -> {
            viewPage();
        });
    }

    public static void addPage(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(300,300);
        JPanel panel = new JPanel(new GridLayout(8,2));
        frame.add(panel);

        JLabel inmatriculare = new JLabel("Inmatriculare");
        JLabel marca = new JLabel("Marca");
        JLabel model = new JLabel("Model");
        JLabel revizie = new JLabel("Revizie");
        JLabel alimentare = new JLabel("Alimentare");
        JLabel tip = new JLabel("Tip");
        JLabel Specific = new JLabel("Specific");

        JTextField inmatriculareText = new JTextField();
        JTextField marcaText = new JTextField();
        JTextField modelText = new JTextField();
        JTextField revizieText = new JTextField();
        JTextField alimentareText = new JTextField();
        JTextField SpecificText = new JTextField();

        JComboBox<String> tipBox = new JComboBox<>();
        tipBox.addItem("Masina");
        tipBox.addItem("Motocicleta");
        tipBox.addItem("Tractor");

        JButton addbutton = new JButton("Add");

        panel.add(inmatriculare);
        panel.add(inmatriculareText);
        panel.add(marca);
        panel.add(marcaText);
        panel.add(model);
        panel.add(modelText);
        panel.add(revizie);
        panel.add(revizieText);
        panel.add(alimentare);
        panel.add(alimentareText);
        panel.add(tip);
        panel.add(tipBox);
        panel.add(Specific);
        panel.add(SpecificText);
        panel.add(addbutton);

        addbutton.addActionListener(e -> {
           try{
               String inm = inmatriculareText.getText();
               String mar = marcaText.getText();
               String mod = modelText.getText();
               String rev = revizieText.getText();
               String al = alimentareText.getText();
               String type = tipBox.getSelectedItem().toString();

               int spe = Integer.parseInt(SpecificText.getText());

               if(type.equals("Masina")){
                   Masina masina = new Masina(inm,mar,mod,rev,al,spe);
                   garaj.addVehicul(masina);
                   JOptionPane.showMessageDialog(frame, "Masina adaugata");
               }else if(type.equals("Motocicleta")){
                   Motocicleta motocicleta = new Motocicleta(inm,mar,mod,rev,al,spe);
                   garaj.addVehicul(motocicleta);
                   JOptionPane.showMessageDialog(frame, "Motocicleta adaugata");
               }else if(type.equals("Tractor")){
                   Tractor tractor = new Tractor(inm,mar,mod,rev,al,spe);
                   garaj.addVehicul(tractor);
                   JOptionPane.showMessageDialog(frame, "Tractor adaugat");
               }
           }catch(NumberFormatException nfe){
               JOptionPane.showMessageDialog(null, "Introduceti numere pentru specific,numar pasageri pentru masina, ari pentru tractor, capacitate cilindrica pentru motocicleta");
           }
        });
    }

    public static void viewPage(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(900,300);
        JPanel panel = new JPanel();
        frame.add(panel);

        JTextArea textarea = new JTextArea();
        panel.add(textarea);
        for(Vehicul vehicul : garaj.getVehicule()){
            textarea.append(vehicul.toString()+"\n");
        }
    }

    public static void removePage(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(300,300);

        JPanel panel = new JPanel(new GridLayout(2,2));
        frame.add(panel);
        JLabel inmatriculare = new JLabel("Inmatriculare");
        JTextField inmatriculareText = new JTextField();

        JButton removebutton = new JButton("Remove");

        panel.add(inmatriculare);
        panel.add(inmatriculareText);
        panel.add(removebutton);

        removebutton.addActionListener(e -> {
           String inm = inmatriculareText.getText();
           int ok = 0;
           for(Vehicul vehicul : garaj.getVehicule()){
               if(inm.equals(vehicul.getInmatriculare())){
                   garaj.removeVehicul(vehicul);
                   JOptionPane.showMessageDialog(frame, "Vehicul scos");
                   ok = 1;
               }
           }
           if(ok == 0){
               JOptionPane.showMessageDialog(frame, "Vehiculul nu a fost gasit");
           }

        });
    }

    public static void modifyPage(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(300,300);
        JPanel panel = new JPanel(new GridLayout(3,2));

        frame.add(panel);
        JLabel inmatriculare = new JLabel("Inmatriculare");
        JTextField inmatriculareText = new JTextField();


        JComboBox<String> edit = new JComboBox<>();
        edit.addItem("Revizie");
        edit.addItem("Alimentare");

        JTextField editText = new JTextField();
        JButton editButton = new JButton("Edit");

        panel.add(inmatriculare);
        panel.add(inmatriculareText);
        panel.add(edit);
        panel.add(editText);
        panel.add(editButton);

        editButton.addActionListener(e -> {
            String inm = inmatriculareText.getText();
            String to_edit = edit.getSelectedItem().toString();
            String newDate = editText.getText();
            garaj.modifyVehicul(inm,newDate,to_edit);
            JOptionPane.showMessageDialog(frame, "Vehicul modificat");
        });
    }
}