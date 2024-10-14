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

public class EventoArribarACola extends Evento {

	public EventoArribarACola(double tiempoDeOcurrencia) {
		super(tiempoDeOcurrencia);
	}

	@Override
	public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
			LibreriaDeRutinasEjemplo libreria) {
			
		
		
		ModeloDelEjemplo modeloActual = (ModeloDelEjemplo) modelo;
		
		ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		
		contadoresEjemplo.recalcularLongitudPromedioCola(modeloActual.getLongitudColaActual());
		
		//Agendar el próximo arribo de solicitud.
		EventoArribarACola nuevoEvento = new EventoArribarACola(libreria.tiempoEntreArribosSolicitudes());	
		
		eventos.agregar(nuevoEvento);
		
		
		//Procesar este arribo, para lo cual es necesario generar la solicitud que acaba de arribar.
		Solicitud solicitudParaAgregar = new Solicitud();
		
		solicitudParaAgregar.setTiempoLlegada(getTiempoDeOcurrencia());
		
		solicitudParaAgregar.setTipoServicio(
			    libreria.generarNumeroAleatorioConParametro(3) < 0.7 ? TipoServicio.BEBIDAS : TipoServicio.PANADERIA);
		
		solicitudParaAgregar.setCantArticulos(
				solicitudParaAgregar.getTipoServicio() == TipoServicio.BEBIDAS? libreria.generarCantidadArticulosBebidas() : libreria.generarCantidadArticulosPanaderia());
		
		
	   double beneficio = obtenerBeneficio(solicitudParaAgregar.getTipoServicio(), solicitudParaAgregar.getCantArticulos());
		
	   solicitudParaAgregar.setBeneficioSolicitud(beneficio);
	   
	   int valorParametro = (solicitudParaAgregar.getTipoServicio() == TipoServicio.BEBIDAS? 1:2);
		
		if(modeloActual.estanServidoresOcupados()) {
			modeloActual.encolarSolicitud(solicitudParaAgregar);
		}
		else {
			int numServidorLibre = modeloActual.obtenerServidorLibre();
		
			//modeloActual.atenderSolicitud(solicitudParaAgregar,numServidorLibre);
			//contadoresEjemplo.agregarTiempoDeOcupacion(tiempoTotal,numServidorLibre);
			double duracionDelProcesamiento = libreria.tiempoDeProcesamiento(valorParametro);
			
			solicitudParaAgregar.setTiempoServicioBase(duracionDelProcesamiento);
			double tiempoAdicional = calcularTiempoAdicional(valorParametro, solicitudParaAgregar.getCantArticulos(),duracionDelProcesamiento); 
			solicitudParaAgregar.setTiempoServicioAdicional(tiempoAdicional);
			double tiempoTotal = duracionDelProcesamiento + tiempoAdicional;
			solicitudParaAgregar.setTiempoServicioTotal(tiempoTotal);
			
			modeloActual.atenderSolicitud(solicitudParaAgregar,numServidorLibre);
			//contadoresEjemplo.agregarTiempoDeOcupacion(tiempoTotal,numServidorLibre);
			System.out.println(tiempoTotal);
		    int servidorQueVaAQuedarLibre = numServidorLibre;
		    
			EventoTerminaProcesamiento nuevoEventoAdicional = new EventoTerminaProcesamiento(tiempoTotal,servidorQueVaAQuedarLibre,solicitudParaAgregar);	
			eventos.agregar(nuevoEventoAdicional);
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
