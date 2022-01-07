public class ListaClientes {

    private int numClientes;
    private static final int MAX_CLIENTE = 20;
    private final Cliente [] clientes;


    public ListaClientes(int MAX_CLIENTE){
        this.clientes = new Cliente[MAX_CLIENTE];
        numClientes = 0;
    }

    public void addCliente(Cliente cliente){
        if(numClientes >= MAX_CLIENTE){
            System.out.println("No se pueden añadir más clientes");
        }else{
            this.clientes[numClientes] = cliente;
            numClientes++;
        }

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

    public Cliente buscarClienteCorreo(String correoElectronico){
        Cliente encontrado = null;

        for(int i = 0; i < numClientes; i++){
            if (clientes[i].getCorreoElectronico().equals(correoElectronico)) {
                encontrado = clientes[i];
            }
        }

        return encontrado;
    }

    public boolean comprobarPersona(String dni){

        return (buscarCliente(dni) != null);
    }

    public boolean comprobarPersonaCorreo(String correoElectronico){

        return (buscarClienteCorreo(correoElectronico) != null);
    }

    public void imprimirTodosClientes(){
        for(int i = 0; i < numClientes; i++){
            System.out.println("["+(i + 1)+"]");
            clientes[i].imprimirCliente();
        }

    }

}
