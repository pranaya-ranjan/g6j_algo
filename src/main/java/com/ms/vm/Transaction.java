package com.ms.vm;

import com.ms.vm.domain.Item;
import com.ms.vm.domain.Note;

public class Transaction {

    // immutable class

    private final String txId; // = "username"+"-"+ new Date().getTime();

    // Can have start time and active duration


    private Item item;
    private Note note;

    public Transaction(String txId) {
        this.txId = txId;

    }

    public String getTxId() {
        return txId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
