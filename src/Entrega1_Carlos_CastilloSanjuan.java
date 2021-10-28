import java.util.Locale;
import java.util.Scanner;

public class Entrega1_Carlos_CastilloSanjuan {

    // Variables estaticas

    static float saldo = 0;
    static String transacciones = "\n", datos = "\n", nombre = "", apellidos = "", dni = "", correo = "", nombape = "", diaString = "",
            mesString = "", ncuenta = "";
    static final int FIJOCE = 9010, FIJOCS = 201;
    static int dia = 0, mes = 0, year = 0, d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, d6 = 0, d7 = 0, d8 = 0, d9 = 0, d10 = 0;
    static char letradni = 0;
    static boolean usuario = false, iban = false;


    public static void main(String[] args) {

        /*
            INICIO DEL PROGRAMA

        En esta funcion se encarga de llamar a la funcion menu, para que pueda empezar el programa
         */

        menu();
    }

    public static void menu() {
        Scanner scan = new Scanner(System.in);

        /*
            CREACION Y VALIDACION DEL MENU

        En esta funcion consiste en la distribucion de todas las funciones, es como bien dice el nombre es el
        menu principal, dependiendo del numero que introduzcas.
         */

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
                nuevoCliente();
                break;
            case 2:
                cuentabancaria();
                break;
            case 3:
                bancariaCreada();
                break;
            case 4:
                menuTransacciones();
                break;
            default:
                System.out.print("Caracter invalido");
                menu();
        }
    }


    public static void nuevoCliente() {

        /*
            CREACION DEL NUEVO CLIENTE

        En esta funcion consiste en la creacion del usuario del cliente, en ella contiene toda las peticiones
        necesarias para la creacion del usuario con sus respectivas validaciones. Para poder hacer cualquier otra opcion
        deben pasar por la creacion del usuario.
         */

        // Nombre

        Scanner scan = new Scanner(System.in);
        System.out.print("Introduce el nombre del cliente: ");
        nombre = scan.nextLine();

        // Apellidos

        System.out.print("Introduce los apellidos del cliente: ");
        apellidos = scan.nextLine();

        // Fecha de nacimiento

        System.out.println("Introduce la fecha de nacimiento");
        System.out.print("DIA (DD): ");
        dia = scan.nextInt();
        if (Integer.toString(dia).length() == 1) {          // Comprobacion si solo tiene 1 caracter añadirle 0 delante
            diaString = "0" + dia;
        }
        System.out.print("MES (MM): ");
        mes = scan.nextInt();
        if (Integer.toString(mes).length() == 1) {          // Comprobacion si solo tiene 1 caracter añadirle 0 delante
            mesString = "0" + mes;
        }
        System.out.print("AÑO (AAAA): ");
        year = scan.nextInt();

        // Comprobacion fecha de nacimiento

        if (validacionFecha(dia, mes, year)) {
            System.out.println("La fecha de nacimiento es: " + diaString + "/" + mesString + "/" + year);
        }

        // DNI

        System.out.print("Introduce el DNI del cliente (solo numeros): ");
        dni = scan.next();

        // Comprobacion DNI

        validacionDNI();

        System.out.print("Introduce la letra del DNI: ");
        letradni = scan.next().charAt(0);
        letradni = Character.toUpperCase(letradni);
        System.out.println("Tu DNI es: " + dni + letradni);

        // Correo electronico y su valoracion

        System.out.print("Introduce la direccion de correo UPM del cliente: ");
        correo = scan.next();

        validacionCorreo();

        // Confirmacion de la creacion de la cuenta

        System.out.println("-----------------------------------------------");
        System.out.println("** Enhorabuena!!, ya eres cliente de UPMBank **");
        System.out.println("-----------------------------------------------");

        // Agrega los datos del nuevo cliente a la variable datos

        datos += "Nombre: " + nombre + "\n" + "Apellidos: " + apellidos + "\n" + "Fecha de nacimiento: " + diaString + "/" + mesString + "/" + year + "\n" + "DNI: " + dni
                + letradni + "\n" + "Correo: " + correo + "\n";

        usuario = true;
        menu();
    }

    public static boolean validacionFecha(int dia, int mes, int year) {

        /*
            VALIDACION DE LA FECHA DE NACIMIENTO

        En esta funcion consiste en la comprabacion de la fecha de nacimiento, en el que devolvera un valor booleano.
         */

        Scanner scan = new Scanner(System.in);

        while (dia <= 0 || dia > 31 || mes <= 0 || mes > 12 || year > 2004 || year < 1900 || mes == 2 && dia > 29) {
            System.out.println("** Fecha incorrecta, vuelve a intentarlo **");

            System.out.println("Introduce la fecha de nacimiento");
            System.out.println("DIA (DD): ");
            dia = scan.nextInt();
            if (Integer.toString(dia).length() == 1) {
                diaString = "0" + dia;
            }
            System.out.println("MES (MM): ");
            mes = scan.nextInt();
            if (Integer.toString(mes).length() == 1) {
                mesString = "0" + mes;
            }
            System.out.println("AÑO (AAAA): ");
            year = scan.nextInt();
        }

        return true;
    }

    public static boolean validacionCorreo() {

        /*
            VALIDACION DEL CORREO ELECTRONICO

        En esta funcion consiste en la comprabacion del correo electronico, que devolvera un valor booleano.
         */

        Scanner scan = new Scanner(System.in);

        while (!correo.contains("@alumnos.upm.es") && !correo.contains("@upm.es")) {
            System.out.println("** Su correo no contiene @alumnos.upm.es o @upm.es, ingreselo de nuevo **");
            correo = scan.next();
        }

        return true;
    }

    public static boolean validacionDNI() {

        /*
            VALIDACION DEL DNI

        En esta funcion consiste en la comprabacion del DNI, que devolvera un valor booleano.
         */

        Scanner scan = new Scanner(System.in);

        while (dni.length() != 8) {
            System.out.println("** DNI incorrecto **");
            System.out.print("Introduce el DNI del cliente (solo numeros): ");
            dni = scan.next();
        }

        return true;
    }


    public static void cuentabancaria() {

        /*
            CREACION DE LA CUENTA BANCARIA DEL CLIENTE

        En esta funcion consiste en la creacion de una cuenta bancaria, consiste en un pequeño menu para seleccionar
        el tipo de cuenta y luego la generacion del numero de cuenta, en la que contiene una variable booleana true,
        que sirve para poder realizar alguna de las otras opciones.
         */

        Scanner scan = new Scanner(System.in);

        // Nombre y apellidos del cliente
        if (usuario == false) {
            System.out.println("** Debes dar de alta a un cliente **");
            menu();
        } else {

            System.out.print("Introduce el nombre del cliente: ");
            nombre = scan.nextLine();
            System.out.print("Introduce los apellidos del cliente: ");
            nombre = scan.nextLine();

            // Tipos de cuentas

            System.out.print("Elige el tipo de cuenta: \n\t1) Corriente\n\t2) Ahorro \n\t3) Remunerada\n");
            System.out.print("Introduzca tu opcion: ");

            int selectOption2 = scan.nextInt();

            switch (selectOption2) {
                case 1:
                    System.out.println("Has elegido: Corriente");
                    break;
                case 2:
                    System.out.println("Has elegido: Ahorro");
                    break;
                case 3:
                    System.out.println("Has elegido: Remunerada");
                    break;
            }

            // Numero de cuenta final

            System.out.printf("Tu numero de cuenta bancaria nuevo es: %2d 0%2d %2d %2d\n", FIJOCE, FIJOCS, creacionDC(), creacionNC());

            System.out.println("El saldo actual de la cuenta es: 0€");

            iban = true;
            menu();
        }
    }

    public static int creacionDC() {

        /*
            GENERADOR DEL DIGITO DE CONTROL (DC)

        En esta funcion consiste en la creacion de el digito de control, que se divide en c1, c2, en el que te devuelve
        una variable de tipo int.
         */

        // Generador DC (C1)

        int b4 = 1, b3 = 0, b2 = 2, b1 = 0, a4 = 0, a3 = 1, a2 = 0, a1 = 9, c1 = 0, c2 = 0;

        int r = (6 * b4 + 3 * b3 + 7 * b2 + 9 * b1 + 10 * a4 + 5 * a3 + 8 * a2 + 4 * a1) % 11;
        int resultado = 11 - r;

        switch (resultado) {
            case 11:
                resultado = 0;
                break;
            case 10:
                resultado = 1;
                break;
            default:
                c1 = resultado;
        }

        String numc1 = String.valueOf(c1);

        // Generador DC (C2)

        int r2 = (d1 + 2 * d2 + 4 * d3 + 8 * d4 + 5 * d5 + 10 * d6 + 9 * d7 + 7 * d8 + 3 * d9 + 6 * d10) % 11;
        int resultado2 = 11 - r2;

        switch (resultado2) {
            case 11:
                resultado2 = 0;
                break;
            case 10:
                resultado2 = 1;
                break;
            default:
                c2 = resultado2;
        }

        String numc2 = String.valueOf(c2);

        String numDCString = (numc1 + numc2);

        int numDC = Integer.parseInt(numDCString);

        return numDC;
    }

    public static long creacionNC() {  // DUDAS Y MAS DUDAS

        /*
            CREACION DEL NUMERO DE CUENTA

        En esta funcion consiste en la creacion del digito del numero de cuenta, en el que la funcion te devuelve
        una variable de tipo long.
         */

        // Generacion de 10 numeros aleatorios

        d1 = (int) Math.floor(Math.random() * (10));
        d2 = (int) Math.floor(Math.random() * (10));
        d3 = (int) Math.floor(Math.random() * (10));
        d4 = (int) Math.floor(Math.random() * (10));
        d5 = (int) Math.floor(Math.random() * (10));
        d6 = (int) Math.floor(Math.random() * (10));
        d7 = (int) Math.floor(Math.random() * (10));
        d8 = (int) Math.floor(Math.random() * (10));
        d9 = (int) Math.floor(Math.random() * (10));
        d10 = (int) Math.floor(Math.random() * (10));

        // Pasar las variables int a long

        long numd1 = Long.valueOf(d1);
        long numd2 = Long.valueOf(d2);
        long numd3 = Long.valueOf(d3);
        long numd4 = Long.valueOf(d4);
        long numd5 = Long.valueOf(d5);
        long numd6 = Long.valueOf(d6);
        long numd7 = Long.valueOf(d7);
        long numd8 = Long.valueOf(d8);
        long numd9 = Long.valueOf(d9);
        long numd10 = Long.valueOf(d10);


        String ncString = String.valueOf(numd1) + numd2 + numd3 + numd4 + numd5 + numd6 + numd7 + numd8 + numd9 + numd10;

        Long ncf = Long.parseLong(ncString);

        return ncf;
    }


    public static void bancariaCreada() {

        /*
            MENU OPCIONES CON UNA CUENTA BANCARIA

        En esta funcion consiste en el menu que se podra acceder una vez creada la cuenta bancaria y dado de alta el
        usuario, en esta funcion se encuentra los apartados de ingresar, extraer, transferencias y prestamos
        hipotecarios.
         */

        Scanner scan = new Scanner(System.in);

        if (usuario == false || iban == false) {
            System.out.println("** Debes dar de alta a un cliente y tener una cuenta bancaria creada **");
            menu();
        } else {

            System.out.println("¿Que es lo que quieres hacer?\n\t1) Ingresar\n\t2) Extraccion \n\t3) Transferencia\n\t4) Prestamo hipotecarios\n\t0) Salir");
            System.out.print("Introduzca tu opcion: ");

            int selectOption3 = scan.nextInt();

            switch (selectOption3) {
                case 0:
                    menu();
                    break;
                case 1:
                    menuIngreso();
                    break;
                case 2:
                    menuExtracion();
                    break;
                case 3:
                    menuTranferencia();
                    break;
                case 4:
                    menuPrestamoHipotecario();
                    break;
                default:
                    System.out.println("El numero no corresponde con ninguna opcion");
                    bancariaCreada();
                    break;
            }
        }
    }

    public static void menuIngreso() {

        /*
            MENU INGRESO

        En esta funcion consiste en menu, que esta dentro de la funcion bancaria creada, en la que el usuario podra
        realizar ingresos a la cuenta.
         */

        Scanner scan = new Scanner(System.in);

        float depositar;

        System.out.println("Bienvenido a el apartado de DEPOSITOS");
        System.out.print("¿Cual es la cantidad que quieres ingresar? ");
        depositar = scan.nextFloat();
        saldo += depositar;
        System.out.println("El saldo de su cuenta es de: " + saldo + "€");

        // Se añadira a la variable transacciones el ingreso realizado

        transacciones += "+ [" + depositar + "€]" + "\tTu saldo restante es " + "[" + saldo + "]\n";

        bancariaCreada();
    }

    public static void menuExtracion() {

        /*
            MENU EXTRACCION

        En esta funcion consiste en menu, que esta dentro de la funcion bancaria creada, en la que el usuario podra
        realizar extraciones de la cuenta, pero tiene una condicion debe tener dinero suficiente en el saldo para poder
        realizar cualquier extraccion.
         */

        Scanner scan = new Scanner(System.in);

        float extraer;

        System.out.println("Bienvenido a el apartado de EXTRACCIONES");
        System.out.print("¿Cual es la cantidad que quieres extraer? ");
        extraer = scan.nextFloat();
        if (extraer > saldo) {
            System.out.println("No tienes dinero suficiente en tu saldo");
        } else {
            saldo -= extraer;
            System.out.println("El saldo de su cuenta es de: " + saldo + "€");
        }

        // Se añadira a la variable transacciones la extraccion realizada

        transacciones += "- [" + extraer + "€]" + "\tTu saldo restante es " + "[" + saldo + "]\n";

        bancariaCreada();
    }

    public static void menuTranferencia() {

        /*
            MENU TRANFERENCIA

        En esta funcion consiste en menu, que esta dentro de la funcion bancaria creada, en la que el usuario podra
        realizar transferencias de la cuenta, pero tiene una condicion debe tener dinero suficiente en el saldo para poder
        realizar cualquier transferencia.
         */

        Scanner scan = new Scanner(System.in);

        float tranferir;
        int cetranferencia = 0, cstranferencia = 0, dctranferencia = 0;
        long nctranferencia = 0;

        System.out.println("Bienvenido a el apartado de TRANFERENCIAS");
        System.out.print("¿Cual es la cantidad que quieres tranferir? ");
        tranferir = scan.nextFloat();
        if (tranferir > saldo) {
            System.out.println("No tienes dinero suficiente en tu saldo");
        } else {
            System.out.println("Introduzca el numero de cuenta al que quieres hacer una tranferencia: ");
            do {
                System.out.print("Introduzca el CE(4 digitos) de tu universidad: \n");
                cetranferencia = scan.nextInt();
            } while (cetranferencia < 0 || cetranferencia > 9999);
            do {
                System.out.print("Introduzca el CS(4 digitos) de tu campus: \n");
                cstranferencia = scan.nextInt();
            } while (cstranferencia < 0 || cstranferencia > 9999);
            do {
                System.out.print("Introduzca el DC(2 digitos): \n");
                dctranferencia = scan.nextInt();
            } while (dctranferencia < 0 || dctranferencia > 99);
            do {
                System.out.print("Introduzca el NC(10 digitos): \n");
                nctranferencia = scan.nextLong();
            } while (nctranferencia < 0 || nctranferencia > 9999999999L);


            System.out.printf("Haciendo tranferencia a la cuenta: %2d %2d %2d %2d", cetranferencia, cstranferencia, dctranferencia, nctranferencia);

            System.out.println("\nSe ha realizado correctamente la tranferencia");
            saldo -= tranferir;
            System.out.println("El saldo de su cuenta es de: " + saldo + "€");

            // Se añadira a la variable transacciones la transferencia realizada

            transacciones += "- [" + tranferir + "€]" + ", a [" + cetranferencia + cstranferencia + dctranferencia + nctranferencia + "]" + "Tu saldo restante es [" + saldo + "]\n";
        }

        bancariaCreada();
    }

    public static void menuPrestamoHipotecario() {

        /*
            MENU PRESTAMOS HIPOTECARIOS

        En esta funcion consiste en menu, que esta dentro de la funcion bancaria creada, en la que el usuario podra
        realizar prestamos hipotecarios a la cuenta.
         */

        Scanner scan = new Scanner(System.in);

        System.out.println("Bienvenido a el apartado de PRESTAMOS HIPOTECARIO");
        System.out.print("Introduzca el capital solicitado: ");
        float capital = scan.nextInt();
        float interes;
        // Interes anual

        do{
            System.out.print("Introduzca el interes anual (en tanto por ciento): ");
            interes = scan.nextInt();
        }while(interes < 0 || interes > 100);

        interes = interes / 100f;
        double imes = interes / 12;

        // Years

        System.out.print("Introduzca el numero de años: ");
        int yearsh = scan.nextInt();
        int meses = yearsh * 12;

        // Calculo cuota

        double cuota = capital * imes * (Math.pow((1 + imes), meses) / (Math.pow((1 + imes), meses) - 1));
        System.out.println("Mensualidad\t\tCuota\t\tInteres\t\tAbonado\t\tRestante");
        System.out.printf(Locale.US, "\t%d\t\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n", 0, 0f, 0f, 0f, capital);
        int i = 1;
        double cv = capital;
        do {
            double ia = cv * imes;
            double ca = cuota - ia;
            cv -= ca;
            System.out.printf(Locale.US, "\t%d\t\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n", i, cuota, ia, ca, cv);
            i++;
        } while (i != meses + 1);

        saldo += capital;

        // Se añadira a la variable transacciones la transferencia realizada

        transacciones += "Capital + [" + capital + "€]" + ", con interes de [" + interes + "] en [" + yearsh + "]\tTu saldo restante es " + "[" + saldo + "]\n";

        bancariaCreada();

    }


    public static void menuTransacciones() {

        /*
            MENU DE DATOS Y TRANSACCIONES

        En esta funcion consiste en el menu que se podra acceder una vez creada la cuenta bancaria y dado de alta el
        usuario, en esta funcion se encuentra los datos del cliente dado de alto y las transacciones del cliente, en
        el switch imprime las variables estaticas que han sido modificadas añadiendo los datos y transacciones.
         */

        Scanner scan = new Scanner(System.in);

        if (usuario == false || iban == false) {
            System.out.println("** Debes dar de alta a un cliente y tener una cuenta bancaria creada **");
            menu();
        } else {

            System.out.println("¿Que es lo que quieres ver?\n\t1) Datos\n\t2) Transacciones \n\t0) Salir");
            System.out.print("Introduzca tu opcion: ");

            int selectOption4 = scan.nextInt();

            switch (selectOption4) {
                case 0:
                    menu();
                    break;
                case 1:
                    System.out.println(datos);
                    menuTransacciones();
                    break;
                case 2:
                    System.out.println(transacciones);
                    menuTransacciones();
                    break;
                default:
                    System.out.print("El numero no corresponde con ninguna opcion");
                    menuTransacciones();
                    break;
            }
        }
    }
}