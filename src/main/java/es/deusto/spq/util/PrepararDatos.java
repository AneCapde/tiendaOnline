package es.deusto.spq.util;


import java.util.ArrayList;
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
import es.deusto.spq.models.Pago;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;



public class PrepararDatos implements IPrepararDatos {

	private static IPrepararDatos instance;
	private static final String FILENAME = "src/main/resources/idiomas.xml";
	private HashMap<String, String> idioma_ingles = new HashMap<>();
	private HashMap<String, String> idioma_espanyol = new HashMap<>();
	private String [] idioma_caract = { "nombre1","nombre2", "listadeseadosBoton", 
	"historialBoton", "cestaBoton", "buscarBoton", "comprarBoton", "categoria", 
	"subcategoria", "color", "talla", "marca", "caracteristicas", "registrarseBoton",
	"iniciarsesionBoton", "iniciarsesionPanel", "aceptarBoton", "volverBoton",
	"eliminarBoton", "anyadircestaBoton", "precio","cantidad", "devolucion", "anyadirComentario",
	"masComprados"};
	
	private static ArrayList<String> coment = new ArrayList<String>();

	
	/** 
	 * @return IPrepararDatos
	 */
	public static IPrepararDatos getInstance() {
		if (instance == null) {
			instance = new PrepararDatos();
		}		
		return instance;
	}
	
	/** Devuelve la lista con todas las etiquetas para poder recorrer el xml de idiomas.
	 * @return String[] lista con todas las etiquetas contenidas en el idiomas.xml
	 */
	@Override
	public String[] getIdiomaCaract() {
		return idioma_caract;
	}
	
	/** Devuelve el HashMap que contiene las palabras del español
	 * @return HashMap<String, String> contiene las etiquetas asociadas a las palabras en español
	 */
	@Override
	public HashMap<String, String> getEspanyol(){
		return idioma_espanyol;
	}
	
	/** Devuelve el HashMap que contiene las palabras de ingles
	 * @return HashMap<String, String> contiene las etiquetas asociadas a las palabras en ingles
	 */
	@Override
	public HashMap<String, String> getIngles(){
		return idioma_ingles;
	}
	
	/** Busca en el Hashmap de español la palabra correspondiente para hacer la traduccion
	 * @param palabra el identificativo/key para buscar en el hashmap
	 * @return String la palabra correspondiente en español
	 */
	@Override
	public String getPalabraEspanyol(String palabra){
		return idioma_espanyol.get(palabra);
	}
	
	
	/** Busca en el Hashmap de ingles la palabra correspondiente para hacer la traduccion
	 * @param palabra el identificativo/key para buscar en el hashmap
	 * @return String la palabra correspondiente en ingles
	 */
	@Override
	public String getPalabraIngles( String palabra){
		return idioma_ingles.get(palabra);
	}
	/** Craga los datos del xml de idiomas en los hashmap de ingle y de español
	 */
	@Override
	public void cargarDatosXML(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      	try {
			  
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(FILENAME));
			doc.getDocumentElement().normalize();

			NodeList list = doc.getElementsByTagName("idioma");

          	for (int temp = 0; temp < list.getLength(); temp++) {

              Node node = list.item(temp);

              if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
				if (temp == 0){
					for (String i : idioma_caract){
						getIngles().put(i, element.getElementsByTagName(i).item(0).getTextContent());
					}
				}else{
					for (String i : idioma_caract){
						getEspanyol().put(i, element.getElementsByTagName(i).item(0).getTextContent());
					}
				}
			  }
			}
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @param args la clase main para carga de los datos de prueba de la BD
	 */
	public static void main(String[] args) {

		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			//Creamos un cliente
			Cliente cliente1 = new Cliente("12399345K", "usuario", "apellido apellido ", "usuario@gmail.com", "12345r", 655786943,
					"direccion", Genero.MUJER, 45678, "provincia", "localidad");
			pm.makePersistent(cliente1);
			
			//Creamos sus credenciales de pago
			HashMap<String,String> paypal = new HashMap<String,String>();
			paypal.put("usuario@gmail.com", "1234");
			HashMap<String,String> visa = new HashMap<String,String>();
			visa.put("4444333322221111", "1234");
			Pago pago1 = new Pago(cliente1.getDNI(), visa, paypal);
			pm.makePersistent(pago1);

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

			//Creamos las marcas
			Marca marca1 = new Marca("Nike","Marca de ropa y zapatos");
			pm.makePersistent(marca1);
			Marca marca2 = new Marca("Converse","Marca de zapatos");
			pm.makePersistent(marca2);
			Marca marca3 = new Marca("Cartier","Marca de joyeria");
			pm.makePersistent(marca3);

			//Creamos productos (de momento algunos de prueba)
			//HashMap provisional
			HashMap<Colores, Tallas> colorTallaS = new HashMap<>();
			colorTallaS.put(Colores.AZUL, Tallas.S);
			colorTallaS.put(Colores.NEGRO, Tallas.S);
			colorTallaS.put(Colores.AMARILLO, Tallas.S);
			colorTallaS.put(Colores.MORADO, Tallas.S);
			colorTallaS.put(Colores.ROSA, Tallas.S);
			colorTallaS.put(Colores.CIAN, Tallas.S);
			colorTallaS.put(Colores.ROJO, Tallas.S);
			
			//Creamos ArrayList de colores y tallas
			ArrayList<Colores> colores = new ArrayList<>();
			colores.add(Colores.AMARILLO);
			colores.add(Colores.AZUL);
			colores.add(Colores.CIAN);
			colores.add(Colores.MORADO);
			colores.add(Colores.NEGRO);
			
			ArrayList<Tallas> tallas = new ArrayList<>();
			tallas.add(Tallas.XS);
			tallas.add(Tallas.S);
			tallas.add(Tallas.M);
			tallas.add(Tallas.L);
			tallas.add(Tallas.XL);
			
			
			Producto producto1 = new Producto("Chaqueta Vaquera","Chaqueta vaquera azul",10,45,"img/chaquetaVaquera.png",subcategoria1,marca1,colores,tallas,coment);
			pm.makePersistent(producto1);
			Producto producto2 = new Producto("Chaqueta de cuero","Chaqueta de cuero negra",10,35,"img/chaquetaCuero.png",subcategoria1,marca1,colores,tallas,coment);
			pm.makePersistent(producto2);
			Producto producto3 = new Producto("Abrigo de piel","Abrigo de piel marron",10,80,"img/chaquetaPiel.png",subcategoria1,marca1,colores,tallas,coment);
			pm.makePersistent(producto3);
			Producto producto4 = new Producto("Camiseta de tirantes","Camiseta de tirantes azul",5,10,"img/camisetaTirante.png",subcategoria2,marca1,colores,tallas,coment);
			pm.makePersistent(producto4);
			Producto producto5 = new Producto("Pantalon Vaquero","Pantalon vaquero azul",15,30,"img/pantalonVaquero.png",subcategoria3,marca2,colores,tallas,coment);
			pm.makePersistent(producto5);
			Producto producto6 = new Producto("Falda de flores","Falda estampado floral",10,25,"img/faldaFlores.jpg",subcategoria4,marca2,colores,tallas,coment);
			pm.makePersistent(producto6);
			Producto producto7 = new Producto("Vestido negro","Vestido negro de tirantes",10,35,"img/vestidoNegro.png",subcategoria6,marca2,colores,tallas,coment);
			pm.makePersistent(producto7);
			Producto producto8 = new Producto("Camisa blanca","Camisa blanca elegante",10,35,"img/camisaBlanca.png",subcategoria7,marca2,colores,tallas,coment);
			pm.makePersistent(producto8);
			Producto producto10 = new Producto("Deportivas","Deportivas blancas planas converse",10,70,"img/zapatillasDeportivas.png",subcategoria8,marca2,colores,tallas,coment);
			pm.makePersistent(producto10);
			Producto producto11 = new Producto("Botas altas","Botas altas negras con cordones",10,75,"img/botasAltas.png",subcategoria10,marca2,colores,tallas,coment);
			pm.makePersistent(producto11);
			//meter mas productos de zapatos
			Producto producto15 = new Producto("Pendientes de aro","Pendienets de aro bañados en plata",10,20,"img/pendientesAro.png",subcategoria12,marca3,coment);
			pm.makePersistent(producto15);
			Producto producto16 = new Producto("Bolso","Bolso pequeño marron con tira desplegable",10,90,"img/bolso.png",subcategoria13,marca3,coment);
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
