public class Fecha {

        private final int dia;
        private final int mes;
        private final int year;

        public Fecha(int dia, int mes, int year) {
            this.dia=dia;
            this.mes=mes;
            this.year=year;
        }


        public void imprimir()
        {
            System.out.printf("%02d/%02d/%04d", dia, mes, year);
        }

        //Metodos estaticos
        public static boolean esBisiesto(int year) {
            return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        }

        public static boolean comprobarFecha(int dia, int mes, int year) {
            boolean fechaCorrecta = (year >= 1920 && year <= 2003 && mes >= 1 && mes <=12 && dia >= 1 && dia <= 31);
            if (fechaCorrecta){
                if (mes == 2)
                    fechaCorrecta = dia <= 28 || (dia <= 29 && esBisiesto(year));
                else if (mes == 4 || mes == 6 || mes == 9 || mes == 11)
                    fechaCorrecta = dia <= 30;
            }else{
                System.out.println("***Fecha incorrecta***");
            }
            return fechaCorrecta;
        }

}
