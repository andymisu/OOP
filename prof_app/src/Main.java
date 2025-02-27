import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Main {
    static List<Students> students = new ArrayList<>();
    static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        // Inițializare date de test
        Subject math1 = new Subject("Math");
        Subject literature1 = new Subject("Literature");
        Subject physics1 = new Subject("Fizica");

        Subject math2 = new Subject("Math");
        Subject literature2 = new Subject("Literature");

        Students student1 = new Students(1, "Alice", 16, new Subject[]{math1, literature1,physics1});
        Students student2 = new Students(2, "Bob", 17, new Subject[]{math2, literature2});

        students.add(student1);
        students.add(student2);

        users.add(new Prof_User("profpass"));
        users.add(new Stud_User());

        // Lansăm aplicația
        SwingUtilities.invokeLater(Main::showLoginScreen);
    }

    public static void showLoginScreen() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel userTypeLabel = new JLabel("Tip utilizator:");
        JComboBox<String> userTypeCombo = new JComboBox<>(new String[]{"Profesor", "Elev"});

        JLabel passwordLabel = new JLabel("Parola:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String userType = (String) userTypeCombo.getSelectedItem();
            String password = new String(passwordField.getPassword());

            if (authenticate(userType, password)) {
                frame.dispose();
                if (userType.equals("Profesor")) {
                    showProfessorPanel();
                } else {
                    showStudentPanel();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Autentificare eșuată!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(userTypeLabel);
        panel.add(userTypeCombo);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static boolean authenticate(String userType, String password) {
        for (User user : users) {
            if ((userType.equals("Profesor") && user instanceof Prof_User && user.verifyPassword(password)) ||
                    (userType.equals("Elev") && user instanceof Stud_User)) {
                return true;
            }
        }
        return false;
    }

    private static void showProfessorPanel() {
        JFrame frame = new JFrame("Panel Profesor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton addGradeButton = new JButton("Adaugă notă");
        addGradeButton.addActionListener(e -> addGradeUI());

        JButton addAbsenceButton = new JButton("Adaugă absență");
        addAbsenceButton.addActionListener(e -> addAbsenceUI());

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            frame.dispose();
            showLoginScreen();
        });

        panel.add(addGradeButton);
        panel.add(addAbsenceButton);
        panel.add(logoutButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void showStudentPanel() {
        JFrame frame = new JFrame("Panel Elev");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new BorderLayout());

        JTextArea studentInfo = new JTextArea();
        studentInfo.setEditable(false);

        for (Students student : students) {
            studentInfo.append("ID: " + student.getId() + "\nNume: " + student.getName() + "\n");
            for (Subject subject : student.getSubjects()) {
                studentInfo.append("  Materia: " + subject.getSubject() + "\n");
                studentInfo.append("    Note:\n");
                for (Grading grade : subject.getGrades()) {
                    studentInfo.append("      Nota: " + grade.getGrade() + " (" + grade.getNote() + ")\n");
                }
                studentInfo.append("    Absențe:\n");
                for (String note : subject.getNotes()) {
                    studentInfo.append("      " + note + "\n");  // Afișează fiecare absență
                }
            }
            studentInfo.append("\n");
        }

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            frame.dispose();
            showLoginScreen();
        });

        panel.add(new JScrollPane(studentInfo), BorderLayout.CENTER);
        panel.add(logoutButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }


    private static void addGradeUI() {
        JFrame frame = new JFrame("Adaugă notă");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        // Declarăm subjectCombo global, în afacerea panourilor
        JComboBox<String> subjectCombo = new JComboBox<>();

        // Panou pentru selectarea elevului
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));

        JLabel idLabel = new JLabel("ID Elev:");
        JTextField idField = new JTextField(10);  // Aici adăugăm o lățime implicită pentru câmpul ID

        JButton loadStudentButton = new JButton("Încarcă Elev");
        loadStudentButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            Students student = findStudentById(id);
            if (student != null) {
                // Actualizăm comboBox-ul cu materiile elevului
                subjectCombo.removeAllItems();
                for (Subject subject : student.getSubjects()) {
                    subjectCombo.addItem(subject.getSubject());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Elevul nu a fost găsit!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });

        studentPanel.add(idLabel);
        studentPanel.add(idField);
        studentPanel.add(loadStudentButton);

        // Panou pentru selectarea materiei și adăugarea notei
        JPanel gradePanel = new JPanel(new GridLayout(5, 2));

        JLabel subjectLabel = new JLabel("Materie:");
        gradePanel.add(subjectLabel);
        gradePanel.add(subjectCombo);  // Aici adăugăm combobox-ul pentru materii

        JLabel gradeLabel = new JLabel("Nota:");
        JTextField gradeField = new JTextField();

        JLabel typeLabel = new JLabel("Tip Notă:");
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Test", "Eseu", "Proiect"});

        JButton addButton = new JButton("Adaugă Nota");
        addButton.addActionListener(e -> {
            try {
                String subjectName = (String) subjectCombo.getSelectedItem();
                if (subjectName == null) {
                    JOptionPane.showMessageDialog(frame, "Te rugăm să selectezi o materie!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int grade = Integer.parseInt(gradeField.getText());
                String type = (String) typeCombo.getSelectedItem();

                // Creăm nota în funcție de tipul ales
                Grading grading;
                switch (type) {
                    case "Test":
                        grading = new Test(grade, "Test");
                        break;
                    case "Eseu":
                        grading = new Essay(grade, "Eseu");
                        break;
                    case "Proiect":
                        grading = new Project(grade, "Proiect");
                        break;
                    default:
                        throw new IllegalArgumentException("Tip necunoscut");
                }

                // Găsim materia selectată și adăugăm nota
                Students student = findStudentById(Integer.parseInt(idField.getText()));
                if (student != null) {
                    Subject subject = findSubjectByName(student, subjectName);
                    if (subject != null) {
                        subject.addGrade(grading);  // Adaugă nota la materia selectată
                        JOptionPane.showMessageDialog(frame, "Nota a fost adăugată cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Materia nu a fost găsită!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Date invalide! Te rugăm să introduci valori corecte.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });

        gradePanel.add(gradeLabel);
        gradePanel.add(gradeField);
        gradePanel.add(typeLabel);
        gradePanel.add(typeCombo);
        gradePanel.add(new JLabel());  // Loc gol pentru a umple grila
        gradePanel.add(addButton);

        // Aranjăm panourile într-un panel general
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(studentPanel);
        mainPanel.add(gradePanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }



    private static void addAbsenceUI() {
        JFrame frame = new JFrame("Adaugă absență");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        // Declarăm subjectCombo global, în afacerea panourilor
        JComboBox<String> subjectCombo = new JComboBox<>();

        // Panou pentru selectarea elevului
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));

        JLabel idLabel = new JLabel("ID Elev:");
        JTextField idField = new JTextField(10);  // Aici adăugăm o lățime implicită pentru câmpul ID

        JButton loadStudentButton = new JButton("Încarcă Elev");
        loadStudentButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            Students student = findStudentById(id);
            if (student != null) {
                // Actualizăm comboBox-ul cu materiile elevului
                subjectCombo.removeAllItems();
                for (Subject subject : student.getSubjects()) {
                    subjectCombo.addItem(subject.getSubject());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Elevul nu a fost găsit!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });

        studentPanel.add(idLabel);
        studentPanel.add(idField);
        studentPanel.add(loadStudentButton);

        // Panou pentru selectarea materiei și adăugarea absenței
        JPanel absencePanel = new JPanel(new GridLayout(4, 2));

        JLabel subjectLabel = new JLabel("Materie:");
        absencePanel.add(subjectLabel);
        absencePanel.add(subjectCombo);  // Aici adăugăm combobox-ul pentru materii

        JLabel dateLabel = new JLabel("Data (dd/MM/yyyy):");
        JTextField dateField = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        JButton addButton = new JButton("Adaugă Absență");
        addButton.addActionListener(e -> {
            try {
                String subjectName = (String) subjectCombo.getSelectedItem();
                if (subjectName == null) {
                    JOptionPane.showMessageDialog(frame, "Te rugăm să selectezi o materie!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String date = dateField.getText();
                if (date.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Te rugăm să introduci o dată validă!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Găsim elevul și materia, apoi adăugăm absența
                Students student = findStudentById(Integer.parseInt(idField.getText()));
                if (student != null) {
                    Subject subject = findSubjectByName(student, subjectName);
                    if (subject != null) {
                        subject.addNotation("Absent - " + date);  // Adăugăm absența la materia selectată
                        JOptionPane.showMessageDialog(frame, "Absența a fost adăugată cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Materia nu a fost găsită!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Date invalide! Te rugăm să introduci valori corecte.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });

        absencePanel.add(dateLabel);
        absencePanel.add(dateField);
        absencePanel.add(new JLabel());  // Loc gol pentru a umple grila
        absencePanel.add(addButton);

        // Aranjăm panourile într-un panel general
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(studentPanel);
        mainPanel.add(absencePanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static Students findStudentById(int id) {
        for (Students student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private static Subject findSubjectByName(Students student, String name) {
        for (Subject subject : student.getSubjects()) {
            if (subject.getSubject().equalsIgnoreCase(name)) {
                return subject;
            }
        }
        return null;
    }
}
