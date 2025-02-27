import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Lectura> lecturi;

    public Biblioteca() {
        this.lecturi = new ArrayList<Lectura>();
    }

    public void add_lecture(Lectura lectura) {
        this.lecturi.add(lectura);
    }

    public void delete_lecture(Lectura lectura) {
        this.lecturi.remove(lectura);
    }

    public void modify_date(Lectura lectura, String new_date) {
        lectura.setDate(new_date);
    }

    public void modify_nrOfPages(Lectura lectura, int new_nrOfPages) {
        lectura.setNr_pag(new_nrOfPages);
    }

    public void modify_year(Lectura lectura, int new_year) {
        lectura.setAn(new_year);
    }

    public boolean deleteByTitle(String title) {
        for (Lectura lectura : this.lecturi) {
            if(title.equalsIgnoreCase(lectura.getTitlu())){
                this.lecturi.remove(lectura);
                return true;
            }
        }
        return false;
    }

    public List<Lectura> getLecturi() {
        return lecturi;
    }

    public boolean modify(String titlu, int caz, String edit){
        for(Lectura lectura : this.lecturi){
            if(titlu.equalsIgnoreCase(lectura.getTitlu())){
                if(caz == 1){
                    int an = Integer.parseInt(edit);
                    lectura.setAn(an);
                    return true;
                }else if(caz == 2){
                    int categorie = 0;
                    if(edit.equalsIgnoreCase("carte")){
                        categorie = 1;
                    }else if(edit.equalsIgnoreCase("revista")){
                        categorie = 2;
                    }else if(edit.equalsIgnoreCase("articol")){
                        categorie = 3;
                    }
                    if(categorie == 0){
                        return false;
                    }
                    lectura.setCategoria(categorie);
                    return true;
                }else if(caz == 3){
                    lectura.setDate(edit);
                    return true;
                }
            }
        }
        return false;
    }
}
