package modeloejemplo.estadodelsistema;

import java.util.LinkedList;
import java.util.Queue;

public class ColaDeSolicitudes {

	private Queue<Solicitud> colaClase1;

	public ColaDeSolicitudes() {
		super();
		colaClase1 = new LinkedList<Solicitud>();
	}

	public void encolarSolicitud(Solicitud solicitudParaAgregar) {
		
		colaClase1.add(solicitudParaAgregar);
		
	}

	public int getCantSolicitudesEsperando() {
		return colaClase1.size();
	}

	public Solicitud solicitudPrioritaria() {
		Solicitud ret;
		ret = colaClase1.remove();
		return ret;
	}
	
}
