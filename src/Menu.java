import java.util.Scanner;
public class Menu {
    private Scanner scanner;

    public Menu(){
        Scanner scanner = new Scanner(System.in);
    }
    public void visMenu(){
        int valg = -1;

        while(valg != 0){
            System.out.println("===== HOVEDMENU =====");
            System.out.println("1. Registrer medlem");
            System.out.println("2. Vis alle medlemmer");
            System.out.println("3. Beregn kontingent");
            System.out.println("4. Vis træningsresultater");
            System.out.println("5. Vis top 5 svømmere");
            System.out.println("0. Afslut");
            System.out.print("Indtast valg: ");

            valg = scanner.nextInt();
            scanner.nextLine();
            switch (valg) {
                case 1:
                    registrerMedlem();
                    break;
                case 2:
                    visMedlemmer();
                    break;
                case 3:
                    beregnKontingent();
                    break;
                case 4:
                    visTraeningsresultater();
                    break;
                case 5:
                    visTop5();
                    break;
                case 0:
                    System.out.println("Program afsluttes.");
                    break;
                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }
    private void registrerMedlem(){

    }
    private void visMedlemmer(){

    }
    private void beregnKontingent(){

    }
    private void visTraeningsresultater(){

    }
    private void visTop5(){

    }
}
