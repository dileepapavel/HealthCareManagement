package model;

public class Payment {
	String type;
	double ammount;
	String paymentHolder;
	String date;
	int payeeID;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmmount() {
		return ammount;
	}
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	public String getPaymentHolder() {
		return paymentHolder;
	}
	public void setPaymentHolder(String paymentHolder) {
		this.paymentHolder = paymentHolder;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPayeeID() {
		return payeeID;
	}
	public void setPayeeID(int payeeID) {
		this.payeeID = payeeID;
	}
}
