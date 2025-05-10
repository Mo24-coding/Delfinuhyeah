public class Competitionresult {
    private String disciplin;
    private double tid;
    private String stævne;
    private int placering;
    private String dato;
    private int memberId;

    public Competitionresult (String disciplin, double tid, String dato, int memberId){
        this.disciplin = disciplin;
        this.tid = tid;
        this.dato = dato;
        this.memberId = memberId;

    }
    public Competitionresult(String disciplin, double tid, String stævne, int placering, String dato, int memberId){
        this.disciplin = disciplin;
        this.tid = tid;
        this.stævne = stævne;
        this.placering = placering;
        this.dato = dato;
        this.memberId = memberId;
    }

    public double getTid() {return tid;}

    public int getPlacering() {return placering;}

    public int getMemberId() {return memberId;}

    public String getDisciplin() {return disciplin;}

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(memberId);
        stringBuilder.append(";");
        stringBuilder.append(disciplin);
        stringBuilder.append(";");
        stringBuilder.append(tid);
        stringBuilder.append(";");
        stringBuilder.append(dato);
        return stringBuilder.toString();
    }


}
