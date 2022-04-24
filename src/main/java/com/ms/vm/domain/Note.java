package com.ms.vm.domain;

public class Note {

    private int denomination;

    public Note(){}

    public Note(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        return denomination == note.denomination;
    }

    @Override
    public int hashCode() {
        return denomination;
    }
}
