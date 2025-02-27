
public class Carte extends Lectura{

    public Carte(String titlu, String autor, int an, int nr_pag,String date) {
        super(titlu, autor, an, 1, nr_pag,date);
    }

    @Override
    public String getCategoria() {
        return "Carte";
    }
}
