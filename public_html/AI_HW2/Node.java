/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AI_HW2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Node{
    
    int movie;
    int user;
    int heuristic;
    int dissimilarity;
    int heu_diss;
    Node parent;
    ArrayList<Node> neighbours= new ArrayList<Node>();
    
    public String display (){
     return ("m" + this.movie );
 }
}
