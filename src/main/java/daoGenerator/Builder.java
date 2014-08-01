package daoGenerator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


import orm.JdbcExecutor;

public class Builder<T> {

	@SuppressWarnings("unchecked")
	public static<T> T make(final Class<T> k, final JdbcExecutor exec) {
		return (T) Proxy.newProxyInstance(k.getClassLoader(), new Class[]{k}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				DomainEntity entity = (DomainEntity) k.getAnnotations()[0];
				SqlCmd cmd =  (SqlCmd) method.getAnnotations()[0];
				ResultBuilder<T> rb = new ResultBuilder(cmd.cmd(), entity.value(), exec, args);
				CmdType cmdType = cmd.type().newInstance();
				return cmdType.execute(rb);
			}
		});
	}

}
