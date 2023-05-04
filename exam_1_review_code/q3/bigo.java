package q3;

class bigo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ASCII value of char i = " +(int)'i');
		
		int[] arr = {100, 102, 104, 106};
		System.out.println(hasI(arr, 0));
	}

	// a
	static void methodA(int[] arr) {
		int p = 0;
		int n = arr.length;
		for (int i = 0; i < n; i++) {		// #iterations: O(n)
			for (int j = i; j < n; j++) {	// #iterations: O(n)
				p += arr[j] % 2;
			}
		}
	}
	// methodA worst case execution time: n + n-1 + n-2 + ... 1 = n(n+1)/2 -> O(n^2)
	
	// b: foo -> O(n^2), bar -> O(n^3)
	static void methodB(int[] arr) {
		int n = arr.length;
		while (n > 0) {						// #iterations: O(log(n))
			foo(arr);						// O(n^2)
			n = n/4;						// O(1)
		}									// total time for while: O(n^2*log(n))
		bar(arr);							// O(n^3)
	}
	// n, n/4, n/16, n/64, ..., 1, (let say it takes x steps for n -> 1)
	// n/(4^x) = 1
	// n = 4^x
	// log(n) = x*log(4)
	// x = (1/log(4))*log(n) = C*log(n) -> O(log(n))
	// methodB worst case execution time: O(n^3) + O(n^2*log(n)) -> O(n^3)

	static void foo(int[] arr) {
		methodA(arr);
	}
	
	static void bar(int[] arr) {
		for(int i = 0; i < arr.length; i++)
			foo(arr);
	}
	
	// c
	static boolean hasI(int[] arr, int i) {
		if(i == arr.length)
			return false;
		if(arr[i] == 'i')		// ASCII value of 'i' = 105
			return true;
		else
			return hasI(arr, i+1);
	}
	// hasI(arr, 0): #recursive calls: O(n)
	// hasI worst case execution time: O(n)
	
	// d
//	QuadSort (A , B , C , D ):
//		sort A using MergeSort												O(n*log(n))
//		sort B using MergeSort												O(n*log(n))
//		sort C using MergeSort												O(n*log(n))
//		sort D using MergeSort												O(n*log(n))
//		p = A . length , q = B . length , r = C . length , s = D . length   O(1)
//		create an empty array E of length p + q								O(n)
//		E = Merge (A , B )													O(n)
//		create an empty array F of length r + s								O(n)
//		F = Merge (C , D )													O(n)
//		t = E . length , u = F . length										O(1)
//		create an empty array G of length t + u								O(n)
//		G = Merge (E , F )													O(n)
//		return G															O(1)
	// QuadSort worst case execution time: O(n*log(n))
}
