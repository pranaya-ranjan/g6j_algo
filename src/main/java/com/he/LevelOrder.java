package com.he;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrder
{
    // A Binary Tree Node 
    static class Node
    {
        int data;
        Node left;
        Node right;

        // constructor 
        Node(int data){
            this.data = data;
            left = null;
            right =null;
        }
    }
    static void printLevelOrder(Node root)
    {
        int h = height(root);
        System.out.println("height - "+h);
        int i;
        for (i=1; i<=h; i++)
        {
            printGivenLevel(root, i);
            System.out.println();
        }
    }

    static int height(Node root){

        if(root == null) return 0;
        return 1+ Math.max(height(root.left), height(root.right));

    }
    /* Print nodes at a given level */
   static void printGivenLevel(Node root, int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.println(root.data);
        else if (level > 1 && level % 2 == 0)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
        else if (level > 1 && level % 2 == 1)
        {
            printGivenLevel(root.right, level-1);
            printGivenLevel(root.left, level-1);

        }
    }

    // Driver program to test above functions 
    public static void main(String[] args)
    {
        // Let us create binary tree shown in above diagram 
       /*               1 
                   /     \ 
                  2       3 
                /   \       \ 
               4     5       6 
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        printLevelOrder(root);

    }

} 