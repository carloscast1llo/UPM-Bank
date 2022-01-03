public class ListaClientes {

    private Cliente [] clientes;
    private int numClientes = 0;
    private final int MAX_CLIENTE = 20;


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

    public void imprimirTodosClientes(){

        for(int i = 0; i < numClientes; i++){
            System.out.println("\t["+(i + 1)+"]");
            clientes[i].imprimirCliente();
        }
    }

}
