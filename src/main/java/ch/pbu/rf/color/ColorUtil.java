package ch.pbu.rf.color;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

import ch.obermuhlner.math.big.BigDecimalMath;
import ch.pbu.rf.MathUtil;
import ch.pbu.rf.RF;
import ch.pbu.rf.color.lab.ColorLab;
import ch.pbu.rf.color.rgb.ChromaticityCoordinate;
import ch.pbu.rf.color.rgb.ColorRGB;
import ch.pbu.rf.color.rgb.ColorSpaceRGB;
import ch.pbu.rf.color.xyz.ColorXYZ;
import ch.pbu.rf.illuminant.Illuminant;

/**
 * Represents the Color-Util.
 * 
 * @author Yanick Senn
 */
public class ColorUtil {
	public static final MathContext MC = RF.MC;
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
	 * 
	 * @throws NullPointerException If illuminant is not specified.
	 */
	public static ColorXYZ calculateReferenceWhite(Illuminant illuminant) {
		Objects.requireNonNull(illuminant, "illuminant is not specified");

		BigDecimal ix = illuminant.getX();
		BigDecimal iy = illuminant.getY();
		BigDecimal iz = illuminant.getZ();

		BigDecimal y = BigDecimal.ONE;
		BigDecimal x = ix.multiply(y, MC).divide(iy, MC);
		BigDecimal z = iz.multiply(y, MC).divide(iy, MC);

		ColorXYZ result = new ColorXYZ(x, y, z);
		return result;
	}

	/**
	 * Converts the <I>color</I> to an XYZ-Color by the <I>illuminant</I>.
	 * 
	 * @param color Color.
	 * @param illuminant Illuminant.
	 * 
	 * @return XYZ-Color.
	 * 
	 * @throws NullPointerException If color is not specified.
	 * @throws NullPointerException If illuminant is not specified.
	 */
	public static ColorXYZ convertToXYZ(ColorLab color, Illuminant illuminant) {
		Objects.requireNonNull(color, "color is not specified");
		Objects.requireNonNull(illuminant, "illuminant is not specified");

		BigDecimal l = color.getL();
		BigDecimal a = color.getA();
		BigDecimal b = color.getB();

		ColorXYZ rw = calculateReferenceWhite(illuminant);
		BigDecimal rx = rw.getX();
		BigDecimal ry = rw.getY();
		BigDecimal rz = rw.getZ();
		

		BigDecimal ty = l.add(bd(16, MC), MC).divide(bd(116, MC), MC);
		BigDecimal tx = a.divide(bd(500, MC), MC).add(ty, MC);
		BigDecimal tz = b.divide(bd(200, MC), MC).subtract(ty, MC);

		BigDecimal x = _calculate_f_xyz(tx, MC).multiply(rx, MC);
		BigDecimal y = _calculate_f_xyz(ty, MC).multiply(ry, MC);
		BigDecimal z = _calculate_f_xyz(tz, MC).multiply(rz, MC);

		return new ColorXYZ(x, y, z);
	}

	/**
	 * Converts the <I>color</I> to an XYZ-Color by the <I>illuminant</I>.
	 * 
	 * @param color Color.
	 * @param illuminant Illuminant.
	 * 
	 * @return XYZ-Color.
	 * 
	 * @throws NullPointerException If color is not specified.
	 * @throws NullPointerException If illuminant is not specified.
	 */
	public static ColorXYZ convertToXYZ(ColorRGB color, Illuminant illuminant) {
		Objects.requireNonNull(color, "color is not specified");
		Objects.requireNonNull(illuminant, "illuminant is not specified");

		BigDecimal r = color.getR();
		BigDecimal g = color.getG();
		BigDecimal b = color.getB();
		
		BigDecimal[][] m = calculateRGBtoXYZTransformationMatrix(RF.RGB.ColorSpace.sRGB);
		BigDecimal[][] rgb = {
			{r},
			{g},
			{b}
		};
		
		BigDecimal[][] xyz = MathUtil.calculateProduct(m, rgb, MC);
		
		ColorXYZ result = new ColorXYZ(
			xyz[0][0],
			xyz[1][0],
			xyz[2][0]
		);
		
		return result;
	}

	/**
	 * Converts the <I>color</I> to an Lab-Color by the <I>illuminant</I>.
	 * 
	 * @param color      Color.
	 * @param illuminant Illuminant.
	 * 
	 * @return Lab-Color.
	 * 
	 * @throws NullPointerException If color is not specified.
	 * @throws NullPointerException If illuminant is not specified.
	 */
	public static ColorLab convertToLab(ColorXYZ color, Illuminant illuminant) {
		Objects.requireNonNull(color, "color is not specified");
		Objects.requireNonNull(illuminant, "illuminant is not specified");

		BigDecimal x = color.getX();
		BigDecimal y = color.getY();
		BigDecimal z = color.getZ();

		ColorXYZ rw = calculateReferenceWhite(illuminant);
		BigDecimal rx = rw.getX();
		BigDecimal ry = rw.getY();
		BigDecimal rz = rw.getZ();

		BigDecimal fx = _calculate_f_lab(x.divide(rx, MC), MC);
		BigDecimal fy = _calculate_f_lab(y.divide(ry, MC), MC);
		BigDecimal fz = _calculate_f_lab(z.divide(rz, MC), MC);

		BigDecimal l = fy.multiply(bd(116, MC), MC).subtract(bd(16, MC), MC);
		BigDecimal a = fx.subtract(fy, MC).multiply(bd(500, MC), MC);
		BigDecimal b = fy.subtract(fz, MC).multiply(bd(200, MC), MC);

		return new ColorLab(l, a, b);
	}
	
	
	/**
	 * Calculates the transformations matrix to convert RGB to XYZ by the given color space.
	 * 
	 * @param colorSpace Color space.
	 * @param illuminant Illuminant.
	 * 
	 * @return The transformations matrix to convert RGB to XYZ by the given color space.
	 * 
	 * @throws NullPointerException If colorSpace is not specified.
	 */
	public static BigDecimal[][] calculateRGBtoXYZTransformationMatrix(ColorSpaceRGB colorSpace) throws NullPointerException {
		Objects.requireNonNull(colorSpace, "colorSpace is not specified");
		
		ChromaticityCoordinate r = colorSpace.getR();
		BigDecimal ccrx = r.getX();
		BigDecimal ccry = r.getY();

		ChromaticityCoordinate g = colorSpace.getG();
		BigDecimal ccgx = g.getX();
		BigDecimal ccgy = g.getY();

		ChromaticityCoordinate b = colorSpace.getB();
		BigDecimal ccbx = b.getX();
		BigDecimal ccby = b.getY();
		
		Illuminant illuminant = colorSpace.getIlluminant();
		ColorXYZ rw = calculateReferenceWhite(illuminant);
		BigDecimal[][] matrixRw = { 
			{ rw.getX() },
			{ rw.getY() },
			{ rw.getZ() } 
		};
		
		BigDecimal xr = ccrx.divide(ccry, MC);
		BigDecimal xg = ccgx.divide(ccgy, MC);
		BigDecimal xb = ccbx.divide(ccby, MC);

		BigDecimal yr = BigDecimal.ONE;
		BigDecimal yg = BigDecimal.ONE;
		BigDecimal yb = BigDecimal.ONE;

		BigDecimal zr = BigDecimal.ONE.subtract(ccrx, MC).subtract(ccry, MC).divide(ccry, MC);
		BigDecimal zg = BigDecimal.ONE.subtract(ccgx, MC).subtract(ccgy, MC).divide(ccgy, MC);
		BigDecimal zb = BigDecimal.ONE.subtract(ccbx, MC).subtract(ccby, MC).divide(ccby, MC);
		
		BigDecimal[][] matrix = {
			{ xr, xg, xb },
			{ yr, yg, yb },
			{ zr, zg, zb }
		};
		
		BigDecimal[][] matrixInversed = MathUtil.calculate3x3Inverse(matrix, MC);
		BigDecimal[][] matrixSrgb = MathUtil.calculateProduct(matrixInversed, matrixRw, MC);
		
		BigDecimal[][] result = {
			{ matrix[0][0].multiply(matrixSrgb[0][0], MC), matrix[0][1].multiply(matrixSrgb[1][0], MC), matrix[0][2].multiply(matrixSrgb[2][0], MC) },
			{ matrix[1][0].multiply(matrixSrgb[0][0], MC), matrix[1][1].multiply(matrixSrgb[1][0], MC), matrix[1][2].multiply(matrixSrgb[2][0], MC) },
			{ matrix[2][0].multiply(matrixSrgb[0][0], MC), matrix[2][1].multiply(matrixSrgb[1][0], MC), matrix[2][2].multiply(matrixSrgb[2][0], MC) }
		};
		
		return result;
	}


	/**
	 * Calculates the transformations matrix to convert XYZ to RGB by the given color space.
	 * 
	 * @param colorSpace Color space.
	 * @param illuminant Illuminant.
	 * 
	 * @return The transformations matrix to convert XYZ to RGB by the given color space.
	 * 
	 * @throws NullPointerException If colorSpace is not specified.
	 */
	public static BigDecimal[][] calculateXYZtoRGBTransformationMatrix(ColorSpaceRGB colorSpace) throws NullPointerException {
		Objects.requireNonNull(colorSpace, "colorSpace is not specified");
		
		BigDecimal[][] transformationMatrix = calculateRGBtoXYZTransformationMatrix(colorSpace);
		BigDecimal[][] result = MathUtil.calculate3x3Inverse(transformationMatrix, MC);
		return result;
	}

	/**
	 * Calculates the gamma correction for RGB to XYZ.
	 * 
	 * @param val Value.
	 * 
	 * @return The gamma correction for RGB to XYZ.
	 * 
	 * @throws NullPointerException If val is not specified.
	 */
	public static BigDecimal calulateRGBtoXYZGammaCorrection(BigDecimal val) {
		Objects.requireNonNull(val, "val is not specified");
		
		BigDecimal result = null;
		if (val.compareTo(bd("0.04045", MC)) < 0) {
			result = val.divide(bd("12.92", MC), MC);
		} else {
			result = BigDecimalMath.pow(val.add(bd("0.055", MC), MC).divide(bd("1.055", MC), MC), bd("2.4", MC), MC);
		}
		
		return result;
	}


	/**
	 * Calculates the gamma correction for XYZ to RGB.
	 * 
	 * @param val Value.
	 * 
	 * @return The gamma correction for XYZ to RGB.
	 * 
	 * @throws NullPointerException If val is not specified.
	 */
	public static BigDecimal calulateXYZtoRGBGammaCorrection(BigDecimal val) {
		Objects.requireNonNull(val, "val is not specified");
		
		BigDecimal result = null;
		if (val.compareTo(bd("0.0031308", MC)) <= 0) {
			result = val.multiply(bd("12.92", MC), MC);
		} else {
			result = bd("1.055", MC).multiply(BigDecimalMath.pow(val, BigDecimal.ONE.divide(bd("2.4", MC), MC), MC), MC).subtract(bd("0.055", MC), MC);
		}
		
		return result;
	}
	
	/**
	 * Calculates the delta E1976 for the given two colors.
	 * 
	 * @param color1 First color.
	 * @param color2 Second color.
	 * 
	 * @return Delta E1976 for the given two colors.
	 * 
	 * @throws NullPointerException If color1 is not specified.
	 * @throws NullPointerException If color2 is not specified.
	 * @throws DeltaE1976CalculationException If it is not possible to calculate the delta E1976.
	 */
	public static BigDecimal calculateDeltaE1976(ColorLab color1, ColorLab color2) throws NullPointerException {
		Objects.requireNonNull(color1, "color1 is not specified");
		Objects.requireNonNull(color2, "color2 is not specified");
		
		BigDecimal color1L = replaceZeroWithNearlyZero(color1.getL(), MC);
		BigDecimal color1a = replaceZeroWithNearlyZero(color1.getA(), MC);
		BigDecimal color1b = replaceZeroWithNearlyZero(color1.getB(), MC);

		BigDecimal color2L = replaceZeroWithNearlyZero(color2.getL(), MC);
		BigDecimal color2a = replaceZeroWithNearlyZero(color2.getA(), MC);
		BigDecimal color2b = replaceZeroWithNearlyZero(color2.getB(), MC);

		BigDecimal result = calculateDeltaE1976(color1L, color1a, color1b, color2L, color2a, color2b, MC);
		return result;
	}
	
	public static BigDecimal calculateDeltaE1976(BigDecimal l1, BigDecimal a1, BigDecimal b1, BigDecimal l2, BigDecimal a2, BigDecimal b2, MathContext mc) {
		Objects.requireNonNull(l1, "l1 is not specified");
		Objects.requireNonNull(a1, "a1 is not specified");
		Objects.requireNonNull(b1, "b1 is not specified");
		Objects.requireNonNull(l2, "l2 is not specified");
		Objects.requireNonNull(a2, "a2 is not specified");
		Objects.requireNonNull(b2, "b2 is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal step1 = l1.subtract(l2, mc).pow(2, mc);
		BigDecimal step2 = a1.subtract(a2, mc).pow(2, mc);
		BigDecimal step3 = b1.subtract(b2, mc).pow(2, mc);
		
		BigDecimal result = BigDecimalMath.sqrt(step1.add(step2).add(step3), mc);
		return result;
	}
	
	/**
	 * Calculates the delta E2000 for the given two colors.
	 * 
	 * @param color1 First color.
	 * @param color2 Second color.
	 * 
	 * @return Delta E2000 for the given two colors.
	 * 
	 * @throws DeltaE2000CalculationException If it is not possible to calculate the delta E2000.
	 */
	public static BigDecimal calculateDeltaE2000(ColorLab color1, ColorLab color2) {
		Objects.requireNonNull(color1, "color1 is not specified");
		Objects.requireNonNull(color2, "color2 is not specified");
		
		BigDecimal color1L = replaceZeroWithNearlyZero(color1.getL(), MC);
		BigDecimal color1a = replaceZeroWithNearlyZero(color1.getA(), MC);
		BigDecimal color1b = replaceZeroWithNearlyZero(color1.getB(), MC);

		BigDecimal color2L = replaceZeroWithNearlyZero(color2.getL(), MC);
		BigDecimal color2a = replaceZeroWithNearlyZero(color2.getA(), MC);
		BigDecimal color2b = replaceZeroWithNearlyZero(color2.getB(), MC);
		
		BigDecimal g = calculateG(color1a, color1b, color2a, color2b, MC);

		BigDecimal a1 = calculateA(g, color1a, MC);
		BigDecimal b1 = calculateB(color1b, MC); 
		BigDecimal c1 = calculateC(a1, b1, MC);
		BigDecimal h1 = calculateH(a1, color1b, MC);
		
		BigDecimal a2 = calculateA(g, color2a, MC);
		BigDecimal b2 = calculateB(color2b, MC); 
		BigDecimal c2 = calculateC(a2, b2, MC);
		BigDecimal h2 = calculateH(a2, color2b, MC);
		
		BigDecimal meanL = calculateMeanL(color1L, color2L, MC);
		BigDecimal meanC = calculateMeanC(c1, c2, MC);
		BigDecimal meanH = calculateMeanH(h1, h2, MC);

		BigDecimal dL = calculateDeltaL(color1L, color2L, MC);
		BigDecimal dC = calculateDeltaC(c1, c2, MC);
		BigDecimal dH = calculateDeltaH(c1, c2, h1, h2, MC);
		
		BigDecimal T = calculateT(meanH, MC);

		BigDecimal SL = calculateSL(meanL, MC);
		BigDecimal SC = calculateSC(meanC, MC);
		BigDecimal SH = calculateSH(meanC, T, MC);

		BigDecimal d0 = calculateD0(meanH , MC);

		BigDecimal RC = calculateRC(meanC, MC);
		BigDecimal RT = calculateRT(RC, d0, MC);
				
		BigDecimal KL = BigDecimal.ONE;
		BigDecimal KC = BigDecimal.ONE;
		BigDecimal KH = BigDecimal.ONE;

		BigDecimal dE = calculateDeltaE2000(dL, dC, dH, KL, KC, KH, SL, SC, SH, RT, MC);
		return dE;
	}
	
	public static BigDecimal calculateG(BigDecimal a1, BigDecimal b1, BigDecimal a2, BigDecimal b2, MathContext mc) {
		Objects.requireNonNull(a1, "a1 is not specified");
		Objects.requireNonNull(b1, "b1 is not specified");
		Objects.requireNonNull(a2, "a2 is not specified");
		Objects.requireNonNull(b2, "b2 is not specified");
		Objects.requireNonNull(mc, "mc is not specified");

		BigDecimal step1 = BigDecimalMath.sqrt(a1.pow(2, mc).add(b1.pow(2, mc), mc), mc);
		BigDecimal step2 = BigDecimalMath.sqrt(a2.pow(2, mc).add(b2.pow(2, mc), mc), mc);

		BigDecimal step3 = step1.add(step2, mc).divide(bd(2, mc), mc).pow(7, mc);
		BigDecimal step4 = step1.add(step2, mc).divide(bd(2, mc), mc).pow(7, mc);
		BigDecimal step5 = bd(25, mc).pow(7, mc);
		
		BigDecimal result = BigDecimal.ONE.subtract(BigDecimalMath.sqrt(step3.divide(step4.add(step5, mc), mc), mc), mc).divide(bd(2, mc), mc);
		return result;
	}

	public static BigDecimal calculateA(BigDecimal g, BigDecimal a, MathContext mc) {
		Objects.requireNonNull(g, "g is not specified");
		Objects.requireNonNull(a, "a is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal result = BigDecimal.ONE.add(g, mc).multiply(a, mc);
		return result;
	}

	public static BigDecimal calculateB(BigDecimal b, MathContext mc) {
		Objects.requireNonNull(b, "b is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal result = b;
		return result;
	}

	public static BigDecimal calculateC(BigDecimal a, BigDecimal b, MathContext mc) {
		Objects.requireNonNull(a, "a is not specified");
		Objects.requireNonNull(b, "b is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal result = BigDecimalMath.sqrt(a.pow(2, mc).add(b.pow(2)), mc);
		return result;
	}

	public static BigDecimal calculateH(BigDecimal a, BigDecimal b, MathContext mc) {
		Objects.requireNonNull(a, "a is not specified");
		Objects.requireNonNull(b, "b is not specified");
		Objects.requireNonNull(mc, "mc is not specified");

		BigDecimal result = toDegrees(BigDecimalMath.atan2(b, a, mc), mc);
		if (result.compareTo(BigDecimal.ZERO) < 0) {
			result = result.add(bd(360, mc), mc);
		}
		
		return result;
	}

	public static BigDecimal calculateMeanL(BigDecimal a, BigDecimal b, MathContext mc) {
		Objects.requireNonNull(a, "a is not specified");
		Objects.requireNonNull(b, "b is not specified");
		Objects.requireNonNull(mc, "mc is not specified");

		BigDecimal result = a.add(b, mc).divide(bd(2, mc), mc);
		return result;
	}

	public static BigDecimal calculateMeanC(BigDecimal a, BigDecimal b, MathContext mc) {
		Objects.requireNonNull(a, "a is not specified");
		Objects.requireNonNull(b, "b is not specified");
		Objects.requireNonNull(mc, "mc is not specified");

		BigDecimal result = a.add(b, mc).divide(bd(2, mc), mc);
		return result;
	}

	public static BigDecimal calculateMeanH(BigDecimal h1, BigDecimal h2, MathContext mc) {
		Objects.requireNonNull(h1, "h1 is not specified");
		Objects.requireNonNull(h2, "h2 is not specified");
		Objects.requireNonNull(mc, "mc is not specified");

		BigDecimal result = null;
		if (h1.subtract(h2, mc).abs(mc).compareTo(bd(180, mc)) <= 0) {
			result = h1.add(h2, mc).divide(bd(2, mc), mc);
		} else {
			result = h1.add(h2, mc).subtract(bd(360, mc), mc).divide(bd(2, mc), mc);
		}
		
		return result;
	}
	
	public static BigDecimal calculateDeltaL(BigDecimal l1, BigDecimal l2, MathContext mc) {
		Objects.requireNonNull(l1, "l1 is not specified");
		Objects.requireNonNull(l2, "l2 is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal result = l2.subtract(l1, mc).abs(mc);
		return result;
	}

	public static BigDecimal calculateDeltaC(BigDecimal c1, BigDecimal c2, MathContext mc) {
		Objects.requireNonNull(c1, "c1 is not specified");
		Objects.requireNonNull(c2, "c2 is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal result = c2.subtract(c1, mc).abs(mc);
		return result;
	}
	
	public static BigDecimal calculateDeltaH(BigDecimal c1, BigDecimal c2, BigDecimal h1, BigDecimal h2, MathContext mc) {
		Objects.requireNonNull(c1, "c1 is not specified");
		Objects.requireNonNull(c2, "c2 is not specified");
		Objects.requireNonNull(h1, "h1 is not specified");
		Objects.requireNonNull(h2, "h2 is not specified");
		Objects.requireNonNull(mc, "mc is not specified");

		BigDecimal step1 = h2.subtract(h1, mc).abs(mc);
		if (step1.compareTo(bd(180, mc)) > 0) {
			step1 = bd(360, mc).subtract(step1);
		}
		
		BigDecimal step2 = BigDecimalMath.sqrt(c1.multiply(c2, mc), mc);
		BigDecimal step3 = BigDecimalMath.sin(toRadians(step1, mc).divide(bd(2, mc), mc), mc);
		BigDecimal result = bd(2, mc).multiply(step2, mc).multiply(step3, mc);
		return result;
	}
	
	public static BigDecimal calculateT(BigDecimal meanH, MathContext mc) {
		Objects.requireNonNull(meanH, "meanH is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal step1 = bd(-1, mc).multiply(bd("0.17", mc).multiply(BigDecimalMath.cos(toRadians(bd(1.0, mc).multiply(meanH, mc).subtract(bd(30, mc), mc), mc), mc), mc));
		BigDecimal step2 = bd(1, mc).multiply(bd("0.24", mc).multiply(BigDecimalMath.cos(toRadians(bd(2.0, mc).multiply(meanH, mc).add(bd(0, mc), mc), mc), mc), mc));
		BigDecimal step3 = bd(1, mc).multiply(bd("0.32", mc).multiply(BigDecimalMath.cos(toRadians(bd(3.0, mc).multiply(meanH, mc).add(bd(6, mc), mc), mc), mc), mc));
		BigDecimal step4 = bd(-1, mc).multiply(bd("0.20", mc).multiply(BigDecimalMath.cos(toRadians(bd(4.0, mc).multiply(meanH, mc).subtract(bd(63, mc), mc), mc), mc), mc));
		BigDecimal result = bd(1, mc).add(step1).add(step2).add(step3).add(step4);
		return result;
	}
	
	public static BigDecimal calculateSL(BigDecimal meanL, MathContext mc) {
		Objects.requireNonNull(meanL, "meanL is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal step1 = meanL.subtract(bd(50, mc), mc).pow(2, mc);
		BigDecimal step2 = BigDecimalMath.sqrt(bd(20, mc).add(step1, mc), mc);
		BigDecimal result = bd(1, mc).add(bd("0.015", mc).multiply(step1).divide(step2, mc), mc);
		return result;
	}

	public static BigDecimal calculateSC(BigDecimal meanC, MathContext mc) {
		Objects.requireNonNull(meanC, "meanC is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal result = bd(1, mc).add(bd("0.045", mc).multiply(meanC, mc), mc);
		return result;
	}
	
	public static BigDecimal calculateSH(BigDecimal meanC, BigDecimal T, MathContext mc) {
		Objects.requireNonNull(meanC, "meanC is not specified");
		Objects.requireNonNull(T, "T is not specified");
		Objects.requireNonNull(mc, "mc is not specified");

		BigDecimal result = bd(1, mc).add(bd("0.015", mc).multiply(meanC, mc).multiply(T, mc), mc);
		return result;
	}
	
	public static BigDecimal calculateD0(BigDecimal meanH, MathContext mc) {
		Objects.requireNonNull(meanH, "meanH is not specified");
		Objects.requireNonNull(mc, "mc is not specified");

		BigDecimal step1 = meanH.subtract(bd(275, mc), mc).divide(bd(25, mc), mc);
		BigDecimal step2 = bd(-1, mc).multiply(step1.pow(2, mc), mc);
		BigDecimal result = bd(30, mc).multiply(BigDecimalMath.exp(step2, mc), mc);
		return result;
	}
	
	public static BigDecimal calculateRC(BigDecimal meanC, MathContext mc) {
		Objects.requireNonNull(meanC, "meanC is not specified");
		Objects.requireNonNull(mc, "mc is not specified");

		BigDecimal step1 = meanC.pow(7, mc).add(bd(25, mc).pow(7, mc), mc);
		BigDecimal result = bd(2, mc).multiply(BigDecimalMath.sqrt(meanC.pow(7, mc).divide(step1, mc), mc), mc);
		return result;
	}
	
	public static BigDecimal calculateRT(BigDecimal RC, BigDecimal d0, MathContext mc) {
		Objects.requireNonNull(RC, "RC is not specified");
		Objects.requireNonNull(d0, "d0 is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal step1 = BigDecimalMath.sin(bd(2, mc).multiply(toRadians(d0, mc), mc), mc);
		BigDecimal result = bd(-1, mc).multiply(RC, mc).multiply(step1, mc);
		return result;
	}
	
	public static BigDecimal calculateDeltaE2000(BigDecimal dL, BigDecimal dC, BigDecimal dH, BigDecimal KL, BigDecimal KC, BigDecimal KH, BigDecimal SL, BigDecimal SC, BigDecimal SH, BigDecimal RT, MathContext mc) {
		Objects.requireNonNull(dL, "dL is not specified");
		Objects.requireNonNull(dC, "dC is not specified");
		Objects.requireNonNull(dH, "dH is not specified");
		Objects.requireNonNull(KL, "KL is not specified");
		Objects.requireNonNull(KC, "KC is not specified");
		Objects.requireNonNull(KH, "KH is not specified");
		Objects.requireNonNull(SL, "SL is not specified");
		Objects.requireNonNull(SC, "SC is not specified");
		Objects.requireNonNull(SH, "SH is not specified");
		Objects.requireNonNull(RT, "RT is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal step1 = dL.divide(KL.multiply(SL, mc), mc).pow(2, mc);
		BigDecimal step2 = dC.divide(KC.multiply(SC, mc), mc).pow(2, mc);
		BigDecimal step3 = dH.divide(KH.multiply(SH, mc), mc).pow(2, mc);
		BigDecimal step4 = dC.divide(KC.multiply(SC, mc), mc);
		BigDecimal step5 = dH.divide(KH.multiply(SH, mc), mc);
		BigDecimal step6 = RT.multiply(step4, mc).multiply(step5, mc);
		BigDecimal result = BigDecimalMath.sqrt(step1.add(step2, mc).add(step3, mc).add(step6, mc), mc);
		return result;
	}
	
	
	private static BigDecimal _calculate_f_xyz(BigDecimal val, MathContext mc) {
		BigDecimal result;

		BigDecimal vP3 = val.pow(3, mc);
		if (vP3.compareTo(E) > 0) {
			result = vP3;
		} else {
			result = val.multiply(bd(116, mc), mc).subtract(bd(16, mc), mc).divide(K, mc);
		}

		return result;
	}

	private static BigDecimal _calculate_f_lab(BigDecimal val, MathContext mc) {
		BigDecimal result;
		
		if (val.compareTo(E) > 0) {
			result = BigDecimalMath.root(val, bd(3, mc), mc);
		} else {
			result = val.multiply(K, mc).add(bd(16, mc), mc).divide(bd(116, mc), mc);
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
	
	private static BigDecimal replaceZeroWithNearlyZero(BigDecimal val, MathContext mc) {
		Objects.requireNonNull(val, "val is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal result = val;
		
		if (BigDecimal.ZERO.compareTo(val) == 0) {
			int digitsAfterComma = mc.getPrecision();
			result = BigDecimal.ONE.divide(BigDecimal.TEN.pow(digitsAfterComma, mc), mc);
		}
		
		return result;
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
