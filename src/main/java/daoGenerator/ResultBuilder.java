package daoGenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import orm.JdbcCmd;
import orm.JdbcExecutor;

public class ResultBuilder<T> {
	
	private Class<T> _k;
	private JdbcExecutor _exec;
	private Object[] _args;
	private String _sql;
	
	public ResultBuilder(String sql, Class <T> k, JdbcExecutor exec, Object...args) {
		_sql = sql;
	    _k = k;
		_exec = exec;
		_args = args;
	}
	
	public Object getResult(Create type){
		//_exec.executeInsert(cmd, _args);
		return null;
	}

	public Object getResult(Delete delete) {
		return delete;
		
	}

	public Object getResult(Update update) {
		return update;
		
	}

	public Object getResult(Get get) {
		JdbcCmd<T> cmd = new JdbcCmd<T>(){

			@Override
			public String getSql() {
				return _sql;
			}

			@Override
			public void bind(PreparedStatement stmt, Object[] args)
					throws SQLException {
				for (int i = 0; args != null && i < args.length; i++) {
					stmt.setObject(i+1, args[i]);
				}
			}

			@Override
			public T convert(ResultSet rs) throws SQLException {
				try {
					
					Field [] fields = _k.getDeclaredFields();
					Object [] values = new Object[fields.length];
					Class <?>[] types = new Class[fields.length];
					for (int i = 0; i < fields.length; i++) {
						Object o = rs.getObject(i+1);
						values[i] = o instanceof BigDecimal ? ((BigDecimal)o).doubleValue() :o;
						types[i] = fields[i].getType();
					}
					Constructor<T> cons = _k.getConstructor(types);
					T t = (T) cons.newInstance(values);
					return t;
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException e) {
					throw new RuntimeException(e);
				} 
			}

			
		};
		try {
			Iterable<T> it = _exec.executeQuery(cmd, _args);
			if(_args != null && _args.length == 1){
				return  it.iterator().next();
			}
			return it;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
