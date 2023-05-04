package q2;

public class ComplexTuple {
	private Complex c1;
	private Complex c2;

	public ComplexTuple(Complex c1, Complex c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	@Override
	public boolean equals (Object o) {
		if(this == o)
			return true;
		if(o == null || this.getClass() != o.getClass())
			return false;
		ComplexTuple t = (ComplexTuple) o;
		if(this.c1.equals(t.c1) && this.c2.equals(t.c2))
			return true;
		return false;
	}
}
