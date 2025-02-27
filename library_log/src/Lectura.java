public abstract class Lectura {
    private String titlu;
    private String autor;
    private int an;
    private int categoria;
    private int nr_pag;
    private String date;
    public abstract String getCategoria();


    public Lectura(String titlu, String autor, int an, int categoria, int nr_pag, String date) {
        this.titlu = titlu;
        this.autor = autor;
        this.an = an;
        this.categoria = categoria;
        this.nr_pag = nr_pag;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getNr_pag() {
        return nr_pag;
    }

    public void setNr_pag(int nr_pag) {
        this.nr_pag = nr_pag;
    }

    @Override
    public String toString() {
        return "Lectura{" +
                "titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", an=" + an +
                ", categoria=" + categoria +
                ", nr_pag=" + nr_pag +
                ", date='" + date + '\'' +
                '}';
    }
}
