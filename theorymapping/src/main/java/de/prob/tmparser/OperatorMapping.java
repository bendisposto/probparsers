package de.prob.tmparser;

public class OperatorMapping {
	private final String theoryName;
	private final String operatorName;
	private final TMOperatorType operatorType;
	private final String spec;

	public OperatorMapping(String theoryName, String operatorName,
			TMOperatorType operatorType, String spec) {
		super();
		this.theoryName = theoryName;
		this.operatorName = operatorName;
		this.operatorType = operatorType;
		this.spec = spec;
	}

	public String getTheoryName() {
		return theoryName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public TMOperatorType getOperatorType() {
		return operatorType;
	}

	public String getSpec() {
		return spec;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("operator '").append(operatorName);
		str.append("' of theory ").append(theoryName);
		str.append(" and type " + operatorType.toString());
		str.append(": {").append(spec).append("}");
		return str.toString();
	}

	@Override
	public int hashCode() {
		int result = 31 * operatorName.hashCode();
		result = 31 * result + operatorType.hashCode();
		result = 31 * result + spec.hashCode();
		result = 31 * result + theoryName.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperatorMapping other = (OperatorMapping) obj;
		if (operatorName == null) {
			if (other.operatorName != null)
				return false;
		} else if (!operatorName.equals(other.operatorName))
			return false;
		if (operatorType != other.operatorType)
			return false;
		if (spec == null) {
			if (other.spec != null)
				return false;
		} else if (!spec.equals(other.spec))
			return false;
		if (theoryName == null) {
			if (other.theoryName != null)
				return false;
		} else if (!theoryName.equals(other.theoryName))
			return false;
		return true;
	}

}
