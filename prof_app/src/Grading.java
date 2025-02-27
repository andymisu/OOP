public class Grading {
    protected int grade;
    protected int type; // 1 - test, 2-essay, 3 project;
    protected String note;

    public Grading(int grade, int type, String note) {
        this.grade = grade;
        this.type = type;
        this.note = note;
    }

    public int getGrade() {
        return grade;
    }

    public int getType() {
        return type;
    }

    public String getNote() {
        return note;
    }
}
