package com.ms.vm.domain;

public class Product<E1, E2> {

    private E1 items;
    private  E2 listOfNotes;

    public Product(E1 items, E2 listOfNotes) {
        this.items = items;
        this.listOfNotes = listOfNotes;
    }

    public E1 getItems() {
        return items;
    }

    public void setItems(E1 items) {
        this.items = items;
    }

    public E2 getListOfNotes() {
        return listOfNotes;
    }

    public void setListOfNotes(E2 listOfNotes) {
        this.listOfNotes = listOfNotes;
    }
}
