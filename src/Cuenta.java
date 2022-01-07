public class Cuenta {

    final static int CODIGO_ENTIDAD = 9010;
    private int codigoSucursal;
    private int digitoControl;
    private long numeroCuenta;
    private double saldo;
    private String iban;
    TipoCuenta.tipoCuenta tipoCuenta;
    ListaMovimientos movimiento;
    ListaPrestamos prestamos;
    ListaTransferencias transferenciasEmitidas;
    ListaTransferencias transferenciasRecibidas;

    public Cuenta(int codigoSucursal, int digitoControl, long numeroCuenta, String iban, TipoCuenta.tipoCuenta tipoCuenta){
        this.codigoSucursal = codigoSucursal;
        this.digitoControl = digitoControl;
        this.numeroCuenta = numeroCuenta;
        this.iban = iban;
        this.saldo = 0;
        this.tipoCuenta = tipoCuenta;
        this.movimiento = new ListaMovimientos();
        this.transferenciasEmitidas = new ListaTransferencias();
        this.transferenciasRecibidas = new ListaTransferencias();
        this.prestamos = new ListaPrestamos();
    }

    public double getSaldo() {
        return saldo;
    }
    public String getIban() {
        return iban;
    }
    public ListaMovimientos getMovimiento() {
        return movimiento;
    }
    public ListaTransferencias getTransferenciasEmitidas() {
        return transferenciasEmitidas;
    }
    public ListaTransferencias getTransferenciasRecibidas() {
        return transferenciasRecibidas;
    }
    public ListaPrestamos getPrestamos() {
        return prestamos;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public static long fNumeroCuenta(){

        long numeroCuenta;

        numeroCuenta = (long) (Math.random() * (9999999999L - 1000000000L) + 1000000000L);

        return numeroCuenta;
    }

    public static int fDigitoControl(long numeroCuenta, int codigoSucursal){

        String dcString, c1, c2;
        int digitoControl;

        int aux1, aux2;
        int a1 = 9, a2 = 0, a3 = 1, a4 = 0;
        int b4 = 0;
        int nC1, nC2;
        int nC1F = 0, nC2F = 0;

        int b3 = codigoSucursal % 10;
        aux1 = codigoSucursal / 10;
        int b2 = aux1 % 10;
        aux1 = aux1 / 10;
        int b1 = aux1 % 10;

        nC1 = 11 - ((6 * b4 + 3 * b3 + 7 * b2 + 9 * b1 + 10 * a4 + 5 * a3 + 8 * a2 + 4 * a1) % 11);

        if(nC1 < 10){
            nC1F = nC1;
        }else if(nC1 == 11){
            nC1F = 0;
        }else if(nC1 == 10){
            nC1F = 1;
        }

        int d1 = (int) ((numeroCuenta % 10000000000L) / 1000000000);
        int d2 = (int) ((numeroCuenta % 1000000000) / 100000000);
        int d3 = (int) ((numeroCuenta % 100000000) / 10000000);
        int d4 = (int) ((numeroCuenta % 10000000) / 1000000);
        int d5 = (int) ((numeroCuenta % 1000000) / 100000);
        int d6 = (int) ((numeroCuenta % 100000) / 10000);
        int d7 = (int) ((numeroCuenta % 10000) / 1000);
        int d8 = (int) ((numeroCuenta % 1000) / 100);
        int d9 = (int) ((numeroCuenta % 100) / 10);
        int d10 = (int) (numeroCuenta % 10);

        nC2 = 11 - ((d1 + 2 * d2 + 4 * d3 + 8 * d4 + 5 * d5 + 10 * d6 + 9 * d7 + 7 * d8 + 3 * d9 + 6 * d10) % 11);

        if(nC2 < 10){
            nC2F = nC2;
        }else if(nC1 == 11){
            nC2F = 0;
        }else if(nC2 == 10){
            nC2F = 1;
        }

        c1 = String.valueOf(nC1F);
        c2 = String.valueOf(nC2F);
        dcString = c1 + c2;

        digitoControl = Integer.parseInt(dcString);

        return digitoControl;
    }

    public static String fIban(long numeroCuenta, int digitoControl, int codigoSucursal){

        String iban;

        String ceString = String.valueOf(CODIGO_ENTIDAD);
        String dcString = String.valueOf(digitoControl);
        String ncString = String.valueOf(numeroCuenta);
        String csString = String.valueOf(codigoSucursal);

        iban = ceString + "0" + csString + dcString + ncString;

        return iban;
    }

    public void imprimirCuenta(){
        System.out.printf("Tipo cuenta: %s --> IBAN: [%d] --> Saldo: %.2d€",tipoCuenta, iban, saldo);
    }

    public void imprimirListaTransacciones(){

        System.out.printf("Tipo cuenta: %s --> IBAN: [%d] --> Saldo: %.2d€",tipoCuenta, iban, saldo);

        System.out.println("\t\tTransacciones realizadas: ");

        System.out.println("\t\t\t~ Movimientos ~");
        movimiento.imprimirTodosMovimientos();

        System.out.println("\t\t\t~ Transferencias ~");
        System.out.println("\t\t\t\tTansferencias emitidas: ");
        transferenciasEmitidas.imprimirTransferenciaEmitidas();
        System.out.println("\t\t\t\tTransferencias recibidas: ");
        transferenciasRecibidas.imprimirTransferenciaRecibidas();

        System.out.println("\t\t\t~ Prestamos ~");
        prestamos.imprimirTodosPrestamos();

    }

    public double saldoFichero(Cuenta cuenta){

        double saldo = 0;

        for(int i = 0;i<transferenciasEmitidas.getNumTransferencias();i++){
            if(transferenciasEmitidas.getTransferenciasPos(i).getCuentaReceptor().equals(cuenta.getIban())){
                saldo += transferenciasEmitidas.getTransferenciasPos(i).getImporte();
            }
        }

        return saldo;
    }
}
