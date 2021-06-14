package Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class parser
{
	private static void parseObject(JSONObject object, JSONArray data , String typeLocal , String key ) 
 	{
	     String type = (String) object.get(key);
	     if (type.compareToIgnoreCase(typeLocal)==0)
	     {
	    	 data.add(object);
	     }
    }
 	private static void parseObjectCl(JSONObject object, JSONArray data , int nci ) 
 	{   
	     String ncI = (String) object.get(key).toString();
	     int cle = Integer.parseInt(ncI);
	     if (cle == nci)
	     {
	    	 data.add(object);
	     }
    }
	private static void parseObjectRsCl(JSONObject object, JSONArray data , int nci) 
		{
		     JSONObject ob = (JSONObject) object.get("client");
		     JSONObject obL = (JSONObject) object.get("local");
		     String nc = (String) ob.get("nci").toString();
		     int cle = Integer.parseInt(nc);
		     if (cle == nci)
		     {
		    	  Object id =  object.get("id").toString();
		    	  Object duree =  object.get("duree");
		    	  Object etat = object.get("etat");
		    	  Object date =  object.get("date");
				    	 JSONObject Obt = new JSONObject();
				    	 Obt.put("id",id);
				    	 Obt.put("duree",duree);
				    	 Obt.put("etat",etat);
				    	 Obt.put("date",date);
				    	 JSONObject Ob = new JSONObject();
				    	 Ob.put("reservation", Obt);
				    	 Ob.put("local",obL);
				    	 data.add(Ob);
		     }
		}
	private static void parseObjectRsL(JSONObject object,JSONArray data , int nci) 
	{
	     JSONObject ob = (JSONObject) object.get("client");
	     String nc = (String) ob.get("nci").toString();
	     int cle = Integer.parseInt(nc);
	     if (cle == nci)
	     {
	    	  Object id =  object.get("id").toString();
	    	  Object duree =  object.get("duree");
	    	  Object etat = object.get("etat");
	    	  Object date =  object.get("date");
			    	 JSONObject Obt = new JSONObject();
			    	 Obt.put("id",id);
			    	 Obt.put("duree",duree);
			    	 Obt.put("etat",etat);
			    	 Obt.put("date",date);
			    	 JSONObject Ob = new JSONObject();
			    	 Ob.put("reservation", Obt);
			    	 Ob.put("client", ob);			    	
			    	 data.add(Ob);
	     }
	}
	private static void parseObjectRsClShow(JSONObject object , int nci) 
		{
		     JSONObject ob = (JSONObject) object.get("client");
		     JSONObject obL = (JSONObject) object.get("local");
		     String nc = (String) ob.get("nci").toString();
		     int cle = Integer.parseInt(nc);
		     if (cle == nci)
		     {	    	
				 System.out.println(obL);
		     }
		}
 	public static void parseObjectInfo(JSONObject object) 
 	{
		String ref = (String) object.get("ref");
		String localisation = (String) object.get("localisation"); 
		Object prix =  object.get("prix"); 
		Object tauxPoc = object.get("tauxPoc");
		String type = (String) object.get("type");
		System.out.println
		(		"):"
				+ "\n \t ref : " + ref 
				+"\n \t localisation : "+localisation
				+"\n \t prix : "+prix
				+"\n \t tauxPoc : "+tauxPoc
				+"\n \t type : "+type
				+"\n :("
		);
 	}
 	public static void parseObjectAll(JSONObject object) 
 	{
	     System.out.println(object);
    }
	public static void parseObjectLDisp(JSONObject object , JSONArray data) 
 	{
	     Object key =  object.get("reservation");
	     if (key == null) {
	    	 System.out.println("Les locaux disponibles "
	    	 		+ "\n\n");
	    	 System.out.println("\t\t"+object);
	    	 data.add(object);
	     }
    }
 	public static void parseObjectLDispo(JSONObject object) 
 	{
 		 Object key =  object.get("reservation");
	     if (key == null) {
	    	 System.out.println("Les locaux disponibles "
	    	 		+ "\n\n");
	    	 System.out.println("\t\t"+object);
	     }
	}
}
