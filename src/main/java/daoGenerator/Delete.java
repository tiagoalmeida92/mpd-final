package daoGenerator;

public class Delete extends CmdType {
	
	@Override
	public Object execute(ResultBuilder b){
		return b.getResult(this);
	}
}
