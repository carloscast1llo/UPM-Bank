public class ListaClientes {

    private Cliente [] clientes;
    private int numClientes = 0;
    private final int MAX_CLIENTE = 20;
    Fecha diaNacimiento;

    public ListaClientes(int MAX_CLIENTE){
        this.clientes = new Cliente[MAX_CLIENTE];
        numClientes = 0;
    }

    public void addCliente(Cliente cliente){
        this.clientes[numClientes] = cliente;
        numClientes++;
    }

    public Cliente buscarCliente(String dni){
        Cliente encontrado = null;
        for(int i = 0; i < numClientes; i++){
            if (clientes[i].getDni().equals(dni)) {
                encontrado = clientes[i];
            }
        }
        return encontrado;
    }

    public boolean comprobarPersona(String dni){

        return (buscarCliente(dni) != null);
    }

    public void imprimirCliente(Cliente cliente){

        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Apellidos: " + cliente.getApellidos());
        System.out.print("Fecha de nacimiento: ");
        diaNacimiento.imprimir();
        System.out.println("\nDNI: " + cliente.getDni());
        System.out.println("Correo Electronico: " + cliente.getCorreoElectronico() + "\n");  //Mirar println si queda bien
        //Imprimir cuenta de ese cliente

    }

    public void imprimirClientes(){
        Cliente imprimirClientesAll;

        for(int i = 0; i < numClientes; i++){
            imprimirClientesAll = clientes[i];
            imprimirCliente(imprimirClientesAll);
        }
    }

}
