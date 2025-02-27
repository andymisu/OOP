
public class Students {
    private int id;
    private String name;
    private int age;
    private Subject[] subjects;

    public Students(int id, String name, int age, Subject[] subjects) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }

    public void add_grade(String subject, Grading grade){
        int size = subjects.length;
        for(int i = 0; i < size; i++){
            if(subjects[i].getSubject().equalsIgnoreCase(subject)){
                subjects[i].addGrade(grade);
                break;
            }
        }
    }


}
