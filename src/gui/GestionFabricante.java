package gui;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Fabricante;
import model.controladores.ControladorFabricante;

public class GestionFabricante {

	/**
	 * 
	 */
	public static void menuGestion() {

		int opcionElegida = -1;
		do {

			System.out.println("\n\t\t\tGESTIÓN DE FABRICANTES");

			System.out.println("\n\t1.- Listado de fabricantes.");
			System.out.println("\t2.- Alta de fabricante.");
			System.out.println("\t3.- Modificación de fabricante.");
			System.out.println("\t4.- Baja de fabricante.");
			System.out.println("\t0.- Salir");
			System.out.println("\n\tElija una opción: ");

			opcionElegida = Utils.getIntConsola(0, 4);

			switch (opcionElegida) {
			case 0:
				break;
			case 1:
				listadoFabricante(true);
				break;
			case 2:
				alta();
				break;
			case 3:
				modificacionFabricante();
				break;
			case 4:
				baja();
				break;
			}

		} while (opcionElegida != 0);
	}

	/**
	 * 
	 * @throws ErrorBBDDException
	 */
	public static void listadoFabricante(boolean pausafinal) {
		List<Fabricante> fabricantes = ControladorFabricante.getAll();
		System.out.println("\n\tListado de fabricantes: \n");
//		for (Fabricante fab : fabricantes) {
//			System.out.println("\t" + fab.toString());
//		}
		if (pausafinal) {
			System.out.println("\n\tPulse 'Intro' tecla para continuar");
			Utils.pausa();
		}

	}

	/**
	 * 
	 */

	private static void alta() {
		System.out.println("\n\tAlta de fabricante\n");
		
		Scanner sc = new Scanner(System.in);

		Fabricante fab = new Fabricante();
		System.out.print("\nIntroduzca 'CIF' del fabricante: ");
		fab.setCif(sc.nextLine());
		System.out.print("\nIntroduzca 'Nombre' del fabricante: ");
		fab.setNombre(sc.nextLine());

		ControladorFabricante.almacenar(fab);

		System.out.println("\n\tInsertado correctamente!. Pulse 'Intro' para continuar");
		
	}

	/**
	 * 
	 * @throws ErrorBBDDException
	 */
	private static void modificacionFabricante() {
		System.out.println("\n\tModificación de fabricante\n");
		Fabricante fab = new Fabricante();
		ControladorFabricante.almacenarModificado(fab);
		System.out.println("\n\tEL fabricante se ha modificado correctamente");
		//Fabricante fab = seleccionPorUsuario();
		
//		if (fab != null) {		
//			System.out.print("\nIntroduzca 'CIF' del fabricante ('Intro' para no modificar): ");
//			String str = Utils.getStringConsola();
//			if (!str.equals("")) 
//				fab.setCif(str);
//			System.out.print("\nIntroduzca 'Nombre' del fabricante  ('Intro' para no modificar): ");
//			str = Utils.getStringConsola();
//			if (!str.equals("")) 
//				fab.setNombre(str);
//		
//			ControladorFabricante.almacenar(fab);  
//
//			System.out.println("\n\tModificado correctamente!. Pulse 'Intro' para continuar");
//			Utils.pausa();
		
	}

	/**
	 * 
	 * @throws ErrorBBDDException
	 */
	private static void baja() {
		System.out.println("\n\tEliminación del fabricante\n");
		
		Fabricante fab = new Fabricante();
		ControladorFabricante.eliminar(fab);
		System.out.println("\n\tEl fabricante ha sido eliminado correctamente");
		
		//Fabricante fab = seleccionPorUsuario();
		
//		if (fab != null) {		
//			System.out.print("\n¿Realmente desea eliminar el registro? (S/N): ");
//			String str = Utils.getStringConsola();
//			if (str.equalsIgnoreCase("S")) { 		 
//				ControladorFabricante.eliminar(fab);  
//				System.out.println("\n\tEliminado correctamente!. Pulse 'Intro' para continuar");
//				Utils.pausa();
//			}
//		}
	}

	/**
	 * 
	 * @return
	 * @throws ErrorBBDDException
	 */
	private static Fabricante seleccionPorUsuario () {
		Fabricante fab = null;
		int id = -2;
		do {
			System.out.println("\n\tIntroduzca ID del fabricante ('-1' - ver listado, '0' - salir): ");
			id = Utils.getIntConsola(-1);
			if (id == -1) {
				listadoFabricante(false);
			}
			else {
				if (id != 0) {
					fab = ControladorFabricante.get(id);
					if (fab == null) {
						System.out.println("\tError. Ha especificado un ID inválido.");
					}
				}
			}
		} while (fab == null && id != 0);
		return fab;
	}
}
