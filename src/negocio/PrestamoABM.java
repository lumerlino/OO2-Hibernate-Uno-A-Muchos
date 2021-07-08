package negocio;

import dao.PrestamoDao;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datos.Cliente;
import datos.Cuota;
import datos.Prestamo;

public class PrestamoABM {
	private PrestamoDao dao = new PrestamoDao();
	ClienteABM clienteABM = new ClienteABM();

	public Prestamo traerPrestamo(long idPrestamo) throws Exception {
		Prestamo p = dao.traer(idPrestamo);
		if (p == null)
			throw new Exception("No existe el Prestamo");
		return p;
	}

	public int agregar(LocalDate fecha, double monto, double interes, int cantCuotas, Cliente cliente) throws Exception {
		if (clienteABM.traer(cliente.getIdCliente()) == null) throw new Exception("El cliente No existe");

		Prestamo p = new Prestamo(fecha, monto, interes, cantCuotas, cliente);
		
		//CALCULO DE CUOTAS
        Set<Cuota> cuotas = new HashSet<Cuota>();  // SE CREA LA LISTA DE CUOTAS QUE SE LE VA A ASIGNAR AL PRESTAMO

		double saldoPendiente = monto, amortizacion, interesCuota, valorCuota, deuda, punitorios;
		LocalDate fechaVencimiento = fecha.plusMonths(1), fechaDePago = LocalDate.now();
		
		for (int i = 0; i < p.getCantCuotas(); i++) {
        	
        	//CALCULO PARA LA PRIMER CUOTA
        	if (i == 0) {
        		amortizacion = (saldoPendiente * interes) / (Math.pow((1+interes), p.getCantCuotas()) - 1);
        		interesCuota = saldoPendiente * interes;
        		valorCuota = amortizacion + interesCuota;
        		deuda = saldoPendiente - amortizacion;
        		saldoPendiente = deuda;
        		
        		cuotas.add(new Cuota((i+1),fechaVencimiento,saldoPendiente,amortizacion, interesCuota, valorCuota, deuda, null, 0.0, p));
        	}
        	//CALCULO PARA LAS CUOTAS SIGUIENTES
        	else {
        		amortizacion = (saldoPendiente * interes) / (Math.pow((1+interes), p.getCantCuotas()-i) - 1);
        		interesCuota = saldoPendiente * interes;
        		valorCuota = amortizacion + interesCuota;
        		deuda = saldoPendiente - amortizacion;
        		saldoPendiente = deuda;
        		
        		cuotas.add(new Cuota(i+1, fechaVencimiento.plusMonths(i), saldoPendiente, amortizacion, interesCuota, valorCuota, deuda, null, 0.0,p));
        	}
        }
        p.setCuotas(cuotas);
		return dao.agregar(p);
	}

	public List<Prestamo> traerPrestamos(Cliente c) throws Exception {
		List<Prestamo> lista = dao.traerPrestamos(c);
		if (lista == null || lista.size() == 0)
			throw new Exception("El Cliente no tiene prestamos.");
		return lista;
	}
	
	public Prestamo traerPrestamoYCuotas(long idPrestamo) throws Exception{
		if (dao.traer(idPrestamo) == null)	throw new Exception("No existe el Prestamo");
		return dao.traerPrestamoYCuotas(idPrestamo);
	}

	public void modificar(Prestamo p) throws Exception {
		dao.actualizar(p);
	}
}