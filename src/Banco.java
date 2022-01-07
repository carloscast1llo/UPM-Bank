import java.io.*;
import java.util.Scanner;

public class Banco {

    private static Fecha fecha = null;
    private static Cuenta cuenta = null;
    private static Cliente cliente = null;
    private static ListaClientes listClientes = new ListaClientes(20);
    private static ListaCuentas listCuenta = null;
    private static ListaMovimientos listMovimientos = null;
    private static ListaTransferencias listTransferencias = null;
    private static ListaPrestamos listaPrestamos = null;
    static int codigoSucursal;

    public static void main(String[] args) throws IOException {
        codigoSucursal = menuCodigoSucursal();
        menuPrincipal();
    }


    public static void menuPrincipal() throws IOException {
        Scanner scan = new Scanner(System.in);

        int opcion;

        do {
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
            System.out.println("\t4) Mostrar datos de clientes");
            System.out.println("\t0) Cerrar sesion");

            System.out.print("Introduzca tu opcion: ");

            opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    crearCliente();
                    break;
                case 2:
                    crearCuentaBancaria(codigoSucursal);
                    break;
                case 3:
                    menuTransacciones();
                    break;
                case 4:
                    menuImprimirCliente(cuenta);
                    break;
                case 0:
                    System.out.println("--------------------------------");
                    System.out.println("*** Has finalizado la sesion ***");
                    System.out.println("--------------------------------");
                    //escribirFichero();
                    break;
                default:
                    System.out.print("***Caracter invalido***");
            }

        }while (opcion != 0);

    }


    public static void crearCliente() {

        Scanner scan = new Scanner(System.in);

        String nombre1, apellidos1, correoElectronico1, dni1;
        int dia, mes, year, dniNum;
        char dniLet;

        do {
            System.out.print("Introduce el nombre: ");
            nombre1 = scan.next();
        } while (!cliente.validacionNombre(nombre1));

        do {
            System.out.print("Introduce los apellidos: ");
            apellidos1 = scan.next();
        } while (!cliente.validacionApellidos(apellidos1));

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
        } while (!cliente.validacionCorreo(correoElectronico1) || !cliente.validacionCorreoRepetido(correoElectronico1, listClientes));

        do {
            do {
                System.out.print("Introduce los numeros del DNI: ");
                dniNum = scan.nextInt();
            } while (dniNum > 99999999);

            System.out.print("Introduce la letra DNI: ");
            dniLet = scan.next().charAt(0);
            dniLet = Character.toUpperCase(dniLet);
            dni1 = String.valueOf(dniNum) + dniLet;
        } while (!cliente.validacionDNI(dniNum, dniLet) || !cliente.validacionDniRepetido(dni1, listClientes));

        System.out.println(dni1);

        System.out.println("-----------------------------------------------");
        System.out.println("** Enhorabuena!!, ya eres cliente de UPMBank **");
        System.out.println("-----------------------------------------------");

        cliente = new Cliente(nombre1, apellidos1, fecha, correoElectronico1, dni1);
        listClientes.addCliente(cliente);
    }


    public static void crearCuentaBancaria(int codigoSucursal) {

        Scanner scan = new Scanner(System.in);
        Cliente clienteCuenta;

        if(cliente == null){
            System.out.println("***Debes darse de alta primero***");
        }else{
            long numeroCuenta = cuenta.fNumeroCuenta();
            int digitoControl;
            String dni, iban;

            System.out.print("Introduce el DNI: ");
            dni = scan.nextLine();

            clienteCuenta = listClientes.buscarCliente(dni);

            if (clienteCuenta == null) {
                System.out.println("***No existe el cliente***");
            } else {

                System.out.println("***Enhorabuna, hemos encontrado tus datos***");

                TipoCuenta.tipoCuenta tipoCuenta = menuTipoCuenta();

                digitoControl = cuenta.fDigitoControl(numeroCuenta, codigoSucursal);

                iban = cuenta.fIban(numeroCuenta, digitoControl, codigoSucursal);

                System.out.printf("Tu numero de cuenta bancaria nuevo es: %2s\n", iban);
                System.out.println("El saldo actual de la cuenta es: 0€");

                cuenta = new Cuenta(codigoSucursal, digitoControl, numeroCuenta, iban, tipoCuenta);
                clienteCuenta.getCuentas().addCuenta(cuenta);
            }
        }

    }

    public static TipoCuenta.tipoCuenta menuTipoCuenta() {
        Scanner scan = new Scanner(System.in);

        TipoCuenta.tipoCuenta tiposCuenta = null;

        System.out.print("Elige el tipo de cuenta: \n\t1) Corriente\n\t2) Ahorro \n\t3) Remunerada\n");

        System.out.print("Introduzca tu opcion: ");
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
                menuTipoCuenta();
        }

        return tiposCuenta;
    }

    public static int menuCodigoSucursal() {
        Scanner scan = new Scanner(System.in);
        int codigoSucursal = 0;
        String selectOption3;

        do {
            leerFicheroTXT();
            System.out.print("\nIntroduce donde estas: ");
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


    public static void menuTransacciones() {     // HACERLO EN SWITCH
        Scanner scan = new Scanner(System.in);

        if (cliente == null || cuenta == null) {
            System.out.println("***Debes darse de alta primero y tener una cuenta***");
        }else{
            System.out.println("¿Que deseas hacer?");
            System.out.println("\t1) Ingresar/Retinar dinero");
            System.out.println("\t2) Traseferencia bancaria");
            System.out.println("\t3) Prestamo hipotecario");
            System.out.println("\t0) Salir");

            System.out.print("Introduzca su opcion: ");
            int selectOption4 = scan.nextInt();

            switch (selectOption4) {
                case 1:
                    menuBusquedaMovimiento();
                    break;
                case 2:
                    menuTransferencia();
                    break;
                case 3:
                    menuPrestamo();
                    break;
                case 0:
                    System.out.println("Has elegido: Salir");
                    break;
                default:
                    System.out.print("Caracter invalido");
                    menuTransacciones();

            }
       }

    }


    public static void menuIngresoExtraccion(Cuenta busquedaCuenta) {
        Scanner scan = new Scanner(System.in);

        Movimiento movimiento;
        Movimiento.TipoMovimiento movimientoTipo = null;

        System.out.println("¿Que deseas hacer?");
        System.out.println("\t1) Ingresar dinero");
        System.out.println("\t2) Retirar dinero");
        System.out.println("\t0) Salir");

        System.out.print("Introduzca su opcion: ");
        int selectOption4 = scan.nextInt();

        switch (selectOption4) {
            case 1:
                movimientoTipo = Movimiento.TipoMovimiento.Deposito;
                movimiento = menuDeposito(busquedaCuenta, movimientoTipo);
                busquedaCuenta.getMovimiento().addMovimiento(movimiento);
                menuIngresoExtraccion(busquedaCuenta);
                break;
            case 2:
                movimientoTipo = Movimiento.TipoMovimiento.Extraccion;
                movimiento = menuExtraccion(busquedaCuenta, movimientoTipo);
                busquedaCuenta.getMovimiento().addMovimiento(movimiento);
                menuIngresoExtraccion(busquedaCuenta);
                break;
            case 0:
                System.out.println("Has elegido: Salir");
                menuTransacciones();
                break;
            default:
                System.out.print("Caracter invalido");
                menuIngresoExtraccion(busquedaCuenta);
        }

    }

    public static void menuBusquedaMovimiento(){
        Scanner scan = new Scanner(System.in);
        String dni, iban;

        Cliente busquedaCliente;
        Cuenta busquedaCuenta;

        System.out.print("Introduce el DNI del cliente: ");
        dni = scan.next();
        busquedaCliente = listClientes.buscarCliente(dni);

        if (busquedaCliente == null) {
            System.out.println("***El cliente no existe***");
            //menuBusquedaMovimiento();
        }else{
            busquedaCliente.imprimirCuentaCliente();
            System.out.print("Introduce el IBAN de la cuenta: ");
            iban = scan.next();
            busquedaCuenta = busquedaCliente.getCuentas().buscarCuenta(iban);
            if (busquedaCuenta == null) {
                System.out.println("***La cuenta no existe***");
                //menuBusquedaMovimiento();
            }else{
                if(busquedaCuenta.getMovimiento().getNumMovimientos() > busquedaCuenta.getMovimiento().getMaxMovimientos()){
                    System.out.println("***Ha alcanzado el numero maximos de movimientos***");
                }else{
                    menuIngresoExtraccion(busquedaCuenta);
                }
            }
        }

    }

    public static Movimiento menuDeposito(Cuenta busquedaCuenta, Movimiento.TipoMovimiento movimientoTipo) {

        Scanner scan = new Scanner(System.in);
        double importe;

        do {
            System.out.print("Introduce el importe: ");
            importe = scan.nextDouble();
            if (importe < 0) {
                System.out.println("El importe no puede ser negativo");
            }
        } while (importe < 0);

        busquedaCuenta.setSaldo(busquedaCuenta.getSaldo() + importe);

        System.out.println("Se ha realizado el deposito con exito");


        return new Movimiento(movimientoTipo, importe);
    }

    public static Movimiento menuExtraccion(Cuenta busquedaCuenta, Movimiento.TipoMovimiento movimientoTipo) {
        Scanner scan = new Scanner(System.in);
        double importe;
        do {
            System.out.print("Introduce el importe: ");
            importe = scan.nextDouble();
            if (importe < 0) {
                System.out.println("El importe no puede ser negativo o menor al saldo de la cuenta");
            }else if(importe > busquedaCuenta.getSaldo()){
                System.out.println("El importe no puede ser mayor al saldo de la cuenta");
            }
        } while (importe < 0 || importe > busquedaCuenta.getSaldo());

        busquedaCuenta.setSaldo(busquedaCuenta.getSaldo() - importe);

        System.out.println("Se ha realizado la extraccion con exito");
        return new Movimiento(movimientoTipo, importe);
    }


    public static void menuTransferencia() {
        Scanner scan = new Scanner(System.in);

        Cuenta cuentaEmisor, cuentaReceptor;
        Transferencia transferenciaEmitida, transferenciaRecibida;
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
                System.out.println("Saldo emisor: " + cuentaEmisor.getSaldo());
                do{
                    System.out.print("Introduce el importe que quieres hacer la transferencia: ");
                    importe = scan.nextDouble();
                    if(cuentaEmisor.getSaldo() < importe){
                        System.out.println("El importe es mayor al saldo de la cuenta emisor");
                    }else if(importe <= 0){
                        System.out.println("El importe no puede ser negativo o igual a 0");
                    }
                }while(cuentaEmisor.getSaldo() < importe);
                System.out.println("La transferencia se ha realizado con exito");

                cuentaEmisor.setSaldo(cuentaEmisor.getSaldo() - importe);
                cuentaReceptor.setSaldo(cuentaReceptor.getSaldo() + importe);
                transferenciaEmitida = new Transferencia(cuentaEmisor.getIban(), cuentaReceptor.getIban(), importe);
                transferenciaRecibida = new Transferencia(cuentaReceptor.getIban(), cuentaEmisor.getIban(), importe);
                cuentaEmisor.getTransferenciasEmitidas().addTransferencia(transferenciaEmitida);
                cuentaReceptor.getTransferenciasRecibidas().addTransferencia(transferenciaRecibida);
            }
        }

    }

    public static Cuenta buscarCuentaEmisorTransferencia(){
        Scanner scan = new Scanner(System.in);
        Cliente clienteEmisor;
        Cuenta cuentaEmisor = null;
        System.out.print("Introduce el DNI de cuenta emisor: ");
        String dniEmisor = scan.nextLine();
        if(listClientes.buscarCliente(dniEmisor) == null){
            System.out.println("No se ha encontrado el cliente");
            menuTransferencia();
        }else{
            clienteEmisor = listClientes.buscarCliente(dniEmisor);
            clienteEmisor.imprimirCuentaCliente();
            System.out.print("Introduce el numero de cuenta emisor: ");
            String numeroCuentaEmisor = scan.next();
            cuentaEmisor = clienteEmisor.getCuentas().buscarCuenta(numeroCuentaEmisor);
        }

        System.out.println("***Datos del emisor encontrado con exito***");

        return cuentaEmisor;
    }

    public static Cuenta buscarCuentaReceptorTransferencia(){
        Scanner scan = new Scanner(System.in);
        Cliente clienteReceptor;
        Cuenta cuentaReceptor = null;
        System.out.print("Introduce el DNI de cuenta receptor: ");
        String dniReceptor = scan.nextLine();
        if(listClientes.buscarCliente(dniReceptor) == null){
            System.out.println("No se ha encontrado el cliente");
            menuTransferencia();
        }else{
            clienteReceptor = listClientes.buscarCliente(dniReceptor);
            clienteReceptor.imprimirCuentaCliente();
            System.out.print("Introduce el numero de cuenta receptor: ");
            String numeroCuentaEmisor = scan.next();
            cuentaReceptor = clienteReceptor.getCuentas().buscarCuenta(numeroCuentaEmisor);
        }

        System.out.println("***Datos del receptor encontrado con exito***");

        return cuentaReceptor;
    }


    public static void menuPrestamo(){

        Scanner scan = new Scanner(System.in);

        double capital, interesAnual;
        int numeroAnios, numeroMeses;
        Cuenta busquedaCuentaPrestamo;
        Prestamo prestamo = null;

        busquedaCuentaPrestamo = buscarCuentaPrestamo();
        if(busquedaCuentaPrestamo == null){
            System.out.println("No se ha encontrado la cuenta");
            menuTransacciones();
        }else if(busquedaCuentaPrestamo.getPrestamos().getNumPrestamos() >= busquedaCuentaPrestamo.getPrestamos().getMAX_PRESTAMOS()){
            System.out.println("***Ha alcanzado el numero maximos de movimientos***");
            menuTransacciones();
        }else{
            System.out.print("Introduzca el capital solicitado: ");
            capital = scan.nextDouble();
            System.out.print("Introduzca el numero de años: ");
            numeroAnios = scan.nextInt();
            System.out.print("Introduzca el interes anual (en tanto por ciento): ");
            interesAnual = scan.nextDouble();

            prestamo = new Prestamo(capital, numeroAnios, interesAnual);
            busquedaCuentaPrestamo.getPrestamos().addPrestamo(prestamo);
            busquedaCuentaPrestamo.setSaldo(busquedaCuentaPrestamo.getSaldo() + capital);
            tablaAmortizaciones(prestamo, numeroAnios, interesAnual);
        }
    }

    public static Cuenta buscarCuentaPrestamo(){
        Scanner scan = new Scanner(System.in);
        Cliente clientePrestamo;
        Cuenta cuentaPrestamo = null;

        System.out.print("Introduce el DNI: ");
        String dniPrestamo = scan.nextLine();

        if(listClientes.buscarCliente(dniPrestamo) == null){
            System.out.println("No se ha encontrado el cliente");
            buscarCuentaPrestamo();
        }else{
            clientePrestamo = listClientes.buscarCliente(dniPrestamo);
            clientePrestamo.imprimirCuentaCliente();
            System.out.print("Introduce el numero de cuenta para realizar prestamo: ");
            String numeroCuentaEmisor = scan.next();
            cuentaPrestamo = clientePrestamo.getCuentas().buscarCuenta(numeroCuentaEmisor);
        }

        return cuentaPrestamo;
    }

    public static void tablaAmortizaciones(Prestamo prestamo, int numeroAnios, double interesAnual){
        int i = 0;
        double cuota, ia, ca, cv = prestamo.getCapital();

        int numeroMeses = numeroAnios * 12;
        interesAnual = interesAnual / 100f;
        double imes = interesAnual / 12;

        cuota = prestamo.getCapital() * imes * (Math.pow((1 + imes), numeroMeses) / (Math.pow((1 + imes), numeroMeses) - 1));
        System.out.println("Mensualidad\t\tCuota\t\tInteres\t\tAbonado\t\tRestante");
        System.out.printf("\t%d\t\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n", 0, 0f, 0f, 0f, cv);
        i++;
        for(int j = 1; j<= numeroMeses; j++){
            ia = cv * imes;
            ca = cuota - ia;
            cv -= ca;
            System.out.printf("\t%d\t\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n", i, cuota, ia, ca, cv);
            i++;
        }

    }


    public static void menuImprimirCliente(Cuenta cuentaPrestamo) throws IOException {

        Cliente clienteImprimir;

        Scanner scan = new Scanner(System.in);

        if(cliente == null){
            System.out.println("***Debes darse de alta primero***");
        }else{
            System.out.println("¿Que datos quieres ver?");
            System.out.println("\t1) Datos de un cliente");
            System.out.println("\t2) Todos los datos de los clientes del banco");
            System.out.println("\t3) Tablas amortizaciones de prestamos");
            System.out.println("\t0) Salir");
            System.out.print("Introduzca tu opcion: ");
            int opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el DNI (Para encontrar el cliente): ");
                    String dni2 = scan.next();

                    clienteImprimir = listClientes.buscarCliente(dni2);

                    if (clienteImprimir == null) {
                        System.out.println("No existe el cliente");
                        menuPrincipal();
                    } else {
                        System.out.println("***Enhorabuna, hemos encontrado tus datos***");
                        System.out.println();
                        clienteImprimir.imprimirCliente();
                    }
                    break;
                case 2:
                    System.out.println();
                    listClientes.imprimirTodosClientes();
                    break;
                case 3:
                    menuImprimirTablaAmortizaciones(cuentaPrestamo);
                    break;
                case 0:
                    System.out.println("Has elegido: Salir");
                    break;
                default:
                    System.out.print("Caracter invalido");
                    menuImprimirCliente(cuentaPrestamo);
            }
        }

    }
    public static void menuImprimirTablaAmortizaciones(Cuenta cuentaPrestamo){

        Scanner scan = new Scanner(System.in);

        Prestamo prestamoCliente;

        cuentaPrestamo = buscarCuentaPrestamo();
        if(cuentaPrestamo == null){
            System.out.println("No se ha encontrado la cuenta");
        }else{
            System.out.print("Introduce el capital: ");
            double capital = scan.nextDouble();
            System.out.print("Introduce el numero de años: ");
            int numeroAnios = scan.nextInt();
            System.out.print("Introduzca el interes anual (en tanto por ciento): ");
            double interesAnual = scan.nextDouble();

            prestamoCliente = cuentaPrestamo.getPrestamos().buscarPrestamo(capital, numeroAnios, interesAnual);
            if (prestamoCliente == null) {
                System.out.println("No se ha encontrado el prestamo");
            }else {
                System.out.println("***Enhorabuna, hemos encontrado el prestamo***");
                tablaAmortizaciones(prestamoCliente, numeroAnios, interesAnual);
            }

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

    public static void escribirFichero() throws IOException {

        File archivo = new File("Transferencias.txt");
        BufferedWriter bw = null;

        bw = new BufferedWriter(new FileWriter(archivo));
        for(int i=0; i<1; i++){
            bw.write(1);
        }

        System.out.println("*** Fichero creado correctamente ***");

        bw.close();

    }
}
