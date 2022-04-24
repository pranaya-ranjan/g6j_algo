package com.anz;

import org.junit.Assert;

public class CurrencyExchangerTest {

    CurrencyExchanger currExch;

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void convertStmt() throws Exception {
        currExch =  new CurrencyExchanger();

        Assert.assertEquals("AUD 1.0 = AUD 1.0", currExch.convertStmt("AUD", 1, "AUD"));
        Assert.assertEquals("AUD 1.0 = CAD 0.96", currExch.convertStmt("AUD", 1, "CAD"));
        Assert.assertEquals("AUD 1.0 = CNY 5.17", currExch.convertStmt("AUD", 1, "CNY"));
        Assert.assertEquals("AUD 1.0 = CZK 18.76", currExch.convertStmt("AUD", 1, "CZK"));
        Assert.assertEquals("AUD 1.0 = DKK 5.06", currExch.convertStmt("AUD", 1, "DKK"));
        Assert.assertEquals("AUD 1.0 = EUR 0.68", currExch.convertStmt("AUD", 1, "EUR"));
        Assert.assertEquals("AUD 1.0 = GBP 0.53", currExch.convertStmt("AUD", 1, "GBP"));
        Assert.assertEquals("AUD 1.0 = JPY 100", currExch.convertStmt("AUD", 1, "JPY"));
        Assert.assertEquals("AUD 1.0 = NOK 5.89", currExch.convertStmt("AUD", 1, "NOK"));
        Assert.assertEquals("AUD 1.0 = NZD 1.08", currExch.convertStmt("AUD", 1, "NZD"));
        Assert.assertEquals("AUD 1.0 = USD 0.84", currExch.convertStmt("AUD", 1, "USD"));
        Assert.assertEquals("Unable to find rate for AUD/INR", currExch.convertStmt("AUD", 1, "INR"));

        // More test can be covered
    }
}