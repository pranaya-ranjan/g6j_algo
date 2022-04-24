package com.ms.vm;

import com.ms.vm.domain.Item;
import com.ms.vm.domain.Note;
import com.ms.vm.domain.Product;
import com.ms.vm.exc.InsufficientBalanceException;
import com.ms.vm.exc.NotSufficientChangeException;
import com.ms.vm.exc.SoldOutException;

import java.util.*;

public class VendingMachineImpl implements VendingMachine {

    private Map<Note, Integer> notesLeft = new HashMap();
    private Map<Item, Integer> itemLeft = new HashMap<Item, Integer>();

    private static Transaction tx;

    public boolean initializeChange(List<Note> initialFeeds) {
        // There can be a custom Logic for Max number of Notes can be stored,
        // Based on that we can return True or False

        for (Note n : initialFeeds) {

            if (notesLeft.containsKey(n)) {
                notesLeft.put(n, notesLeft.get(n) + 1);

            } else {
                notesLeft.put(n, 1);
            }
        }

        return true;
    }

    public boolean initializeInventory(List<Item> inventoryList, int quantity) {
        // There can be a custom Logic for Max number of Items can be stored,
        // Based on that we can return True or False


        for (Item n : inventoryList) {

            if (itemLeft.containsKey(n)) {
                itemLeft.put(n, notesLeft.get(n) == null ? 0 :  notesLeft.get(n) + quantity);

            } else {
                itemLeft.put(n, quantity);
            }
        }

        return true;
    }

    public Transaction getLock(String userName) {

        try {
            return new TxHelper().getTx(userName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int checkProductPrice(final String itemName, Transaction tx) {

        synchronized (Transaction.class) {
            if (tx != null) {

                for (Item itm : itemLeft.keySet()) {
                    if (itm.getName().equals(itemName)) {
                        return itm.getPrice();
                    }

                }
            }
            return -1; // Throws Invalid Item Name Input
        }

    }

    public boolean insertNote(Note note) {
        // Enum or Map can be used here
        if (note.getDenomination() == 1 || note.getDenomination() == 5
                || note.getDenomination() == 10 || note.getDenomination() == 20) {
            notesLeft.put(note, notesLeft.get(note) == null ? 0: notesLeft.get(note) + 1);
            return true;
        } else
            return false;
    }




    private List<Note> getChangeMoney(int i) throws NotSufficientChangeException {
        StringBuilder error = new StringBuilder();
        int remaining;
        int tens = i / 10;
        remaining = i % 10;
        List<Note> notes = new ArrayList<Note>();
        if (tens > notesLeft.get(new Note(10))) {
            // check for sufficient notes;
            // Add error
            remaining = (tens - notesLeft.get(new Note(10))) * 10;
        }
        for (int j = 1; j <= tens; j++) {
            notes.add(new Note(10));
        }
        // TODO Custom Logic to be completed

        int fives = remaining / 5;
        remaining = remaining % 5;

        if (fives > notesLeft.get(new Note(5))) {
            // check for sufficient notes;
            // Add error
            remaining = (fives - notesLeft.get(new Note(5))) * 5;
        }
        for (int j = 1; j <= fives; j++) {
            notes.add(new Note(5));
        }

        if (remaining > notesLeft.get(new Note(1))) {
            error.append("Shortage of Rs-"+ (remaining - notesLeft.get(new Note(1))));
        }
        for (int j = 1; j <= remaining; j++) {
            notes.add(new Note(1));
        }

        if (error.length() > 0) {
            throw new NotSufficientChangeException(error.toString());
        }

        return notes;

    }

    public Product<Item, List<Note>> checkoutItem(String itemName, Transaction txn) throws SoldOutException, NotSufficientChangeException, InsufficientBalanceException {

        if (txn == null) return null; // Not active session Error

        Item item = null;
        for (Item itm : itemLeft.keySet()) {
            if (itm.getName().equals(itemName)) {
                item = itm;
            }

        }

        if (item.getPrice() > txn.getNote().getDenomination()) throw new InsufficientBalanceException();

        if (itemLeft.get(item) == 0) throw new SoldOutException();

        List<Note> retNotes = getChangeMoney(txn.getNote().getDenomination() - item.getPrice());


        return new Product<Item, List<Note>>(item, retNotes);


    }

    public List<Note> cancelTransaction(Transaction txn) {

        Note n = txn.getNote();
        returnNote(n);
        returnItem(txn.getItem());
        List<Note> notes = new ArrayList<Note>();
        notes.add(n);

        return notes;
    }

    public boolean releaseLock(Transaction txn) {
        if (txn != null)
            return new TxHelper().release();
        return false;
    }

    public boolean returnNote(Note note) {
        // User Inserted

        notesLeft.put(note, notesLeft.get(note) - 1);
        return true;

    }

    public boolean returnItem(Item item) {
        // User requested

        itemLeft.put(item, notesLeft.get(item) + 1);
        return true;

    }
}
