package ch.pbu.rf;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.pbu.rf.color.rgb.ColorSpaceRGB;
import ch.pbu.rf.illuminant.Illuminant;

/**
 * Represents the RF.
 * 
 * @author Yanick Senn
 */
public final class RF {
	public static final MathContext MC = new MathContext(100, RoundingMode.HALF_UP);
	
	/**
	 * Private constructor.
	 */
	private RF() {
		throw new AssertionError();
	}
	
	/**
	 * Represents the CIE 1931.
	 * 
	 * @author Yanick Senn
	 */
	public static final class CIE1931 {
		//                                                  Name   Year  A  X              Y              M   CCT
		// ---------------------------------------------------------------------------------------------------------
		public static final Illuminant A   = new Illuminant("A",   1931, 2, bd("0.44757"), bd("0.40745"), MC, 2856);
		public static final Illuminant B   = new Illuminant("B",   1931, 2, bd("0.34842"), bd("0.35161"), MC, 4874);
		public static final Illuminant C   = new Illuminant("C",   1931, 2, bd("0.31006"), bd("0.31616"), MC, 6774);
		public static final Illuminant D50 = new Illuminant("D50", 1931, 2, bd("0.34567"), bd("0.35850"), MC, 5003);
		public static final Illuminant D55 = new Illuminant("D55", 1931, 2, bd("0.33242"), bd("0.34743"), MC, 5503);
		public static final Illuminant D65 = new Illuminant("D65", 1931, 2, bd("0.31271"), bd("0.32902"), MC, 6504);
		public static final Illuminant D75 = new Illuminant("D75", 1931, 2, bd("0.29902"), bd("0.31485"), MC, 7504);
		public static final Illuminant E   = new Illuminant("E",   1931, 2, bd("0.33333"), bd("0.33333"), MC, 5454);
		public static final Illuminant F1  = new Illuminant("F1",  1931, 2, bd("0.31310"), bd("0.33727"), MC, 6430);
		public static final Illuminant F2  = new Illuminant("F2",  1931, 2, bd("0.37208"), bd("0.37529"), MC, 4230);
		public static final Illuminant F3  = new Illuminant("F3",  1931, 2, bd("0.40910"), bd("0.39430"), MC, 3450);
		public static final Illuminant F4  = new Illuminant("F4",  1931, 2, bd("0.44018"), bd("0.40329"), MC, 2940);
		public static final Illuminant F5  = new Illuminant("F5",  1931, 2, bd("0.31379"), bd("0.34531"), MC, 6350);
		public static final Illuminant F6  = new Illuminant("F6",  1931, 2, bd("0.37790"), bd("0.38835"), MC, 4150);
		public static final Illuminant F7  = new Illuminant("F7",  1931, 2, bd("0.31292"), bd("0.32933"), MC, 6500);
		public static final Illuminant F8  = new Illuminant("F8",  1931, 2, bd("0.34588"), bd("0.35875"), MC, 5000);
		public static final Illuminant F9  = new Illuminant("F9",  1931, 2, bd("0.37417"), bd("0.37281"), MC, 4150);
		public static final Illuminant F10 = new Illuminant("F10", 1931, 2, bd("0.34609"), bd("0.35986"), MC, 5000);
		public static final Illuminant F11 = new Illuminant("F11", 1931, 2, bd("0.38052"), bd("0.37713"), MC, 4000);
		public static final Illuminant F12 = new Illuminant("F12", 1931, 2, bd("0.43695"), bd("0.40441"), MC, 3000);
		
		
		
		
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
	 * Represents the CIE 1964.
	 * 
	 * @author Yanick Senn
	 */
	public static final class CIE1964 {
		//                                                  Name   Year  A   X              Y              M   CCT
		// ----------------------------------------------------------------------------------------------------------
		public static final Illuminant A   = new Illuminant("A",   1964, 10, bd("0.45117"), bd("0.40594"), MC, 2856);
		public static final Illuminant B   = new Illuminant("B",   1964, 10, bd("0.34980"), bd("0.35270"), MC, 4874);
		public static final Illuminant C   = new Illuminant("C",   1964, 10, bd("0.31039"), bd("0.31905"), MC, 6774);
		public static final Illuminant D50 = new Illuminant("D50", 1964, 10, bd("0.34773"), bd("0.35952"), MC, 5003);
		public static final Illuminant D55 = new Illuminant("D55", 1964, 10, bd("0.33411"), bd("0.34877"), MC, 5503);
		public static final Illuminant D65 = new Illuminant("D65", 1964, 10, bd("0.31382"), bd("0.33100"), MC, 6504);
		public static final Illuminant D75 = new Illuminant("D75", 1964, 10, bd("0.29968"), bd("0.31740"), MC, 7504);
		public static final Illuminant E   = new Illuminant("E",   1964, 10, bd("0.33333"), bd("0.33333"), MC, 5454);
		public static final Illuminant F1  = new Illuminant("F1",  1964, 10, bd("0.31811"), bd("0.33559"), MC, 6430);
		public static final Illuminant F2  = new Illuminant("F2",  1964, 10, bd("0.37925"), bd("0.36733"), MC, 4230);
		public static final Illuminant F3  = new Illuminant("F3",  1964, 10, bd("0.41761"), bd("0.38324"), MC, 3450);
		public static final Illuminant F4  = new Illuminant("F4",  1964, 10, bd("0.44920"), bd("0.39074"), MC, 2940);
		public static final Illuminant F5  = new Illuminant("F5",  1964, 10, bd("0.31975"), bd("0.34246"), MC, 6350);
		public static final Illuminant F6  = new Illuminant("F6",  1964, 10, bd("0.38660"), bd("0.37847"), MC, 4150);
		public static final Illuminant F7  = new Illuminant("F7",  1964, 10, bd("0.31569"), bd("0.32960"), MC, 6500);
		public static final Illuminant F8  = new Illuminant("F8",  1964, 10, bd("0.34902"), bd("0.35939"), MC, 5000);
		public static final Illuminant F9  = new Illuminant("F9",  1964, 10, bd("0.37829"), bd("0.37045"), MC, 4150);
		public static final Illuminant F10 = new Illuminant("F10", 1964, 10, bd("0.35090"), bd("0.35444"), MC, 5000);
		public static final Illuminant F11 = new Illuminant("F11", 1964, 10, bd("0.38541"), bd("0.37123"), MC, 4000);
		public static final Illuminant F12 = new Illuminant("F12", 1964, 10, bd("0.44256"), bd("0.39717"), MC, 3000);
		
		
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
	

	/**
	 * Represents the RGB.
	 * 
	 * @author Yanick Senn
	 */
	public static final class RGB {

		/**
		 * Represents the color space.
		 * 
		 * @author Yanick Senn
		 */
		public static final class ColorSpace {
			//                                Name   Year  A  X              Y              M   CCT
			// ---------------------------------------------------------------------------------------------------------
			public static final ColorSpaceRGB sRGB     = new ColorSpaceRGB("Standard RGB", CIE1931.D50, bd("0.64"), bd("0.33"), bd("0.30"), bd("0.60"), bd("0.15"), bd("0.06"));
//			public static final ColorSpaceRGB AdobeRGB = new ColorSpaceRGB("Adobe RGB",    bd("0.64"), bd("0.33"), bd("0.30"), bd("0.60"), bd("0.15"), bd("0.06"));
//			public static final ColorSpaceRGB AppleRGB = new ColorSpaceRGB("Apple RGB",    bd("0.64"), bd("0.33"), bd("0.30"), bd("0.60"), bd("0.15"), bd("0.06"));
//			public static final ColorSpaceRGB CIERGB   = new ColorSpaceRGB("CIE RGB",      bd("0.64"), bd("0.33"), bd("0.30"), bd("0.60"), bd("0.15"), bd("0.06"));
			
			/**
			 * Private constructor.
			 */
			private ColorSpace() {
				throw new AssertionError();
			}
			
			/**
			 * Returns the default colorspace rgb.
			 * 
			 * @return The default colorspace rgb.
			 */
			public static ColorSpaceRGB getDefault() {
				return sRGB;
			}
			
		}
		
		/**
		 * Private constructor.
		 */
		private RGB() {
			throw new AssertionError();
		}
	}
	
	private static BigDecimal bd(String value) {
		return MathUtil.bd(value, MC);
	}
}
