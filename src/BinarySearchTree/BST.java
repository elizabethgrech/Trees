package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BST {
	
    private static final String LNB = "["; //leftNodeBorder
    private static final String RNB = "]"; //rightNodeBorder
    private static final int LEVELSTART = 0;
    
	public static List<String> treePrint2 = new ArrayList<String>(0);
	public static List<String> treePrintRaw = new ArrayList<String>(0);
	public static List<String> treePrintIndented = new ArrayList<String>(0);
	
	public static String indent = "|-";
	
    
	
/************************************************
 ************************************************
 *               
 *                Node Definition 
 *                
 ************************************************
 ************************************************/
	
	private Node rootNode;             // root of BST

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
    		return(false);
        }
/************************************************
 *                
 *                Node Definition : Delete
 *                
 ************************************************/
		public boolean delete(int deleteValue){
    		if(this.value > deleteValue){ //traverse left
    			if(this.left == null){
    				return false;
    			}
    			else
    			{
    				if(this.left.value == deleteValue)
    				{
    					//Both Children are Null
    					if(this.left.left == null && this.left.right == null)
    					{
    						this.left = null;
    						return true;
    					}
    					//Left is null
    					if(this.left.left == null && this.left.right != null)
    					{
    						this.left = this.left.right;
    						return true;
    					}
    					//Right is null
    					if(this.left.left != null && this.left.right == null)
    					{
    						this.left = this.left.left;
    						return true;
    					}
    					//Neither Child is null
    					if(this.left.left != null && this.left.right != null)
    					{
    						this.left.value = this.left.right.findMin();
    						return this.left.right.delete(this.left.value);
    					}
						return true;
    				}
    				else
    				{
	    				return this.left.delete(deleteValue);
    				}
    			}
    		}
    		if(this.value < deleteValue){ //traverse right
    			if(this.right == null){
    				return false;
    			}
    			else
    			{
    				if(this.right.value == deleteValue)
    				{
    					//Both Children are Null
    					if(this.right.left == null && this.right.right == null)
    					{
    						this.right = null;
    						return true;
    					}
    					//Left is null
    					if(this.right.left == null && this.right.right != null)
    					{
    						this.right = this.right.right;
    						return true;
    					}
    					//Right is null
    					if(this.right.left != null && this.right.right == null)
    					{
    						this.right = this.right.left;
    						return true;
    					}
    					//Neither Child is null
    					if(this.right.left != null && this.right.right != null)
    					{
    						this.right.value = this.right.right.findMin();
    						return this.right.right.delete(this.right.value);
    					}
						//return true;
    				}
    				else
    				{
	    				return this.right.delete(deleteValue);
    				}
    			}
    		}
			return(false);
		}
		
		private int findMin(){
			if(left != null)
				return left.findMin();
			return value;
		}
		

/************************************************
 *                
 *                Node Definition : Search
 *                
 ************************************************/
	/*-------------------------------------------
	 * Traverses the tree in search of a node
	 * containing data to match the search
	 *------------------------------------------*/
		private boolean search(int searchValue){
			//Node startNode = rootNode;
			
			if(this.value == searchValue)
			{
				System.out.println("Search Value Matched");
				return true;
			}
    		if(this.value > searchValue){ //traverse left
				System.out.println("Trav left on : "+searchValue);
    			if(this.left == null){
    				return false;
    			}
    			else{
    				return this.left.search(searchValue);
    			}
    		}
    		if(this.value < searchValue){ //traverse right
				System.out.println("Trav Right on : "+searchValue);
    			if(this.right == null){
    				return false;
    			}
    			else{
    				return this.right.search(searchValue);
    			}
    		}
			return false;
		}

/************************************************
 *                
 *                Node Definition : Print
 *                
 ************************************************/
		            
		private void printRaw(int level){
			//TODO debug why it prints N on root sometimes
			
			//Add array element if none exists
			if(treePrintRaw.size()-1<level)
				treePrintRaw.add(level,"");
			
			//treePrintRaw.set(level, treePrintRaw.get(level)+LNB+this.value+RNB);
			treePrintRaw.set(level, treePrintRaw.get(level)+LNB+String.format("%03d", this.value)+RNB);
			if(!(this.left == null))
			{
				this.left.printRaw(level+1);
			}
			else
			{
				treePrintRaw.set(level, treePrintRaw.get(level)+LNB+" N "+RNB);
			}
			if(!(this.right == null))
			{
				this.right.printRaw(level+1);
			}
			else
			{
				treePrintRaw.set(level, treePrintRaw.get(level)+LNB+" N "+RNB);
			}
				
			return;
		}
		
		private void printIndent(int level){
				//System.out.print(level+" - ");
			System.out.println("Level:"+level+"  -->"+String.format("%"+(level+5)+"s",""+LNB+String.format("%03d", this.value)+RNB));
			if(!(this.left == null))
			{
				//System.out.print("Trav Left");
				this.left.printIndent(level+1);
			}
			else
			{
				System.out.println("Level:"+level+"  -->"+String.format("%"+(level+5)+"s",LNB+" N "+RNB));
			}
			if(!(this.right == null))
			{
				//System.out.print("Trav right");
				this.right.printIndent(level+1);
			}
			else
			{
				//System.out.print("Null");
				System.out.println("Level:"+level+"  -->"+String.format("%"+(level+6)+"s",LNB+" N "+RNB));
			}
			//System.out.println("Returning up");
			return;
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
		//rootNode = new Node();
	}
	
	

/************************************************
 *                
 *                Search
 *                
 ************************************************/

	public boolean search(int insertValue){
		//return(search(rootNode, insertValue));
		return(rootNode.search(insertValue));
	}
	
/************************************************
 *                
 *                BST Definition : Insert
 *                
 ************************************************/
	public boolean insert(int insertValue){
    	if(rootNode == null){
    		rootNode = new Node(insertValue);
    		return(true);
    	}
    	else{
    		return(rootNode.insert(insertValue));
    	}
	}
	
/************************************************
 *                
 *                Delete
 *                
 ************************************************/
	public boolean delete(int insertValue){
		if(rootNode == null)
			return false;
		return(rootNode.delete(insertValue));
	}
	
/************************************************
 *                
 *                Print
 *                
 ************************************************/
	public void printRaw(){
		rootNode.printRaw(LEVELSTART);
		return;
	}
	public void printIndent(){
		rootNode.printIndent(LEVELSTART);
		return;
	}
/************************************************
 *                
 *                Main
 *                
 ************************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BST me2 = new BST();



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

		Random rand = new Random();
		rand.ints(100);
		for(int i=0; i<10; i++){
			//System.out.println(me.insert(rand.nextInt(10)));
			me2.insert(rand.nextInt(100));
		}
		//me2.insert(50);
		
		System.out.println("--------Da Raw Tree--------");

		
		//List<String> treePrint2 = me2.print2();
		me2.printRaw();
		List<String> treePrint2 = treePrintRaw;
        for (int i=0; i<treePrint2.size(); i++){
        	System.out.println(treePrint2.get(i)+" ");
        }

        System.out.println("\nDoes 50 exist?");
        System.out.println(me2.search(50));
        
        System.out.println("\nTrying to delete 50");
        System.out.println(me2.delete(50));

		System.out.println("\n--------Da Raw Tree--------");
        treePrintRaw = new ArrayList<String>(0);
		me2.printRaw();
		List<String> treePrint3 = treePrintRaw;
        for (int i=0; i<treePrint3.size(); i++){
        	System.out.println(treePrint3.get(i)+" ");
        }
        

		System.out.println("\n--------Indented Tree--------");
        me2.printIndent();
	}

}
