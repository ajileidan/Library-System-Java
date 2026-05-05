package part01;

public enum BookType {
	FICTION("Fiction"), NON_FICTION("Non-Fiction"), REFERENCE("Reference");

	private String type;
	private BookType(String type) {
		this.type = type;
	}
	public String toString() {
		return type;
	}
}
