/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formationcontinue;

import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Bruno
 */
public class Usager{

	private String noPermis;
	private String cycle;
	private int heuresPrecedent;
	private ArrayList<Activite> activites = new ArrayList<Activite>();

	public Usager(){
	
	}
        
        public Usager(JSONObject json){
            this.noPermis = json.getString("numero_de_permis");
            this.cycle = json.getString("cycle");
            this.heuresPrecedent = json.getInt("heures_transferees_du_cycle_precedent");
            
            for(Object i : json.getJSONArray("activites")){
                this.activites.add(new Activite((JSONObject)i));
            }
        }

	public String getNoPermis(){
		return noPermis;
	}

	public String getCycle(){
		return cycle;
	}

	public int getHeuresPrecedent(){
		return heuresPrecedent;
	}

	public ArrayList<Activite> getActivites(){
		return activites;
	}

	public void setNoPermis(String noPermis){
		this.noPermis = noPermis;
	}

	public void setCycle(String cycle){
		this.cycle = cycle;
	} 

	public void SetHeuresPrecedent(int heuresPrecedent){
		this.heuresPrecedent = heuresPrecedent;
	}

	public void setActivites(ArrayList<Activite> activites){
		this.activites = activites;
	}
        
        @Override
        public String toString(){  
            return noPermis + "\n" + cycle + "\n" + heuresPrecedent + "\n" + activites;
        }
}
