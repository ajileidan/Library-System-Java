package part01;

public enum BookStatus {
	AVAILABLE("Available"), ON_LOAN("On-Loan"), WITHDRAWN("Withdrawn");
	private String status;
	private BookStatus(String status) {
		this.status = status;
	}
	public String toString() {
		return status;
	}
}
