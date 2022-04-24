package com.ms.vm;

import com.ms.vm.domain.Item;
import com.ms.vm.domain.Note;
import com.ms.vm.domain.Product;
import com.ms.vm.exc.InsufficientBalanceException;
import com.ms.vm.exc.NotSufficientChangeException;
import com.ms.vm.exc.SoldOutException;

import java.util.ArrayList;
import java.util.List;

public class DriverClass {

    public static void main(String[] args) throws InsufficientBalanceException, NotSufficientChangeException, SoldOutException {

        VendingMachine vm = new VendingMachineImpl();

        Note n1 =  new Note(1);
        Note n5 = new Note(5);
        Note n10 = new Note(10);

        List<Note> notes = new ArrayList() ;
        List<Item> items = new ArrayList() ;
        for(int i = 0; i< 10; i++){

            notes.add(n1);
            notes.add(n5);
            notes.add(n10);

        }

        items.add(new Item("Tropicana", 13));


        vm.initializeChange(notes);
        vm.initializeInventory(items,5);


        Transaction tx = vm.getLock("pranaya");
        Note note = new Note(20);
        System.out.println(tx.getTxId());
        tx.setNote(note);
        vm.insertNote(note);

        int price = vm.checkProductPrice("Tropicana", tx);
        System.out.println("Price of Tropicana :" + price);

        Product p = vm.checkoutItem("Tropicana", tx);

        vm.releaseLock(tx);
        System.out.println(tx.getTxId());

        System.out.println("Return Money :");
        for(Object n : (ArrayList) p.getListOfNotes()) {
            System.out.println(((Note) n).getDenomination());
        }




    }
}
