package model.controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Concesionario;
import model.Fabricante;

public class ControladorConcesionario {

	public static List<Concesionario> getAll() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Gestion_Ventas");

		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM concesionario;", Concesionario.class);

		List<Concesionario> concesionarios = (List<Concesionario>) q.getResultList();

		for (Concesionario conce : concesionarios) {
			System.out.println("Concesionario: " + conce.getId() + " CIF: " + conce.getCif() + " Nombre: "
					+ conce.getNombre() + " Localidad: " + conce.getLocalidad());
		}

		em.close();
//		List<Concesionario> concesionarios = new ArrayList<Concesionario>();
//
//		Connection conn = null;
//		try {
//			conn = ConnectionManagerV2.getConexion();
//
//			Statement s = conn.createStatement();
//			ResultSet rs = s.executeQuery("Select * from concesionario");
//
//			while (rs.next()) {
//				Concesionario conce = new Concesionario();
//				conce.setId(rs.getInt("id"));
//				conce.setCif(rs.getString("cif"));
//				conce.setNombre(rs.getString("nombre"));
//				conce.setLocalidad(rs.getString("localidad"));
//				concesionarios.add(conce);
//			}
//
//			s.close();
//
//		} catch (SQLException | ImposibleConectarException e) {
//			throw new ErrorBBDDException(e);
//		}
		return concesionarios;
	}

	/**
	 * @throws ErrorBBDDException
	 * 
	 */

	public static Concesionario obtenerId(int id) {
		return null;
//		Connection conn = null;
//		Concesionario conce = null;
//
//		try {
//			conn = ConnectionManagerV2.getConexion();
//			
//			Statement s = conn.createStatement();
//			ResultSet rs = s.executeQuery("Select * from concesionario where id = " + id);
//
//			if (rs.next()) {
//				conce = new Concesionario();
//				conce.setId(id);
//				conce.setCif(rs.getString("cif"));
//				conce.setNombre(rs.getString("nombre"));
//				conce.setLocalidad(rs.getNString("localidad"));
//				
//			}
//			s.close();
//			
//		} catch (SQLException | ImposibleConectarException e) {			
//			throw new ErrorBBDDException(e);
//		} 
//		return conce;

	}

	/**
	 * 
	 * @param conce
	 * @throws ErrorBBDDException
	 */
	public static void almacenarNuevo(Concesionario conce) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Gestion_Ventas");

		EntityManager em = entityManagerFactory.createEntityManager();

		Concesionario conce1 = new Concesionario();
		conce1.setCif(conce.getCif());
		conce1.setNombre(conce.getNombre());

		em.getTransaction().begin();
		em.persist(conce);
		em.getTransaction().commit();

		TypedQuery<Concesionario> q = em.createQuery("SELECT f FROM Concesionario as f", Concesionario.class);

		List<Concesionario> concesionarios = q.getResultList();

		for (Concesionario concesionario : concesionarios) {
			System.out.println("Concesionario: " + concesionario.getId() + " CIF: " + concesionario.getCif() + " Nombre: "
					+ concesionario.getNombre() + " Localidad: " + concesionario.getLocalidad());
		}

		em.close();

//		Connection conn = null;
//
//		try {
//			conn = ConnectionManagerV2.getConexion();
//			
//			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO concesionario (id, cif, nombre, localidad) "
//					+ "VALUES  (?, ?, ?, ?)");
//			int registrosInsertados;
//			
//			ps.setInt(1, nextIdEnTabla(conn, "concesionario")); 
//			ps.setString(2, conce.getCif());
//			ps.setString(3, conce.getNombre());
//			ps.setString(4, conce.getLocalidad());
//			registrosInsertados = ps.executeUpdate();
//			if (registrosInsertados != 1) {
//				throw new ErrorBBDDException ("No ha sido posible la inserción del nuevo registro");
//			}
//			ps.close();
//			
//		} catch (SQLException | ImposibleConectarException e) {			
//			throw new ErrorBBDDException(e);
//		}
	}

	public static void almacenarModificacion(Concesionario conce) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Gestion_Ventas");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Concesionario> q = em.createQuery("SELECT f FROM Concesionario as f where f.cif = '1234566777J'", Concesionario.class);
		
		List<Concesionario> concesionarios = q.getResultList();
		
		em.getTransaction().begin();
		for (Concesionario concesionario : concesionarios) {
			concesionario.setNombre("Bernabelito");
			concesionario.setLocalidad("Valdepeñas");
			em.persist(concesionario);
		}
		em.getTransaction().commit();
		
		em.close();
		
//		Connection conn = null;
//		
//		try {
//			conn = ConnectionManagerV2.getConexion();
//			
//			PreparedStatement ps = (PreparedStatement) conn.
//					prepareStatement("update concesionario set cif = ?, nombre = ?, localidad = ? where id = ?");
//			int registrosInsertados;
//			
//			ps.setString(1, conce.getCif());
//			ps.setString(2, conce.getNombre());
//			ps.setString(3, conce.getLocalidad());
//			ps.setInt(4, conce.getId()); 
//
//			registrosInsertados = ps.executeUpdate();
//			if (registrosInsertados != 1) {
//				throw new ErrorBBDDException ("No ha sido posible la modificación del registro");
//			}
//			ps.close();
//			
//		} catch (SQLException | ImposibleConectarException e) {			
//			throw new ErrorBBDDException(e);
//		}

	}

	/**
	 * 
	 */

	public static void eliminarConcesionario(Concesionario conce) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Gestion_Ventas");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Concesionario> q = em.createQuery("SELECT f FROM Concesionario as f where f.cif = '1234566777J'", Concesionario.class);
		
		List<Concesionario> concesionarios = q.getResultList();
		
		em.getTransaction().begin();
		for (Concesionario concesionario : concesionarios) {
			em.remove(concesionario);
		}
		em.getTransaction().commit();
		
		
		em.close();
//
//			Connection conn = null;
//
//			try {
//				conn = ConnectionManagerV2.getConexion();
//				
//				PreparedStatement ps = (PreparedStatement) conn.
//						prepareStatement("delete from concesionario where id = ?");
//				int registrosInsertados;
//				
//				ps.setInt(1, conce.getId()); 
//
//				registrosInsertados = ps.executeUpdate();
//				if (registrosInsertados != 1) {
//					throw new ErrorBBDDException ("No ha sido posible la eliminación del registro");
//				}
//				ps.close();
//				
//			} catch (SQLException | ImposibleConectarException e) {			
//				throw new ErrorBBDDException(e);
//			}

	}

}
