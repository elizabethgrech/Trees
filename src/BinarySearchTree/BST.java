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
        
        //TODO delete this method
/*        public void insertLeft(Node node){
        	this.left = node;
        }
        //TODO delete this method
        public void insertRight(Node node){
        	this.right = node;
        }*/

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
		//indent += "--";
		
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
		
		//right
/*		if(nodeNull(printNode))
			treePrint2.set(level, treePrint2.get(level)+"N");
		else{
			treePrint2.set(level, treePrint2.get(level)+LNB+printNode.value().toString()+RNB);
			print2(printNode.right());
		}*/
		
		level--;
		//indent = indent.substring(0, indent.length()-2);
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
 *                Print
 *                
 ************************************************/
/*	int i = 1;
	List<String> treePrint = new ArrayList<String>(i);
	int masterNodeDepth;
	int expandedNodeDepth;
	
	
	public void print(){
		print(rootNode);
		return;
	}
	
	public void print(Node printNode){
		//System.out.println("poop Test");
		
		i = 0;
		treePrint.add(i, "");
		
		if(nodeNull(printNode))
		{
			//System.out.println("N");
			//treePrint.add(i,"n");
			treePrint.set(i, treePrint.get(i)+LNB+"N"+RNB);
			}
		else{
			//System.out.println(printNode.value());
			//treePrint.add(i,""+printNode.value());
			//System.out.println(treePrint.size());
			treePrint.set(i, treePrint.get(i)+LNB+printNode.value()+RNB);
		}
		
		if(!nodeNull(printNode.left()))
			printLeft(printNode.left());
		
		printRight(printNode.right());

		System.out.println("--------Da Raw Tree--------");
        for (int i=0; i<treePrint.size(); i++){
        	System.out.println(treePrint.get(i)+" ");
        }

		System.out.println("--------Da Prety Tree--------");
		//there must be a better way to do this...
		masterNodeDepth = treePrint.size();
		System.out.println("Log size:"+treePrint.size());
		printAddArrows();
		expandedNodeDepth = treePrint.size();
		System.out.println("Log size:"+treePrint.size());
		printAddSpaces();
		System.out.println("Log size:"+treePrint.size());
		
        // Printing elements one by one
        for (int i=0; i<treePrint.size(); i++){
        	System.out.println(treePrint.get(i));
        }
        	
		
		return;
	}
	
	public void printLeft(Node printNode){
		//i++;
		//System.out.println("|");
		//if(treePrint.size()-1<i)
		//else
		//	treePrint.set(i,treePrint.get(i)+"/");
		i++;
		if(treePrint.size()-1<i)
			treePrint.add(i,"");
		
		if(nodeNull(printNode))
		{
			//System.out.println("N");
			treePrint.set(i, treePrint.get(i)+LNB+"N"+RNB);
			i--;
			//i--;
		}
		else{
			//System.out.println(printNode.value());
			treePrint.set(i, treePrint.get(i)+LNB+printNode.value()+RNB);
			printLeft(printNode.left());
			printRight(printNode.right());
		}
		
	}
	
	public void printRight(Node printNode){
		//i++;
		//System.out.println(" \\");
		//treePrint.set(i, treePrint.get(i)+" \\  ");
		i++;
		if(nodeNull(printNode))
		{
			//System.out.println("N");
			treePrint.set(i, treePrint.get(i)+LNB+"N"+RNB);
			i--;
			//i--;
		}
		else{
			//System.out.println(printNode.value());
			treePrint.set(i, treePrint.get(i)+LNB+printNode.value()+RNB);
			printLeft(printNode.left());
			printRight(printNode.right());
		}
		i--;
		//i--;
	}
	
	public boolean nodeNull(Node printNode){
		if(printNode == null || ((Integer)printNode.value()) == null)
			return true;
		else
			return false;
	}
	
	private void printAddArrows(){
		int size = treePrint.size();
		String strbase = "/\\";
		String str = "/\\";
		int nfactor = 0;
		int strfactor = 1;
		
		for(int i = 1; i<treePrint.size(); i++){
			
			for(int j=0; j<size;j++){
				treePrint.add(i,str);
				i++;
			}
			
			size=size/2;
			strfactor=strfactor*4;
			
			try{
				nfactor += treePrint.get(i+1).length() - treePrint.get(i+1).replace("N", "").length();
				strfactor=strfactor-nfactor;
				str="";
				for(int z=0; z<strfactor; z++){
					str+=strbase;
				}
			}
			catch(IndexOutOfBoundsException e){
				//do nothing
				System.out.println("welp");
			}
		}
	}
	
	private void printAddSpaces(){
		int countUp = 1;
		String spacesUp = "";
		
		
		int countDown = 1;
		String spacesDown = "";
		
        // 
        for (int i=0; i < expandedNodeDepth; i++){
        	if(treePrint.get(i).contains("/\\")){
        		for(int j=0; j < countUp; j++){
        			spacesUp+= " ";
        		}
        		//treePrint.get(i).replaceAll("/\\", "/"+spaces+"\\");
        		//System.out.println(treePrint.get(i).replace("/\\", "/"+spaces+"\\"));
        		treePrint.set(i, treePrint.get(i).replace("/\\", "/"+spacesUp+"\\"));
        		countUp+=2;
        		spacesUp = "";
        	}
        	else{
        		//reset count and spaces
        		countUp = 1;
        		spacesUp = "";
        	}
        	
        	if(!treePrint.get(i).contains("/")){
        		for(int j=0; j < countUp; j++){
        			spacesUp+= " ";
        		}
        		//treePrint.get(i).replaceAll("/\\", "/"+spaces+"\\");
        		//System.out.println(treePrint.get(i).replace("/\\", "/"+spaces+"\\"));
        		treePrint.set(i, treePrint.get(i).replace("/\\", "/"+spacesUp+"\\"));
        		countUp+=2;
        		spacesUp = "";
        	}
        	else{
        		//reset count and spaces
        		countUp = 1;
        		spacesUp = "";
        	}
        }
        
    	for (int i=expandedNodeDepth-1; i > 0; i--){
        	if(treePrint.get(i).contains("\\/")){
        		
        		for(int j=0; j < countDown; j++){
        			spacesDown+= " ";
        		}
        		treePrint.set(i, treePrint.get(i).replace("\\/", "\\"+spacesDown+"/"));
        		countDown+=2;
        		spacesDown = "";
        	}
        	else{
        		//reset count and spaces
        		//countDown = expandedNodeDepth-(i+1);
        		countDown = 0 + expandedNodeDepth - i;
        		//System.out.println("Down i = "+i);
        		//countDown = i-1;
        		spacesDown = "";
        	}
        	

        }

		int reverseLvl = treePrint.size();
		String rplcString = "";
        for (int i=0; i<treePrint.size(); i++){
            for(int j=0; j<reverseLvl-1; j++){
            	rplcString = rplcString+" ";
            }
            treePrint.set(i, rplcString+treePrint.get(i));
        	//System.out.println(treePrint.get(i)+" ");
            reverseLvl--;
            rplcString = "";
        }
	}*/
	
/************************************************
 *                
 *                
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
