package Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import Views.Iservice;
import Classe.Client;
import Classe.Local;
import Classe.Reservations;

public class Service implements Iservice
{
    private  JSONArray listeReservation= new  JSONArray();
    private  JSONArray listeClient= new  JSONArray();
    private  JSONArray listeLocal = new  JSONArray();
    private jsonFile js = new JsonFile();
    private parser parse = new parser();
	@Override
	public JSONObject createLocal(Local local) 
	{
		 	JSONObject l = new JSONObject();
	        l.put("ref", local.getRef());
	        l.put("localisation", local.getLocalisation());
	        l.put("prix", local.getPrix());
	        l.put("tauxPoc", local.getTauxPoc());
	        l.put("type", local.getType());
	        return l;
	}
	@Override
	public JSONObject createClient(Client client) 
	{
		 	JSONObject c = new JSONObject();
	        c.put("nci", client.getNci());
	        c.put("nomComplet", client.getNomComplet());
	        c.put("tel", client.getTel());
	        c.put("adresse", client.getAdresse());
	        c.put("email", client.getEmail());
	        return c;	
	}
	@Override
	public JSONObject createReservation(Reservations reserv) 
	{
		 	JSONObject r = new JSONObject();
	        r.put("id", reserv.getId());
	        r.put("duree", reserv.getDuree());
	        r.put("etat", reserv.isEtat());
	        r.put("date", reserv.getDate());
	  	    return r;
	}

	@Override
	public JSONArray searchClient(int nci) 
	{
		JSONArray dataClient = new JSONArray();
		JSONArray lList = js.readFile("Clients.json", listeClient);
		lList.forEach( emp -> parse.parseObjectCl( (JSONObject) emp , dataClient, nci));
		return dataClient;
	}
	@Override
	public JSONArray searchLocal(String type) 
	{
		JSONArray dataLocal = new JSONArray();
		JSONArray lList = js.readFile("Locaux.json", listeLocal);
		lList.forEach( emp -> parse.parseObject( (JSONObject) emp , dataLocal, type ,"type"));
		return dataLocal;
	}
	
	@Override
	public JSONArray searchReservation(int id) {
		JSONArray dataReser = new JSONArray();
		JSONArray lList = js.readFile("Reser.json", listeReservation);
		lList.forEach( emp -> parse.parseObjectRs( (JSONObject) emp , dataReser , id,"id"));
		return dataReser;
	}
	@Override
    public void lister(String file) 
	{
		JSONArray lList = js.readFile("Reser.json", listeLocal);
        lList.forEach( emp -> parse.parseObjectAll( (JSONObject) emp) );
        
    }
	@Override
	public void ListerLocalReserByClient(int nci) {
		JSONArray lList = js.readFile("Reser.json", listeReservation);
		lList.forEach( emp -> parse.parseObjectRsClShow( (JSONObject) emp , nci));
		
	}
	@Override
	public void ListerLocalDispo() {
		JSONArray lList = js.readFile("locaux.json", listeLocal);
        lList.forEach( emp -> parse.parseObjectLDispo( (JSONObject) emp) ); 
	}
	
	@Override
	public void Detail(JSONArray local) 
	{
		local.forEach( emp -> parse.parseObjectInfo( (JSONObject) emp) );
	}
	
	public void annulerReservation(int idCancel)
    {
    	JSONArray obj = searchReservation(idCancel);
    	if ( obj != null)
		{
    		obj.forEach( emp -> {
    			emp.replace("etat", "false");
    		});    		
		}
    }
	public JSONArray searchLocalByRef(String ref) {
		JSONArray data = new JSONArray();
		JSONArray lList = js.readFile("Locaux.json", listeLocal);
		lList.forEach( emp -> parse.parseObject( (JSONObject) emp , data, ref,"ref"));
		return data;
	}
	public JSONArray searchLocalByRes(int nci) {
		JSONArray dataReser = new JSONArray();
		JSONArray lList = js.readFile("Reser.json", listeReservation);
		lList.forEach( emp -> parse.parseObjectRsL( (JSONObject) emp , dataReser , nci));
		return dataReser;
	}
	public JSONArray searchReservationByclient(int nci) {
		JSONArray dataReser = new JSONArray();
		JSONArray lList = js.readFile("Reser.json", listeReservation);
		lList.forEach( emp -> parse.parseObjectRsCl( (JSONObject) emp , dataReser , nci));
		return dataReser;
	}
	

}

