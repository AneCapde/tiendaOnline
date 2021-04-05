package com.example.util;

import java.awt.Color;
import java.util.HashMap;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import dao.DBManager;
import models.Categoria;
import models.Colores;
import models.Marca;
import models.Producto;
import models.SubCategoria;
import models.Tallas;




//Clase para preparar los datos tengo que cambiar el pom.xml) -> descomentar lo que esta en azul
public class PrepararDatos {
	
	public static void main(String[] args) {
			
			//Creamos las categorias
			Categoria categoria1 = new Categoria("Ropa","Aqui se podra encontrar ropa de diferentes estilos y de todo tipo");
			DBManager.getInstance().store(categoria1);
			Categoria categoria2 = new Categoria("Zapatos","Aqui se podran encontrar zapatos de todo tipo y estilos");
			DBManager.getInstance().store(categoria2);
			Categoria categoria3 = new Categoria("Accesorios","Aqui se podran encontrar accesorios de todo tipo");
//			pm.makePersistent(categoria3);
			DBManager.getInstance().store(categoria3);
			//Creamos las subcategorias
			
			//Categoria 1 (Ropa)
			SubCategoria subcategoria1 = new SubCategoria("Abrigos","Abrigos y chaquetas de todo tipo",categoria1);
//			pm.makePersistent(subcategoria1);
			DBManager.getInstance().store(subcategoria1);
			SubCategoria subcategoria2 = new SubCategoria("Camisetas","Camisetas de verano e invierno",categoria1);
//			pm.makePersistent(subcategoria2);
			DBManager.getInstance().store(subcategoria2);
			SubCategoria subcategoria3 = new SubCategoria("Pantalones","Pantalones de todos los estilos",categoria1);
//			pm.makePersistent(subcategoria3);
			DBManager.getInstance().store(subcategoria3);
			SubCategoria subcategoria4 = new SubCategoria("Faldas","Faldas de todo tipo",categoria1);
//			pm.makePersistent(subcategoria4);
			DBManager.getInstance().store(subcategoria4);
			SubCategoria subcategoria5 = new SubCategoria("Sudaderas","Sudaderas y jerseys variados",categoria1);
//			pm.makePersistent(subcategoria5);
			DBManager.getInstance().store(subcategoria5);
			SubCategoria subcategoria6 = new SubCategoria("Vestidos","Vestidos de verano e invierno",categoria1);
//			pm.makePersistent(subcategoria6);
			DBManager.getInstance().store(subcategoria6);
			SubCategoria subcategoria7 = new SubCategoria("Camisas","Camisas y blusas de todos los estilos",categoria1);
//			pm.makePersistent(subcategoria7);
			DBManager.getInstance().store(subcategoria7);
			
			//Categoria 2 (Zapatos)
			SubCategoria subcategoria8 = new SubCategoria("Deportivas","Zapatos para correr",categoria2);
//			pm.makePersistent(subcategoria8);
			DBManager.getInstance().store(subcategoria8);
			SubCategoria subcategoria9 = new SubCategoria("Zapatos de tacón","Zapatos de tacón",categoria2);
//			pm.makePersistent(subcategoria9);
			DBManager.getInstance().store(subcategoria9);
			SubCategoria subcategoria10 = new SubCategoria("Botas","Zapatos de invierno",categoria2);
//			pm.makePersistent(subcategoria10);
			DBManager.getInstance().store(subcategoria10);
			SubCategoria subcategoria11 = new SubCategoria("Sandalias","Zapatos de verano",categoria2);
//			pm.makePersistent(subcategoria11);
			DBManager.getInstance().store(subcategoria11);

			//Categoria 3 (Accesorios)
			SubCategoria subcategoria12 = new SubCategoria("Joyeria","Pendientes, anillos y pulseras",categoria3);
//			pm.makePersistent(subcategoria12);
			DBManager.getInstance().store(subcategoria12);
			SubCategoria subcategoria13 = new SubCategoria("Bolsos","Bolsos de todo tipo y riñoneras",categoria3);
//			pm.makePersistent(subcategoria13);
			DBManager.getInstance().store(subcategoria13);
			
			//Creamos las marcas
			Marca marca1 = new Marca("Nike","Marca de ropa y zapatos");
//			pm.makePersistent(marca1);
			DBManager.getInstance().store(marca1);
			Marca marca2 = new Marca("Converse","Marca de zapatos");
//			pm.makePersistent(marca2);
			DBManager.getInstance().store(marca2);
			Marca marca3 = new Marca("Cartier","Marca de joyeria");
//			pm.makePersistent(marca3);
			DBManager.getInstance().store(marca3);

			//Creamos productos (de momento algunos de prueba)
			//como pasar imagen y hm de colores y tallas como lo metemos?
			//Para hacer lo del hashmap de colores (no se si se hace asi)
			HashMap<Colores, Tallas> colorTalla = new HashMap<>();
			colorTalla.put(Colores.AZUL, Tallas.M);
			colorTalla.put(Colores.AZUL, Tallas.L);
			colorTalla.put(Colores.AZUL, Tallas.S);
			colorTalla.put(Colores.AZUL, Tallas.XL);
			colorTalla.put(Colores.NEGRO, Tallas.M);
			colorTalla.put(Colores.NEGRO, Tallas.L);
			colorTalla.put(Colores.NEGRO, Tallas.S);
			colorTalla.put(Colores.NEGRO, Tallas.XL);
			Producto producto1 = new Producto("Chaqueta Vaquera","Chaqueta vaquera azul",10,45,"TENGO QUE PASAR IMAGEN",subcategoria1,marca1);
			producto1.setTallas_colores(colorTalla);
			DBManager.getInstance().store(producto1);
			Producto producto2 = new Producto("Chaqueta de cuero","Chaqueta de cuero negra",10,35,"imagen",subcategoria1,marca1);
			DBManager.getInstance().store(producto2);
			producto2.setTallas_colores(colorTalla);
			Producto producto3 = new Producto("Abrigo de piel","Abrigo de piel marron",10,80,"imagen",subcategoria1,marca1);
			producto3.setTallas_colores(colorTalla);
			DBManager.getInstance().store(producto3);
			Producto producto4 = new Producto("Camiseta de tirantes","Camiseta de tirantes azul",5,10,"imagen",subcategoria2,marca1);
			producto4.setTallas_colores(colorTalla);
			DBManager.getInstance().store(producto4);
			//meter mas de productos de ropa
			Producto producto10 = new Producto("Deportivas","Deportivas blancas planas converse",10,70,"TENGO QUE PASAR IMAGEN",subcategoria8,marca2);
			DBManager.getInstance().store(producto10);
			Producto producto11 = new Producto("Botas altas","Botas altas negras con cordones",10,75,"TENGO QUE PASAR IMAGEN",subcategoria10,marca2);
			DBManager.getInstance().store(producto11);
			//meter mas productos de zapatos
			Producto producto15 = new Producto("Pendientes de aro","Pendienets de aro bañados en plata",10,20,"TENGO QUE PASAR IMAGEN",subcategoria12,marca3);
			DBManager.getInstance().store(producto15);
			Producto producto16 = new Producto("Bolso","Bolso pequeño marron con tira desplegable",10,90,"TENGO QUE PASAR IMAGEN",subcategoria13,marca3);
			DBManager.getInstance().store(producto16);
			

		
	}
	
	

}
