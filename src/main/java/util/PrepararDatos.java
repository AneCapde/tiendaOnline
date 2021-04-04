package util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import models.Categoria;
import models.Marca;
import models.Producto;
import models.SubCategoria;




//Clase para preparar los datos tengo que cambiar el pom.xml) -> descomentar lo que esta en azul
public class PrepararDatos {
	
	public static void main(String[] args) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			//Creamos las categorias
			Categoria categoria1 = new Categoria("Ropa","Aqui se podra encontrar ropa de diferentes estilos y de todo tipo");
			pm.makePersistent(categoria1);
			Categoria categoria2 = new Categoria("Zapatos","Aqui se podran encontrar zapatos de todo tipo y estilos");
			pm.makePersistent(categoria2);
			Categoria categoria3 = new Categoria("Accesorios","Aqui se podran encontrar accesorios de todo tipo");
			pm.makePersistent(categoria3);
			//Creamos las subcategorias
			
			//Categoria 1 (Ropa)
			SubCategoria subcategoria1 = new SubCategoria("Abrigos","Abrigos y chaquetas de todo tipo",categoria1);
			pm.makePersistent(subcategoria1);
			SubCategoria subcategoria2 = new SubCategoria("Camisetas","Camisetas de verano e invierno",categoria1);
			pm.makePersistent(subcategoria2);
			SubCategoria subcategoria3 = new SubCategoria("Pantalones","Pantalones de todos los estilos",categoria1);
			pm.makePersistent(subcategoria3);
			SubCategoria subcategoria4 = new SubCategoria("Faldas","Faldas de todo tipo",categoria1);
			pm.makePersistent(subcategoria4);
			SubCategoria subcategoria5 = new SubCategoria("Sudaderas","Sudaderas y jerseys variados",categoria1);
			pm.makePersistent(subcategoria5);
			SubCategoria subcategoria6 = new SubCategoria("Vestidos","Vestidos de verano e invierno",categoria1);
			pm.makePersistent(subcategoria6);
			SubCategoria subcategoria7 = new SubCategoria("Camisas","Camisas y blusas de todos los estilos",categoria1);
			pm.makePersistent(subcategoria7);
			
			//Categoria 2 (Zapatos)
			SubCategoria subcategoria8 = new SubCategoria("Deportivas","Zapatos para correr",categoria2);
			pm.makePersistent(subcategoria8);
			SubCategoria subcategoria9 = new SubCategoria("Zapatos de tacón","Zapatos de tacón",categoria2);
			pm.makePersistent(subcategoria9);
			SubCategoria subcategoria10 = new SubCategoria("Botas","Zapatos de invierno",categoria2);
			pm.makePersistent(subcategoria10);
			SubCategoria subcategoria11 = new SubCategoria("Sandalias","Zapatos de verano",categoria2);
			pm.makePersistent(subcategoria11);

			//Categoria 3 (Accesorios)
			SubCategoria subcategoria12 = new SubCategoria("Joyeria","Pendientes, anillos y pulseras",categoria3);
			pm.makePersistent(subcategoria12);
			SubCategoria subcategoria13 = new SubCategoria("Bolsos","Bolsos de todo tipo y riñoneras",categoria3);
			pm.makePersistent(subcategoria13);
			
			//Creamos las marcas (marcas?Esto hay que cambiarlo)
			Marca marca1 = new Marca("Marca1","marca1");
			pm.makePersistent(marca1);
			Marca marca2 = new Marca("Marca2","marca2");
			pm.makePersistent(marca2);
			Marca marca3 = new Marca("Marca3","marca3");
			pm.makePersistent(marca3);

			//Creamos productos (de momento 3 de cada subcategoria)
//			public String nombre;
//			public String descripcion;
//			public int stock;
//			public int precio;
//			public String imagen;
//			private Categoria categoria;
//			private Marca marca;
//			private SubCategoria subcategoria;
//			Producto producto1 = new Producto("Chaqueta Vaquera","Chaqueta vaquera azul",10,45,"TENGO QUE PASAR IMAGEN,");
//			pm.makePersistent(producto1);
//			Producto producto2 = new Producto("Chaqueta de cuero");
//			pm.makePersistent(producto2);
//			Producto producto3 = new Producto("Abrigo de piel");
//			pm.makePersistent(producto3);
//			Producto producto4 = new Producto();
//			pm.makePersistent(producto4);
			
			
			
		}finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}
	
	

}
