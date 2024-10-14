package modeloejemplo.componentespropios;

import des.LibreriaDeRutinas;

/* Subprogramas usados para generar observaciones aleatorias desde las distribuciones de 
 * probabilidad asociadas al modelo. */

public class LibreriaDeRutinasEjemplo extends LibreriaDeRutinas {

	
	private long[] semillas;
	
	public LibreriaDeRutinasEjemplo( ) {
		
		semillas = new long[5];
		semillas[0] = 7075; //TiempoEntreArribos
        semillas[1] = 8872; //TiempoBaseServicioBebidas
        semillas[2] = 6262; //TiempoBaseServicioPanaderia
        semillas[3] = 1911; //TipoServicio
        semillas[4] = 851; //CantidadArticulos	
	}
	
	public double generarNumeroAleatorioConParametro(int parametro) {
        //El metodo ya actualiza la semilla en funcion al parámetro
    	int cantidadDigitos = 4; // Longitud fija de 4 dígitos

        long semilla = semillas[parametro];
        // Calculamos el cuadrado de la semilla
        long cuadrado = semilla * semilla;

        // Convertimos a cadena para extraer los dígitos centrales
        String cuadradoStr = String.format("%0" + (cantidadDigitos * 2) + "d", cuadrado);
        int longitud = cuadradoStr.length();

        // Extraemos los dígitos centrales
        int inicio = (longitud - cantidadDigitos) / 2;
        String digitosCentros = cuadradoStr.substring(inicio, inicio + cantidadDigitos);

        // Actualizamos la semilla para el siguiente uso
        semillas[parametro] = Long.parseLong(digitosCentros);
        
        // Retornamos el número entre 0 y 1
        //System.out.println(semilla / Math.pow(10, cantidadDigitos));
        return (double) semilla / Math.pow(10, cantidadDigitos);
    }
	
	
	
	
	
	public double generarVariableAleatoriaExponencial(int parametroTipo, double unaMedia) {
        double u = generarNumeroAleatorioConParametro(parametroTipo); // Número aleatorio entre 0 y 1
        
        double LAMBDA = 1/unaMedia;
        
        return -Math.log(1 - u) / LAMBDA; // Transformada inversa para la distribución exponencial
	
    }
	
	
	public double tiempoEntreArribosSolicitudes() {
		
		
		return generarVariableAleatoriaExponencial(0,4); //Parametro 0, tiempo entre arribo,
		//4 -> MEDIA
	}


	public double tiempoDeProcesamiento(int parametro) {
		
		//tiempoBase
		double unaMedia;  
		unaMedia = (parametro == 1)? 2.4: 3.5;
		
		return generarVariableAleatoriaExponencial(parametro,unaMedia);
	}
	
    public int generarCantidadArticulosBebidas() {
        double random = generarNumeroAleatorioConParametro(4); // Genera un número entre 0 y 1
        if (random < 0.57) {
            return 1; // 57% de probabilidad
        } else if (random < 0.90) { // 57% + 33% = 90%
            return 2; // 33% de probabilidad
        } else {
            return 3; // 10% de probabilidad
        }
    }

    
    public int generarCantidadArticulosPanaderia() {
        double random = generarNumeroAleatorioConParametro(4); // Genera un número entre 0 y 1
        if (random < 0.27) {   
            return 1; // 27% de probabilidad
        } else if (random < 0.52) { 
            return 2; // 25% de probabilidad
        } else if (random < 0.87) { 
            return 3; // 35% de probabilidad
        } else {
            return 4; // 13% de probabilidad
        }   
    }

}
