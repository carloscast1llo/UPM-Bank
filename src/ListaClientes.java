public class ListaClientes {

    private int numClientes;
    private static final int MAX_CLIENTES = 20;
    private Cliente [] clientes;

    public ListaClientes(int MAX_CLIENTES){
        this.clientes = new Cliente[MAX_CLIENTES];
        numClientes = 0;
    }

    public void addCliente(Cliente cliente){    //Añade un cliente a la lista clientes
        if(numClientes >= MAX_CLIENTES){
            System.out.println("***No se pueden añadir más clientes***");
        }else{
            this.clientes[numClientes] = cliente;
            numClientes++;
        }

    }

    public Cliente buscarCliente(String dni){   //Busca un cliente por su DNI
        Cliente encontrado = null;

        for(int i = 0; i < numClientes; i++){
            if (clientes[i].getDni().equals(dni)) {
                encontrado = clientes[i];
            }
        }

        return encontrado;
    }

    public Cliente buscarClienteCorreo(String correoElectronico){   //Busca un cliente por su correo electrónico
        Cliente encontrado = null;

        for(int i = 0; i < numClientes; i++){
            if (clientes[i].getCorreoElectronico().equals(correoElectronico)) {
                encontrado = clientes[i];
            }
        }

        return encontrado;
    }

    public boolean comprobarPersona(String dni){    //Comprueba si un cliente existe

        return (buscarCliente(dni) != null);
    }

    public boolean comprobarPersonaCorreo(String correoElectronico){   //Comprueba si un cliente existe

        return (buscarClienteCorreo(correoElectronico) != null);
    }

    public void imprimirTodosClientes(){    //Imprime todos los clientes
        for(int i = 0; i < numClientes; i++){
            System.out.println("["+(i + 1)+"]");
            clientes[i].imprimirCliente();
        }

    }

}
