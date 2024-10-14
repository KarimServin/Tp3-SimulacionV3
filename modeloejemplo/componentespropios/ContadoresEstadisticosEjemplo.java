package modeloejemplo.componentespropios;

import des.ContadoresEstadisticos;
import modeloejemplo.estadodelsistema.Servidor;
import modeloejemplo.estadodelsistema.ModeloDelEjemplo;
/* Variables que almacenan información estadística referida al comportamiento del sistema. */

public class ContadoresEstadisticosEjemplo extends ContadoresEstadisticos {
	
	private int cantSolicitudesProcesadas;
	private double[] tiempoProcesamientoPorServidor;
	private int cantServidores;
	private double beneficiosTotales;
	private double tiempoEsperaTotal;
	private double tiempoEnKiosco;
	private double longitudPromedio;
	private int[]  cantidadClientesPorServidor;
	public int getCantServidores() {
		return cantServidores;
	}

	public ContadoresEstadisticosEjemplo() {
		super();
	}

	public void inicializar() {
		
		cantServidores = ModeloDelEjemplo.getCantServidores();
		beneficiosTotales = 0;
		tiempoEsperaTotal =0;
		tiempoEnKiosco=0;
		if (cantServidores == 2) {
			tiempoProcesamientoPorServidor = new double[2];
			tiempoProcesamientoPorServidor[0] = 0;
			tiempoProcesamientoPorServidor[1] = 0;
			cantidadClientesPorServidor = new int[2];
			cantidadClientesPorServidor[0] = 0;
			cantidadClientesPorServidor[1] = 0;
			} else {
				tiempoProcesamientoPorServidor = new double[1];
				tiempoProcesamientoPorServidor[0] = 0;
				cantidadClientesPorServidor = new int[1];
				cantidadClientesPorServidor[0] = 0;
			}
		
		
	}

	public int getCantProcesadas() {
		return cantSolicitudesProcesadas;
	}

	public void actualizarCantProcesadas() {
		cantSolicitudesProcesadas++;		
	}
	
	public double getTiempoOcupadoServidor(int i) {
		
		
			
			return tiempoProcesamientoPorServidor[i];
			
		}
		
	
	
	public void agregarTiempoDeOcupacion(double tiempo, int numeroServidor) {
		
		tiempoProcesamientoPorServidor[numeroServidor] = tiempoProcesamientoPorServidor[numeroServidor] + tiempo;
		
		
	}
	
	
	public void agregarBeneficios(double valor) {
		
		beneficiosTotales = beneficiosTotales + valor;
		
	}
	
	public double getBeneficios() {
		return beneficiosTotales;
	}
	
	
	
	public void agregarTiempoEnKiosco(double tiempo) {
		
		tiempoEnKiosco = tiempoEnKiosco + tiempo;
		
	}
	
     public double getTiempoPromedioEnKiosco() {
		
		return tiempoEnKiosco / cantSolicitudesProcesadas;
		
	}

     public double getPromedioLongitudDeCola() {
	 return longitudPromedio/ cantSolicitudesProcesadas;
    }

     public void recalcularLongitudPromedioCola(int cantidad){
 	
	
	longitudPromedio = longitudPromedio + cantidad;

	
      }

      
      public double tasaAtencion(double tiempoTotal,int servidor) {
    	
    	  return 60 * cantidadClientesPorServidor[servidor] / tiempoTotal;
    	  
    
      }
      
      public void sumarClientePorServidor(int servidor) {
    	  
    	  cantidadClientesPorServidor[servidor]++;
    	  
      }
      
      public double porcentajeTiempoLibrePorServidor(double tiempoTotal,int servidor) {
    	  
    	  return ((tiempoTotal - tiempoProcesamientoPorServidor[servidor]) /tiempoTotal)*100;
      }


}
