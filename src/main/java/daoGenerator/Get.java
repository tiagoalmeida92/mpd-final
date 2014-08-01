package daoGenerator;

public class Get extends CmdType {
	@Override
	Object execute(ResultBuilder b) {
		return b.getResult(this);
	}
}
