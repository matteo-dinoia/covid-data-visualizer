package plot;

public class GraphData<X,Y> {
	
	//CONSTRUCTOR
	public GraphData() {}
	public GraphData(X key, Y value) {
		this.key=key;
		this.value=value;
	}
	
	
	//KEY
	private X key;
	public X getKey() {
		return key;
	}
	public void setKey(X key) {
		this.key = key;
	}
	
	
	//VALUE
	private Y value;
	public Y getValue() {
		return value;
	}
	public void setValue(Y value) {
		this.value = value;
	}

}
