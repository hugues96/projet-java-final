package Views;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Classe.Client;
import Classe.Local;
import Classe.Reservations;

public interface Iservice{
    public JSONObject createLocal(Local local);
    public JSONObject createClient(Client client);
    public JSONObject createReservation(Reservations reserv);

    public JSONArray searchClient(int nci);
    public JSONArray searchLocal(String ref);
    public JSONArray searchReservation(int id);   
    public void lister(String file);
    public void ListerLocalDispo();
    public void showDetailLocal(JSONArray local);
	public void ListerLocalReserByClient(int nci);
}
