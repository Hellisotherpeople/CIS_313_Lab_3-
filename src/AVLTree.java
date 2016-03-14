import java.util.Scanner;


public class AVLTree
{

    private AVLNode root;

    public void traverse()
    {
        //wrapper for recursive inorder traversal function 
        traverse(root);
    }

    public boolean search(int val)
    {
        //wrapper for recursive search function
        boolean found = search(root, val);
        if (found == true)
        {
            System.out.println("Value found in the tree!");
        } else
        {
            System.out.println("Value was not found in the tree!");
        }
        return found;
    }

    public void insert(int input)
    {
        // wrapper for recursive insert function 
        root = insert(root, input);
        System.out.println("Inserted: " + input);
    }

    public void delete(int input)
    {
        //wrapper for recursive remove function 
        root = delete(root, input);
        System.out.println("Deleted: " + input);

    }

    private AVLNode insert(AVLNode position, int toInsert)
    {

        /* 1.  Perform the normal BST rotation */
        if (position == null)
        {
            return (new AVLNode(toInsert));
        }

        if (toInsert < position.data)
        {
            position.left = insert(position.left, toInsert);
        } else
        {
            position.right = insert(position.right, toInsert);
        }

        /* 2. Update height of this ancestor node */
        position.height = max(height(position.left), height(position.right)) + 1;

        /* 3. Get the balance factor of this ancestor node to check whether
         this node became unbalanced */
        int balance = getBalance(position);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && toInsert < position.left.data)
        {
            return rightRotate(position);
        }

        // Right Right Case
        if (balance < -1 && toInsert > position.right.data)
        {
            return leftRotate(position);
        }

        // Left Right Case
        if (balance > 1 && toInsert > position.left.data)
        {
            position.left = leftRotate(position.left);
            return rightRotate(position);
        }

        // Right Left Case
        if (balance < -1 && toInsert < position.right.data)
        {
            position.right = rightRotate(position.right);
            return leftRotate(position);
        }

        /* return the (unchanged) node pointer */
        return position;
    }

    /* utility function to find the height of a node */
    private int height(AVLNode N)
    {
        if (N == null)
        {
            return 0;
        }
        return N.height;
    }


    /* Utility Function that returns the  max of left/right node */
    private int max(int lside, int rside)
    {
        return lside > rside ? lside : rside;
    }

    /* Rotate binary tree node with its left child */
    private AVLNode leftRotate(AVLNode input)
    {
        AVLNode left = input.left;
        input.left = left.right;
        left.right = input;
        input.height = max(height(input.left), height(input.right)) + 1;
        left.height = max(height(left.left), input.height) + 1;
        return left;
    }

    /* Rotate binary tree node with its right child */
    private AVLNode rightRotate(AVLNode input)
    {
        AVLNode right = input.right;
        input.right = right.left;
        right.left = input;
        input.height = max(height(input.left), height(input.right)) + 1;
        right.height = max(height(right.right), input.height) + 1;
        return right;
    }


    private boolean search(AVLNode position, int tofind)
    {
        // searches for a node passed in by the user. 
        boolean found = false;
        while ((position != null) && found == false)
        {
            int pos = position.data;
            if (tofind < pos)
                position = position.left;
            else if (tofind > pos)
                position = position.right;
            else
            {
                found = true;
                break;
            }
            found = search(position, tofind);
        }
        return found;
    }

    private void traverse(AVLNode position)
    {
        if (position != null)
        {
            traverse(position.left);
            System.out.print(position.data + " ");
            traverse(position.right);
        }
    }

    /* Given a non-empty binary search tree, return the node with minimum
    key value found in that tree.*/
    private AVLNode minValueNode(AVLNode node)
    {
        AVLNode current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
        {
            current = current.left;
        }

        return current;
    }

    // Get Balance factor of node n
    private int getBalance(AVLNode n)
    {
        if (n == null)
        {
            return 0;
        }
        return height(n.left) - height(n.right);
    }

    private AVLNode delete(AVLNode position, int toRemove)
    {

        // standard bst delete 
        if (position == null)
        {
            return position;
        }

        // If the key to be deleted is smaller than the root's key,than it's left       
        if (toRemove < position.data)
        {
            position.left = delete(position.left, toRemove);
        }

        // If the key to be deleted is greater than the root's key,than it's right 
        else if (toRemove > position.data)
        {
            position.right = delete(position.right, toRemove);
        }

        // if key is same as root's key, then we will delete this exact node 
        else
        {

            // case for when node has only one child or no child
            if ((position.left == null) || (position.right == null))
            {
                AVLNode temp = null;
                if (temp == position.left)
                {
                    temp = position.right;
                } else
                {
                    temp = position.left;
                }

                // No child case
                if (temp == null)
                {
                    temp = position;
                    position = null;
                } else
                // One child case
                {
                    position = temp; // Copy the contents of the non-empty child
                }
            } else
            {

                // node with two children: Get the inorder successor
                AVLNode temp = minValueNode(position.right);
                position.data = temp.data;
                position.right = delete(position.right, temp.data);
            }
        }

        // If the tree had only one node then return
        if (position == null)
        {
            return position;
        }

        // Update the height of the current node 
        position.height = max(height(position.left), height(position.right)) + 1;

        // get the balance factor 
        int balance = getBalance(position);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(position.left) >= 0)
        {
            return rightRotate(position);
        }

        // Left Right Case
        if (balance > 1 && getBalance(position.left) < 0)
        {
            position.left = leftRotate(position.left);
            return rightRotate(position);
        }

        // Right Right Case
        if (balance < -1 && getBalance(position.right) <= 0)
        {
            return leftRotate(position);
        }

        // Right Left Case
        if (balance < -1 && getBalance(position.right) > 0)
        {
            position.right = rightRotate(position.right);
            return leftRotate(position);
        }

        return position;
    }
    public static void main(String[] args)
    {
        AVLTree b = new AVLTree(); //create tree object 
        Scanner scanner = new Scanner(System.in);
        int i = 0;

        String userInput = scanner.next();
        while (!userInput.equals("exit"))
        {
            if (userInput.equals("insert"))
            {
                i = scanner.nextInt();
                b.insert(i);
            } else if (userInput.equals("traverse"))
            {
                b.traverse(b.root);
            }

            else if (userInput.equals("search"))
            {
                i = scanner.nextInt();
                b.search(i);
            } else if (userInput.equals("delete"))
            {
                i = scanner.nextInt();
                b.delete(i);
            }
            userInput = scanner.next();
        }
        System.out.println("Exiting!");
        scanner.close();
    }

    }

