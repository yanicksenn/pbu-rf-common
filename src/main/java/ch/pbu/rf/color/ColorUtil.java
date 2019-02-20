package ch.pbu.rf.color;

import java.util.Objects;

import ch.pbu.rf.color.lab.ColorLab;
import ch.pbu.rf.color.xyz.ColorXYZ;
import ch.pbu.rf.illuminant.Illuminant;

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
		
		double y = 1;
		double x = (ix * y) / iy;
		double z = (iz * y) / iy;
		
		return new ColorXYZ(x, y, z);
	}

	/**
	 * Converts the <I>color</I> to an XYZ-Color by the <I>illuminant</I>.
	 * 
	 * @param color Color.
	 * @param illuminant Illuminant.
	 * 
	 * @return XYZ-Color.
	 */
	public static ColorXYZ convertToXYZ(ColorLab color, Illuminant illuminant) {
		Objects.requireNonNull(color, "color is not specified");
		Objects.requireNonNull(illuminant, "illuminant is not specified");
		
		double l = color.getL();
		double a = color.getA();
		double b = color.getB();
		
		double ty = (l + 16.0) / 116.0;
		double tx = (a / 500.0) + ty;
		double tz = (b / 200.0) - ty;
		

		ColorXYZ rw = getReferenceWhite(illuminant);
		double rx = rw.getX();
		double ry = rw.getY();
		double rz = rw.getZ();

		double x = _calculate_f_xyz(tx) * rx;
		double y = _calculate_f_xyz(ty) * ry;
		double z = _calculate_f_xyz(tz) * rz;
		
		return new ColorXYZ(x, y, z);
	}
	
	private static double _calculate_f_xyz(double v) {
		double result;
		
		double vP3 = Math.pow(v, 3);
		if (vP3 > E) {
			result = vP3;
		} else {
			result = (((v * 116) - 16.0) / K);
		}
		
		return result;
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

		ColorXYZ rw = getReferenceWhite(illuminant);
		
		double rx = color.getX() / rw.getX();
		double ry = color.getY() / rw.getY();
		double rz = color.getZ() / rw.getZ();
		
		double fx = _calculate_f_lab(rx);
		double fy = _calculate_f_lab(ry);
		double fz = _calculate_f_lab(rz);
		
		double l = (116.0 * fy) - 16.0;
		double a = 500.0 * (fx - fy);
		double b = 200.0 * (fy - fz);
		
		return new ColorLab(l, a, b);
	}
	
	private static double _calculate_f_lab(double v) {
		double result;
		
		if (v > E) {
			result = Math.pow(v, (1.0 / 3.0));
		} else {
			result = ((K * v) + 16.0) / 116.0;
		}
		
		return result;
	}
	
	public static double calculateDeltaE2000(ColorLab color1, ColorLab color2) {
		Objects.requireNonNull(color1, "color1 is not specified");
		Objects.requireNonNull(color2, "color2 is not specified");
		
		double L1 = color1.getL();
		double a1 = color1.getA();
		double b1 = color1.getB();

		double L2 = color2.getL();
		double a2 = color2.getA();
		double b2 = color2.getB();
		
		double L_ = (L1 + L2) / 2.0;
		
		double C1 = Math.sqrt(Math.pow(a1, 2.0) + Math.pow(b1, 2.0));
		double C2 = Math.sqrt(Math.pow(a2, 2.0) + Math.pow(b2, 2.0));
		double C = (C1 + C2) / 2.0;
		
		double G = (1.0 - Math.sqrt(Math.pow(C, 7.0) / (Math.pow(C, 7.0) + Math.pow(25, 7.0))));
		
		double a1_ = a1 * (1.0 + G);
		double a2_ = a2 * (1.0 + G);
		
		double C1_ = Math.sqrt(Math.pow(a1_, 2) + Math.pow(b1, 2));
		double C2_ = Math.sqrt(Math.pow(a2_, 2) + Math.pow(b2, 2));
		double C_ = (C1_ + C2_) / 2;
		
		double h1_ = Math.atan2(b1, a1_);
		if (h1_ < 0.0) {
			h1_ += 360.0;
		}
		
		double h2_ = Math.atan2(b2, a2_);
		if (h2_ < 0.0) {
			h2_ += 360.0;
		}
		
		double H_ = (h1_ + h2_ + 360.0) / 2.0;
		if (Math.abs(h1_ - h2_) > 180.0) {
			H_ = (h1_ + h2_) / 2.0;
		}
		
		double T = 1.0
				- (0.17 * Math.toDegrees(Math.cos(Math.toRadians(H_ - 30.0))))
				+ (0.24 * Math.toDegrees(Math.cos(Math.toRadians(2.0 * H_))))
				+ (0.32 * Math.toDegrees(Math.cos(Math.toRadians(3.0 * H_ + 6.0))))
				- (0.20 * Math.toDegrees(Math.cos(Math.toRadians(4.0 * H_ - 63.0))));
		
		double dh_;
		if (Math.abs(h2_ - h1_) <= 180.0) {
			dh_ = h2_ - h1_;
		} else if (Math.abs(h2_ - h1_) > 180.0 && h2_ <= h1_) {
			dh_ = h2_ - h1_ + 360.0;
		} else {
			dh_ = h2_ - h1_ - 360.0;
		}
		
		double dL_ = L2 - L1;
		double dC_ = C2_ - C1_;
		double dH_ = 2.0 * Math.sqrt(C1_ * C2_ * Math.toDegrees((Math.sin(Math.toRadians(dh_ / 2.0)))));
		
		double SL = 1.0 + ((0.015 * Math.pow(L_ - 50.0, 2.0)) / Math.sqrt(20.0 + Math.pow(L_ - 50.0, 2.0)));
		double SC = 1.0 + 0.045 * C_;
		double SH = 1.0 + 0.015 * C_ * T;
		
		double d0 = 30 * Math.exp(- Math.pow(((H_ - 275.0) / 25.0), 2.0));
		
		double RC = 2.0 * Math.sqrt(Math.sqrt(Math.pow(C, 7.0) / (Math.pow(C, 7.0) + Math.pow(25, 7.0))));
		double RT = -1.0 * RC * Math.toDegrees((Math.sin(Math.toRadians(2.0 * d0))));
		
		double KL = 1.0;
		double KC = 1.0;
		double KH = 1.0;
		
		
	}
}
