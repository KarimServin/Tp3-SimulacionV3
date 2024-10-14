package modeloejemplo.eventos;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.ListaDeEventos;
import modeloejemplo.componentespropios.ContadoresEstadisticosEjemplo;
import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;
import modeloejemplo.estadodelsistema.ModeloDelEjemplo;
import modeloejemplo.estadodelsistema.Solicitud;
import modeloejemplo.estadodelsistema.TipoServicio;

public class EventoTerminaProcesamiento extends Evento {

	
	private int numeroServidorQueQuedaLibre;
	Solicitud solicitud;
	public EventoTerminaProcesamiento(double tiempoDeOcurrencia, int servidorALibre,Solicitud unaSolicitud) {
		
		super(tiempoDeOcurrencia);
		numeroServidorQueQuedaLibre = servidorALibre; 
		solicitud = unaSolicitud;
	}

	@Override
	public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
			LibreriaDeRutinasEjemplo libreria) {
		
		
		ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		
		
		
		contadoresEjemplo.actualizarCantProcesadas();
		contadoresEjemplo.agregarTiempoDeOcupacion(solicitud.getTiempoServicioTotal(), numeroServidorQueQuedaLibre);
		contadoresEjemplo.agregarBeneficios(solicitud.getBeneficioSolicitud());
		contadoresEjemplo.agregarTiempoEnKiosco(getTiempoDeOcurrencia()-solicitud.getTiempoLlegada());
		contadoresEjemplo.sumarClientePorServidor(numeroServidorQueQuedaLibre);

		
		ModeloDelEjemplo modeloActual = (ModeloDelEjemplo) modelo;
		
		
		
		if(modeloActual.haySolicitudesEnEspera()) {
			Solicitud solicitudAProcesar = modeloActual.obtenerSolicitudPrioritaria();
			
			int valorParametro = (solicitudAProcesar.getTipoServicio() == TipoServicio.BEBIDAS? 1:2);
			double duracionDelProcesamiento = libreria.tiempoDeProcesamiento(valorParametro);
			
			solicitudAProcesar.setTiempoServicioBase(duracionDelProcesamiento);
			double tiempoAdicional = calcularTiempoAdicional(valorParametro, solicitudAProcesar.getCantArticulos(),duracionDelProcesamiento); 
			solicitudAProcesar.setTiempoServicioAdicional(tiempoAdicional);
			double tiempoTotal = duracionDelProcesamiento + tiempoAdicional;
			solicitudAProcesar.setTiempoServicioTotal(tiempoTotal);
			modeloActual.atenderSolicitud(solicitudAProcesar,numeroServidorQueQuedaLibre);
			
			
			EventoTerminaProcesamiento nuevoEvento = new EventoTerminaProcesamiento(tiempoTotal,numeroServidorQueQuedaLibre,solicitudAProcesar); //(Que se estaria desocupando mejor dicho, pero en realidad entra otra)
			eventos.agregar(nuevoEvento);
			
			//contadoresEjemplo.agregarTiempoDeOcupacion(tiempoTotal,numeroServidorQueQuedaLibre);
		}
		else {
			
			modeloActual.actualizarServidorDisponible(numeroServidorQueQuedaLibre);
		}

	}
	
	private double calcularTiempoAdicional(int parametro, int cantidad, double tiempoBase) {
	    
	    

	    // Calcular el tiempo adicional según el tipo de servicio y la cantidad de artículos
	    if (parametro == 1) {
	        switch (cantidad) {
	            case 2:
	                return 0.1 * tiempoBase; // 10%
	            case 3:
	                return 0.13 * tiempoBase; // 13%
	            default:
	                return 0; // Para 1 artículo, no hay tiempo adicional
	        }
	    } else if (parametro == 2) {
	        switch (cantidad) {
	            case 2:
	                return 0.12 * tiempoBase; // 12%
	            case 3:
	                return 0.15 * tiempoBase; // 15%
	            case 4:
	                return 0.20 * tiempoBase; // 20%
	            default:
	                return 0; // Para 1 artículo, no hay tiempo adicional
	        }
	    }
	    return 0; // Por defecto, no hay tiempo adicional
	}

	public double obtenerBeneficio(TipoServicio unTipo,  int cantidad) {
		
		  int costoTotal = unTipo.getCosto() * cantidad;
		    int precioVentaTotal = unTipo.getPrecioVenta() * cantidad;

		    
		    double beneficio = precioVentaTotal - costoTotal;
		return beneficio;
		
	}

}
