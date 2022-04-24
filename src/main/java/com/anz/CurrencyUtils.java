package com.anz;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

public class CurrencyUtils {


    private Map<String, Map<String, Double>> currencyVertices = new HashMap();
    private static CurrencyUtils obj;
    public static String SRC_PATH = "anz/src/";

    private CurrencyUtils() {
    }

    public static CurrencyUtils getInstance() {
        if (obj == null) {
            synchronized (CurrencyUtils.class) {
                if (obj == null) {
                    obj = new CurrencyUtils();
                    obj.setup();
                }
            }
        }
        return obj;
    }

    private static Properties getProperty(String fileName) throws IOException {
        Properties prop = null;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            // Log Exception
            e.printStackTrace();
        }
        return prop;
    }

    private static List<String> readFile(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            if (Files.exists(Paths.get(SRC_PATH + fileName))) {
                lines = Files.readAllLines(Paths.get(SRC_PATH + fileName), StandardCharsets.UTF_8);
            } else if (Files.exists(Paths.get("src/" + fileName))) {
                lines = Files.readAllLines(Paths.get("src/" + fileName), StandardCharsets.UTF_8);
            } else if (Files.exists(Paths.get(fileName))) {
                lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            // Log Exception
            e.printStackTrace();
        }
        return lines;
    }


    private void setup() {
        List<String> props = readFile("currency-rate.properties");
        for (String str : props) {
            String[] strs = str.split("=");
            insert(strs[0].substring(0, 3), strs[0].substring(3), Double.parseDouble(strs[1]));
            insert(strs[0].substring(3), strs[0].substring(0, 3), 1 / Double.parseDouble(strs[1]));
        }
    }

    private void insert(String src, String dst, double rate) {
        if (!currencyVertices.containsKey(src)) {
            Map<String, Double> map = new HashMap();
            map.put(dst, rate);
            currencyVertices.put(src, map);
        } else if (!currencyVertices.get(src).containsKey(dst)) {
            currencyVertices.get(src).put(dst, rate);
        }
    }

    /**
     * Format the amount based on Currency Type with specified decimal precision
     *
     * @param curr
     * @param amount
     * @return
     */
    public static String formatValue(String curr, double amount) {
        int pPoints = 0;
        try {
            if (Files.exists(Paths.get(SRC_PATH + "currency-precision.properties"))) {
                pPoints = Integer.parseInt(CurrencyUtils.getProperty(CurrencyUtils.SRC_PATH + "currency-precision.properties").getProperty(curr));
            } else if (Files.exists(Paths.get("src/currency-precision.properties"))) {
                pPoints = Integer.parseInt(CurrencyUtils.getProperty("src/currency-precision.properties").getProperty(curr));
            } else if (Files.exists(Paths.get("currency-precision.properties"))) {
                pPoints = Integer.parseInt(CurrencyUtils.getProperty("currency-precision.properties").getProperty(curr));
            }

        } catch (IOException e) {
            //
            e.printStackTrace();
        }
        if (pPoints == 0) {
            return String.valueOf(Math.round(amount / 1));
        }
        StringBuilder decimalFmtStr = new StringBuilder("#.");

        for (int i = 1; i <= pPoints; i++) {
            decimalFmtStr.append("#");
        }
        // Default rounding mode HALF_EVEN
        DecimalFormat df = new DecimalFormat(decimalFmtStr.toString());

        return df.format(amount);
    }

    public Map<String, Map<String, Double>> getCurrencyVertices() {
        return currencyVertices;
    }

}
