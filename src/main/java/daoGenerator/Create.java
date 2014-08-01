package daoGenerator;

public class Create extends CmdType{
	@Override
	Object execute(ResultBuilder b) {
		return b.getResult(this);
	}
}