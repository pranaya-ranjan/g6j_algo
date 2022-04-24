package com.ms.vm;

import com.ms.vm.domain.Item;
import com.ms.vm.domain.Note;
import com.ms.vm.domain.Product;
import com.ms.vm.exc.InsufficientBalanceException;
import com.ms.vm.exc.SoldOutException;
import com.ms.vm.exc.NotSufficientChangeException;

import java.util.List;

public interface VendingMachine {

    boolean initializeChange(List<Note> initialFeeds);

    boolean initializeInventory(List<Item> inventoryList, int quantity);

    Transaction getLock(String userName);

    int checkProductPrice(String itemName, Transaction tx);

    boolean insertNote(Note note);

    Product<Item, List<Note>> checkoutItem(String itemName, Transaction txn) throws SoldOutException, NotSufficientChangeException, InsufficientBalanceException;

    List<Note> cancelTransaction(Transaction txn);

    boolean releaseLock(Transaction txn);


}
