package model;
import java.io.Serializable;

public class Operation implements Serializable {
	private double op1;
	private double op2;
	private String op;

	public Operation(double op1, String op, double op2) {
		this.op1 = op1;
		this.op = op;
		this.op2 = op2;
	}

	public double getOp1() {
		return op1;
	}

	public double getOp2() {
		return op2;
	}

	public String getOp() {
		return op;
	}	

}
