import java.util.ArrayList;
import java.util.List;

public class Garaj {
    private List<Vehicul> vehicule;

    public Garaj() {
        this.vehicule = new ArrayList<Vehicul>();
    }

    public List<Vehicul> getVehicule() {
        return vehicule;
    }

    public void setVehicule(List<Vehicul> vehicule) {
        this.vehicule = vehicule;
    }

    public void addVehicul(Vehicul vehicul) {
        this.vehicule.add(vehicul);
    }

    public void removeVehicul(Vehicul vehicul) {
        this.vehicule.remove(vehicul);
    }

    public void modifyVehicul(String inmatriculare,String newDate,String edit){
       for(Vehicul vehicul : vehicule){
           if(inmatriculare.equals(vehicul.getInmatriculare())){
               if(edit.equalsIgnoreCase("Revizie")){
                   vehicul.setRevizie(newDate);
               }else if(edit.equalsIgnoreCase("Alimentare")){
                   vehicul.setAlimentare(newDate);
               }
               break;
           }
       }
    }
}
