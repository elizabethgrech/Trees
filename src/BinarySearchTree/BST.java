package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BST {
	
    private static final String LNB = "["; //leftNodeBorder
    private static final String RNB = "]"; //rightNodeBorder
    
	public static List<String> treePrint2 = new ArrayList<String>(0);
	public static List<String> treePrintRaw = new ArrayList<String>(0);
	public static List<String> treePrintIndented = new ArrayList<String>(0);
	
	public static int level = -1;
	public static String indent = "|-";
	
    
	
/************************************************
 ************************************************
 *               
 *                Node Definition 
 *                
 ************************************************
 ************************************************/
	
	private Node rootNode = null;             // root of BST

    private class Node {
        private Integer value;         // associated data
        private Node left;
        private Node right;

        public Node() {
            this.value = null;
            this.left = null;
            this.right = null;
        }
        
        public Node(int val) {
            this.value = val;
            this.left = null;
            this.right = null;
        }

/************************************************
 *                
 *                Node Definition : Gets
 *                
 ************************************************/
        
        public Integer value(){
        	return value;
        }
        public Node left(){
        	return left;
        }
        
        public Node right(){
        	return right;
        }
        
/************************************************
 *                
 *                Node Definition : Insert
 *                
 ************************************************/
        /**
         *  Recursive Function for traversing the nodes 
         * @param value
         * @return true or false based on success of inserting a new node
         */
        private boolean insert(int value){
        	if(rootNode == null || this.value == null){
        		rootNode = new Node(value);
        		return(true);
        	}
        	else{
        		if(this.value == value){
        			return(false);
        		}
        		if(this.value > value){ //traverse left
        			if(this.left == null){
        				this.left = new Node(value);
        			}
        			else{
        				this.left.insert(value);
        			}
        		}
        		if(this.value < value){ //traverse right
        			if(this.right == null){
        				this.right = new Node(value);
        			}
        			else{
        				this.right.insert(value);
        			}
        		}
        	}
    		return(false);
        }
/************************************************
 *                
 *                Node Definition : Print
 *                
 ************************************************/
            
        	private void printRaw(){
        		level++;
        		
        		//Add array element if none exists
        		if(treePrintRaw.size()-1<level)
        			treePrintRaw.add(level,"");
        		
    			treePrintRaw.set(level, treePrintRaw.get(level)+LNB+this.value+RNB);
    			if(!(this.left == null))
    				this.left.printRaw();
    			if(!(this.right == null))
        			this.right.printRaw();
        			
        		level--;
        		return;
        	}
        
        

/************************************************
 *                
 *                Node Definition : Delete
 *                
 ************************************************/
		public boolean delete(int insertValue){
			
			
			
			return(false);
		}
		
	
    }/** End Node Definition **/

    
    
/************************************************
 ************************************************
 *                
 *                BST Definition
 *                
 ************************************************
 ************************************************/
    
    
	public BST() {
		// TODO Auto-generated constructor stub
		rootNode = new Node();
	}
	
	

/************************************************
 *                
 *                Search
 *                
 ************************************************/

	public Node search(int insertValue){
		return(search(rootNode, insertValue));
	}
	
	/*-------------------------------------------
	 * Traverses the tree in search of a node
	 * containing data to match the search
	 *------------------------------------------*/
	public Node search(Node startNode, int searchValue){
		//Node startNode = rootNode;
		
		while(true)
		{
			if(startNode.value() == searchValue)
				return(startNode);

			if(startNode == null)
				return(null);
			
			if(startNode.value() > searchValue)
				startNode = startNode.right();
			else
				startNode = startNode.left();
			
		}
	}
	

/************************************************
 *                
 *                BST Definition : Insert
 *                
 ************************************************/
	public boolean insert(int insertValue){
		return(rootNode.insert(insertValue));
	}
	
/************************************************
 *                
 *                Delete
 *                
 ************************************************/
	public boolean delete(int insertValue){
		return(rootNode.delete(insertValue));
	}
	
/************************************************
 *                
 *                Print2 - revamp recursion
 *                
 ************************************************/
	public void printRaw(){
		rootNode.printRaw();
		return;
	}
	
	/*public List<String> print2(){
		print2(rootNode);
		return(treePrint2);
	}
	
	public void print2(Node printNode){
		
		level++;
		
		//Add array element if none exists
		if(treePrint2.size()-1<level)
			treePrint2.add(level,"");
		
		
		
		if(nodeNull(printNode))
			treePrint2.set(level, treePrint2.get(level)+LNB+"N"+RNB);
		else{
			treePrint2.set(level, treePrint2.get(level)+LNB+printNode.value().toString()+RNB);
			print2(printNode.left());
			print2(printNode.right());
		}
		
		level--;
		return;
	}
	
	public boolean nodeNull(Node printNode){
		if(printNode == null || ((Integer)printNode.value()) == null)
			return true;
		else
			return false;
	}*/
	
/************************************************
 *                
 *                Main
 *                
 ************************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BST me2 = new BST();



		me2.insert(6);
		me2.insert(3);
		me2.insert(8);
		me2.insert(1);
		me2.insert(6);
		me2.insert(7);
		me2.insert(2);
		me2.insert(9);
		me2.insert(8);
		me2.insert(0);
		me2.insert(5);
		me2.insert(8);
		me2.insert(0);
		me2.insert(0);
		me2.insert(5);
		me2.insert(6);
		me2.insert(0);
		me2.insert(9);
		me2.insert(7);
		me2.insert(6);

		System.out.println("--------Da Raw Tree--------");

		
		//List<String> treePrint2 = me2.print2();
		me2.printRaw();
		List<String> treePrint2 = treePrintRaw;
        for (int i=0; i<treePrint2.size(); i++){
        	System.out.println(treePrint2.get(i)+" ");
        }
	}

}
