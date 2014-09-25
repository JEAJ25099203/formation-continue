/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formationcontinue;

import java.util.ArrayList;
import java.util.Arrays;
import net.sf.json.JSONObject;

/**
 *
 * @author Bruno
 */
public class Usager {

    private final String CYCLE_ACTUEL = "2012-2014";
    private final String DATE_MIN = "2012-04-01";
    private final String DATE_MAX = "2014-04-02";
    private final String[] CATEGORIES_RECONNUES = {"cours", "atelier", "séminaire", "colloque", "conférence", "lecture dirigée", "présentation", "groupe de discussion", "projet de recherche", "rédaction professionnelle"};
    private final int CYCLE_PRECEDENT_MAX = 7;
    private final int COURS_HEURES_MIN = 17;
    private final String[] COURS_RECONNUS = {"cours", "atelier", "séminaire", "colloque", "conférence", "lecture dirigée"};
    private final int PRESENTATION_HEURES_MAX = 23;
    private final int GROUPE_HEURES_MAX = 17;
    private final int RECHERCHE_HEURES_MAX = 23;
    private final int REDACTION_HEURES_MAX = 17;
    private final int CHAQUE_HEURES_MIN = 1;
    private final int TOTAL_HEURES_MIN = 40;
    private String noPermis, cycle;
    private int heuresPrecedent, heuresTotal;
    private boolean complet;
    ArrayList<String> erreurs = new ArrayList<>();
    private ArrayList<Activite> activites = new ArrayList<>();

    ////
    public Usager() {
    }

    public Usager(JSONObject json) {
        this.noPermis = json.getString("numero_de_permis");
        this.cycle = json.getString("cycle");
        this.heuresPrecedent = json.getInt("heures_transferees_du_cycle_precedent");

        for (Object i : json.getJSONArray("activites")) {
            this.activites.add(new Activite((JSONObject) i));
        }
    }

    //// SETTERS ////
    public void setNoPermis(String noPermis) {
        this.noPermis = noPermis;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public void SetHeuresPrecedent(int heuresPrecedent) {
        this.heuresPrecedent = heuresPrecedent;
    }

    public void setComplet(boolean complet) {
        this.complet = complet;
    }

    public void setActivites(ArrayList<Activite> activites) {
        this.activites = activites;
    }

    //// GETTERS ////
    public String getNoPermis() {
        return noPermis;
    }

    public String getCycle() {
        return cycle;
    }

    public boolean getComplet() {
        return complet;
    }

    public int getHeuresPrecedent() {
        return heuresPrecedent;
    }

    public int getHeuresTotal() {
        return this.heuresTotal;
    }

    public ArrayList<String> getErreurs() {
        return erreurs;
    }

    public ArrayList<Activite> getActivites() {
        return activites;
    }

    //// VALIDATION ////
    public void validation() {
        this.complet = validerCycle();
        this.complet = validerDate();
        this.complet = validerCategorie();
        this.heuresTotal = validerCours();
        this.complet = validerHeuresMin();
        this.complet = validerHeuresTotal();
    }

    public boolean validerCycle() {
        return this.cycle.equals(CYCLE_ACTUEL);
    }

    public boolean validerDate() {
        boolean i = false;
        for (Activite j : activites) {
            if (j.getDate().compareTo(DATE_MIN) > 0 && j.getDate().compareTo(DATE_MAX) < 0) {
                i = true;
            }
        }
        return i;
    }

    public boolean validerCategorie() {
        boolean i = false;
        for (Activite j : activites) {
            boolean l = false;
            for (String k : CATEGORIES_RECONNUES) {
                if (j.getCategorie().equals(k)) {
                    l = true;
                }
            }
            if (!l) {
                this.erreurs.add("L'activité " + j.getDescription() + " est dans une catégorie non reconnue. Elle sera ignorée.");
            }
        }
        return i;
    }

    public int validerCyclePrecedent() {
        int i = 0;
        if (this.heuresPrecedent > 0 && this.heuresPrecedent <= CYCLE_PRECEDENT_MAX) {
            i += this.heuresPrecedent;
        } else if (this.heuresPrecedent > CYCLE_PRECEDENT_MAX) {
            i = 7;
            this.erreurs.add("Seulement 7h transférées du cycle précédent seront considérées.");
        }
        return i;
    }

    public int validerCours() {

        int cours = validerCyclePrecedent();
        int presentation = 0;
        int groupe = 0;
        int recherche = 0;
        int redaction = 0;

        for (Activite j : activites) {

            for (String k : COURS_RECONNUS) {
                if (j.getCategorie().equals(k)) {
                    cours += j.getHeures();
                }
            }

            if (j.getCategorie().equals("présentation")) {
                presentation += j.getHeures();
            }

            if (j.getCategorie().equals("groupe de discussion")) {
                groupe += j.getHeures();
            }

            if (j.getCategorie().equals("projet de recherche")) {
                recherche += j.getHeures();
            }

            if (j.getCategorie().equals("rédaction professionnelle")) {
                redaction += j.getHeures();
            }

        }

        if (cours < 17) {
            this.erreurs.add("Il y a moins de 17h de déclarées dans les catégories suivantes : " + Arrays.toString(COURS_RECONNUS) + ".");
        }

        if (presentation > PRESENTATION_HEURES_MAX) {
            presentation = PRESENTATION_HEURES_MAX;
        }

        if (groupe > GROUPE_HEURES_MAX) {
            groupe = GROUPE_HEURES_MAX;
        }

        if (recherche > RECHERCHE_HEURES_MAX) {
            recherche = RECHERCHE_HEURES_MAX;
        }

        if (redaction > REDACTION_HEURES_MAX) {
            redaction = REDACTION_HEURES_MAX;
        }

        return cours + presentation + groupe + recherche + redaction;

    }

    public boolean validerHeuresMin() {
        boolean i = true;
        for (Activite j : activites) {
            if (j.getHeures() < CHAQUE_HEURES_MIN) {
                i = false;
            }
        }
        return i;
    }

    public boolean validerHeuresTotal() {
        if (heuresTotal < TOTAL_HEURES_MIN) {
            this.erreurs.add("Il manque " + (TOTAL_HEURES_MIN - heuresTotal) + " heures de formation pour compléter le cycle.");
        }
        return heuresTotal >= TOTAL_HEURES_MIN;
    }

    @Override
    public String toString() {
        return noPermis + "\n" + cycle + "\n" + heuresPrecedent + "\n" + activites;
    }
}
