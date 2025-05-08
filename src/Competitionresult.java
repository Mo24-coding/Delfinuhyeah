import java.util.Date;
public class Competitionresult {
    private String disciplin;
    private double tid;
    private String stævne;
    private int placering;
    private String dato;
    private int medlemsnummer;

    public Competitionresult (String disciplin, double tid, String dato, int medlemsnummer){
        this.disciplin = disciplin;
        this.tid = tid;
        this.dato = dato;
        this.medlemsnummer = medlemsnummer;

    }
    public Competitionresult(String disciplin, double tid, String stævne, int placering, String dato, int medlemsnummer){
        this.disciplin = disciplin;
        this.tid = tid;
        this.stævne = stævne;
        this.placering = placering;
        this.dato = dato;
        this.medlemsnummer = medlemsnummer;
    }

    public double getTid() {return tid;}

    public int getPlacering() {return placering;}

    public int getMedlemsnummer() {return medlemsnummer;}

    public String getDisciplin() {return disciplin;}

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(medlemsnummer);
        stringBuilder.append(";");
        stringBuilder.append(disciplin);
        stringBuilder.append(";");
        stringBuilder.append(tid);
        stringBuilder.append(";");
        stringBuilder.append(dato);
        return stringBuilder.toString();
    }


}
