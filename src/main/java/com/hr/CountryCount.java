package com.hr;

import java.util.Scanner;

public class CountryCount {

	public static void main(String args[]) {
		try {
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			int testCases = s.nextInt();
			int t, n, a, i, countries;
			int limit = 0, cc = 0;
			boolean result = true; // false -> invalid
			for (t = 1; t <= testCases; t++) {
				n = s.nextInt();
				countries = 0;
				result = true;
				limit = 0;
				for (i = 1; i <= n; i++) {
					a = s.nextInt();
					if (result == true) {
						if (a > n)
							result = false;
						else {
							if (limit == 0) {
								countries += 1;
								limit = a - 1;
								cc = 1;
							} else {
								cc += 1;
								if (a != limit + 1)
									result = false;
								if (cc == limit + 1)
									limit = 0;
							}
						}
					}
				}
				if (result == false)
					System.out.println("Invalid Data");
				else
					System.out.println(countries);
			}
		} catch (Exception e) {
			System.out.println("Exception caught - " + e);
		}
	}

}
