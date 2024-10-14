package modeloejemplo.componentespropios;

import des.ContadoresEstadisticos;
import des.GeneradorDeReportes;

/* Subprograma que calcula las estimaciones de las medidas de performance 
 * (a partir de los Contadores Estadísticos). */

public class GeneradorDeReportesEjemplo extends GeneradorDeReportes {

	public void run(ContadoresEstadisticos contadores) {
		
		ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		
		System.out.println("------------------------------------------------------");
		System.out.println("***GENERADOR DE REPORTES *** ");
		System.out.println("------------------------------------------------------");

		System.out.println("La cantidad de solicitudes procesadas es de: " + contadoresEjemplo.getCantProcesadas());
		
		System.out.println("BENEFICIOS: $" + contadoresEjemplo.getBeneficios());
		
		System.out.println("Tiempo Promedio Clientes en Kiosco: " + contadoresEjemplo.getTiempoPromedioEnKiosco());
		
		System.out.println("Longitud Promedio de Cola: " + contadoresEjemplo.getPromedioLongitudDeCola());
		
		for (int i=0; i<contadoresEjemplo.getCantServidores();i++) {
			 
			System.out.println("Para el servidor " + i + " el tiempo de ocupación es " + contadoresEjemplo.getTiempoOcupadoServidor(i));
			
			System.out.println("Para el servidor " + i + " la tasa de atención (clientes por hora) es:  " 
			+ contadoresEjemplo.tasaAtencion(45,i));
			System.out.println("Para el servidor " + i + " el % de tiempo libre es :  % " 
					+ contadoresEjemplo.porcentajeTiempoLibrePorServidor(45,i));
			
		}
		
	}
	
	

}
