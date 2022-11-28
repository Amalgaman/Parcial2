package interfaz;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import clases.*;

class Main {

	public static void main(String[] args) {
		
		String opCafe=" ",opCliente=" ";
		int op1,cant;
		double paga;
		boolean fin_dia = false;
		LinkedList<Transaccion> ventas = new LinkedList<Transaccion>();

		
		//Opciones
	    String[] opcion1 = {"Realizar venta","Terminar Dia"};
		String[] opcion2 = {"Mostrar ventas","Cafe mas vendido","Recaudacion total","Ventas con descuento","Salir"};
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
		String []opcionesCliente= new String[clientes.size()];
		
		for (Cafe cafe: cafes) {
			
			opcionesCafe[cafes.indexOf(cafe)]=cafe.getNombre();
		}
		for (int i = 0; i < clientes.size(); i+=2) {
			opcionesCliente[i]=clientes.get(i).getNombre()+" "+clientes.get(i).getApellido();
			opcionesCliente[i+1]=socios.get(i).getNombre()+" "+socios.get(i).getApellido();
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
						,opcionesCafe // el objeto
						,opcionesCafe[0] // posicion del que va aparecer seleccionado
						);
				
				for (int i = 0; i < clientes.size(); i++) {
					if(opCliente.equals(clientes.get(i).getNombre()+" "+clientes.get(i).getApellido())) {
						paga = clientes.get(i).pagarVenta(activo.getPrecio(),cant);
					}
					if(opCliente.equals(socios.get(i).getNombre()+" "+socios.get(i).getApellido())) {
						paga = socios.get(i).pagarVenta(activo.getPrecio(),cant);
					}	
				}
				
			}else {
				
				fin_dia = true;
			}
			
		}while(!fin_dia);

	}

}
