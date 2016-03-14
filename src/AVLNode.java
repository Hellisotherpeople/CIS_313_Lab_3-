/*
 * Allen Roush 
 * CIS 313 
 * Simple node class for the AVLTree. Constructor will initialize root value to 0 if none is passed in. 
 */

public class AVLNode
{
    AVLNode left, right;
    int height, data;

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public AVLNode()
    {
        left = null; 
        right = null; 
        data = 0;
        height = 0; 
    }
    
    public AVLNode(int input)
    {
       // This overloaded constructor will initialize the root to the passed in value
        left = null;
        right = null; 
        data = input; 
        height = 0; 
    }
}

    
    
    

