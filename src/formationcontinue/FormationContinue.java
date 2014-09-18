/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formationcontinue;

import net.sf.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Bruno
 */
public class FormationContinue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException  {
        // TODO code application logic here
        Usager user = new Usager(JSONObject.fromObject(FileReader.loadFileIntoString("json/declaration.json", "UTF-8")));
        System.out.println(user);
    
    }
    
}
