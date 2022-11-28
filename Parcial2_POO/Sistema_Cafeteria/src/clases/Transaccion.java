package clases;

public class Transaccion {

	private int id;
	private Cafe cafe;
	private int cantidad;
	private double total;
	private String cliente;
	private String nro_socio;
	private static int au=1;
	
	public Transaccion(Cafe cafe, int cantidad, String cliente, String nro_socio,double total) {
		super();
		this.id = au;
		this.total = total;
		this.cafe = cafe;
		this.cantidad = cantidad;
		this.cliente = cliente;
		this.nro_socio = nro_socio;
		au++;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cafe getCafe() {
		return cafe;
	}
	public void setCafe(Cafe cafe) {
		this.cafe = cafe;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getNro_socio() {
		return nro_socio;
	}
	public void setNro_socio(String nro_socio) {
		this.nro_socio = nro_socio;
	}
	@Override
	public String toString() {
		return "Transaccion "+id+", "+this.cafe.getNombre()+", unidades: "+cantidad+ ", total: "+total+", "+cliente+", tarjeta: "+nro_socio;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public static int getAu() {
		return au;
	}
	public static void setAu(int au) {
		Transaccion.au = au;
	}
	
	
}
