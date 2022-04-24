package com.ms.vm;

import java.util.Date;

public class TxHelper {

    static Transaction tx;

    public Transaction getTx(String userName) throws InterruptedException {


        while (tx != null) {
            synchronized (tx) {
                tx.wait();

            }
        }
        tx = new Transaction(userName + "-" + new Date().getTime());
        return tx;
    }


    public boolean release() {
        synchronized (tx) {
            while (tx != null) {
                tx.notify();
                tx = null;
                return true;
            }
        }
        return false;

    }
}
