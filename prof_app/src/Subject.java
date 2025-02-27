import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String subject;
    private List<Grading> grades;
    private List<String> notes;

    public Subject(String subject) {
        this.subject = subject;
        this.grades = new ArrayList<Grading>();
        this.notes = new ArrayList<String>();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Grading> getGrades() {
        return grades;
    }

    public void setGrades(List<Grading> grades) {
        this.grades = grades;
    }

    public List<String> getnotes() {
        return notes;
    }

    public void setnotes(List<String> notes) {
        this.notes = notes;
    }

    public void addGrade(Grading grade) {
        this.grades.add(grade);
    }

    public void addNotation(String notation) {
        this.notes.add(notation);
    }


    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

}
