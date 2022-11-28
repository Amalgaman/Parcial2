package clases;

public class Socio extends Cliente{

	private String nro_tarjeta;

	public Socio(String nombre, String apellido, String dni, String nro_tarjeta) {
		super(nombre, apellido, dni);
		this.nro_tarjeta = nro_tarjeta;
	}

	public String getNro_tarjeta() {
		return nro_tarjeta;
	}

	public void setNro_tarjeta(String nro_tarjeta) {
		this.nro_tarjeta = nro_tarjeta;
	}
	
	
	public double pagarVenta(double precio,int cantidad) {
		double total=(precio*cantidad)*0.85;
		
		return total;
	}
	
}
