package chapter3.maps;

public class Lesson {

	private String name;
	
	private double value;
	
	public Lesson(String name, double value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(this.getName());
		builder.append("-");
		builder.append(this.getValue());
		builder.append("]");
		return builder.toString();
	}
	
}
