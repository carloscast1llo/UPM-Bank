public class Cliente {

    private final String nombre;
    private final String apellidos;
    private final String correoElectronico;
    private final String dni;
    Fecha diaNacimiento;
    ListaCuentas cuentas;

    public Cliente(String nombre, String apellidos, Fecha fecha, String correoElectronico, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.dni = dni;
        this.cuentas = new ListaCuentas(10);
        this.diaNacimiento = fecha;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public String getDni() {
        return dni;
    }
    public ListaCuentas getCuentas() {
        return cuentas;
    }

    public static boolean validacionNombre(String nombre){
        boolean correcto = true;
        if(nombre.contains("0") || nombre.contains("1") || nombre.contains("2") || nombre.contains("3") || nombre.contains("4") || nombre.contains("5") || nombre.contains("6") || nombre.contains("7") || nombre.contains("8") || nombre.contains("9")){
            correcto = false;
            System.out.println("***Nombre incorrecto***");
        }
        return correcto;
    }
    public static boolean validacionApellidos(String apellidos){
        boolean correcto = true;
        if(apellidos.contains("0") || apellidos.contains("1") || apellidos.contains("2") || apellidos.contains("3") || apellidos.contains("4") || apellidos.contains("5") || apellidos.contains("6") || apellidos.contains("7") || apellidos.contains("8") || apellidos.contains("9")){
            correcto = false;
            System.out.println("***Apellidos incorrectos***");
        }
        return correcto;
    }
    public static boolean validacionCorreo(String correoElectronico) {
        boolean correcto = false;
        if(correoElectronico.contains("@upm.es") || correoElectronico.contains("@alumnos.upm.es") || correoElectronico.contains("@UPM.ES") || correoElectronico.contains("@ALUMNOS.UPM.ES")){
            correcto = true;
        }else{
            System.out.println("***Correo incorrecto***");
        }
        return correcto;
    }
    public static boolean validacionCorreoRepetido(String correoElectronico, ListaClientes listaClientes){
        boolean repetido = false;
        if(!listaClientes.comprobarPersonaCorreo(correoElectronico)){
            repetido = true;
        }else{
            System.out.println("***El correo ya esta en uso***");
        }

        return repetido;
    }
    public static boolean validacionDNI(int dniNum, char dniLet) {

        boolean correcto = false;
        char [] letraDNI = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int resto =(dniNum % 23);

        if(dniLet == letraDNI[resto]){
            correcto = true;
        }else{
            System.out.println("***DNI incorrecto***");
        }

        return correcto;
    }
    public static boolean validacionDniRepetido(String dni, ListaClientes listaClientes){
        boolean repetido = false;
        if(!listaClientes.comprobarPersona(dni)){
            repetido = true;
        }else{
            System.out.println("***El DNI ya esta en uso***");
        }

        return repetido;
    }

    public void imprimirCliente(){

        System.out.println("Nombre: " + nombre);
        System.out.println("Apellidos: " + apellidos);
        System.out.print("Fecha de nacimiento: ");
        diaNacimiento.imprimir();
        System.out.println("\nDNI: " + dni);
        System.out.println("Correo Electronico: " + correoElectronico);
        System.out.print("Cuentas: \n");
        cuentas.imprimirTodasCuenta2();
        System.out.println();


    }

    public void imprimirCuentaCliente(){
        cuentas.imprimirTodasCuentas();
    }

}
