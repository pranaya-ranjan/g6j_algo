package com.anz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CurrencyExchanger {
    private Map<String, Map<String, Double>> currencyVertex = CurrencyUtils.getInstance().getCurrencyVertices();
    private boolean isFound = false;

    /**
     * Do conversion and decimal precision formatting and return in specified statement format
     * @param srcCurr
     * @param amount
     * @param dstCurr
     * @return
     * @throws Exception
     */
    public String convertStmt(String srcCurr, double amount, String dstCurr) throws Exception {
        isFound = false;
        if (currencyVertex.get(srcCurr) == null) {
            return ("Unable to find rate for " + srcCurr + "/" + dstCurr);
        } else if (srcCurr.equals(dstCurr)) return srcCurr + " " + amount + " = " + dstCurr + " " + amount;

        List<String> visited = new ArrayList();
        visited.add(srcCurr);
        double d1 = calculateRate(visited, srcCurr, dstCurr, 1);
        if (isFound) {
            return srcCurr + " " + amount + " = " + dstCurr + " " + CurrencyUtils.formatValue(dstCurr, d1 * amount);
        }
        return ("Unable to find rate for " + srcCurr + "/" + dstCurr);
    }

    private double calculateRate(List<String> visited, String src, String dst, double rate) {
        if (isFound == true) {
            return rate;
        } else if (currencyVertex.get(src).get(dst) != null) {
            isFound = true;
            return rate * currencyVertex.get(src).get(dst);
        }

        double origRate = rate;
        for (String sInt : currencyVertex.get(src).keySet()) {
            if (visited.contains(sInt)) {
                continue;
            }
            visited.add(sInt);
            rate = calculateRate(visited, sInt, dst, rate * currencyVertex.get(src).get(sInt));
            if (isFound == true) {
                return rate;
            }
            visited.remove(sInt);
            rate = origRate;
        }

        return origRate;
    }

    public static void main(String args[]) throws Exception {

        // This can be done in several ways, by both Dijkstra and Bellman-Ford
        // Here we will not have Arbitrage, So Dijkstra will ne preferred as Bellman-Ford is slow in performance

        String test1 = new CurrencyExchanger().convertStmt("AUD", 100, "JPY"); // AUD 100.0 = JPY 10041
        System.out.println(test1);

        String test2 = new CurrencyExchanger().convertStmt("AUD", 100, "NZD"); // AUD 100.0 = NZD 108.01
        System.out.println(test2);

    }

}
