package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BST {

	
/************************************************
 *               
 *                Node Definition 
 *                
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
         * 
         * @param insertValue
         * @return true or false based on success of inserting a new node
         */
        public boolean insert(int insertValue){
        	return(insert(new Node(insertValue)));
        }
        /**
         * 
         * @param insertNode
         * @return true or false based on success of inserting a new node
         */
        public boolean insert(Node insertNode){
    		if(rootNode == null || rootNode.value() == null){
    			rootNode = insertNode;
    			return(true);
    		}
        	return(insert(rootNode, insertNode));
        }
        
        /**
         *  Recursive Function for traversing the nodes 
         * @param travNode
         * @param insertNode
         * @return true or false based on success of inserting a new node
         */
        private boolean insert(Node travNode, Node insertNode){
    		
    		
    		if(travNode.value() > insertNode.value()){
    			if(nodeNull(travNode.left())){
    				//this.left = insertNode;
    				travNode.left = insertNode;
    				
    				return(true);
    			}else{
    				return(insert(travNode.left(), insertNode));
    			}
    		}
    		else {
    			if(travNode.value() < insertNode.value()){
    				if(nodeNull(travNode.right())){
        				//this.right = insertNode;
        				travNode.right = insertNode;
    					return(true);
    				}else{
    					return(insert(travNode.right(), insertNode));
    				}
    			}
    		}
    		return(false);
        	
        }
        
    }

    
/************************************************
 *                
 *                BST Definition
 *                
 ************************************************/

    private static final String LNB = "["; //leftNodeBorder
    private static final String RNB = "]"; //rightNodeBorder
    
    
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
	
/************************************************
 *                
 *                Print2 - revamp recursion
 *                
 ************************************************/
	List<String> treePrint2 = new ArrayList<String>(0);
	List<String> treePrintIndented = new ArrayList<String>(0);
	public static int level = -1;
	public static String indent = "|-";
	
	public List<String> print2(){
		print2(rootNode);
		return(treePrint2);
	}

/*	public List<String> printIndented(){
		print2(rootNode);
		
        for (int i=0; i<treePrint2.size(); i++){
        	System.out.println(treePrint2.get(i)+" ");
        	
        	indent += "--";
        	
        }
		//indent = indent.substring(0, indent.length()-2);
		return(treePrint2);
	}*/
	
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
	}
	
/************************************************
 *                
 *                Main
 *                
 ************************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//BST me = new BST();
		BST me2 = new BST();

		/*me.insert(6);
		me.insert(3);
		me.insert(8);
		me.insert(1);
		me.insert(6);
		me.insert(7);
		me.insert(2);
		me.insert(9);
		me.insert(8);
		me.insert(0);
		me.insert(5);
		me.insert(8);
		me.insert(0);
		me.insert(0);
		me.insert(5);
		me.insert(6);
		me.insert(0);
		me.insert(9);
		me.insert(7);
		me.insert(6);*/
		
		Random rand = new Random();
		rand.ints(100);
		for(int i=0; i<50; i++){
			//System.out.println(me.insert(rand.nextInt(10)));
			me2.insert(rand.nextInt(100));
		}
		
		//me.print();
		
		


		
/*		me2.insert(6);
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
		me2.insert(6);*/

		System.out.println("--------Da Raw Tree--------");

		
		List<String> treePrint2 = me2.print2();
        for (int i=0; i<treePrint2.size(); i++){
        	System.out.println(treePrint2.get(i)+" ");
        }
	}

}
