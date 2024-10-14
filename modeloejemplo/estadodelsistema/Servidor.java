package modeloejemplo.estadodelsistema;


public class Servidor {

	Boolean estaOcupado; /* false = libre / true = ocupado */
	Solicitud solicitudEnProcesamiento; /* Solicitud que está siendo retenida en el servidor. */
	double tiempoOcupado;

	public Servidor(boolean estado) {
		super();
		estaOcupado = estado;
		solicitudEnProcesamiento = null;
		tiempoOcupado = 0;
	}

	public boolean getEstaOcupado() {
		return estaOcupado;
	}

	public void pasarAOcupado(Solicitud solicitud) {
		estaOcupado = true;
		solicitudEnProcesamiento = solicitud;
		tiempoOcupado =+ solicitudEnProcesamiento.getTiempoServicioTotal();
	}

	public void setEstaOcupado(boolean estado) {
		estaOcupado = estado;
	}
	
}
