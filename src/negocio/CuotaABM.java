package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.CuotaDao;
import datos.Cliente;
import datos.Cuota;
import datos.Prestamo;

public class CuotaABM {
	private CuotaDao dao = new CuotaDao();
	private PrestamoABM abm = new PrestamoABM();
	
	public Cuota traerCuota(long idCuota)throws Exception {
		Cuota c = dao.traer(idCuota);
		if (c == null) throw new Exception ("No existe la Cuota con ID: "+idCuota);
		return c;
	}
	
	public int agregar(int nroCuota, LocalDate fechaVencimiento, double saldoPendiente, double amortizacion,
			double interesCuota, double cuota, double deuda, LocalDate fechaDePago, double punitorios, Prestamo prestamo) throws Exception {
		if (abm.traerPrestamo(prestamo.getIdPrestamo()) == null) throw new Exception ("El Prestamo no existe"); 
		Cuota c = new Cuota (nroCuota, fechaVencimiento, saldoPendiente, amortizacion, interesCuota, cuota,deuda, fechaDePago, punitorios, prestamo);
		return dao.agregar(c);
	}
	
	public List<Cuota> traerCuotas(Prestamo p) throws Exception {
		List<Cuota> lista = dao.traerCuotas(p);
		if (lista == null || lista.size() == 0)
			throw new Exception("El Prestamo no tiene Cuotas.");
		return lista;
	}

	public void modificar(Cuota c) throws Exception {
		dao.actualizar(c);
	}

}
