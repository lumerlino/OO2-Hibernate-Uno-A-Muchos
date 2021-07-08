package test;

import datos.Prestamo;
import negocio.CuotaABM;
import negocio.PrestamoABM;

public class TestTraerPrestamoYCuotas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PrestamoABM prestamoABM = new PrestamoABM();
		CuotaABM cuotaABM = new CuotaABM();
		
		long idPrestamo = 4;
		
		
		
		// METODO 1. NO FUNCIONA
		/*try {
			Prestamo p = prestamoABM.traerPrestamo(idPrestamo);
			System.out.println(p.getCuotas());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		// METODO 2. 
		try {
			Prestamo p = prestamoABM.traerPrestamo(idPrestamo);
			System.out.println(cuotaABM.traerCuotas(p));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
