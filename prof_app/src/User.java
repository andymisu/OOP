public class User {
    private int type;// 1 prof, 2 student
    private String password;

    public User(int type, String password) {
        this.type = type;
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean verifyPassword(String password) {
        if(type == 1){
            if(password.equals(this.password)){
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }
    }
}
