import java.util.Scanner;

public class Banco {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n" +
                "██╗░░░██╗██████╗░███╗░░░███╗╚═════╝░██████╗░░█████╗░███╗░░██╗██╗░░██╗\n" +
                "██║░░░██║██╔══██╗████╗░████║╚═════╝░██╔══██╗██╔══██╗████╗░██║██║░██╔╝\n" +
                "██║░░░██║██████╔╝██╔████╔██║╚═════╝░██████╦╝███████║██╔██╗██║█████═╝░\n" +
                "██║░░░██║██╔═══╝░██║╚██╔╝██║╚═════╝░██╔══██╗██╔══██║██║╚████║██╔═██╗░\n" +
                "╚██████╔╝██║░░░░░██║░╚═╝░██║╚═════╝░██████╦╝██║░░██║██║░╚███║██║░╚██╗\n" +
                "░╚═════╝░╚═╝░░░░░╚═╝░░░░░╚═╝╚═════╝░╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝░░╚══╝╚░");     // Logo

        System.out.println("*** Bienvenido a UPMBank ***");
        System.out.println("Elige una opcion");
        System.out.println("    1) Dar de alto nuevo cliente");
        System.out.println("    2) Crear una nueva cuenta bancaria");
        System.out.println("    3) Hacer operaciones bancarias");
        System.out.println("    4) Ver datos o transacciones");
        System.out.println("    0) Cerrar sesion");

        System.out.print("Introduzca tu opcion: ");

        int selectOption = scan.nextInt();

        switch (selectOption) {
            case 0:
                System.out.println("--------------------------------");
                System.out.println("*** Has finalizado la sesion ***");
                System.out.println("--------------------------------");
                break;
            case 1:
                // nuevoCliente();
                break;
            case 2:
                // cuentabancaria();
                break;
            case 3:
                //bancariaCreada();
                break;
            case 4:
                //menuTransacciones();
                break;
            default:
                System.out.print("Caracter invalido");
                menu();
        }
    }

}
