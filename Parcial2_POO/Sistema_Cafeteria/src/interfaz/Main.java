package interfaz;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import clases.*;

class Main {

	public static void main(String[] args) {
		
		String opCafe=" ",opCliente=" ",mVendido = "",recaudacion = "",vDescuento = "",lVentas = "";
		int op1,cant;
		double paga;
		boolean fin_dia = false;
		LinkedList<Transaccion> ventas = new LinkedList<Transaccion>();

		
		//Opciones
	    String[] opcion1 = {"Realizar venta","Terminar Dia"};
		String[] opcion2 = {"Mostrar ventas","Cafe mas vendido","Recaudacion total","Ventas con descuento","Anular Venta","Salir"};
		
		//Cafes
		LinkedList<Cafe> cafes = new LinkedList<Cafe>();
		cafes.add(new Cafe(1, "Latte", 1.50));
		cafes.add(new Cafe(2, "Flat White", 2.50));
		cafes.add(new Cafe(3, "Lagrima", 1.30));
		cafes.add(new Cafe(4, "Expresso", 1.00));
		Cafe activo= new Cafe(0," ",0);
		//Clientes
		LinkedList<Socio> socios = new LinkedList<Socio>();
		LinkedList<Cliente> clientes = new LinkedList<Cliente>();
		clientes.add(new Cliente("Luisito", "Comunica", "12345678"));
		clientes.add(new Cliente("Julio", "Profe", "32165487"));
		socios.add(new Socio("Paulina", "Cocina", "87654321", "23/45"));
		socios.add(new Socio("Manuel", "Belgrano", "7777777", "65/87"));
		
		
		//Seteo las opciones
		String []opcionesCafe= new String[cafes.size()];
		String []opcionesCliente= new String[4];
		
		for (Cafe cafe: cafes) {
			
			opcionesCafe[cafes.indexOf(cafe)]=cafe.getNombre();
		}
		for (int i = 0; i < 2; i++) {
			opcionesCliente[i]=clientes.get(i).getNombre()+" "+clientes.get(i).getApellido();
			opcionesCliente[i+2]=socios.get(i).getNombre()+" "+socios.get(i).getApellido();
		}

		do {
			
			op1 = JOptionPane.showOptionDialog(null, "Que accion quieres realizar?", null, 0, 0, null, opcion1, opcion1);
			
			if(op1==0) {
				
				opCafe = (String) JOptionPane.showInputDialog(
						null // para que se muestre centrado
						,"Selecciona un Cafe" // Mensaje de la ventana
						,"Lista de Datos" // Titulo de la ventana
						,JOptionPane.QUESTION_MESSAGE // Icono
						,null //null para icono defecto de la ventana
						,opcionesCafe // el objeto
						,opcionesCafe[0] // posicion del que va aparecer seleccionado
						);
				
				for (Cafe cafe: cafes) {
					
					if(cafe.getNombre().equals(opCafe)) {
						activo=cafe;
					}
				}
				
				do {
				cant = Integer.parseInt(JOptionPane.showInputDialog("Cuantas unidades?"));
				}while(cant < 1);
				
				opCliente = (String) JOptionPane.showInputDialog(
						null // para que se muestre centrado
						,"Selecciona un Cliente" // Mensaje de la ventana
						,"Lista de Datos" // Titulo de la ventana
						,JOptionPane.QUESTION_MESSAGE // Icono
						,null //null para icono defecto de la ventana
						,opcionesCliente // el objeto
						,opcionesCliente[0] // posicion del que va aparecer seleccionado
						);
				
				for (int i = 0; i < clientes.size(); i++) {
					if(opCliente.equals(clientes.get(i).getNombre()+" "+clientes.get(i).getApellido())) {
						paga = clientes.get(i).pagarVenta(activo.getPrecio(),cant);
						ventas.add(new Transaccion(activo, cant, opCliente, "No tiene",paga));
					}
					if(opCliente.equals(socios.get(i).getNombre()+" "+socios.get(i).getApellido())) {
						paga = socios.get(i).pagarVenta(activo.getPrecio(),cant);
						ventas.add(new Transaccion(activo, cant, opCliente,socios.get(i).getNro_tarjeta() ,paga));
					}	
				}
				JOptionPane.showMessageDialog(null, "Venta completada");
			
			}else {
				do {
				lVentas = mostrarVentas(ventas);
				mVendido = calcularVendido(ventas);
				recaudacion = calcularRecaudacion(ventas);
				vDescuento = calcularDescuentos(ventas);
				
				op1 = JOptionPane.showOptionDialog(null, "Que accion quieres realizar?", null, 0, 0, null, opcion2, opcion2);
				
				switch (op1) {
				case 0:
					JOptionPane.showMessageDialog(null, lVentas);
					break;
				case 1:
					JOptionPane.showMessageDialog(null, mVendido);
					break;
				case 2:
					JOptionPane.showMessageDialog(null, recaudacion);
					break;
				case 3:
					JOptionPane.showMessageDialog(null, vDescuento);
					break;
				case 4:
					quitarVenta(ventas);
					break;
				case 5:
					fin_dia=true;
					break;
			}
				}while(!fin_dia);
			}
			
		}while(!fin_dia);

	}
	public static String mostrarVentas(LinkedList<Transaccion> ventas){
		String lista=" ";
		
		for (Transaccion venta : ventas) {
			lista= lista+" \n"+venta;
		}
		return lista;
	}
	public static String calcularVendido(LinkedList<Transaccion> ventas){
		String xd=" ";
		int aux,aux1=0,aux2=0,aux3=0,aux4=0;
		for (Transaccion venta : ventas) {
			aux=venta.getCafe().getId();
			
			switch (aux) {
			case 1:
				aux1 = aux1+1+venta.getCantidad();
				break;
			case 2:
				aux2 =aux2+1+venta.getCantidad();
				break;
			case 3:
				aux3 = aux3+1+venta.getCantidad();
				break;
			case 4:
				aux4 = aux4+1+venta.getCantidad();
				break;
		}
	}
		if(aux1 > aux2 && aux1 > aux3 && aux1 > aux4) {
		xd = "El cafe mas consumido fue Latte con "+aux1+" unidades";
		}else if(aux2 > aux3 && aux2 > aux4) {
			xd = "El cafe mas consumido fue Flat White con "+aux2+" unidades";
		}else if(aux3 > aux4){
			xd = "El cafe mas consumido fue Lagrima con "+aux3+" unidades";
		}else {
			xd = "El cafe mas consumido fue Expresso con "+aux4+" unidades";
		}
		return xd;
	}
	public static String calcularRecaudacion(LinkedList<Transaccion> ventas){
		String xd="";
		double aux = 0;
		
		for (Transaccion venta : ventas) {
			aux = aux+venta.getTotal();
		}
		xd = "La recaudacion total del dia es: "+String.valueOf(aux);
		return xd;
	}
	public static String calcularDescuentos(LinkedList<Transaccion> ventas){
		String lista=" ";
		
		for (Transaccion venta : ventas) {
			if (!venta.getNro_socio().equals("No tiene")) {
				lista= lista+" \n"+venta;
			}
		}
		return lista;
}
	public static void quitarVenta(LinkedList<Transaccion> ventas){
		String[] lista=new String[ventas.size()];
		String op;
		boolean xd =false;
		
		for (Transaccion venta : ventas) {
			
			lista[ventas.indexOf(venta)]=String.valueOf(venta.getId());
		}
		
		op = (String) JOptionPane.showInputDialog(
				null // para que se muestre centrado
				,"Selecciona la venta que queres anular" // Mensaje de la ventana
				,"Lista de Datos" // Titulo de la ventana
				,JOptionPane.QUESTION_MESSAGE // Icono
				,null //null para icono defecto de la ventana
				,lista // el objeto
				,lista[0] // posicion del que va aparecer seleccionado
				);
		
		int i=0;
		while(i < ventas.size() && !xd) {
			if (op.equals(String.valueOf(ventas.get(i).getId()))) {
				ventas.remove(i);
				xd =true;
			}
		}
		
}

}
