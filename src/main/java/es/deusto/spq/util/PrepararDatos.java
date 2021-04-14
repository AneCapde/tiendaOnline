package es.deusto.spq.util;


import java.util.HashMap;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.spq.models.Categoria;
import es.deusto.spq.models.Cliente;
import es.deusto.spq.models.Colores;
import es.deusto.spq.models.Marca;
import es.deusto.spq.models.Producto;
import es.deusto.spq.models.SubCategoria;
import es.deusto.spq.models.Tallas;
import es.deusto.spq.models.Cliente.Genero;




//Clase para preparar los datos tengo que cambiar el pom.xml) -> descomentar lo que esta en azul
public class PrepararDatos {
	
	public static void main(String[] args) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			//Creamos un cliente
			Cliente cliente1 = new Cliente("12399345K", "Leire", "Fernandez Olabarrieta", "leire@gmail.com", "12345r", 655786943,
					"luis bilbao libarno", Genero.MUJER, 45678, "Bizkaia", "Leioa");
			pm.makePersistent(cliente1);
			
			//Creamos las categorias
			Categoria categoria1 = new Categoria("Ropa","Aqui se podra encontrar ropa de diferentes estilos y de todo tipo");
			//DBManager.getInstance().store(categoria1);
			pm.makePersistent(categoria1);
			Categoria categoria2 = new Categoria("Zapatos","Aqui se podran encontrar zapatos de todo tipo y estilos");
			//DBManager.getInstance().store(categoria2);
			pm.makePersistent(categoria2);
			Categoria categoria3 = new Categoria("Accesorios","Aqui se podran encontrar accesorios de todo tipo");
//			pm.makePersistent(categoria3);
			pm.makePersistent(categoria3);
			//DBManager.getInstance().store(categoria3);
			//Creamos las subcategorias
			
			//Categoria 1 (Ropa)
			SubCategoria subcategoria1 = new SubCategoria("Abrigos","Abrigos y chaquetas de todo tipo",categoria1);
//			pm.makePersistent(subcategoria1);
			pm.makePersistent(categoria1);
			//DBManager.getInstance().store(subcategoria1);
			SubCategoria subcategoria2 = new SubCategoria("Camisetas","Camisetas de verano e invierno",categoria1);
			pm.makePersistent(subcategoria2);
			//DBManager.getInstance().store(subcategoria2);
			SubCategoria subcategoria3 = new SubCategoria("Pantalones","Pantalones de todos los estilos",categoria1);
			pm.makePersistent(subcategoria3);
			//DBManager.getInstance().store(subcategoria3);
			SubCategoria subcategoria4 = new SubCategoria("Faldas","Faldas de todo tipo",categoria1);
			pm.makePersistent(subcategoria4);
			//DBManager.getInstance().store(subcategoria4);
			SubCategoria subcategoria5 = new SubCategoria("Sudaderas","Sudaderas y jerseys variados",categoria1);
			pm.makePersistent(subcategoria5);
			//DBManager.getInstance().store(subcategoria5);
			SubCategoria subcategoria6 = new SubCategoria("Vestidos","Vestidos de verano e invierno",categoria1);
			pm.makePersistent(subcategoria6);
			//DBManager.getInstance().store(subcategoria6);
			SubCategoria subcategoria7 = new SubCategoria("Camisas","Camisas y blusas de todos los estilos",categoria1);
			pm.makePersistent(subcategoria7);
			//DBManager.getInstance().store(subcategoria7);
			
			//Categoria 2 (Zapatos)
			SubCategoria subcategoria8 = new SubCategoria("Deportivas","Zapatos para correr",categoria2);
			pm.makePersistent(subcategoria8);
			//DBManager.getInstance().store(subcategoria8);
			SubCategoria subcategoria9 = new SubCategoria("Zapatos de tacón","Zapatos de tacón",categoria2);
			pm.makePersistent(subcategoria9);
			//DBManager.getInstance().store(subcategoria9);
			SubCategoria subcategoria10 = new SubCategoria("Botas","Zapatos de invierno",categoria2);
			pm.makePersistent(subcategoria10);
			//DBManager.getInstance().store(subcategoria10);
			SubCategoria subcategoria11 = new SubCategoria("Sandalias","Zapatos de verano",categoria2);
			pm.makePersistent(subcategoria11);
			//DBManager.getInstance().store(subcategoria11);

			//Categoria 3 (Accesorios)
			SubCategoria subcategoria12 = new SubCategoria("Joyeria","Pendientes, anillos y pulseras",categoria3);
			pm.makePersistent(subcategoria12);
			//DBManager.getInstance().store(subcategoria12);
			SubCategoria subcategoria13 = new SubCategoria("Bolsos","Bolsos de todo tipo y riñoneras",categoria3);
			pm.makePersistent(subcategoria13);
			//DBManager.getInstance().store(subcategoria13);
			
			//Creamos las marcas
			Marca marca1 = new Marca("Nike","Marca de ropa y zapatos");
			pm.makePersistent(marca1);
			//DBManager.getInstance().store(marca1);
			Marca marca2 = new Marca("Converse","Marca de zapatos");
			pm.makePersistent(marca2);
			//DBManager.getInstance().store(marca2);
			Marca marca3 = new Marca("Cartier","Marca de joyeria");
			pm.makePersistent(marca3);
			//DBManager.getInstance().store(marca3);

			//Creamos productos (de momento algunos de prueba)
			
			//Para hacer lo del hashmap de colores
			//he hecho dos hashmap de diferente talla porque solo cogia la ultima talla que se metia al HM
			HashMap<Colores, Tallas> colorTallaS = new HashMap<>();
			colorTallaS.put(Colores.AZUL, Tallas.S);
			colorTallaS.put(Colores.NEGRO, Tallas.S);
			colorTallaS.put(Colores.AMARILLO, Tallas.S);
			colorTallaS.put(Colores.MORADO, Tallas.S);
			colorTallaS.put(Colores.ROSA, Tallas.S);
			colorTallaS.put(Colores.CIAN, Tallas.S);
			colorTallaS.put(Colores.ROJO, Tallas.S);
			
//			HashMap<Colores, Tallas> colorTallaXL = new HashMap<>();
//			colorTallaXL.put(Colores.AZUL, Tallas.XL);
//			colorTallaXL.put(Colores.NEGRO, Tallas.XL);
//			colorTallaXL.put(Colores.AMARILLO, Tallas.XL);
//			colorTallaXL.put(Colores.MORADO, Tallas.XL);
//			colorTallaXL.put(Colores.ROSA, Tallas.XL);
//			colorTallaXL.put(Colores.CIAN, Tallas.XL);
//			colorTallaXL.put(Colores.ROJO, Tallas.XL);
			//colorTalla.put(Colores.AZUL, Tallas.M);
			//colorTalla.put(Colores.NEGRO, Tallas.M);
			
			Producto producto1 = new Producto("Chaqueta Vaquera","Chaqueta vaquera azul",10,45,"img/chaquetaVaquera.png",subcategoria1,marca1);
			//producto1.setTallas_colores(colorTallaS);
			pm.makePersistent(producto1);
			Producto producto2 = new Producto("Chaqueta de cuero","Chaqueta de cuero negra",10,35,"img/chaquetaCuero.png",subcategoria1,marca1);
			//producto2.setTallas_colores(colorTallaS);
			pm.makePersistent(producto2);
			Producto producto3 = new Producto("Abrigo de piel","Abrigo de piel marron",10,80,"img/chaquetaPiel.png",subcategoria1,marca1);
			//producto3.setTallas_colores(colorTallaS);
			pm.makePersistent(producto3);
			Producto producto4 = new Producto("Camiseta de tirantes","Camiseta de tirantes azul",5,10,"img/camisetaTirante.png",subcategoria2,marca1);
			//producto4.setTallas_colores(colorTallaS);
			pm.makePersistent(producto4);
			Producto producto5 = new Producto("Pantalon Vaquero","Pantalon vaquero azul",15,30,"img/pantalonVaquero.png",subcategoria3,marca2);
			//producto5.setTallas_colores(colorTallaS);
			pm.makePersistent(producto5);
			Producto producto6 = new Producto("Falda de flores","Falda estampado floral",10,25,"img/faldaFlores.png",subcategoria4,marca2);
			//producto6.setTallas_colores(colorTallaS);
			pm.makePersistent(producto6);
			Producto producto7 = new Producto("Vestido negro","Vestido negro de tirantes",10,35,"img/vestidoNegro.png",subcategoria6,marca2);
			//producto7.setTallas_colores(colorTallaS);
			pm.makePersistent(producto7);
			Producto producto8 = new Producto("Camisa blanca","Camisa blanca elegante",10,35,"img/camisaBlanca.png",subcategoria7,marca2);
			//producto8.setTallas_colores(colorTallaS);
			pm.makePersistent(producto8);
			Producto producto10 = new Producto("Deportivas","Deportivas blancas planas converse",10,70,"img/zapatillasDeportivas.png",subcategoria8,marca2);
			pm.makePersistent(producto10);
			Producto producto11 = new Producto("Botas altas","Botas altas negras con cordones",10,75,"img/botasAltas.png",subcategoria10,marca2);
			pm.makePersistent(producto11);
			//meter mas productos de zapatos
			Producto producto15 = new Producto("Pendientes de aro","Pendienets de aro bañados en plata",10,20,"img/pendientesAro.png",subcategoria12,marca3);
			pm.makePersistent(producto15);
			Producto producto16 = new Producto("Bolso","Bolso pequeño marron con tira desplegable",10,90,"img/bolso.png",subcategoria13,marca3);
			pm.makePersistent(producto16);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		
	}
	
	

}
