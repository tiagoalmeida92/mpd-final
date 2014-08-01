package daoGenerator;

public class Update extends CmdType{

	@Override
	Object execute(ResultBuilder b) {
		return b.getResult(this);
	}
}
