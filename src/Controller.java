import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    // ha' adgang til nødvendige datastrukturer
    List<Buket> buketter;
    List<Ordre> bestillinger;
    Scanner sc = new Scanner(System.in);

    public void runProgram() {
        int choice = 0;
        // kører et loop med main actions
        // vis main action - prompt for action
        printMainAction();
       choice = sc.nextInt();

        // vis buketter
        // opret ordre
        // vis alle ordrer
        // godkend ordre
        // arkiver ordre
        // hent dagens ordrer

    }

    public void printMainAction() {
        System.out.println("1) vis buketter");
        System.out.println("2) opret ordre ");
        System.out.println("3) godkend ordre");
        System.out.println("4) ret ordre");
        System.out.println("5) arkiver ordre");
        System.out.println("6) vis alle bestillinger");
        System.out.println("7) vis statistik");
        System.out.println("9) afslut programmet");
    }
}
