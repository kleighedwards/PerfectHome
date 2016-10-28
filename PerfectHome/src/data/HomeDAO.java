package data;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import entities.Home;

@Transactional
public class HomeDAO {

	@PersistenceContext
	private EntityManager em;

	// Get All Homes
	public List<Home> index() {
		String query = "Select h from Home h";
		List<Home> homes = em.createQuery(query, Home.class).getResultList();

		return homes;
	}

	// Get Home By ID
	public Home show(int id) {
		return em.find(Home.class, id);
	}

	// Add New Home
	public Home create(Home home) {

		em.persist(home);
		em.flush();

		return home;
	}

	// Delete Home
	public void destroy(int id) {
		Home deleteHome = em.find(Home.class, id);

		em.remove(deleteHome);
	}

	public ZillowDTO zillow(String zillowUrl) throws Exception {
		URL url = new URL(zillowUrl);
		System.out.println("In DAO before xml " + url);
		URLConnection conn = url.openConnection();

		Document doc = parseXML(conn.getInputStream());
		NodeList descNode = doc.getElementsByTagName("result");
		
		ZillowDTO zillow = new ZillowDTO();

		for (int i = 0; i < descNode.getLength(); i++) {
			System.out.println("*************************");
			//Zillow ID
			System.out.println(descNode.item(i).getFirstChild().getTextContent());
			zillow.setZillowId(Integer.parseInt(descNode.item(i).getFirstChild().getTextContent()));
			
			// Street Address
			System.out.println(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getFirstChild().getTextContent());
			zillow.setStreet(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getFirstChild().getTextContent());
			
			// Zip Code
			System.out.println(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getFirstChild().getNextSibling().getTextContent());
			zillow.setZip(Integer.parseInt((descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getFirstChild().getNextSibling().getTextContent())));
			
			// City
			System.out.println(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getFirstChild().getNextSibling().getNextSibling().getTextContent());
			zillow.setCity(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getFirstChild().getNextSibling().getNextSibling().getTextContent());
			
			// State
			System.out.println(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			zillow.setState(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			
			// Use Code
			System.out.println(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			zillow.setUseCode(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			
			// Year Built
			System.out.println(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			zillow.setYearBuilt(Integer.parseInt(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent()));
			
			// Total Lot Square Foot
			System.out.println(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			zillow.setTotalSqFoot(Integer.parseInt(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent()));
			
			// Finished Square Foot
			System.out.println(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			zillow.setFinishedSqFoot(Integer.parseInt(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent()));
			
			// Bathrooms
			System.out.println(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			zillow.setBathrooms(Double.parseDouble(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent()));
			
			// Bedrooms
			System.out.println(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			zillow.setBedrooms(Integer.parseInt(descNode.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent()));
			System.out.println("*************************");
		}
		
		System.out.println(zillow);

		return zillow;

	}

	public Document parseXML(InputStream s) throws Exception {
		DocumentBuilderFactory dbf = null;
		DocumentBuilder db = null;
		Document doc = null;

		dbf = DocumentBuilderFactory.newInstance();
		db = dbf.newDocumentBuilder();

		doc = db.parse(s);
		System.out.println("DOC");
		System.out.println(doc);
		
		return doc;
	}
}
