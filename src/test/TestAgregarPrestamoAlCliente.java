package test;

import java.time.LocalDate;

import datos.Cliente;
import datos.Prestamo;
import negocio.ClienteABM;
import negocio.PrestamoABM;

public class TestAgregarPrestamoAlCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClienteABM clienteABM = new ClienteABM();
		PrestamoABM prestamoABM = new PrestamoABM();


		try {			
			long idCliente = 2;
			long idPrestamo = 0;
			Cliente c = clienteABM.traer(idCliente);
			idPrestamo=prestamoABM.agregar(LocalDate.of(2021, 4, 10), 10000, 10, 10, c);
			Prestamo p = prestamoABM.traerPrestamoYCuotas(idPrestamo);
			System.out.println(p + ", " + p.getCuotas());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
