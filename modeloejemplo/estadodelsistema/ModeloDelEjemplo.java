package modeloejemplo.estadodelsistema;

import des.EstadoDelSistema;

/* Colección de variables de estado necesarias para describir  el sistema en un punto en el tiempo. */

public class ModeloDelEjemplo extends EstadoDelSistema {
	
	private ColaDeSolicitudes cola;
	private Servidor[] servidores;
	private static int cantServidores=1;
	
	public static int getCantServidores() {
		return cantServidores;
	}

	public void inicializar() {
		cola = new ColaDeSolicitudes();
	

		
		if (cantServidores == 2) {
		servidores = new Servidor[2];
	    servidores[0] = new Servidor(false); // Primer servidor
	    servidores[1] = new Servidor(false); // Segundo servidor
	    
		} else {
			
			servidores = new Servidor[1];
			servidores[0] = new Servidor(false);
		}
	}

	public void encolarSolicitud(Solicitud solicitudParaAgregar) {		
		System.out.println("\t\t-- El MODELO encola una solicitud ya que todos los servidores están ocupados.");
		cola.encolarSolicitud(solicitudParaAgregar);
	}
	
	public boolean estanServidoresOcupados() {
		
		int servidoresDisponibles = 0;

       for (Servidor servidor : servidores) {
             if (!servidor.getEstaOcupado()) {
             servidoresDisponibles++;
        }
             
           
}
       return servidoresDisponibles > 0? false : true; 

	}
	
	public int obtenerServidorLibre() {
		
		for (int i = 0; i < cantServidores; i++) {
            if (!servidores[i].getEstaOcupado()) {
            return i;
       }
            }
		return -1;
	
	}
	

	public int getLongitudColaActual(){
		return cola.getCantSolicitudesEsperando();
	}
	
	public void atenderSolicitud(Solicitud solicitudParaAgregar, int numServidor) {//+

		servidores[numServidor].pasarAOcupado(solicitudParaAgregar); 
	}

	public boolean haySolicitudesEnEspera() {
		return (cola.getCantSolicitudesEsperando()>0);
	}

	public Solicitud obtenerSolicitudPrioritaria() {
		return cola.solicitudPrioritaria();
	}

	public void actualizarServidorDisponible(int numeroServidor) { //+
		System.out.println("\t\t-- El MODELO deja al servidor disponible ya que no hay solicitudes en espera.");
		servidores[numeroServidor].setEstaOcupado(false);
	}
	
}
