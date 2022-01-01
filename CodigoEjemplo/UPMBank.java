public class UPMBank {
    public static void main(String[] args) {
        Fecha f = null;
        int dia = 1, mes = 1, año = 2001; //Valores que deben leerse por teclado
        if (Fecha.comprobarFecha(dia, mes, año)) {
            f = new Fecha(dia, mes, año);
        }
        if (f != null)
            f.imprimir();
    }
}