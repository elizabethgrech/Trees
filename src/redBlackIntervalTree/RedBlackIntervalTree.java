/*
 * Write code that implements a data structure that can efficiently discover all ranges that contain a specific value. 
 * As an example, this could be used to find all plays running on broadway on a specific date. 
 * You should implement this using an augmented red-black tree along the lines discussed in class. 
 * You may use the programming language of your choice. Upload the source code.
 */
/*
 * RedBlack Tree Rules
 * 1) Every node is either red or black
 * 2) The root is black
 * 3) Every leaf (NIL) is black
 * 4) If a node is red, then both its children are black
 * 5) For each node, all simple paths from the node to descendant leaves contain the same number of black nodes.
 */
package redBlackIntervalTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//import nodes.Node;

public class RedBlackIntervalTree {
	
    private static final String LNBB = "["; //leftNodeBorder Black
    private static final String RNBB = "]"; //rightNodeBorder Black
    private static final String LNBR = "{"; //leftNodeBorder Red
    private static final String RNBR = "}"; //rightNodeBorder Red
    private static final int LEVELSTART = 0;
    
	public static List<String> treePrint2 = new ArrayList<String>(0);
	public static List<String> treePrintRaw = new ArrayList<String>(0);
	public static List<String> treePrintIndented = new ArrayList<String>(0);
	

	
	private Node rootNode;             // root of RBST

/************************************************
 ************************************************
 *               
 *                Node Definition 
 *                
 ************************************************
 ************************************************/
    private class Node {
        private Integer value;         // associated data
		private boolean isRed; //if not red it is black
        private Node left;
        private Node right;
        private Node parent;

        public Node() {
            this.value = null;
            this.left = null;
            this.right = null;
            this.isRed = true;
        }
        
        public Node(int val) {
            this.value = val;
            this.left = null;
            this.right = null;
            this.isRed = true;
            this.parent = null;
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
    				this.left.parent = this;
    				this.left.validate();
    			}
    			else{
    				this.left.insert(value);
    			}
    		}
    		if(this.value < value){ //traverse right
    			if(this.right == null){
    				this.right = new Node(value);
    				this.right.parent = this;
    				this.right.validate();
    			}
    			else{
    				this.right.insert(value);
    			}
    		}
    		return(false);
        }
/************************************************
 *                
 *                Node Definition : Insert
 *                
 ************************************************/
        public boolean validate(){
        	if(this.parent != null && this.parent.parent != null && this.parent.isRed)
        	{
        		if(this.parent.parent.right !=null && this.parent.parent.left !=null
        				&& this.parent.parent.right.isRed && this.parent.parent.left.isRed)//uncle red & parent are red
        		{
        			this.parent.parent.right.isRed = false;//set uncle and parent to black
        			this.parent.parent.left.isRed = false;//set uncle and parent to black
        			this.parent.parent.isRed = true;//set grandparent to red
        			this.parent.parent.validate();//run validate for new x
        		}
        		else //uncle is black
        		{
        			
        			if(this.parent.parent.left !=null && this.parent.parent.left.left !=null
        					&& this == this.parent.parent.left.left)//left left case
        			{
            			this.parent.parent.rotateRight();
            			this.parent.isRed = this.parent.right.isRed;
            			this.parent.right.isRed = !this.parent.right.isRed;
        				
        			}
        			else if(this.parent.parent.left !=null && this.parent.parent.left.right !=null
        					&& this == this.parent.parent.left.right)//left right case
        			{
        				this.parent.rotateLeft();
            			this.parent.parent.rotateRight();
            			this.parent.isRed = this.parent.right.isRed;
            			this.parent.right.isRed = !this.parent.right.isRed;
        				
        			}
        			else if(this.parent.parent.right !=null && this.parent.parent.right.right !=null
        					&& this == this.parent.parent.right.right)//right right case
        			{
        				this.parent.parent.rotateLeft();
            			this.parent.isRed = this.parent.left.isRed;
            			this.parent.left.isRed = !this.parent.left.isRed;
        			}
        			else if(this.parent.parent.right !=null && this.parent.parent.right.left !=null
        					&& this == this.parent.parent.right.left)//right left case
        			{
        				this.parent.rotateRight();
        				this.parent.parent.rotateLeft();
            			this.parent.isRed = this.parent.left.isRed;
            			this.parent.left.isRed = !this.parent.left.isRed;
        			}
        		}
        	}
        	else //uncle black
        	{
        		
        	}
			return false;
        }
        
        private boolean rotateLeft(){//null issues?
        	this.right.parent = this.parent;
        	this.parent = this.right;
        	this.right = this.parent.left;
        	this.parent.left = this;
        	return true;
        }
        
        private boolean rotateRight(){//null issues?
        	this.left.parent = this.parent;
        	this.parent = this.left;
        	this.left = this.parent.right;
        	this.parent.right = this;
        	return true;
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
			if(this.isRed)
				treePrintRaw.set(level, treePrintRaw.get(level)+LNBR+String.format("%03d", this.value)+RNBR);
			else
				treePrintRaw.set(level, treePrintRaw.get(level)+LNBB+String.format("%03d", this.value)+RNBB);
			if(!(this.left == null))
			{
				this.left.printRaw(level+1);
			}
			else
			{
				if(treePrintRaw.size()-1==level) 
					treePrintRaw.add(level+1,"");
				if(this.isRed)
					treePrintRaw.set(level+1, treePrintRaw.get(level+1)+LNBB+" N "+RNBB);
				else
					treePrintRaw.set(level+1, treePrintRaw.get(level+1)+LNBR+" N "+RNBR);
					
			}
			if(!(this.right == null))
			{
				this.right.printRaw(level+1);
			}
			else
			{
				if(treePrintRaw.size()-1==level)
					treePrintRaw.add(level+1,"");
				if(this.isRed)
					treePrintRaw.set(level+1, treePrintRaw.get(level+1)+LNBB+" BN "+RNBB);
				else
					treePrintRaw.set(level+1, treePrintRaw.get(level+1)+LNBR+" RN "+RNBR);
			}
				
			return;
		}
		
		private void printIndent(int level){
				//System.out.print(level+" - ");
			System.out.println("Level:"+level+"  -->"+String.format("%"+(level+5)+"s",""+LNBB+String.format("%03d", this.value)+RNBB));
			if(!(this.left == null))
			{
				//System.out.print("Trav Left");
				this.left.printIndent(level+1);
			}
			else
			{
				System.out.println("Level:"+level+"  -->"+String.format("%"+(level+5)+"s",LNBB+" N "+RNBB));
			}
			if(!(this.right == null))
			{
				//System.out.print("Trav right");
				this.right.printIndent(level+1);
			}
			else
			{
				//System.out.print("Null");
				System.out.println("Level:"+level+"  -->"+String.format("%"+(level+6)+"s",LNBB+" N "+RNBB));
			}
			//System.out.println("Returning up");
			return;
		}
		        
		        

	
    }/** End Node Definition **/
	
	public RedBlackIntervalTree() {
		// TODO Auto-generated constructor stub
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
 *                Insert
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

		RedBlackIntervalTree me2 = new RedBlackIntervalTree();

		me2.insert(10);
		me2.insert(20);
		me2.insert(30);
		me2.insert(15);
		
/*		me2.printRaw();
		List<String> treePrint2 = treePrintRaw;
        for (int i=0; i<treePrint2.size(); i++){
        	System.out.println(treePrint2.get(i)+" ");
        }
    	System.out.println();
    	treePrintRaw = new ArrayList<String>(0);
		me2.insert(20);
		me2.printRaw();
		treePrint2 = treePrintRaw;
        for (int i=0; i<treePrint2.size(); i++){
        	System.out.println(treePrint2.get(i)+" ");
        }
    	System.out.println();
    	treePrintRaw = new ArrayList<String>(0);
		me2.insert(30);
		me2.printRaw();
		treePrint2 = treePrintRaw;
        for (int i=0; i<treePrint2.size(); i++){
        	System.out.println(treePrint2.get(i)+" ");
        }
    	System.out.println();
		me2.insert(15);
		me2.printRaw();
		treePrint2 = treePrintRaw;
        for (int i=0; i<treePrint2.size(); i++){
        	System.out.println(treePrint2.get(i)+" ");
        }
    	System.out.println();*/
		
		Random rand = new Random();
		rand.ints(100);
		for(int i=0; i<10; i++){
			//System.out.println(me.insert(rand.nextInt(10)));
			me2.insert(rand.nextInt(100));
		}
		//me2.insert(50);
/*		
		System.out.println("--------Da Raw Tree--------");

		
		//List<String> treePrint2 = me2.print2();
		me2.printRaw();
		treePrint2 = treePrintRaw;
        for (int i=0; i<treePrint2.size(); i++){
        	System.out.println(treePrint2.get(i)+" ");
        }
        System.out.println("\nDoes 50 exist?");
        System.out.println(me2.search(50));
        
        System.out.println("\nTrying to delete 50");
        System.out.println(me2.delete(50));*/

		System.out.println("\n--------Da Raw Tree--------");
        //treePrintRaw = new ArrayList<String>(0);
		me2.printRaw();
		List<String> treePrint3 = treePrintRaw;
        for (int i=0; i<treePrint3.size(); i++){
        	System.out.println(treePrint3.get(i)+" ");
        }
        
/*
		System.out.println("\n--------Indented Tree--------");
        me2.printIndent();*/
	}
	
	
	
}
