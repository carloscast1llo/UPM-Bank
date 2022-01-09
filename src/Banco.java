import java.io.*;
import java.util.*;

public class Banco {

    private static Fecha fecha = null;
    private static Cuenta cuenta = null;
    private static Cliente cliente = null;
    private static ListaClientes listClientes = new ListaClientes(20);
    private static ListaCuentas listCuentas = new ListaCuentas(200);
    static int codigoSucursal;

    public static void main(String[] args) throws IOException {
        codigoSucursal = leerFicheroSucursal();
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
                    menuImprimirCliente();
                    break;
                case 0:
                    escribirFichero();
                    System.out.println("--------------------------------");
                    System.out.println("*** Has finalizado la sesion ***");
                    System.out.println("--------------------------------");
                    break;
                default:
                    System.out.print("***Caracter invalido***");
            }

        }while (opcion != 0);

    }


    public static void crearCliente() {     //Crea un nuevo cliente

        Scanner scan = new Scanner(System.in);

        String nombre1, apellidos1, correoElectronico1, dni1;
        int dia, mes, year, dniNum;
        char dniLet;

        do {
            System.out.print("Introduce el nombre: ");
            nombre1 = scan.next();
        } while (!Cliente.validacionNombre(nombre1));

        do {
            System.out.print("Introduce los apellidos: ");
            apellidos1 = scan.next();
        } while (!Cliente.validacionApellidos(apellidos1));

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
                System.out.print("Fecha de nacimiento: ");
                fecha.imprimir();
                System.out.println();
            }
        } while (fecha == null);

        do {
            System.out.print("Introduce el correo: ");
            correoElectronico1 = scan.next();
        } while (!Cliente.validacionCorreo(correoElectronico1) || !Cliente.validacionCorreoRepetido(correoElectronico1, listClientes));

        do {
            do {
                System.out.print("Introduce los numeros del DNI: ");
                dniNum = scan.nextInt();
            } while (dniNum > 99999999);

            System.out.print("Introduce la letra DNI: ");
            dniLet = scan.next().charAt(0);
            dniLet = Character.toUpperCase(dniLet);
            dni1 = String.valueOf(dniNum) + dniLet;

            for (int i = 0; i < dni1.length(); i++) {
                if (dni1.length() < 9){
                    dni1 = "0" + dni1;
                }
            }

        } while (!Cliente.validacionDNI(dniNum, dniLet) || !Cliente.validacionDniRepetido(dni1, listClientes));

        System.out.println("DNI: " + dni1);

        System.out.println("-------------------------------------------------");
        System.out.println("*** Enhorabuena!!, ya eres cliente de UPMBank ***");
        System.out.println("-------------------------------------------------");

        cliente = new Cliente(nombre1, apellidos1, fecha, correoElectronico1, dni1);
        listClientes.addCliente(cliente);
    }


    public static void crearCuentaBancaria(int codigoSucursal) {    //Crea una nueva cuenta bancaria

        Scanner scan = new Scanner(System.in);
        Cliente clienteCuenta;
        long numeroCuenta;

        if(cliente == null){
            System.out.println("***Debes darse de alta primero***");
        }else{
            numeroCuenta = Cuenta.fNumeroCuenta();

            for(int i = 0; i < listCuentas.getNumCuentas(); i++){
                if(listCuentas.getCuentaPos(i).getNumeroCuenta() == numeroCuenta){
                    numeroCuenta = Cuenta.fNumeroCuenta();
                }
            }

            int digitoControl;
            String dni, iban;

            System.out.print("Introduce el DNI: ");
            dni = scan.nextLine();

            dni = dni.toUpperCase();

            clienteCuenta = listClientes.buscarCliente(dni);

            if (clienteCuenta == null) {
                System.out.println("***No existe el cliente***");
            } else {

                System.out.println("*** Enhorabuna, hemos encontrado tus datos ***");

                TipoCuenta.tipoCuenta tipoCuenta = menuTipoCuenta();

                if(tipoCuenta == null){
                    tipoCuenta = menuTipoCuenta();
                }

                digitoControl = Cuenta.fDigitoControl(numeroCuenta, codigoSucursal);

                iban = Cuenta.fIban(numeroCuenta, digitoControl, codigoSucursal);

                System.out.printf("Tu numero de cuenta bancaria nuevo es: %2s\n", iban);

                cuenta = new Cuenta(codigoSucursal, digitoControl, numeroCuenta, iban, tipoCuenta);
                clienteCuenta.getCuentas().addCuenta(cuenta);
                listCuentas.addCuenta(cuenta);
            }
        }

    }

    public static TipoCuenta.tipoCuenta menuTipoCuenta() {  //Menu para elegir el tipo de cuenta
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
                System.out.println("***Caracter no valida***");
        }

        return tiposCuenta;
    }


    public static void menuTransacciones() {    //Menu para elegir la transaccion que quiere realizar
        Scanner scan = new Scanner(System.in);

        if (cliente == null || cuenta == null) {
            System.out.println("***Debes darse de alta primero y tener una cuenta***");
        }else{
            System.out.println("¿Que deseas hacer?");
            System.out.println("\t1) Ingresar/Retirar dinero");
            System.out.println("\t2) Transferencia bancaria");
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
                    System.out.print("***Caracter invalido***\n");
                    menuTransacciones();

            }
       }

    }


    public static void menuIngresoExtraccion(Cuenta busquedaCuenta) {    //Menu para elegir el movimiento que quiere realizar
        Scanner scan = new Scanner(System.in);

        Movimiento movimiento;
        Movimiento.TipoMovimiento movimientoTipo;

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
                System.out.print("***Caracter invalido***\n");
                menuIngresoExtraccion(busquedaCuenta);
        }

    }

    public static void menuBusquedaMovimiento(){   //Menu para buscar el cliente y cuenta que quiere realizar el movimiento
        Scanner scan = new Scanner(System.in);
        String dni, iban;

        Cliente busquedaCliente;
        Cuenta busquedaCuenta;

        System.out.print("Introduce el DNI del cliente: ");
        dni = scan.next();
        dni = dni.toUpperCase();

        busquedaCliente = listClientes.buscarCliente(dni);

        if (busquedaCliente == null) {
            System.out.println("***El cliente no existe***");
            menuTransacciones();
        }else{
            busquedaCliente.imprimirCuentaCliente();
            System.out.print("Introduce el IBAN de la cuenta: ");
            iban = scan.next();
            busquedaCuenta = busquedaCliente.getCuentas().buscarCuenta(iban);
            if (busquedaCuenta == null) {
                System.out.println("***La cuenta no existe***");
                menuTransacciones();
            }else{
                if(busquedaCuenta.getMovimiento().getNumMovimientos() > busquedaCuenta.getMovimiento().getMaxMovimientos()){
                    System.out.println("***Ha alcanzado el numero maximos de movimientos***");
                }else{
                    menuIngresoExtraccion(busquedaCuenta);
                }
            }
        }

    }

    public static Movimiento menuDeposito(Cuenta busquedaCuenta, Movimiento.TipoMovimiento movimientoTipo) {    //Menu para realizar el deposito

        Scanner scan = new Scanner(System.in);
        double importe;

        do {
            System.out.print("Introduce el importe: ");
            importe = scan.nextDouble();
            if (importe <= 0) {
                System.out.println("***El importe no puede ser negativo***");
            }
        } while (importe <= 0);

        busquedaCuenta.setSaldo(busquedaCuenta.getSaldo() + importe);

        System.out.println("Se ha realizado el deposito con exito");


        return new Movimiento(movimientoTipo, importe);
    }

    public static Movimiento menuExtraccion(Cuenta busquedaCuenta, Movimiento.TipoMovimiento movimientoTipo) {  //Menu para realizar la extraccion
        Scanner scan = new Scanner(System.in);
        double importe;
        do {
            System.out.print("Introduce el importe: ");
            importe = scan.nextDouble();
            if (importe <= 0) {
                System.out.println("***El importe no puede ser negativo***");
            }else if(importe > busquedaCuenta.getSaldo()){
                System.out.println("***El importe no puede ser mayor al saldo de la cuenta***");
            }
        } while (importe <= 0 || importe > busquedaCuenta.getSaldo());

        busquedaCuenta.setSaldo(busquedaCuenta.getSaldo() - importe);

        System.out.println("Se ha realizado la extraccion con exito");
        return new Movimiento(movimientoTipo, importe);
    }


    public static void menuTransferencia() {    //Menu para realizar la transferencia
        Scanner scan = new Scanner(System.in);

        Cuenta cuentaEmisor, cuentaReceptor;
        Transferencia transferenciaEmitida, transferenciaRecibida;
        double importe;

        cuentaEmisor = buscarCuentaEmisorTransferencia();
        if(cuentaEmisor == null){
            System.out.println("***No se ha encontrado la cuenta emisor***");
            menuTransacciones();
        }else{
            System.out.println("*** Datos del emisor encontrado con exito ***");
            cuentaReceptor = buscarCuentaReceptorTransferencia();
            if(cuentaReceptor == null){
                System.out.println("***No se ha encontrado la cuenta receptor***");
                menuTransacciones();
            }else {
                System.out.println("*** Datos del receptor encontrado con exito ***");
                System.out.println("Calculando saldo....");
                System.out.println("Saldo emisor: " + cuentaEmisor.getSaldo());
                do{
                    System.out.print("Introduce el importe que quieres hacer la transferencia: ");
                    importe = scan.nextDouble();
                    if(cuentaEmisor.getSaldo() < importe){
                        System.out.println("***El importe es mayor al saldo de la cuenta emisor***");
                    }else if(importe <= 0){
                        System.out.println("***El importe no puede ser negativo o igual a 0***");
                    }
                }while(cuentaEmisor.getSaldo() < importe || importe <= 0);

                System.out.println("*** La transferencia se ha realizado con exito ***");

                cuentaEmisor.setSaldo(cuentaEmisor.getSaldo() - importe);
                cuentaReceptor.setSaldo(cuentaReceptor.getSaldo() + importe);
                transferenciaEmitida = new Transferencia(cuentaEmisor.getIban(), cuentaReceptor.getIban(), importe);
                transferenciaRecibida = new Transferencia(cuentaReceptor.getIban(), cuentaEmisor.getIban(), importe);
                cuentaEmisor.getTransferenciasEmitidas().addTransferencia(transferenciaEmitida);
                cuentaReceptor.getTransferenciasRecibidas().addTransferencia(transferenciaRecibida);
            }
        }

    }

    public static Cuenta buscarCuentaEmisorTransferencia(){   //Busca la cuenta emisor de la transferencia
        Scanner scan = new Scanner(System.in);

        Cliente clienteEmisor;
        Cuenta cuentaEmisor = null;

        System.out.print("Introduce el DNI de cuenta emisor: ");
        String dniEmisor = scan.nextLine();
        dniEmisor = dniEmisor.toUpperCase();

        if(listClientes.buscarCliente(dniEmisor) == null){
            System.out.println("***No se ha encontrado el cliente***");
            menuTransacciones();
        }else{
            clienteEmisor = listClientes.buscarCliente(dniEmisor);
            clienteEmisor.imprimirCuentaCliente();
            System.out.print("Introduce el numero de cuenta emisor: ");
            String numeroCuentaEmisor = scan.next();
            cuentaEmisor = clienteEmisor.getCuentas().buscarCuenta(numeroCuentaEmisor);
        }

        return cuentaEmisor;
    }

    public static Cuenta buscarCuentaReceptorTransferencia(){   //Busca la cuenta receptor de la transferencia

        Scanner scan = new Scanner(System.in);
        Cliente clienteReceptor;
        Cuenta cuentaReceptor = null;

        System.out.print("Introduce el DNI de cuenta receptor: ");
        String dniReceptor = scan.nextLine();
        dniReceptor = dniReceptor.toUpperCase();

        if(listClientes.buscarCliente(dniReceptor) == null){
            System.out.println("***No se ha encontrado el cliente***");
            menuTransacciones();
        }else{
            clienteReceptor = listClientes.buscarCliente(dniReceptor);
            clienteReceptor.imprimirCuentaCliente();
            System.out.print("Introduce el numero de cuenta receptor: ");
            String numeroCuentaEmisor = scan.next();
            cuentaReceptor = clienteReceptor.getCuentas().buscarCuenta(numeroCuentaEmisor);
        }

        return cuentaReceptor;
    }


    public static void menuPrestamo(){  //Menu para realizar un prestamo

        Scanner scan = new Scanner(System.in);

        double capital, interesAnual;
        int numeroAnios;
        Cuenta busquedaCuentaPrestamo;
        Prestamo prestamo;

        busquedaCuentaPrestamo = buscarCuentaPrestamo();
        if(busquedaCuentaPrestamo == null){
            System.out.println("***No se ha encontrado la cuenta***");
            menuTransacciones();
        }else if(busquedaCuentaPrestamo.getPrestamos().getNumPrestamos() >= busquedaCuentaPrestamo.getPrestamos().getMAX_PRESTAMOS()){
            System.out.println("***Ha alcanzado el numero maximos de movimientos***");
            menuTransacciones();
        }else{
            System.out.println("*** Datos del cliente encontrado con exito ***");
            do{
                System.out.print("Introduzca el capital solicitado: ");
                capital = scan.nextDouble();
            }while(capital <= 0);
            do {
                System.out.print("Introduzca el numero de años: ");
                numeroAnios = scan.nextInt();
            }while (numeroAnios <= 0);
            do {
                System.out.print("Introduzca el interes anual (en tanto por ciento): ");
                interesAnual = scan.nextDouble();
            }while (interesAnual < 0);


            prestamo = new Prestamo(capital, numeroAnios, interesAnual);
            busquedaCuentaPrestamo.getPrestamos().addPrestamo(prestamo);
            busquedaCuentaPrestamo.setSaldo(busquedaCuentaPrestamo.getSaldo() + capital);
            tablaAmortizaciones(prestamo, numeroAnios, interesAnual);
        }
    }

    public static Cuenta buscarCuentaPrestamo(){    //Busca el cliente y la cuenta para realizar un prestamo
        Scanner scan = new Scanner(System.in);
        Cliente clientePrestamo;
        Cuenta cuentaPrestamo = null;

        System.out.print("Introduce el DNI: ");
        String dniPrestamo = scan.nextLine();
        dniPrestamo = dniPrestamo.toUpperCase();

        if(listClientes.buscarCliente(dniPrestamo) == null){
            System.out.println("***No se ha encontrado el cliente***");
            menuTransacciones();
        }else{
            clientePrestamo = listClientes.buscarCliente(dniPrestamo);
            clientePrestamo.imprimirCuentaCliente();
            System.out.print("Introduce el numero de cuenta para realizar prestamo: ");
            String numeroCuentaEmisor = scan.next();
            cuentaPrestamo = clientePrestamo.getCuentas().buscarCuenta(numeroCuentaEmisor);
        }

        return cuentaPrestamo;
    }

    public static void tablaAmortizaciones(Prestamo prestamo, int numeroAnios, double interesAnual){    //Calcula la tabla de amortizaciones
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


    public static void menuImprimirCliente() throws IOException {    //Menu para imprimir los datos de un cliente y cuentas

        Cliente clienteImprimir;

        Scanner scan = new Scanner(System.in);

        if(cliente == null){
            System.out.println("***Debes darse de alta primero***");
        }else{
            System.out.println("¿Que datos quieres ver?");
            System.out.println("\t1) Datos de un cliente");
            System.out.println("\t2) Todos los datos de los clientes del banco");
            System.out.println("\t0) Salir");
            System.out.print("Introduzca tu opcion: ");
            int opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el DNI: ");
                    String dni2 = scan.next();
                    dni2 = dni2.toUpperCase();

                    clienteImprimir = listClientes.buscarCliente(dni2);

                    if (clienteImprimir == null) {
                        System.out.println("***No existe el cliente***");
                        menuPrincipal();
                    } else {
                        System.out.println("*** Enhorabuna, hemos encontrado tus datos ***");
                        System.out.println();
                        clienteImprimir.imprimirCliente();
                    }
                    break;
                case 2:
                    System.out.println();
                    listClientes.imprimirTodosClientes();
                    break;
                case 0:
                    System.out.println("Has elegido: Salir");
                    break;
                default:
                    System.out.print("***Caracter invalido***\n");
                    menuImprimirCliente();
            }
        }

    }

    public static int leerFicheroSucursal() throws IOException {    //Lee el fichero de sucursales y devuelve el numero de sucursal

        Scanner scan = new Scanner(System.in);
        File leerFichero = new File("Sucursales.txt");
        FileReader fr;
        BufferedReader br;
        String[][] datos = new String[4][2];

        fr = new FileReader(leerFichero);
        br = new BufferedReader(fr);
        String linea;
        int i = 0, option;

        while((linea = br.readLine()) != null) {
            datos[i][0] = linea.split("=")[0];
            datos[i][1] = linea.split("=")[1];
            System.out.println((i+1) + ") " + datos[i][0]);
            i++;
        }

        do{
            System.out.print("\nIntroduce el numero de la sucursal que estas: ");
            option = scan.nextInt();

            if(option < 0 || option > i){
                System.out.println("***El numero de sucursal no es valido***");
            }
        }while(option < 0 || option > i);

        codigoSucursal = Integer.parseInt(datos[option-1][1]);
        System.out.println("*** Has elegido la sucursal " + datos[option-1][0] + " ***");

        br.close();

        return codigoSucursal;
    }


    public static void escribirFichero() throws IOException {    //Escribe la matriz de las cuentas y las transferencias en el fichero

        File archivo = new File("Transferencias.txt");
        BufferedWriter bw;

        bw = new BufferedWriter(new FileWriter(archivo));

        for (int i=0; i<listCuentas.getNumCuentas(); i++){
            bw.write(listCuentas.getCuentaPos(i).getIban() + "\n");
        }

        for (int i=0; i<listCuentas.getNumCuentas(); i++){
                bw.write("\n");
                for (int j=0; j<listCuentas.getNumCuentas(); j++){
                    double saldo = listCuentas.getCuentaPos(i).saldoFichero(listCuentas.getCuentaPos(j));
                    bw.write(String.format("%10.2f", saldo));
                }
        }

        System.out.println("*** Fichero creado correctamente ***");

        bw.close();

    }

}
