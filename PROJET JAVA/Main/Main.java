package Main;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Library.Validator;
import Classe.Appartements;
import Classe.Chambres;
import Classe.Client;
import Classe.Local;
import Classe.Reservations;

import java.util.List;

@SuppressWarnings("unused")
public class Main 
{
    public static Scanner scanner = new Scanner(System.in);
	public static void main(String args[])
    {

        Service service = new Service();
        Validator val = new Validator();
        jsonFile file  = new jsonFile();
        int choix;
        do
        {
            choix=menu();
			switch(choix)
            {	
                case 1:
                	do 
                	{
                		choix=menu1ocal();
                    	switch(choix)
                    	{
                    	case 1:
                    		System.out.println("Veuillez saisir la localisation ");
                        	String localisation = scanner.nextLine();
                        	System.out.println("Veuillez saisir le prix  ");
                        	int prix = Integer.parseInt(scanner.nextLine());
                        	System.out.println("Veuillez saisir le taux ");
                        	float  taux = Float.parseFloat(scanner.nextLine());
                        	System.out.println("Veuillez saisir la dimension de la chambre ");
                        	int dimension =Integer.parseInt(scanner.nextLine());
                    		Local chambre = new Chambres(localisation,prix,taux,dimension);
                    		JSONObject  l = service.createLocal(chambre);
                    		l.put("dimension", ((Chambres) chambre).getDimension());
                            JSONArray json =  new JSONArray();
                            JSONArray data = file.readFile("Locaux.json",json);
                            data.add(l);
                            file.saveFile(data,"Locaux.json");
                    		break;
                    	case 2:
                    		System.out.println("Veuillez saisir la localisation ");
                        	localisation = scanner.nextLine();
                        	System.out.println("Veuillez saisir le prix  ");
                        	prix = Integer.parseInt(scanner.nextLine());		
                        	System.out.println("Veuillez saisir le taux ");
                            taux = Float.parseFloat(scanner.nextLine());
                        	System.out.println("Veuillez saisir le nombre de piece de la chambre ");
                        	int nbrePiece =Integer.parseInt(scanner.nextLine());
                    		Local app = new Appartements(localisation,prix,taux,nbrePiece);
                    		JSONObject  ll = service.createLocal(app);
                    		ll.put("nombre_de_piece", ((Appartements) app).getNbrePiece());
                            json =  new JSONArray();
                            data = file.readFile("Locaux.json",json);
                            data.add(ll);
                            file.saveFile(data,"Locaux.json");
                    		break;
                    	case 3:
                    		break;
                    	default :
                    		System.out.println("Mauvais choix :( ");
                            break;
                    	}
                	}while(choix!=3);
                    break;
                case 2:
                	do 
                	{
                		choix=menulocal();
                    	switch(choix)
                    	{
                    	case 1:
							
							  data = service.searchLocal("Chambres"); 
							  if(!data.isEmpty()) 
							  { 
								  data.forEach( emp -> {
									  System.out.println(emp);
								  } );
							  }
                    		break;
                    	case 2:
                    		
                    		data = service.searchLocal("appartements");
                    		if (!data.isEmpty())
                    		{
                    			 data.forEach( emp -> {
									  System.out.println(emp);
								  } );
                    		}
                    		break;
                    	case 3:
                    		break;
                    	default :
                    		System.out.println("Mauvais choix :( ");
                            break;
                    	}
                	}while(choix!=3);
                    break;
                case 3:
                	System.out.println("veuillez entrez le nci ");	
                	int nci = Integer.parseInt(scanner.nextLine());
                	service.ListerLocalReserByClient(nci);
                    break;
                case 4:
                	System.out.println("veuillez entrez la reference du local en format RefCCCC ");
                	String ref = scanner.nextLine();
                	JSONArray data = service.searchLocalByRef(ref);
                	service.Detail(data);
                    break;
                case 5:
                	Client client = new Client();
                    Reservations res = new Reservations();   
                    	System.out.println("Veuillez saisir le nci du client");
                        nci  = Integer.parseInt(scanner.nextLine());
	                    System.out.println("Veuillez saisir la reference du local ");
	                    ref  = scanner.nextLine();
	                    js	=  service.searchClient(nci);
	                    if (js.isEmpty()) 
	                    {
	                    	System.out.println("Veuillez vous inscrire "+"\n Veuillez saisir votre nci ");
                        	nci = Integer.parseInt(scanner.nextLine()); 
                        	System.out.println("Veuillez saisir votre nom Complet ");
                        	String nom = scanner.nextLine();	
                        	System.out.println("Veuillez saisir votre numéro de téléphone ");
                            int tel = Integer.parseInt(scanner.nextLine()); 
                        	System.out.println("Veuillez saisir l'adresse ");
                        	String adresse =scanner.nextLine();
                        	System.out.println("Veuillez saisir votre email ");
                        	String email =scanner.nextLine();                  	
		                    client = new Client(nci,nom,tel,adresse,email);
		                    js	=  service.searchLocal(ref);
		                    if (!js.isEmpty()) 
		                    {
			                    JSONObject local = (JSONObject) js.get(0);
			                    JSONObject obj = res.setClient(client,local);
			                    JSONArray json =  new JSONArray();
			                    JSONArray jsonR =  new JSONArray();
			                    JSONArray jsonL =  new JSONArray();
			                    JSONArray dataR = file.readFile("Reser.json",json);
			                    dataR.add(obj);
			                    file.saveFile(dataR,"Reser.json");
			                    JSONArray jsonArray = service.searchReservationByclient(client.getNci());
			                    JSONObject cli = service.createClient(client);
			                    cli.put("reservation", jsonArray);     
			                    JSONArray jsonArrayL = service.searchLocalByRes(client.getNci());
			                    local.put("reservation", jsonArrayL);
			                    JSONArray donnee = file.readFile("Clients.json", jsonR);
			                    donnee.add(cli);
			                    file.saveFile(donnee,"Clients.json");
			                    JSONArray dataL = file.readFile("Locaux.json", jsonL);
			                    dataL.add(local);
			                    file.saveFile(dataL,"Locaux.json");
		                    }
		                    break;
	                   }else 
	                   {
	                    	  JSONObject cl = (JSONObject) js.get(0);
	                    	  js	=  service.searchLocal(ref);
	                          if (!js.isEmpty()) 
	                          {
		                          JSONObject l = (JSONObject) js.get(0);
		                          JSONObject obj = res.setClientExist(cl,l);
		                         
		                          JSONArray json =  new JSONArray();
		                          JSONArray jsonR =  new JSONArray();
		                          JSONArray jsonL =  new JSONArray();
		                          JSONArray dataR = service.readFile("Reser.json",json);
		                          dataR.add(obj);
		                          service.saveFile(dataR,"Reser.json");
		                          String nc = (String) cl.get("nci").toString();
		                          nci= Integer.parseInt(nc);
		                          JSONArray jsonArray = service.searchReservationByclient(nci);
		                          cl.put("reservation", jsonArray);
		                          JSONArray jsonArrayL = service.searchLocalByRes(nci);
		                          l.put("reservation", jsonArrayL);
		                          JSONArray donnee = file.readFile("Clients.json", jsonR);
		                          donnee.add(cl);
		                          file.saveFile(donnee,"Clients.json");
		                          JSONArray dataL = file.readFile("Locaux.json", jsonL);
		                          dataL.add(l);
		                          file.saveFile(dataL,"Locaux.json");
	                          }
	                    }
                    break;
                case 6:	
                	service.lister("Reser.json");
                	System.out.println("\n\nVeuillez saisir l' id de la reservation ");
                	t = scanner.nextLine();
                	if (val.estVide(t) || !val.isNumeric(t)) {
                		t =scanner.nextLine();
                	}
                	int id = Integer.parseInt(t);
                	service.annulerReservation(id);
                    break;
                case 7:
                	service.ListerLocalDispo();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Mauvais choix : ");
                    break;
                
            }
        }while(choix!=8);
    }
	public static int menu()
    {
    	 Validator val = new Validator();
        int choix;
        System.out.println
        ("\n Menu" 
        + "\n 1- Ajouter un local "  
        + "\n 2- Lister les locaux par type "
        + "\n 3- Lister les locaux réservés par un client"
        + "\n 4- Voir les détails d'un local "
        + "\n 5- Faire une réservation "
        + "\n 6- Annuler une réservation "
        + "\n 7- Lister les locaux disponibles "
        + "\n 8- Quitter ");
        System.out.println("Faites votre choix : ");
        String t = scanner.nextLine();
    	if (!val.estVide(t) || !val.isNumeric(t)) {
    		t =scanner.nextLine();
    	}
        choix = Integer.parseInt(t);
        return choix;
    } 
	public static int menu1ocal()
    {
    	 Validator val = new Validator();
    	int type;
        System.out.println
        ("\nle type de local"
        		+ "\n" 
        + "\n 1- chambres "  
        + "\n 2- Appartements"
        + "\n 3- Quitter");
        System.out.println("Faites votre choix : ");
        String t = scanner.nextLine();
    	if (!val.estVide(t) || !val.isNumeric(t)) {
    		t =scanner.nextLine();
    	}
        type = Integer.parseInt(t);
        return type;
    }
}
