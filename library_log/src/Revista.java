public class Revista extends Lectura{

    public Revista(String titlu, String autor, int an, int nr_pag,String date) {
        super(titlu, autor, an, 2, nr_pag,date);
    }

    @Override
    public String getCategoria() {
        return "Revista";
    }
}
