package test;

import java.time.LocalDate;

import datos.Prestamo;
import negocio.CuotaABM;
import negocio.PrestamoABM;

public class TestAgregarCuotaAlPrestamo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//HACERLO ASI, POR SEPARADO DEL PRESTAMO, ESTA MAL. HAY QUE AGREGAR TODOO JUNTO
		
		CuotaABM cuotaABM = new CuotaABM();
		PrestamoABM prestamoABM = new PrestamoABM();
		
		long idPrestamo = 21;
		long idCuota=0;
		
	
		try {
			//CALCULO DE LA 1° CUOTA
			int nroCuota = 1;
			LocalDate fechaVencimiento = LocalDate.of(2022, 2, 10);
			double saldoPendiente = 10000;
			double interesCuota = saldoPendiente;
			double amortizacion = (saldoPendiente*interesCuota)/(1+interesCuota)-1;
			double cuota = amortizacion + interesCuota;
			double deuda = saldoPendiente - amortizacion;
			boolean cancelada = false;
			LocalDate fechaDePago = LocalDate.now();
			double punitorios =0;
			
			
			Prestamo p = prestamoABM.traerPrestamo(idPrestamo);
			cuotaABM.agregar(nroCuota,fechaVencimiento,saldoPendiente, amortizacion, interesCuota,cuota,deuda,fechaDePago,punitorios, p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
