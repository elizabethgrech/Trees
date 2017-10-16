package BinarySearchTree;

public class BST {

	
/************************************************
 *               
 *                Node Definition 
 *                
 ************************************************/
	
	private Node rootNode = null;             // root of BST

    private class Node {
        private int value;         // associated data
        private Node left;
        private Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    
/************************************************
 *                
 *                
 *                
 ************************************************/
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
			if(startNode.value == searchValue)
				return(startNode);

			if(startNode.equals(null))
				return(null);
			
			if(startNode.value > searchValue)
				startNode = startNode.right;
			else
				startNode = startNode.left;
			
		}
	}
	

/************************************************
 *                
 *                Insert
 *                
 ************************************************/
	public boolean insert(int insertValue){
		return(insert(rootNode, insertValue));
	}
	
	public boolean insert(Node startNode, int insertValue){
		
		if(startNode.equals(null))
			return(false);
		
		if(startNode.value == insertValue)
			return(false);
		
		while(true)
		{
			if(insertValue > startNode.value)
			{
				if(startNode.right == null)
				{
					startNode.right = new Node(insertValue);
					return(true);
				}
				else
					startNode = startNode.right;
			}
			else //if(insertValue > startNode.value)
			{
				if(startNode.left == null)
				{
					startNode.right = new Node(insertValue);
					return(true);
				}
				else
					startNode = startNode.left;
			}
		}
	}
	
/************************************************
 *                
 *                Delete
 *                
 ************************************************/
	
	
/************************************************
 *                
 *                
 *                
 ************************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
	}

}
