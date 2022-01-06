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

        int d1 = (int) Math.floor(Math.random() * (10));
        int d2 = (int) Math.floor(Math.random() * (10));
        int d3 = (int) Math.floor(Math.random() * (10));
        int d4 = (int) Math.floor(Math.random() * (10));
        int d5 = (int) Math.floor(Math.random() * (10));
        int d6 = (int) Math.floor(Math.random() * (10));
        int d7 = (int) Math.floor(Math.random() * (10));
        int d8 = (int) Math.floor(Math.random() * (10));
        int d9 = (int) Math.floor(Math.random() * (10));
        int d10 = (int) Math.floor(Math.random() * (10));

        String numd1 = String.valueOf(d1);
        String numd2 = String.valueOf(d2);
        String numd3 = String.valueOf(d3);
        String numd4 = String.valueOf(d4);
        String numd5 = String.valueOf(d5);
        String numd6 = String.valueOf(d6);
        String numd7 = String.valueOf(d7);
        String numd8 = String.valueOf(d8);
        String numd9 = String.valueOf(d9);
        String numd10 = String.valueOf(d10);

        String ncString = numd1 + numd2 + numd3 + numd4 + numd5 + numd6 + numd7 + numd8 + numd9 + numd10;

        numeroCuenta = Long.parseLong(ncString);

        return numeroCuenta;
    }

    public static int fDigitoControl(long numeroCuenta, int codigoSucursal){

        String dcString, c1, c2;
        int digitoControl;

        int aux1, aux2;
        int a1 = 9, a2 = 0, a3 = 1, a4 = 0;
        int b4 = 0;
        int nC1, nC2;
        int nC1F = 0;

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

        int d10 = (int) numeroCuenta % 10;
        aux2 = (int) numeroCuenta / 10;
        int d9 = aux2 % 10;
        aux2 = aux2 / 10;
        int d8 = aux2 % 10;
        aux2 = aux2 / 10;
        int d7 = aux2 % 10;
        aux2 = aux2 / 10;
        int d6 = aux2 % 10;
        aux2 = aux2 / 10;
        int d5 = aux2 % 10;
        aux2 = aux2 / 10;
        int d4 = aux2 % 10;
        aux2 = aux2 / 10;
        int d3 = aux2 % 10;
        aux2 = aux2 / 10;
        int d2 = aux2 % 10;
        aux2 = aux2 / 10;
        int d1 = aux2 % 10;
        aux2 = aux2 / 10;

        nC2 = 11 - ((d1 + 2 * d2 + 4 * d3 + 8 * d4 + 5 * d5 + 10 * d6 + 9 * d7 + 7 * d8 + 3 * d9 + 6 * d10) % 11);

        c1 = String.valueOf(nC1F);
        c2 = String.valueOf(nC2);
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
        System.out.println(tipoCuenta + " -- [" + iban + "] -- " + saldo + "€");
    }

}
