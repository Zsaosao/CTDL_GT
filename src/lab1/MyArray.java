package lab1;

import java.util.Arrays;

public class MyArray {
	private int[] array;

	public MyArray(int[] array) {
		this.array = array;
	}

	public int[] mirror() {

		int mirorArray[] = new int[this.array.length * 2];
		for (int i = 0; i < this.array.length; i++) {
			mirorArray[i] = this.array[i];
			mirorArray[mirorArray.length - 1 - i] = this.array[i];
		}

		return mirorArray;
	}

	public int[] removeDuplicates() {

		int count = 0;
		for (int i = 0; i < this.array.length; i++) {
			for (int j = i - 1; j > 0; j--) {
				if (this.array[i] == this.array[j]) {
					count++;
					break;
				}
			}
		}
		int[] res = new int[this.array.length - count];
		int index = 0;
		for (int i = 0; i < this.array.length; i++) {
			boolean flag = true;
			for (int j = i - 1; j > 0; j--) {
				if (this.array[i] == this.array[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				res[index] = this.array[i];
				index++;
			}
		}
		return res;
	}

	public int[] getMissingValues() {
		int count = 0;

		for (int i = 1; i < this.array.length; i++) {
			if (this.array[i] > this.array[i - 1]) {
				count += this.array[i] - this.array[i - 1] - 1;
			}
		}
		int res[] = new int[count];
		int index = 0;
		for (int i = 1; i < this.array.length; i++) {
			if (this.array[i] - 1 > this.array[i - 1]) {
				for (int j = this.array[i - 1] + 1; j < this.array[i]; j++) {
					res[index] = j;
					index++;
				}
			}
		}
		return res;
	}

	public int[] fillMissingValues(int k) {
		int res[] = new int[this.array.length];
		int front = k / 2;
		int back = k - front;
		for (int i = 0; i < this.array.length; i++) {
			if (this.array[i] == -1) {
				int sum = 0;
				if (i - front < 0) {
					for (int j = 0; j <= k; j++) {
						sum += this.array[j];
					}
				} else {
					for (int j = i - front; j <= i + back; j++) {
						sum += this.array[j];
					}
				}
				res[i] = Math.round((sum + 1) / k);
			} else {
				res[i] = this.array[i];
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int arrInit[] = { 1, 2, 3, 5, 6, 7, 9, 10, 10, 9, 9, 7, 3 };
		MyArray myArray = new MyArray(arrInit);
		System.out.println(Arrays.toString(myArray.mirror()));
		System.out.println(Arrays.toString(myArray.removeDuplicates()));
		int arrInit2[] = { 1, 2, 3, 5, 7, 9, 10 };
		MyArray myArray2 = new MyArray(arrInit2);
		System.out.println(Arrays.toString(myArray2.getMissingValues()));

		int arrInit3[] = { 1, 2, 3, -1, -1, 7, 9, 10, 1 };
		MyArray myArray3 = new MyArray(arrInit3);
		System.out.println(Arrays.toString(myArray3.fillMissingValues(2)));

	}

}