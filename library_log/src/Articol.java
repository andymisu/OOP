public class Articol extends Lectura{

    public Articol(String titlu, String autor, int an, int nr_pag,String date) {
        super(titlu, autor, an, 3, nr_pag,date);
    }

    @Override
    public String getCategoria() {
        return "Articol";
    }
}
