package test;

import java.time.LocalDate;

import datos.Cliente;
import negocio.ClienteABM;
import negocio.PrestamoABM;

public class TestAgregarPrestamoConCuotas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClienteABM clienteABM = new ClienteABM();
		PrestamoABM prestamoABM = new PrestamoABM();
		
		long idCliente = 6;
		long idPrestamo = 0;
		

		try {
			Cliente c = clienteABM.traer(idCliente);
			idPrestamo = prestamoABM.agregar(LocalDate.of(2021, 5, 10), 100000, 10, 10, c);
			System.out.println(prestamoABM.traerPrestamo(idPrestamo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
