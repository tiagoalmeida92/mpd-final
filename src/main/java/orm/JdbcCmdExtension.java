package orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCmdExtension<T> implements JdbcCmd<T> {

	
	
	private String _append;
	private JdbcCmd<T> _cmd;

	public JdbcCmdExtension(JdbcCmd<T> cmd, String append){
		_cmd = cmd;
		_append = append;
	}
	
	@Override
	public String getSql() {
		return _cmd.getSql() + _append;
	}

	@Override
	public void bind(PreparedStatement stmt, Object[] args) throws SQLException {
		_cmd.bind(stmt, args);
		
	}

	@Override
	public T convert(ResultSet rs) throws SQLException {
		return _cmd.convert(rs);
	}

}
