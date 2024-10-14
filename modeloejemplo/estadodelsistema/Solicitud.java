package modeloejemplo.estadodelsistema;

import modeloejemplo.componentespropios.*;
/* Solicitud que puede ser procesada por el servidor. */

public class Solicitud {
	
	LibreriaDeRutinasEjemplo libreria;
	private int clase;
	private double tiempoEntreArribo;
	private double tiempoServicioBase;
	private double tiempoServicioAdicional;
	private double tiempoServicioTotal;
	private double tiempoLlegada;
	private double tiempoInicioServicio;
	private TipoServicio tipoServicio;
	private int cantArticulos;
	private double importe;
	
	public double getTiempoInicioServicio() {
		return tiempoInicioServicio;
	}

	public void setTiempoInicioServicio(double tiempoInicioServicio) {
		this.tiempoInicioServicio = tiempoInicioServicio;
	}

	
	
	public double getTiempoEntreArribo() {
		return tiempoEntreArribo;
	}

	public void setTiempoEntreArribo(double tiempoEntreArribo) {
		this.tiempoEntreArribo = tiempoEntreArribo;
	}

	public double getTiempoServicioBase() {
		return tiempoServicioBase;
	}

	public void setTiempoServicioBase(double tiempoServicioBase) {
		this.tiempoServicioBase = tiempoServicioBase;
	}

	public double getTiempoServicioAdicional() {
		return tiempoServicioAdicional;
	}

	public void setTiempoServicioAdicional(double tiempoServicioAdicional) {
		this.tiempoServicioAdicional = tiempoServicioAdicional;
	}

	public double getTiempoServicioTotal() {
		return tiempoServicioTotal;
	}

	public void setTiempoServicioTotal(double tiempoServicioTotal) {
		this.tiempoServicioTotal = tiempoServicioTotal;
	}

	public double getTiempoLlegada() {
		return tiempoLlegada;
	}

	public void setTiempoLlegada(double tiempoLlegada) {
		this.tiempoLlegada = tiempoLlegada;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public int getCantArticulos() {
		return cantArticulos;
	}

	public void setCantArticulos(int cantArticulos) {
		this.cantArticulos = cantArticulos;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getBeneficioSolicitud() {
		return beneficioSolicitud;
	}

	public void setBeneficioSolicitud(double beneficioSolicitud) {
		this.beneficioSolicitud = beneficioSolicitud;
	}

	public void setClase(int clase) {
		this.clase = clase;
	}

	private double costo;
	private double beneficioSolicitud;
	

	public Solicitud() {
		super();
		this.clase = (int) ((Math.random()*3) + 1);	
	}

	public int getClase() {
		return clase;
	}
	
}
