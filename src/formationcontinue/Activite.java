/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formationcontinue;

import net.sf.json.JSONObject;

/**
 *
 * @author Bruno
 */
public class Activite {

    private String description;
    private String categorie;
    private int heures;
    private String date;

    public Activite() {
    }

    public Activite(JSONObject json) {
        this.description = json.getString("description");
        this.categorie = json.getString("categorie");
        this.heures = json.getInt("heures");
        this.date = json.getString("date");
    }

    public String getDescription() {
        return description;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getHeures() {
        return heures;
    }

    public String getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setHeures(int heures) {
        this.heures = heures;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return description + "\n" + categorie + "\n" + heures + "\n" + date + "\n";
    }
}
