package ch.pbu.rf.color;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

import ch.obermuhlner.math.big.BigDecimalMath;
import ch.pbu.rf.color.lab.ColorLab;
import ch.pbu.rf.color.xyz.ColorXYZ;
import ch.pbu.rf.illuminant.Illuminant;

/**
 * Represents the Color-Util.
 * 
 * @author Yanick Senn
 */
public class ColorUtil {
	public static final MathContext MC = new MathContext(100, RoundingMode.HALF_UP);
	public static final BigDecimal E = bd(216, MC).divide(bd(24389, MC), MC);
	public static final BigDecimal K = bd(24389, MC).divide(bd(27, MC), MC);

	/**
	 * Private constructor.
	 */
	private ColorUtil() {
		throw new AssertionError();
	}

	/**
	 * Returns the reference white for <I>illuminant</I>.
	 * 
	 * @param illuminant Illuminant.
	 * 
	 * @return Reference white as XYZ.
	 */
	public static ColorXYZ getReferenceWhite(Illuminant illuminant) {
		Objects.requireNonNull(illuminant, "illuminant is not specified");

		BigDecimal ix = illuminant.getX();
		BigDecimal iy = illuminant.getY();
		BigDecimal iz = illuminant.getZ();

		BigDecimal y = BigDecimal.ONE;
		BigDecimal x = ix.multiply(y, MC).divide(iy, MC);
		BigDecimal z = iz.multiply(y, MC).divide(iy, MC);

		return new ColorXYZ(x, y, z);
	}

	/**
	 * Converts the <I>color</I> to an XYZ-Color by the <I>illuminant</I>.
	 * 
	 * @param color      Color.
	 * @param illuminant Illuminant.
	 * 
	 * @return XYZ-Color.
	 */
	public static ColorXYZ convertToXYZ(ColorLab color, Illuminant illuminant) {
		Objects.requireNonNull(color, "color is not specified");
		Objects.requireNonNull(illuminant, "illuminant is not specified");

		BigDecimal l = color.getL();
		BigDecimal a = color.getA();
		BigDecimal b = color.getB();

		ColorXYZ rw = getReferenceWhite(illuminant);
		BigDecimal rx = rw.getX();
		BigDecimal ry = rw.getY();
		BigDecimal rz = rw.getZ();
		

		BigDecimal ty = l.add(bd(16, MC), MC).divide(bd(116, MC), MC);
		BigDecimal tx = a.divide(bd(500, MC), MC).add(ty, MC);
		BigDecimal tz = b.divide(bd(200, MC), MC).subtract(ty, MC);

		BigDecimal x = _calculate_f_xyz(tx).multiply(rx, MC);
		BigDecimal y = _calculate_f_xyz(ty).multiply(ry, MC);
		BigDecimal z = _calculate_f_xyz(tz).multiply(rz, MC);

		return new ColorXYZ(x, y, z);
	}

	/**
	 * Converts the <I>color</I> to an Lab-Color by the <I>illuminant</I>.
	 * 
	 * @param color      Color.
	 * @param illuminant Illuminant.
	 * 
	 * @return Lab-Color.
	 */
	public static ColorLab convertToLab(ColorXYZ color, Illuminant illuminant) {
		Objects.requireNonNull(color, "color is not specified");
		Objects.requireNonNull(illuminant, "illuminant is not specified");

		BigDecimal x = color.getX();
		BigDecimal y = color.getY();
		BigDecimal z = color.getZ();

		ColorXYZ rw = getReferenceWhite(illuminant);
		BigDecimal rx = rw.getX();
		BigDecimal ry = rw.getY();
		BigDecimal rz = rw.getZ();


		BigDecimal fx = _calculate_f_lab(x.divide(rx, MC));
		BigDecimal fy = _calculate_f_lab(y.divide(ry, MC));
		BigDecimal fz = _calculate_f_lab(z.divide(rz, MC));

		BigDecimal l = fy.multiply(bd(116, MC), MC).subtract(bd(16, MC), MC);
		BigDecimal a = fx.subtract(fy, MC).multiply(bd(500, MC), MC);
		BigDecimal b = fy.subtract(fz, MC).multiply(bd(200, MC), MC);

		return new ColorLab(l, a, b);
	}

	public static BigDecimal calculateDeltaE2000(ColorLab color1, ColorLab color2) {
		Objects.requireNonNull(color1, "color1 is not specified");
		Objects.requireNonNull(color2, "color2 is not specified");
		
		BigDecimal l1 = color1.getL();
		BigDecimal a1 = color1.getA();
		BigDecimal b1 = color1.getB();

		BigDecimal l2 = color2.getL();
		BigDecimal a2 = color2.getA();
		BigDecimal b2 = color2.getB();
		
		BigDecimal c1 = BigDecimalMath.sqrt(a1.pow(2, MC).add(b1.pow(2)), MC);
		BigDecimal c2 = BigDecimalMath.sqrt(a2.pow(2, MC).add(b2.pow(2)), MC);

		BigDecimal g1 = c1.add(c2, MC).divide(bd(2, MC), MC).pow(7, MC);
		BigDecimal g2 = c1.add(c2, MC).divide(bd(2, MC), MC).pow(7, MC);
		BigDecimal g3 = bd(25, MC).pow(7, MC);
		BigDecimal g = BigDecimal.ONE.subtract(BigDecimalMath.sqrt(g1.divide(g2.add(g3, MC), MC), MC), MC).divide(bd(2, MC), MC);

		BigDecimal a1_ = BigDecimal.ONE.add(g, MC).multiply(a1, MC);
		BigDecimal b1_ = b1; 
		BigDecimal c1_ = BigDecimalMath.sqrt(a1_.pow(2, MC).add(b1_.pow(2)), MC);

		BigDecimal h1_ = toDegrees(BigDecimalMath.atan2(b1, a1_, MC), MC);
		if (h1_.compareTo(BigDecimal.ZERO) < 0) {
			h1_ = h1_.add(bd(360, MC), MC);
		}
		
		BigDecimal a2_ = BigDecimal.ONE.add(g, MC).multiply(a2, MC);
		BigDecimal b2_ = b2; 
		BigDecimal c2_ = BigDecimalMath.sqrt(a2_.pow(2, MC).add(b2_.pow(2)), MC);

		BigDecimal h2_ = toDegrees(BigDecimalMath.atan2(b2, a2_, MC), MC);
		if (h2_.compareTo(BigDecimal.ZERO) < 0) {
			h2_ = h2_.add(bd(360, MC), MC);
		}
		
		BigDecimal dH = h2_.subtract(h1_, MC).abs(MC);
		if (dH.compareTo(bd(180, MC)) > 0) {
			dH = bd(360, MC).subtract(dH);
		}

		BigDecimal meanL = l1.add(l2, MC).divide(bd(2, MC), MC);
		BigDecimal meanC = c1_.add(c2_, MC).divide(bd(2, MC), MC);
		BigDecimal meanH = null;
		if (h1_.subtract(h2_, MC).abs(MC).compareTo(bd(180, MC)) <= 0) {
			meanH = h1_.add(h2_, MC).divide(bd(2, MC), MC);
		} else {
			meanH = h1_.add(h2_, MC).subtract(bd(360, MC), MC).divide(bd(2, MC), MC);
		}

		BigDecimal dL = l2.subtract(l1, MC).abs(MC);
		BigDecimal dC = c2_.subtract(c1_, MC).abs(MC);
		
		BigDecimal dH_1 = BigDecimalMath.sqrt(c1_.multiply(c2_, MC), MC);
		BigDecimal dH_2 = BigDecimalMath.sin(toRadians(dH, MC).divide(bd(2, MC), MC), MC);
		BigDecimal dH_ = bd(2, MC).multiply(dH_1, MC).multiply(dH_2, MC);
		
		BigDecimal Tp1 = bd(-1, MC).multiply(bd("0.17", MC).multiply(BigDecimalMath.cos(toRadians(bd(1.0, MC).multiply(meanH, MC).subtract(bd(30, MC), MC), MC), MC), MC));
		BigDecimal Tp2 = bd(1, MC).multiply(bd("0.24", MC).multiply(BigDecimalMath.cos(toRadians(bd(2.0, MC).multiply(meanH, MC).add(bd(0, MC), MC), MC), MC), MC));
		BigDecimal Tp3 = bd(1, MC).multiply(bd("0.32", MC).multiply(BigDecimalMath.cos(toRadians(bd(3.0, MC).multiply(meanH, MC).add(bd(6, MC), MC), MC), MC), MC));
		BigDecimal Tp4 = bd(-1, MC).multiply(bd("0.20", MC).multiply(BigDecimalMath.cos(toRadians(bd(4.0, MC).multiply(meanH, MC).subtract(bd(63, MC), MC), MC), MC), MC));
		BigDecimal T = bd(1, MC).add(Tp1).add(Tp2).add(Tp3).add(Tp4);

		BigDecimal SL = bd(1, MC).add(bd("0.015", MC).multiply(meanL.subtract(bd(50, MC), MC).pow(2, MC), MC).divide(BigDecimalMath.sqrt(bd(20, MC).add(meanL.subtract(bd(50, MC), MC).pow(2, MC), MC), MC), MC), MC);
		BigDecimal SC = bd(1, MC).add(bd("0.045", MC).multiply(meanC));
		BigDecimal SH = bd(1, MC).add(bd("0.015", MC).multiply(meanC).multiply(T));

		BigDecimal dq1 = meanH.subtract(bd(275, MC), MC).divide(bd(25, MC), MC);
		BigDecimal dq2 = bd(-1, MC).multiply(dq1.pow(2, MC), MC);
		BigDecimal dq = bd(30, MC).multiply(BigDecimalMath.exp(dq2, MC), MC);

		BigDecimal RC = bd(2, MC).multiply(BigDecimalMath.sqrt(meanC.pow(7, MC).divide(meanC.pow(7, MC).add(bd(25, MC).pow(7, MC)), MC), MC), MC);
//		BigDecimal RT = bd(-1, MC).multiply(RC, MC).multiply(toDegrees(BigDecimalMath.sin(toRadians(bd(2, MC).multiply(dq, MC), MC), MC), MC));
		BigDecimal RT = bd(-1, MC).multiply(RC, MC).multiply(BigDecimalMath.sin(bd(2, MC).multiply(toRadians(dq, MC), MC), MC), MC);
				
		BigDecimal KL = BigDecimal.ONE;
		BigDecimal KC = BigDecimal.ONE;
		BigDecimal KH = BigDecimal.ONE;

		BigDecimal dE1 = dL.divide(KL.multiply(SL, MC), MC).pow(2, MC);
		BigDecimal dE2 = dC.divide(KC.multiply(SC, MC), MC).pow(2, MC);
		BigDecimal dE3 = dH_.divide(KH.multiply(SH, MC), MC).pow(2, MC);
		BigDecimal dE4 = dC.divide(KC.multiply(SC, MC), MC);
		BigDecimal dE5 = dH_.divide(KH.multiply(SH, MC), MC);
		BigDecimal dE = BigDecimalMath.sqrt(dE1.add(dE2, MC).add(dE3, MC).add(RT.multiply(dE4, MC).multiply(dE5, MC), MC), MC);
		
		return dE;
	}

	private static BigDecimal _calculate_f_xyz(BigDecimal val) {
		BigDecimal result;

		BigDecimal vP3 = val.pow(3, MC);
		if (vP3.compareTo(E) > 0) {
			result = vP3;
		} else {
			result = val.multiply(bd(116, MC), MC).subtract(bd(16, MC), MC).divide(K, MC);
		}

		return result;
	}

	private static BigDecimal _calculate_f_lab(BigDecimal val) {
		BigDecimal result;
		
		if (val.compareTo(E) > 0) {
			result = BigDecimalMath.root(val, bd(3, MC), MC);
		} else {
			result = val.multiply(K, MC).add(bd(16, MC), MC).divide(bd(116, MC), MC);
		}
		
		return result;
	}


	public static final BigDecimal PI = BigDecimalMath.pi(MC);
	
	private static BigDecimal toRadians(BigDecimal angdeg, MathContext mc) {
        return angdeg.divide(bd(180, mc), mc).multiply(PI, mc);
    }
	
	private static BigDecimal toDegrees(BigDecimal angrad, MathContext mc) {
        return angrad.multiply(bd(180, mc), mc).divide(PI, mc);
    }
	
	
	private static BigDecimal bd(int i, MathContext mc) {
		return new BigDecimal(i, mc);
	}
	
	private static BigDecimal bd(double i, MathContext mc) {
		return new BigDecimal(i, mc);
	}
	
	private static BigDecimal bd(String i, MathContext mc) {
		return new BigDecimal(i, mc);
	}
}
