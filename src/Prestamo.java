import java.util.Scanner;

public class Prestamo {

    public static void menuPrestamoHipotecario() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Bienvenido a el apartado de PRESTAMOS HIPOTECARIO");
        System.out.print("Introduzca el capital solicitado: ");
        float capital = scan.nextInt();
        float interes;
        // Interes anual

        do{
            System.out.print("Introduzca el interes anual (en tanto por ciento): ");
            interes = scan.nextLong();
        }while(interes < 0 || interes > 100);

        interes = interes / 100f;
        double imes = interes / 12;

        // Years

        System.out.print("Introduzca el numero de años: ");
        int yearsh = scan.nextInt();
        int meses = yearsh * 12;

        // Calculo cuota

        double cuota = capital * imes * (Math.pow((1 + imes), meses) / (Math.pow((1 + imes), meses) - 1));
        System.out.println("Mensualidad\t\tCuota\t\tInteres\t\tAbonado\t\tRestante");
        System.out.printf("\t%d\t\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n", 0, 0f, 0f, 0f, capital);
        int i = 1;
        double cv = capital;
        do {
            double ia = cv * imes;
            double ca = cuota - ia;
            cv -= ca;
            System.out.printf("\t%d\t\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n", i, cuota, ia, ca, cv);
            i++;
        } while (i != meses + 1);

    }

}
