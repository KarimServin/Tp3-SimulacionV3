package modeloejemplo.estadodelsistema;

import java.util.Random;

public enum TipoServicio {

	BEBIDAS(600, 1200, 2.4), 
    PANADERIA(400, 850, 3.5); 
	
	private final int costo;
    private final int precioVenta;
    private final double tiempoMedioServicio;
   

    TipoServicio(int costo, int precioVenta, double tiempoMedioServicio) {
        this.costo = costo;
        this.precioVenta = precioVenta;
        this.tiempoMedioServicio = tiempoMedioServicio;
    }

    public int getCosto() {
        return costo;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public double getTiempoMedioServicio() {
    	
        return tiempoMedioServicio; 
        
    }
	
	
}
