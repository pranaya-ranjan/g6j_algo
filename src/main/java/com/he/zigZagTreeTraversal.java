package com.he;// Java implementation of a O(n) time
// method for Zigzag order traversal 
import java.util.*;

// Binary Tree Node1 
class Node1
{
    int data;
    Node1 leftChild;
    Node1 rightChild;
    Node1(int data)
    {
        this.data = data;
    }
}

class BinaryTree {
    Node1 rootNode1;

    // function to print the 
// zigzag traversal 
    void printZigZagTraversal() {

        // if null then return 
        if (rootNode1 == null) {
            return;
        }

        // declare two stacks 
        Stack<Node1> currentLevel = new Stack<>();
        Stack<Node1> nextLevel = new Stack<>();

        // push the root 
        currentLevel.push(rootNode1);
        boolean leftToRight = true;

        // check if stack is empty 
        while (!currentLevel.isEmpty()) {

            // pop out of stack 
            Node1 Node1 = currentLevel.pop();

            // print the data in it 
            System.out.print(Node1.data + " ");

            // store data according to current 
            // order. 
            if (leftToRight) {
                if (Node1.leftChild != null) {
                    nextLevel.push(Node1.leftChild);
                }

                if (Node1.rightChild != null) {
                    nextLevel.push(Node1.rightChild);
                }
            }
            else {
                if (Node1.rightChild != null) {
                    nextLevel.push(Node1.rightChild);
                }

                if (Node1.leftChild != null) {
                    nextLevel.push(Node1.leftChild);
                }
            }

            if (currentLevel.isEmpty()) {
                leftToRight = !leftToRight;
                Stack<Node1> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
            }
        }
    }
}

public class zigZagTreeTraversal {

    // driver program to test the above function 
    public static void main(String[] args)
    {
        BinaryTree tree = new BinaryTree();
        tree.rootNode1 = new Node1(1);
        tree.rootNode1.leftChild = new Node1(2);
        tree.rootNode1.rightChild = new Node1(3);
        tree.rootNode1.leftChild.leftChild = new Node1(7);
        tree.rootNode1.leftChild.rightChild = new Node1(6);
        tree.rootNode1.rightChild.leftChild = new Node1(5);
        tree.rootNode1.rightChild.rightChild = new Node1(4);

        System.out.println("ZigZag Order traversal of binary tree is");
        tree.printZigZagTraversal();
    }
}

// This Code is contributed by Harikrishnan Rajan. 
