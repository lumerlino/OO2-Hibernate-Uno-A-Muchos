package negocio;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Funciones {
	
	public static int traerAnio(LocalDate fechaDeNacimiento) {
		return fechaDeNacimiento.getYear();
	}

	public static int traerMes(LocalDate fechaDeNacimiento) {
		return fechaDeNacimiento.getMonthValue();
	}

	public static int traerDia(LocalDate fechaDeNacimiento) {
		return fechaDeNacimiento.getDayOfMonth();
	}

	public static boolean esBisiesto(int anio) {
		boolean verdad = false;
		if ((anio % 4) == 0) {
			if (((anio % 100) != 0) && ((anio % 400) != 0)) {
				verdad = true;
			}
		}
		return verdad;
	}

	public static int traerCantDiasDeUnMes(int anio, int mes, int dia) {
		int a = 0;
		switch (mes) {
		case 1:
			a = 31;
			break;
		case 2:
			if (esBisiesto(anio)) {
				a = 29;
			} else {
				a = 28;
			}
			break;
		case 3:
			a = 31;
			break;
		case 4:
			a = 30;
			break;
		case 5:
			a = 31;
			break;
		case 6:
			a = 30;
			break;
		case 7:
			a = 31;
			break;
		case 8:
			a = 31;
			break;
		case 9:
			a = 30;
			break;
		case 10:
			a = 31;
			break;
		case 11:
			a = 30;
			break;
		case 12:
			a = 31;
			break;
		}
		return a;
	}

	public static boolean esFechaValida(int anio, int mes, int dia) {
		boolean fecha = false;
		if ((mes >= 1 && mes <= 12) && (dia >= 1 && dia <= traerCantDiasDeUnMes(anio, mes, dia))) {
			fecha = true;
		}
		return fecha;
	}

	public static LocalDate traerFecha() {
		return LocalDate.now();
	}

	public static String traerFechaCorta(LocalDate fechaDeNacimiento) {
		int d = traerDia(fechaDeNacimiento), m = traerMes(fechaDeNacimiento), a = traerAnio(fechaDeNacimiento);
		String fecha = "";
		if (d < 10)
			fecha += "0" + d + "/";
		else
			fecha += d + "/";
		if (m < 10)
			fecha += "0" + m + "/";
		else
			fecha += m + "/";
		fecha += a;
		return fecha;
	}

	public static LocalDate traerFechaProximo(LocalDate f, int cantDias) {
		LocalDate fecha = f.plusDays(cantDias);
		return fecha;
	}

	public static boolean esDiaHabil(LocalDate fecha) {
        // Consideramos hábil de lunes a viernes.
        boolean resultado = false;
        if (fecha.getDayOfWeek() != DayOfWeek.SUNDAY && fecha.getDayOfWeek() != DayOfWeek.SATURDAY) {
            System.out.println("Es dia habil");
            resultado = true;
        } else {

            System.out.println("No es dia habil");

        }
        return resultado;

    }

	public static String traerDiaDeLaSemana(GregorianCalendar f) {

		String dias[] = { "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
		return dias[f.get(Calendar.DAY_OF_WEEK) - 1];
	}

	public static String traerMesLetras(LocalDate f) {

		String meses[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
				"Octubre", "Noviembre", "Diciembre" };
		return meses[traerMes(f)];
	}

	public static String traerFechaLarga(LocalDate f1) {
		String a = f1.getDayOfMonth() + " de " + traerMesLetras(f1) + " de " + f1.getYear();
		return a;
	}

	public static GregorianCalendar traerFecha(String fecha) {

		String dia = fecha.substring(0, 2);
		String mes = fecha.substring(3, 5);
		String año = fecha.substring(6, 10);

		int a = Integer.valueOf(dia);
		int b = Integer.valueOf(mes);
		int c = Integer.valueOf(año);

		return new GregorianCalendar(c, b, a);
	}

	public static double aproximar2Decimal(double valor) {

		BigDecimal big = new BigDecimal(valor);
		big = big.setScale(2, RoundingMode.HALF_UP);

		double valorAproximar2 = big.doubleValue();

		return valorAproximar2;
	}

	public static boolean esNumero(char c) {
		boolean esNumero = false;

		if (c >= '0' && c <= '9') {
			esNumero = true;
		}
		return esNumero;

	}
	       
    public static boolean esCaracter (char c) throws Exception{
   	 boolean esCaracter = false;
   	 
   	 String diccionario = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVXYZ";
   	 
   	 String cString = Character.toString(c);
   	 
   	 
   	 for (int i=0;i<=diccionario.length()-1; i++){
   		 if ((diccionario.substring(i,i+1).equals(cString))){
   			esCaracter = true;}
    	 }
   	 
   	 
   	 if (esCaracter==false){
            throw new Exception("¡¡¡¡ No es un caracter !!!!");
   	 }
   

   	return esCaracter;
   		
   	
    }
     
    public static boolean esCadenaDeNumeros (String s) throws Exception{
    	
    	boolean esCadenaDeNumeros = false;
    	
    	int dimensionS = s.length();
    	
    	for (int i=0; i<=dimensionS-1;i++){
    		
    		String letraDeStringEnPosicionI = s.substring(i,i+1);
    		char letraChar = letraDeStringEnPosicionI.charAt(0);
    		
    		if (Funciones.esNumero(letraChar)) {
    			esCadenaDeNumeros = true;
    		}
    	}
    	return esCadenaDeNumeros;
    }
 
    public static boolean esCadenaDeCaracteres (String s) throws Exception{
    	
    	boolean esCadenaDeCaracteres = false;
    	
    	int dimensionS = s.length();
    	
    	for (int i=0; i<=dimensionS-1;i++){
    		
    		String letraDeStringEnPosicionI = s.substring(i,i+1);
    		char letraChar = letraDeStringEnPosicionI.charAt(0);
    		
    		if (Funciones.esCaracter(letraChar)) {
    			esCadenaDeCaracteres = true;
    		}
    	}
    	return esCadenaDeCaracteres;
    }
}
