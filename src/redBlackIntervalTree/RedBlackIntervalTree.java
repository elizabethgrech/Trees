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

//import nodes.Node;

public class RedBlackIntervalTree {
    private static final String LNB = "["; //leftNodeBorder
    private static final String RNB = "]"; //rightNodeBorder
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
        	if(this.parent != null && this.parent.isRed)
        	{
        		if()//uncle red
        		{
        			
        		}
        		else //uncle black
        		{
        			
        		}
        	}
			return false;
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
	
	public RedBlackIntervalTree() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void TreeCheck(){
		
	}
	
	public void TreeInsert(){
		
	}
	
	public void TreeDelete(){
		
	}
	
	public void TreeFix(){
		
	}
	
	public void TreeMin(){
		
	}
	
	public void TreeMax(){
		
	}
	
	public void TreeNodeSuccessor(){
		
	}
	
	public void TreeNodePredecessor(){
		
	}
	
	public void TreeNodeRotateLeft(){
		
	}
	
	public void TreeNodeRotateRight(){
		
	}
	
	
	
}
