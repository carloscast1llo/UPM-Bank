public class ListaCuentas {

    private int numCuentas = 0;
    private int numClientes = 0;
    private final int MAX_CUENTAS = 200;
    private final int MAX_CLIENTES = 20;
    private final Cuenta [] listaCuentas;

    public ListaCuentas(Cuenta[] listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public Cuenta getCuenta(String iban){
        int i = 0;
        Cuenta cuent = null;

        while(i < listaCuentas.length && i < numCuentas){
            if(listaCuentas[i].getIban().equals(iban)){
                cuent = listaCuentas[i];
            }
            i++;
        }

        return cuent;
    }









}
