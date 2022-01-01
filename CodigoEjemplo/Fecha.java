public class Fecha {
    private int dia;
    private int mes;
    private int año;
	
	public Fecha(int dia, int mes, int año) {
        this.dia=dia;
		this.mes=mes;
		this.año=año;
    }
	
	public int getDia(){
		return dia;
	}
	
	public void setDia(int dia){
		this.dia = dia;
	}
	
	public int getMes(){
		return mes;
	}
	
	public void setMes(int mes){
		this.mes = mes;
	}
	
	public int getAño(){
		return año;
	}
	
	public void setAño(int año){
		this.año = año;
	}
	
	public void imprimir()
    {
        System.out.printf("%02d/%02d/%04d", dia, mes, año);
    }

	//Metodos estaticos
    public static boolean esBisiesto(int año) {
        return (año % 4 == 0 && año % 100 != 0) || año % 400 == 0;
    }
    
    public static boolean comprobarFecha(int dia, int mes, int año) {
		boolean fechaCorrecta = (año >= 1920 && año <= 2003 && mes >= 1 && mes <=12 && dia >= 1 && dia <= 31);
		if (fechaCorrecta){
			if (mes == 2)
				fechaCorrecta = dia <= 28 || (dia <= 29 && esBisiesto(año));
			else if (mes == 4 || mes == 6 || mes == 9 || mes == 11)
				fechaCorrecta = dia <= 30;
		}
        return fechaCorrecta;
    }
}