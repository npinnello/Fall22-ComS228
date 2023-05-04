package q2;

public class Complex implements Cloneable {
	private int re; // real part
	private int im;

	// imaginary part
	public Complex(int re, int im) {
		this.re = re;
		this.im = im;
	}

	@Override
	public boolean equals(Object o) {
		// Assume this is already implemented ; the
		// implementation details irrelevant .
		if (this == o) {
			return true;
		}
		if(o == null) {
			return false;
		}
		if(this.getClass() != o.getClass()) {
			return false;
		}
		Complex c = (Complex)o;
		if(this.re != c.re || this.im != c.im) {
			return false;
		}
		return true;
	}
	
	@Override
	public Object clone() {
		try {
			Complex c = (Complex) super.clone();
			return c;
		}
		catch(CloneNotSupportedException e) {
			return null;
		}
	}
}
