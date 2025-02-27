public class Vehicul {
    private String inmatriculare;
    private String marca;
    private String model;
    private String revizie;
    private String alimentare;
    private int tip;
    private int specific;



    public Vehicul(String inmatriculare, String marca, String model, String revizie, String alimentare, int tip, int specific) {
        this.inmatriculare = inmatriculare;
        this.marca = marca;
        this.model = model;
        this.revizie = revizie;
        this.alimentare = alimentare;
        this.tip = tip;
        this.specific = specific;
    }

    public String getInmatriculare() {
        return inmatriculare;
    }

    public void setInmatriculare(String inmatriculare) {
        this.inmatriculare = inmatriculare;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRevizie() {
        return revizie;
    }

    public void setRevizie(String revizie) {
        this.revizie = revizie;
    }

    public String getAlimentare() {
        return alimentare;
    }

    public void setAlimentare(String alimentare) {
        this.alimentare = alimentare;
    }

    public String getTip() {
        if(tip == 1){
            return "Masina";
        }else if(tip == 2){
            return "Motocicleta";
        }else{
            return "Tractor";
        }
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public String getSpecific() {
        if(tip == 1){
            return "Pasageri " + specific;
        }else if(tip == 2){
            return "Capacitate Cilindrica " + specific;
        }else{
            return "Ari " + specific;
        }
    }

    public void setSpecific(int specific) {
        this.specific = specific;
    }

    @Override
    public String toString() {
        return "Vehicul{" +
                "inmatriculare='" + inmatriculare + '\'' +
                ", marca='" + marca + '\'' +
                ", model='" + model + '\'' +
                ", revizie='" + revizie + '\'' +
                ", alimentare='" + alimentare + '\'' +
                ", tip=" + getTip() +
                ", " + getSpecific() +
                '}';
    }
}
