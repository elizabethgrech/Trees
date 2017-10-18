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
        private int value;         // associated data
        private Node left = null;
        private Node right = null;

        public Node(int val) {
            this.value = val;
        }
        
        public void insertLeft(Node node){
        	this.left = node;
        }
        public void insertRight(Node node){
        	this.right = node;
        }
        
        public int value(){
        	return value;
        }
        public Node left(){
        	return left;
        }
        
        public Node right(){
        	return right;
        }
    }

    
/************************************************
 *                
 *                
 *                
 ************************************************/
    
    String leftNodeBorder = "[";
    String rightNodeBorder = "]";
    
    
	public BST() {
		// TODO Auto-generated constructor stub
		
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
 *                Insert
 *                
 ************************************************/
	public boolean insert(int insertValue){
		//System.out.println("log: root call inserting "+insertValue);
		return(insert(rootNode, insertValue));
	}
	
	public boolean insert(Node startNode, int insertValue){
		//System.out.println("log: inserting "+insertValue);
		
		if(startNode == null){
			//System.out.println("log: startNode is null ");
			rootNode = new Node(insertValue);
			return(true);
		}
		
		if(rootNode.value() > insertValue){
			if(nodeNull(rootNode.left())){
				rootNode.insertLeft(new Node(insertValue));
				return(true);
			}else{
				return(insertTraverseLeft(rootNode.left(), insertValue));
			}
		}
		else {
			if(rootNode.value() < insertValue){
				if(nodeNull(rootNode.right())){
					rootNode.insertRight(new Node(insertValue));
					return(true);
				}else{
					return(insertTraverseRight(rootNode.right(), insertValue));
				}
			}
		}
		
		return(false);
	}
	
	public boolean insertTraverseLeft(Node travNode, int insertValue){
		if(travNode.value() > insertValue){
			if(nodeNull(travNode.left())){
				travNode.insertLeft(new Node(insertValue));
				return(true);
			}else{
				return(insertTraverseLeft(travNode.left(), insertValue));
			}
		}
		else {
			if(travNode.value() < insertValue){
				if(nodeNull(travNode.right())){
					travNode.insertRight(new Node(insertValue));
					return(true);
				}else{
					return(insertTraverseRight(travNode.right(), insertValue));
				}
			}
		}
		return(false);
	}
	
	public boolean insertTraverseRight(Node travNode, int insertValue){
		if(travNode.value() > insertValue){
			if(nodeNull(travNode.left())){
				travNode.insertLeft(new Node(insertValue));
				return(true);
			}else{
				return(insertTraverseLeft(travNode.left(), insertValue));
			}
		}
		else {
			if(travNode.value() < insertValue){
				if(nodeNull(travNode.right())){
					travNode.insertRight(new Node(insertValue));
					return(true);
				}else{
					return(insertTraverseRight(travNode.right(), insertValue));
				}
			}
		}
		return(false);
	}
	
/************************************************
 *                
 *                Delete
 *                
 ************************************************/
	

	
/************************************************
 *                
 *                Print
 *                
 ************************************************/
	int i = 1;
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
			treePrint.set(i, treePrint.get(i)+leftNodeBorder+"N"+rightNodeBorder);
			}
		else{
			//System.out.println(printNode.value());
			//treePrint.add(i,""+printNode.value());
			//System.out.println(treePrint.size());
			treePrint.set(i, treePrint.get(i)+leftNodeBorder+printNode.value()+rightNodeBorder);
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
			treePrint.set(i, treePrint.get(i)+leftNodeBorder+"N"+rightNodeBorder);
			i--;
			//i--;
		}
		else{
			//System.out.println(printNode.value());
			treePrint.set(i, treePrint.get(i)+leftNodeBorder+printNode.value()+rightNodeBorder);
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
			treePrint.set(i, treePrint.get(i)+leftNodeBorder+"N"+rightNodeBorder);
			i--;
			//i--;
		}
		else{
			//System.out.println(printNode.value());
			treePrint.set(i, treePrint.get(i)+leftNodeBorder+printNode.value()+rightNodeBorder);
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
        	
/*        	if(!treePrint.get(i).contains("/")){
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
        	}*/
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
	}
	
/************************************************
 *                
 *                
 *                
 ************************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BST me = new BST();

/*		System.out.println(me.insert(6));
		System.out.println(me.insert(3));
		System.out.println(me.insert(8));
		System.out.println(me.insert(1));
		System.out.println(me.insert(6));
		System.out.println(me.insert(7));
		System.out.println(me.insert(2));
		System.out.println(me.insert(9));
		System.out.println(me.insert(8));
		System.out.println(me.insert(0));
		System.out.println(me.insert(5));
		System.out.println(me.insert(8));
		System.out.println(me.insert(0));
		System.out.println(me.insert(0));
		System.out.println(me.insert(5));
		System.out.println(me.insert(6));
		System.out.println(me.insert(0));
		System.out.println(me.insert(9));
		System.out.println(me.insert(7));
		System.out.println(me.insert(6));*/
		
		Random rand = new Random();
		rand.ints(100);
		for(int i=0; i<50; i++){
			//System.out.println(me.insert(rand.nextInt(10)));
			me.insert(rand.nextInt(100));
		}
		
		me.print();
		
	}

}
