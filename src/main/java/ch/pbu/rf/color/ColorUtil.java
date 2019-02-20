package ch.pbu.rf.color;

import java.util.Objects;

import ch.pbu.rf.color.lab.ColorLab;
import ch.pbu.rf.color.xyz.ColorXYZ;

/**
 * Represents the Color-Util.
 * 
 * @author Yanick Senn
 */
public class ColorUtil {
	
	public static final double E =   216.0 / 24389.0;
	public static final double K = 24389.0 / 27.0;
	
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
		
		double ix = illuminant.getX();
		double iy = illuminant.getY();
		double iz = illuminant.getZ();
		
		double y = 100;
		double x = (ix * y) / iy;
		double z = (iz * y) / iy;
		
		return new ColorXYZ(x, y, z);
	}
	
	public static ColorXYZ convertToXYZ(ColorLab color, Illuminant illuminant) {
		Objects.requireNonNull(color, "color is not specified");
		Objects.requireNonNull(illuminant, "illuminant is not specified");
		
		double l = color.getL();
		double a = color.getA();
		double b = color.getB();
		
		double ty = (l + 16.0) / 116.0;
		double tx = (a / 500.0) + ty;
		double tz = (b / 200.0) - ty;
		
		double y;
		double y3 = Math.pow(ty, 3);
		if (y3 > E) {
			y = y3;
		} else {
			y = (((ty * 116) - 16.0) / K);
		}
		
		double x;
		double x3 = Math.pow(tx, 3);
		if (x3 > E) {
			x = x3;
		} else {
			x = (((tx * 116) - 16.0) / K);
		}
		
		double z;
		double z3 = Math.pow(tz, 3);
		if (z3 > E) {
			z = z3;
		} else {
			z = (((tz * 116) - 16.0) / K);
		}
		

		ColorXYZ w = getReferenceWhite(illuminant);
		double rx = w.getX();
		double ry = w.getY();
		double rz = w.getZ();
		
		return new ColorXYZ(x * rx, y * ry, z * rz);
	}

	/**
	 * Converts the <I>color</I> to an Lab-Color by the <I>illuminant</I>.
	 * 
	 * @param color Color.
	 * @param illuminant Illuminant.
	 * 
	 * @return Lab-Color.
	 */
	public static ColorLab convertToLab(ColorXYZ color, Illuminant illuminant) {
		Objects.requireNonNull(color, "color is not specified");
		Objects.requireNonNull(illuminant, "illuminant is not specified");

		ColorXYZ w = getReferenceWhite(illuminant);
		
		double rx = color.getX() / w.getX();
		double ry = color.getY() / w.getY();
		double rz = color.getZ() / w.getZ();
		
		double fx;
		if (rx > E) {
			fx = Math.pow(Math.E, Math.log(rx) / 3);
		} else {
			fx = ((K * rx) + 16.0) / 116.0;
		}
		
		double fy;
		if (ry > E) {
			fy = Math.pow(Math.E, Math.log(ry) / 3);
		} else {
			fy = ((K * ry) + 16.0) / 116.0;
		}
		
		double fz;
		if (rz > E) {
			fz = Math.pow(Math.E, Math.log(rz) / 3);
		} else {
			fz = ((K * rz) + 16.0) / 116.0;
		}
		
		double l = (116.0 * fy) - 16.0;
		double a = 500.0 * (fx - fy);
		double b = 200.0 * (fy - fz);
		
		return new ColorLab(l, a, b);
	}
}
