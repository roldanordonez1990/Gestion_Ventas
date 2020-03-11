package model.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Fabricante;

public class ControladorFabricante {

	public static List<Fabricante> getAll() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Gestion_Ventas");

		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM fabricante;", Fabricante.class);

		List<Fabricante> fabricantes = (List<Fabricante>) q.getResultList();

		for (Fabricante fab : fabricantes) {
			System.out.println("Fabricante: " + fab.getId() + " CIF: " + fab.getCif() + " Nombre: " + fab.getNombre());
		}

		em.close();
		
		//Repositoriooooooooooooooooooo
		
//				
//		Connection conn = null;
//
//		try {
//			conn = ConnectionManagerV2.getConexion();
//			
//			Statement s = conn.createStatement();
//			ResultSet rs = s.executeQuery("Select * from fabricante");
//
//			while (rs.next()) {
//				Fabricante fab = new Fabricante();
//				fab.setId(rs.getInt("id"));
//				fab.setCif(rs.getString("cif"));
//				fab.setNombre(rs.getString("nombre"));
//				fabricantes.add(fab);
//			}
//			
//			s.close();
//			
//		} catch (SQLException | ImposibleConectarException e) {			
//			throw new ErrorBBDDException(e);
//		} 

		return fabricantes;
	}

	/**
	 * 
	 * @param actors
	 * @throws ErrorBBDDException
	 */
	public static void almacenar(Fabricante fab) {
//		if (get(fabricante.getId()) != null) { // Solo modificar
//			almacenarModificado(fabricante);
//		}
//		else { // Crear nuevo objeto en la BBDD
//			almacenarNuevo(fabricante);
//		}
		almacenarNuevo(fab);
	}

	public static void almacenarNuevo(Fabricante fab) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Gestion_Ventas");

		EntityManager em = entityManagerFactory.createEntityManager();

		Fabricante fab1 = new Fabricante();
		fab1.setCif(fab.getCif());
		fab1.setNombre(fab.getNombre());

		em.getTransaction().begin();
		em.persist(fab);
		em.getTransaction().commit();

		TypedQuery<Fabricante> q = em.createQuery("SELECT f FROM Fabricante as f", Fabricante.class);

		List<Fabricante> fabricantes = q.getResultList();

		for (Fabricante fabricante : fabricantes) {
			System.out.println("Fabricante: " + fabricante.getId() + " CIF: " + fabricante.getCif() + " Nombre: "
					+ fabricante.getNombre());
		}

		em.close();

	}

	/**
	 * 
	 * @param fabricante
	 * @return
	 * @throws ErrorBBDDException
	 */
	public static Fabricante get(int id) {
		// Connection conn = null;
		Fabricante fab = null;
//
//		try {
//			conn = ConnectionManagerV2.getConexion();
//			
//			Statement s = conn.createStatement();
//			ResultSet rs = s.executeQuery("Select * from fabricante where id = " + id);
//
//			if (rs.next()) {
//				fab = new Fabricante();
//				fab.setId(id);
//				fab.setCif(rs.getString("cif"));
//				fab.setNombre(rs.getString("nombre"));
//			}
//			s.close();
//			
//		} catch (SQLException | ImposibleConectarException e) {			
//			throw new ErrorBBDDException(e);
//		} 
		return fab;
	}

	/**
	 * 
	 * @param actors
	 * @throws ErrorBBDDException
	 */
//	private static void almacenarNuevo(Fabricante fab) {

//		Connection conn = null;
//
//		try {
//			conn = ConnectionManagerV2.getConexion();
//			
//			PreparedStatement ps = (PreparedStatement) conn.
//					prepareStatement(
//					"INSERT INTO fabricante (id, cif, nombre) VALUES  (?, ?, ?)");
//			int registrosInsertados;
//			
//			ps.setInt(1, nextIdEnTabla(conn, "fabricante")); 
//			ps.setString(2, fab.getCif());
//			ps.setString(3, fab.getNombre());
//
//			registrosInsertados = ps.executeUpdate();
//			if (registrosInsertados != 1) {
//				throw new ErrorBBDDException ("No ha sido posible la inserción del nuevo registro");
//			}
//			ps.close();
//			
//		} catch (SQLException | ImposibleConectarException e) {			
//			throw new ErrorBBDDException(e);
//		}

//	}

	/**
	 * 
	 * @param actors
	 * @throws ErrorBBDDException
	 */
	public static void almacenarModificado(Fabricante fab) {

//		Connection conn = null;
//
//		try {
//			conn = ConnectionManagerV2.getConexion();
//			
//			PreparedStatement ps = (PreparedStatement) conn.
//					prepareStatement(
//					"update fabricante set cif = ?, nombre = ? where id = ?");
//			int registrosInsertados;
//			
//			ps.setString(1, fab.getCif());
//			ps.setString(2, fab.getNombre());
//			ps.setInt(3, fab.getId()); 
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

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Gestion_Ventas");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Fabricante> q = em.createQuery("SELECT f FROM Fabricante as f where f.cif = '12345666U'", Fabricante.class);
		
		List<Fabricante> fabricantes = q.getResultList();
		
		em.getTransaction().begin();
		for (Fabricante fabricante : fabricantes) {
			fabricante.setNombre("ModificadoPorMi");
			em.persist(fabricante);
		}
		em.getTransaction().commit();
		
		em.close();
	}

	/**
	 * 
	 * @param actors
	 * @throws ErrorBBDDException
	 */
	public static void eliminar(Fabricante fab) {

//		Connection conn = null;
//
//		try {
//			conn = ConnectionManagerV2.getConexion();
//			
//			PreparedStatement ps = (PreparedStatement) conn.
//					prepareStatement(
//					"delete from fabricante where id = ?");
//			int registrosInsertados;
//			
//			ps.setInt(1, fab.getId()); 
//
//			registrosInsertados = ps.executeUpdate();
//			if (registrosInsertados != 1) {
//				throw new ErrorBBDDException ("No ha sido posible la eliminación del registro");
//			}
//			ps.close();
//			
//		} catch (SQLException | ImposibleConectarException e) {			
//			throw new ErrorBBDDException(e);
//		}
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Gestion_Ventas");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Fabricante> q = em.createQuery("SELECT f FROM Fabricante as f where f.nombre = 'Arturito'", Fabricante.class);
		
		List<Fabricante> fabricantes = q.getResultList();
		
		em.getTransaction().begin();
		for (Fabricante fabEnLista : fabricantes) {
			em.remove(fabEnLista);
		}
		em.getTransaction().commit();
		
		
		em.close();

	}

	public ControladorFabricante() {

	}

}
