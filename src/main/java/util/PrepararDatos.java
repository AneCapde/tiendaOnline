package util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import models.Categoria;
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
//			//Creamos las subcategorias
//			SubCategoria subcategoria1 = new SubCategoria("Camiseta","",categoria1);
//			pm.makePersistent(subcategoria1);
//			SubCategoria subcategoria2 = new SubCategoria("","",categoria1);
//			pm.makePersistent(subcategoria2);
//			SubCategoria subcategoria3 = new SubCategoria("","",categoria1);
//			pm.makePersistent(subcategoria3);
//			SubCategoria subcategoria4 = new SubCategoria("","",categoria1);
//			pm.makePersistent(subcategoria4);
//			SubCategoria subcategoria5 = new SubCategoria("","",categoria1);
//			pm.makePersistent(subcategoria5);
//			SubCategoria subcategoria6 = new SubCategoria();
//			pm.makePersistent(subcategoria6);
//			//Creamos productos
			
		}finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}
	
	

}
