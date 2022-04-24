package com.he;

public class LinkedListCustm {

    class _Node{

        Node next;
        int data;

    }


    public Node reverseInChunk(Node head, int size){

        Node prev = null;
        Node curr = head;
        Node next;

        int counter = 0;

        while(curr.next !=  null){

            counter ++;

            next = curr.next;
            curr.next = prev;

            next.next = curr;

            prev = curr;
            curr = next;


 int a, int b;

        }


    }

   // private reverse










}
