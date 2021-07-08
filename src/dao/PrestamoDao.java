package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import datos.Cliente;
import datos.Prestamo;

public class PrestamoDao {
	private static Session session;
	private Transaction tx;

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}
	
	public int agregar(Prestamo objeto) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id; 
	}

	public Prestamo traer(long idPrestamo) throws HibernateException {
		Prestamo obj = null;
		try {
			iniciaOperacion();
			obj = (Prestamo) session.createQuery("from Prestamo p inner join fetch p.cliente c where p.idPrestamo=" + idPrestamo).uniqueResult();
		} finally {
			session.close();
		}
		return obj;
	}
	
	public Prestamo traerPrestamoYCuotas(long idPrestamo) {
		Prestamo objeto = null;
		try {
			iniciaOperacion();
			objeto = (Prestamo) session.createQuery("from Prestamo p where p.idPrestamo =" + idPrestamo).uniqueResult();
			Hibernate.initialize(objeto.getCuotas());
		} finally {
			session.close();
		}
		return objeto;
	}

	@SuppressWarnings("unchecked")
	public List<Prestamo> traerPrestamos(Cliente c) throws HibernateException {
		List<Prestamo> lista = null;
		try {
			iniciaOperacion();
			String hQL = "from Prestamo p inner join fetch p.cliente c where c.idCliente=" + c.getIdCliente();
			lista = session.createQuery(hQL).list();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public void actualizar(Prestamo objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}

	public void eliminar(Prestamo objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
}