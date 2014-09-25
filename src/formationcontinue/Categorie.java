/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formationcontinue;

import java.util.ArrayList;
import java.util.Arrays;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

/**
 *
 * @author Bruno
 */
public class Categorie {

    private ArrayList<String> categorie;
    private int min;
    private int max;
    private String msg;

    public Categorie(String[] categorie, int min, int max, String msg) {
        this.categorie.addAll(Arrays.asList(categorie));
        this.min = min;
        this.max = max;
        this.msg = msg;
    }

    public Categorie(JSONObject json) {
    }
}
