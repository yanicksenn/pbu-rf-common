package ch.pbu.rf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.pbu.rf.illuminant.Illuminant;

/**
 * Represents the RF.
 * 
 * @author Yanick Senn
 */
public final class RF {
	
	/**
	 * Private constructor.
	 */
	private RF() {
		throw new AssertionError();
	}
	
	/**
	 * Represents the CIE 1931
	 * 
	 * @author Yanick Senn
	 *
	 */
	public static final class CIE1931 {
		//                             Name	                          X        Y        CCT
		// -----------------------------------------------------------------------------------
		public static final Illuminant A   = new Illuminant("A", 1931, 2, 0.44757, 0.40745, 2856);
		public static final Illuminant B   = new Illuminant("B", 1931, 2, 0.34842, 0.35161, 4874);
		public static final Illuminant C   = new Illuminant("C", 1931, 2, 0.31006, 0.31616, 6774);
		public static final Illuminant D50 = new Illuminant("D50", 1931, 2, 0.34567, 0.35850, 5003);
		public static final Illuminant D55 = new Illuminant("D55", 1931, 2, 0.33242, 0.34743, 5503);
		public static final Illuminant D65 = new Illuminant("D65", 1931, 2, 0.31271, 0.32902, 6504);
		public static final Illuminant D75 = new Illuminant("D75", 1931, 2, 0.29902, 0.31485, 7504);
		public static final Illuminant E   = new Illuminant("E", 1931, 2, 0.33333, 0.33333, 5454);
		public static final Illuminant F1  = new Illuminant("F1", 1931, 2, 0.31310, 0.33727, 6430);
		public static final Illuminant F2  = new Illuminant("F2", 1931, 2, 0.37208, 0.37529, 4230);
		public static final Illuminant F3  = new Illuminant("F3", 1931, 2, 0.40910, 0.39430, 3450);
		public static final Illuminant F4  = new Illuminant("F4", 1931, 2, 0.44018, 0.40329, 2940);
		public static final Illuminant F5  = new Illuminant("F5", 1931, 2, 0.31379, 0.34531, 6350);
		public static final Illuminant F6  = new Illuminant("F6", 1931, 2, 0.37790, 0.38835, 4150);
		public static final Illuminant F7  = new Illuminant("F7", 1931, 2, 0.31292, 0.32933, 6500);
		public static final Illuminant F8  = new Illuminant("F8", 1931, 2, 0.34588, 0.35875, 5000);
		public static final Illuminant F9  = new Illuminant("F9", 1931, 2, 0.37417, 0.37281, 4150);
		public static final Illuminant F10 = new Illuminant("F10", 1931, 2, 0.34609, 0.35986, 5000);
		public static final Illuminant F11 = new Illuminant("F11", 1931, 2, 0.38052, 0.37713, 4000);
		public static final Illuminant F12 = new Illuminant("F12", 1931, 2, 0.43695, 0.40441, 3000);
		
		
		private static final List<Illuminant> illuminants = new ArrayList<>();
		
		
		static {
			illuminants.add(A);
			illuminants.add(B);
			illuminants.add(C);
			illuminants.add(D50);
			illuminants.add(D55);
			illuminants.add(D65);
			illuminants.add(D75);
			illuminants.add(E);
			illuminants.add(F1);
			illuminants.add(F2);
			illuminants.add(F3);
			illuminants.add(F4);
			illuminants.add(F5);
			illuminants.add(F6);
			illuminants.add(F7);
			illuminants.add(F8);
			illuminants.add(F9);
			illuminants.add(F10);
			illuminants.add(F11);
			illuminants.add(F12);
		}
		
		
		/**
		 * Returns the illuminants.
		 * 
		 * @return Illuminants.
		 */
		public static List<Illuminant> getIlluminants() {
			return Collections.unmodifiableList(illuminants);
		}
		
		
		/**
		 * Private constructor.
		 */
		private CIE1931() {
			throw new AssertionError();
		}
	}
	
	
	/**
	 * Represents the CIE 1964
	 * 
	 * @author Yanick Senn
	 */
	public static final class CIE1964 {
		//                             Name	                          X        Y        CCT
		// -----------------------------------------------------------------------------------
		public static final Illuminant A   = new Illuminant("A", 1964, 10, 0.45117, 0.40594, 2856);
		public static final Illuminant B   = new Illuminant("B", 1964, 10, 0.34980, 0.35270, 4874);
		public static final Illuminant C   = new Illuminant("C", 1964, 10, 0.31039, 0.31905, 6774);
		public static final Illuminant D50 = new Illuminant("D50", 1964, 10, 0.34773, 0.35952, 5003);
		public static final Illuminant D55 = new Illuminant("D55", 1964, 10, 0.33411, 0.34877, 5503);
		public static final Illuminant D65 = new Illuminant("D65", 1964, 10, 0.31382, 0.33100, 6504);
		public static final Illuminant D75 = new Illuminant("D75", 1964, 10, 0.29968, 0.31740, 7504);
		public static final Illuminant E   = new Illuminant("E", 1964, 10, 0.33333, 0.33333, 5454);
		public static final Illuminant F1  = new Illuminant("F1", 1964, 10, 0.31811, 0.33559, 6430);
		public static final Illuminant F2  = new Illuminant("F2", 1964, 10, 0.37925, 0.36733, 4230);
		public static final Illuminant F3  = new Illuminant("F3", 1964, 10, 0.41761, 0.38324, 3450);
		public static final Illuminant F4  = new Illuminant("F4", 1964, 10, 0.44920, 0.39074, 2940);
		public static final Illuminant F5  = new Illuminant("F5", 1964, 10, 0.31975, 0.34246, 6350);
		public static final Illuminant F6  = new Illuminant("F6", 1964, 10, 0.38660, 0.37847, 4150);
		public static final Illuminant F7  = new Illuminant("F7", 1964, 10, 0.31569, 0.32960, 6500);
		public static final Illuminant F8  = new Illuminant("F8", 1964, 10, 0.34902, 0.35939, 5000);
		public static final Illuminant F9  = new Illuminant("F9", 1964, 10, 0.37829, 0.37045, 4150);
		public static final Illuminant F10 = new Illuminant("F10", 1964, 10, 0.35090, 0.35444, 5000);
		public static final Illuminant F11 = new Illuminant("F11", 1964, 10, 0.38541, 0.37123, 4000);
		public static final Illuminant F12 = new Illuminant("F12", 1964, 10, 0.44256, 0.39717, 3000);
		
		
		private static final List<Illuminant> illuminants = new ArrayList<>();
		
		
		static {
			illuminants.add(A);
			illuminants.add(B);
			illuminants.add(C);
			illuminants.add(D50);
			illuminants.add(D55);
			illuminants.add(D65);
			illuminants.add(D75);
			illuminants.add(E);
			illuminants.add(F1);
			illuminants.add(F2);
			illuminants.add(F3);
			illuminants.add(F4);
			illuminants.add(F5);
			illuminants.add(F6);
			illuminants.add(F7);
			illuminants.add(F8);
			illuminants.add(F9);
			illuminants.add(F10);
			illuminants.add(F11);
			illuminants.add(F12);
		}
		
		
		/**
		 * Returns the illuminants.
		 * 
		 * @return Illuminants.
		 */
		public static List<Illuminant> getIlluminants() {
			return Collections.unmodifiableList(illuminants);
		}

		
		/**
		 * Private constructor.
		 */
		private CIE1964() {
			throw new AssertionError();
		}
	}
}
