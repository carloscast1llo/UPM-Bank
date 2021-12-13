import java.util.Scanner;

public class Banco {

    private static Fecha fecha = null;
    private static Cuenta cuenta = null;
    private static Cliente cliente = null;
    private static ListaClientes listaClientes = null;
    private static Cliente clienteCuenta = null;
    private static ListaClientes listClientes = new ListaClientes(20);

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n" +
                "██╗░░░██╗██████╗░███╗░░░███╗╚═════╝░██████╗░░█████╗░███╗░░██╗██╗░░██╗\n" +
                "██║░░░██║██╔══██╗████╗░████║╚═════╝░██╔══██╗██╔══██╗████╗░██║██║░██╔╝\n" +
                "██║░░░██║██████╔╝██╔████╔██║╚═════╝░██████╦╝███████║██╔██╗██║█████═╝░\n" +
                "██║░░░██║██╔═══╝░██║╚██╔╝██║╚═════╝░██╔══██╗██╔══██║██║╚████║██╔═██╗░\n" +
                "╚██████╔╝██║░░░░░██║░╚═╝░██║╚═════╝░██████╦╝██║░░██║██║░╚███║██║░╚██╗\n" +
                "░╚═════╝░╚═╝░░░░░╚═╝░░░░░╚═╝╚═════╝░╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝░░╚══╝╚░");

        System.out.println("*** Bienvenido a UPMBank ***");
        System.out.println("Elige una opcion");
        System.out.println("\t1) Dar de alta nuevo cliente");
        System.out.println("\t2) Crear una nueva cuenta bancaria");
        System.out.println("\t3) Hacer operaciones bancarias");
        System.out.println("\t4) Mostrar operaciones realizadas de cuentas");
        System.out.println("\t5) Mostrar datos de clientes");
        System.out.println("\t0) Cerrar sesion");

        System.out.print("Introduzca tu opcion: ");

        int selectOption = scan.nextInt();

        switch (selectOption) {
            case 0:
                System.out.println("--------------------------------");
                System.out.println("*** Has finalizado la sesion ***");
                System.out.println("--------------------------------");
                break;
            case 1:
                crearCliente();
                menuPrincipal();
                break;
            case 2:
                if(listaClientes != null){
                    System.out.println("Debes darse de alta primero");
                }else{
                    crearCuentaBancaria(clienteCuenta);
                }
                menuPrincipal();
                break;
            case 3:
                //bancariaCreada();
                break;
            case 4:
                //menuTransacciones();
                break;
            default:
                System.out.print("Caracter invalido");
                menuPrincipal();
        }
    }

    public static void crearCliente(){

        Scanner scan = new Scanner(System.in);

        String nombre1, apellidos1, correoElectronico1, dni1;
        int dia, mes, year, dniNum;
        char dniLet;

        do{
            System.out.print("Introduce el nombre: ");
            nombre1 = scan.next();
        }while(cliente.validacionNombre(nombre1));

        do{
            System.out.print("Introduce los apellidos: ");
            apellidos1 = scan.next();
        }while(cliente.validacionApellidos(apellidos1));

        do{
            System.out.println("Introduce la fecha de nacimiento");
            System.out.print("Dia: ");
            dia = scan.nextInt();
            System.out.print("Mes: ");
            mes = scan.nextInt();
            System.out.print("Año: ");
            year = scan.nextInt();
            if(Fecha.comprobarFecha(dia, mes, year)){
                fecha = new Fecha(dia, mes, year);
            }
            if(fecha != null){
                fecha.imprimir();
                System.out.println();
            }
        }while(fecha == null);

        do{
            System.out.print("Introduce el correo: ");
            correoElectronico1 = scan.next();
        }while(!cliente.validacionCorreo(correoElectronico1));

        do{
            do{
                System.out.print("Introduce los numeros del DNI: ");
                dniNum = scan.nextInt();
            }while(dniNum > 99999999);

            System.out.print("Introduce la letra DNI: ");
            dniLet = scan.next().charAt(0);
            dniLet = Character.toUpperCase(dniLet);
            dni1 = String.valueOf(dniNum) + dniLet;
        }while(!cliente.validacionDNI(dniNum, dniLet));

        System.out.println(dni1);

        System.out.println("-----------------------------------------------");
        System.out.println("** Enhorabuena!!, ya eres cliente de UPMBank **");
        System.out.println("-----------------------------------------------");

        cliente = new Cliente(nombre1, apellidos1, fecha, correoElectronico1, dni1);
        listClientes.addCliente(cliente);

    }

    public static void crearCuentaBancaria(Cliente clienteCuenta) {

        Scanner scan = new Scanner(System.in);

        long numeroCuenta = cuenta.fNumeroCuenta();
        int digitoControl, codigoSucursal;
        String dni, iban = "";
        ListaCuentas listaCuentaCliente;

        System.out.print("Introduce el DNI: ");
        dni = scan.nextLine();

        clienteCuenta = listaClientes.buscarCliente(dni);

        if(clienteCuenta == null){
            System.out.println("No existe el cliente");
            menuPrincipal();
        }else{
            listaCuentaCliente = clienteCuenta.getCuentas();

            System.out.println("Enhorabuna, hemos encontrado tus datos");
            System.out.print("Elige el tipo de cuenta: \n\t1) Corriente\n\t2) Ahorro \n\t3) Remunerada\n");
            System.out.print("Introduzca tu opcion: ");

            menuTipoCuenta();
            codigoSucursal = menuCodigoSucursal();


            digitoControl = cuenta.fDigitoControl(numeroCuenta, codigoSucursal);

            iban = cuenta.fIban(numeroCuenta, digitoControl);
        }

        System.out.printf("Tu numero de cuenta bancaria nuevo es: %2d\n", iban);
        System.out.println("El saldo actual de la cuenta es: 0€");
    }

    public static TipoCuenta.tipoCuenta menuTipoCuenta(){
        Scanner scan = new Scanner(System.in);

        TipoCuenta.tipoCuenta tiposCuenta = null;
        int selectOption2 = scan.nextInt();

        switch (selectOption2) {
            case 1:
                tiposCuenta = TipoCuenta.tipoCuenta.Corriente;
                System.out.println("Has elegido: Corriente");
                break;
            case 2:
                tiposCuenta = TipoCuenta.tipoCuenta.Ahorro;
                System.out.println("Has elegido: Ahorro");
                break;
            case 3:
                tiposCuenta = TipoCuenta.tipoCuenta.Remunerada;
                System.out.println("Has elegido: Remunerada");
                break;
        }

        return tiposCuenta;
    }

    public static int menuCodigoSucursal(){
        Scanner scan = new Scanner(System.in);

        int codigoSucursal = 0;

        int selectOption3 = scan.nextInt();

        switch (selectOption3) {
            case 1:
                System.out.println("Has elegido la sucursal: Campus sur");
                codigoSucursal = 0201;
                break;
            case 2:
                System.out.println("Has elegido la sucursal: Campus Ciudad Universitaria");
                codigoSucursal = 0202;
                break;
            case 3:
                System.out.println("Has elegido la sucursal: Campus Madrid Ciudad");
                codigoSucursal = 0203;
                break;
            case 4:
                System.out.println("Has elegido la sucursal: Campus Montegarcedo");
                codigoSucursal = 0204;
                break;
        }

        return codigoSucursal;
    }

}
