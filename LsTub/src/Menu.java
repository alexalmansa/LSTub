import java.util.Scanner;

/**
 * Classe que s'encarrega de gestionar el menu printar-lo i llegir i comprovar la opcio que entra l'usuari
 *
 * @author Alex Almansa, Marc Llort
 *//*
 @param opcio l'opcio que escull l'usuari al menú

 */
public class Menu {

    //Variables

    private int opcio = -1;


    //Getters i Setters

    public int getOpcio() {
        return opcio;
    }


    //Funcions

    /**
     * S'encarrega de printar el menu
     *
     * @author Alex Almansa, Marc Llort
     */
    public void printMenu() {

        System.out.println("\n1. Cerca de Resultats");
        System.out.println("2. Desar Preferits");
        System.out.println("3. Millors Videos");
        System.out.println("4. Estadístiques");
        System.out.println("5. Llistes de Reproducció");
        System.out.println("6. El Mosaic");
        System.out.println("7. Sortir\n");
        System.out.println("Sel·lecciona una opcio:");
    }

    /**
     * Llegeix la cerca i la prepara per introduir-la a la url
     *
     * @return Retorna el string de la cerca que ha realitzat el usuari substituint espais per %20
     * @author Alex Almansa, Marc Llort
     */

    public String llegeixCerca() {

        System.out.println("Introdueix cerca");
        Scanner sc1 = new Scanner(System.in);
        String cerca = sc1.nextLine();

        String cercaT = cerca.replace(" ", "%20");
        return cercaT;
    }

    /**
     * Comprova que la opció que entra l'usuari és correcta, si no ho és la torna a demanar
     *
     * @author Alex Almansa, Marc Llort
     */
    public void comprovaOpcio() {

        Scanner sc1 = new Scanner(System.in);                    //inicialitzem scanner i printem menu
        boolean opcioIncorrecta = true;
        Menu menu = new Menu();

        while (opcioIncorrecta) {                                //controlo que en cas de 6 acabi el programa, de 1 a 5 agafi les opcions, i altres numeros siguin error

            if (sc1.hasNextInt()) {

                opcio = sc1.nextInt();

                if (0 >= opcio || opcio > 7) {

                    System.out.println("Error! La opció ha de ser un nombre entre 1 i 7!\n");
                    menu.printMenu();
                } else {

                    opcioIncorrecta = false;
                }
            } else {

                sc1.next();
                System.out.println("Error! La opció ha de ser un nombre!\n");
                menu.printMenu();

            }
        }
    }


}
