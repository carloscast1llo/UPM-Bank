import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Banco {

    private static Fecha fecha = null;
    private static Cuenta cuenta = null;
    private static Cliente cliente = null;
    private static ListaClientes listClientes = new ListaClientes(20);;
    private static ListaCuentas listCuenta = null;

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
        boolean existe = false;
        switch (selectOption) {
            case 0:
                System.out.println("--------------------------------");
                System.out.println("*** Has finalizado la sesion ***");
                System.out.println("--------------------------------");
                break;
            case 1:
                crearCliente(existe);
                menuPrincipal();
                break;
            case 2:
                if (listClientes == null) {
                    System.out.println("Debes darse de alta primero");
                } else {
                    crearCuentaBancaria();
                }
                menuPrincipal();
                break;
            case 3:
                menuMovimiento();
                menuPrincipal();
                break;
            case 4:
                //menuTransacciones();
                break;
            case 5:
                menuImprimirCliente();
                break;
            default:
                System.out.print("Caracter invalido");
                menuPrincipal();
        }
    }

    public static void crearCliente(boolean existe) {

        Scanner scan = new Scanner(System.in);

        String nombre1, apellidos1, correoElectronico1, dni1;
        int dia, mes, year, dniNum;
        char dniLet;

        do {
            System.out.print("Introduce el nombre: ");
            nombre1 = scan.next();
        } while (cliente.validacionNombre(nombre1));

        do {
            System.out.print("Introduce los apellidos: ");
            apellidos1 = scan.next();
        } while (cliente.validacionApellidos(apellidos1));

        do {
            System.out.println("Introduce la fecha de nacimiento");
            System.out.print("Dia: ");
            dia = scan.nextInt();
            System.out.print("Mes: ");
            mes = scan.nextInt();
            System.out.print("Año: ");
            year = scan.nextInt();
            if (Fecha.comprobarFecha(dia, mes, year)) {
                fecha = new Fecha(dia, mes, year);
            }
            if (fecha != null) {
                fecha.imprimir();
                System.out.println();
            }
        } while (fecha == null);

        do {
            System.out.print("Introduce el correo: ");
            correoElectronico1 = scan.next();
        } while (!cliente.validacionCorreo(correoElectronico1));

        do {
            do {
                System.out.print("Introduce los numeros del DNI: ");
                dniNum = scan.nextInt();
            } while (dniNum > 99999999);

            System.out.print("Introduce la letra DNI: ");
            dniLet = scan.next().charAt(0);
            dniLet = Character.toUpperCase(dniLet);
            dni1 = String.valueOf(dniNum) + dniLet;
        } while (!cliente.validacionDNI(dniNum, dniLet));

        System.out.println(dni1);

        System.out.println("-----------------------------------------------");
        System.out.println("** Enhorabuena!!, ya eres cliente de UPMBank **");
        System.out.println("-----------------------------------------------");
        existe = true;

        cliente = new Cliente(nombre1, apellidos1, fecha, correoElectronico1, dni1);
        listClientes.addCliente(cliente);
    }

    public static void crearCuentaBancaria() {

        Scanner scan = new Scanner(System.in);

        Cliente clienteCuenta;

        long numeroCuenta = cuenta.fNumeroCuenta();
        int digitoControl = 0, codigoSucursal;
        String dni, iban = "";

        System.out.print("Introduce el DNI: ");
        dni = scan.nextLine();

        clienteCuenta = listClientes.buscarCliente(dni);
        codigoSucursal = clienteCuenta.getCS();

        if (clienteCuenta == null) {
            System.out.println("No existe el cliente");
            menuPrincipal();
        } else {

            System.out.println("Enhorabuna, hemos encontrado tus datos");
            System.out.print("Elige el tipo de cuenta: \n\t1) Corriente\n\t2) Ahorro \n\t3) Remunerada\n");
            System.out.print("Introduzca tu opcion: ");

            TipoCuenta.tipoCuenta tipoCuenta = menuTipoCuenta();

            if(codigoSucursal == 0){
                codigoSucursal = menuCodigoSucursal();
            }

            digitoControl = cuenta.fDigitoControl(numeroCuenta, codigoSucursal);

            iban = cuenta.fIban(numeroCuenta, digitoControl, codigoSucursal);

            System.out.printf("Tu numero de cuenta bancaria nuevo es: %2s\n", iban);
            System.out.println("El saldo actual de la cuenta es: 0€");

            cuenta = new Cuenta(codigoSucursal, digitoControl, numeroCuenta, iban, tipoCuenta);
            clienteCuenta.getCuentas().añadirCuenta(cuenta);
        }

    }

    public static TipoCuenta.tipoCuenta menuTipoCuenta() {
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
            default:
                System.out.println("Caracter no valida");
        }

        return tiposCuenta;
    }

    public static int menuCodigoSucursal() {
        Scanner scan = new Scanner(System.in);
        int codigoSucursal = 0;
        String selectOption3;

        do {
            leerFicheroTXT();
            System.out.println("Introduce donde estas: ");
            selectOption3 = scan.nextLine();

            if (selectOption3.equals("Campus Sur")) {
                System.out.println("Has elegido la sucursal: Campus Sur");
                codigoSucursal = 201;
            } else if (selectOption3.equals("Campus Ciudad Universitaria")) {
                System.out.println("Has elegido la sucursal: Campus Ciudad Universitaria");
                codigoSucursal = 202;
            } else if (selectOption3.equals("Campus Madrid Ciudad")) {
                System.out.println("Has elegido la sucursal: Campus Madrid Ciudad");
                codigoSucursal = 203;
            } else if (selectOption3.equals("Campus Montegarcedo")) {
                System.out.println("Has elegido la sucursal: Campus Montegarcedo");
                codigoSucursal = 204;
            }
        }while (!(selectOption3.equals("Campus Sur") || selectOption3.equals("Campus Ciudad Universitaria") || selectOption3.equals("Campus Madrid Ciudad") || selectOption3.equals("Campus Montegarcedo")));

        return codigoSucursal;
    }

    public static void menuMovimiento() {
        Scanner scan = new Scanner(System.in);

        ListaMovimientos movimiento = null;
        Movimiento.TipoMovimiento movimientoTipo = null;

        System.out.println("Que deseas hacer: \n\t1) Ingresar dinero\n\t2) Retirar dinero\n\t3) Transferencia\n\t0) Salir");
        System.out.print("Introduzca su opcion: ");
        int selectOption4 = scan.nextInt();
        switch (selectOption4) {
            case 1:
                movimientoTipo = Movimiento.TipoMovimiento.Deposito;
                menuDeposito(scan, cuenta, movimientoTipo);
                menuMovimiento();
                break;
            case 2:
                movimientoTipo = Movimiento.TipoMovimiento.Extraccion;
                menuExtraccion(scan, cuenta, movimientoTipo);
                menuMovimiento();
                break;
            case 3:
                movimientoTipo = Movimiento.TipoMovimiento.Transferencia;
                menuTransferencia();
                menuMovimiento();
                break;
            case 0:
                System.out.println("Has elegido: Salir");
                break;
            default:
                System.out.print("Caracter invalido");
                menuMovimiento();
        }

    }

    public static Movimiento menuDeposito(Scanner scan, Cuenta cuenta, Movimiento.TipoMovimiento movimientoTipo) {
        double importe, saldo;
        do {
            System.out.print("Introduce el importe: ");
            importe = scan.nextDouble();
            if (importe < 0) {
                System.out.println("El importe no puede ser negativo");
            }
        } while (importe < 0);

        cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() + importe);

        System.out.println("Se ha realizado el deposito con exito");

        return new Movimiento(movimientoTipo, importe);
    }

    public static Movimiento menuExtraccion(Scanner scan, Cuenta cuenta, Movimiento.TipoMovimiento movimientoTipo) {
        double importe;
        do {
            System.out.print("Introduce el importe: ");
            importe = scan.nextDouble();
            if (importe < 0) {
                System.out.println("El importe no puede ser negativo");
            }
        } while (importe < 0);

        cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() + importe);

        System.out.println("Se ha realizado la extraccion con exito");
        return new Movimiento(movimientoTipo, importe);
    }

    public static void menuTransferencia() {
        Scanner scan = new Scanner(System.in);
        Cuenta cuentaEmisor, cuentaReceptor;
        Transferencia transferenciaEmintada, tranferienciaRecibida;
        double importe;
        cuentaEmisor = buscarCuentaEmisorTransferencia();
        if(cuentaEmisor == null){
            System.out.println("No se ha encontrado la cuenta emisor");
            menuTransferencia();
        }else{
            cuentaReceptor = buscarCuentaReceptorTransferencia();
            if(cuentaReceptor == null){
                System.out.println("No se ha encontrado la cuenta receptor");
                menuTransferencia();
            }else {
                System.out.println("Calculando saldo....");
                System.out.println("Saldo emisor: " + cuentaEmisor.getSaldoCuenta());
                do{
                    System.out.print("Introduce el importe que quieres hacer la transferencia: ");
                    importe = scan.nextDouble();
                    if(cuentaEmisor.getSaldoCuenta() > importe){
                        System.out.println("El importe es mayor al saldo de la cuenta emisor");
                    }
                }while(cuentaEmisor.getSaldoCuenta() > importe);
                System.out.println("La transferencia se ha realizado con exito");

                cuentaEmisor.setSaldoCuenta(cuentaEmisor.getSaldoCuenta() - importe);
                cuentaReceptor.setSaldoCuenta(cuentaReceptor.getSaldoCuenta() + importe);
                //tranferienciaEmitida = new Transferencia();
                //tranferienciaRecibida = new Transferencia();
                //cuentaEmisor.getTransferenciasEmitidas().addTransferencia(tranferienciaEmitida);
               // cuentaReceptor.getTransferenciasRecibidas().addTransferencia(tranferienciaRecibida);
            }
        }

    }

    public static Cuenta buscarCuentaEmisorTransferencia(){
        Scanner scan = new Scanner(System.in);
        Cliente clienteEmisor;
        Cuenta cuentaEmisor = null;
        System.out.println("Introduce el DNI de cuenta emisor: ");
        String dniEmisor = scan.nextLine();
        if(listClientes.buscarCliente(dniEmisor) == null){
            System.out.println("No se ha encontrado el cliente");
            menuTransferencia();
        }else{
            clienteEmisor = listClientes.buscarCliente(dniEmisor);
            System.out.println("Introduce el numero de cuenta emisor: ");
            String numeroCuentaEmisor = scan.next();
            cuentaEmisor = clienteEmisor.getCuentas().getCuenta(numeroCuentaEmisor);
        }

        return cuentaEmisor;
    }

    public static Cuenta buscarCuentaReceptorTransferencia(){
        Scanner scan = new Scanner(System.in);
        Cliente clienteReceptor;
        Cuenta cuentaReceptor = null;
        System.out.println("Introduce el DNI de cuenta receptor: ");
        String dniReceptor = scan.nextLine();
        if(listClientes.buscarCliente(dniReceptor) == null){
            System.out.println("No se ha encontrado el cliente");
            menuTransferencia();
        }else{
            clienteReceptor = listClientes.buscarCliente(dniReceptor);
            System.out.println("Introduce el numero de cuenta emisor: ");
            String numeroCuentaEmisor = scan.next();
            cuentaReceptor = clienteReceptor.getCuentas().getCuenta(numeroCuentaEmisor);
        }

        return cuentaReceptor;
    }

    public static void menuImprimirCliente() {

        Cliente clienteImprimir;

        Scanner scan = new Scanner(System.in);
        String dni2 = "", iban2 = "";

        System.out.println("¿Que datos quieres ver?");
        System.out.println("\t1) Datos de un cliente");
        System.out.println("\t2) Todos los datos de loos clientes del banco");
        System.out.println("\t0) Salir");
        System.out.print("Introduzca tu opcion: ");
        int opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                System.out.print("Introduce el DNI (Para encontrar el cliente): ");
                dni2 = scan.next();

                clienteImprimir = listClientes.buscarCliente(dni2);

                if (clienteImprimir == null) {
                    System.out.println("No existe el cliente");
                    menuPrincipal();
                } else {
                    System.out.println("***Enhorabuna, hemos encontrado tus datos***");
                    clienteImprimir.imprimirCliente();
                }
                menuImprimirCliente();
                break;
            case 2:
                listClientes.imprimirTodosClientes();
                menuImprimirCliente();
                break;
            case 0:
                menuPrincipal();
                break;
            default:
                System.out.print("Caracter invalido");
                menuImprimirCliente();
        }

    }

    public static void leerFicheroTXT(){

        File leerFichero = new File("Sucursales.txt");
        FileReader fr = null;
        BufferedReader br = null;
        try{
            fr = new FileReader(leerFichero);
            br = new BufferedReader(fr);
            String linea;
            while((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

        }catch(Exception e) {
            e.getMessage();
        }finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
    }



}
