package com.fshows.commons.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBKfShopUtilsTemplate {

	private static QueryRunner queryRunner;
	private static final Log LOG = LogFactory.getLog(DBKfShopUtilsTemplate.class);

	static{
		System.out.println("============init DBUtilsTemplate============");
		queryRunner = DbHelperOnlineKfShop.getQueryRunner();
	}
	

	/**
	 * 
	 * @param sql
	 *            插入sql语句
	 * @param params
	 *            插入参数
	 * @return 返回影响行数
	 */
	public static int insert(String sql, Object[] params) {
		int affectedRows = 0;
		try {
			if (params == null) {
				affectedRows = queryRunner.update(sql);
			} else {
				affectedRows = queryRunner.update(sql, params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("insert.插入记录错误：" + sql, e);
		}
		return affectedRows;
	}


	@SuppressWarnings("rawtypes")
	private static ScalarHandler scalarHandler = new ScalarHandler() {
		@Override
		public Object handle(ResultSet rs) throws SQLException {
			Object obj = super.handle(rs);
			if (obj instanceof BigInteger)
				return ((BigInteger) obj).longValue();
			return obj;
		}
	};

	@SuppressWarnings("unchecked")
	public static long count(String sql, Object... params) {
		Number num = 0;
		try {
			
			if (params == null) {
				num = (Number) queryRunner.query(sql, scalarHandler);
			} else {
				num = (Number) queryRunner.query(sql, scalarHandler, params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("count.统计数量错误" + sql, e);
		}
		return (num != null) ? num.longValue() : -1;
	}

	/**
	 * 执行sql语句
	 * 
	 * @param sql
	 *            sql语句
	 * @return 受影响的行数
	 */
	public static int update(String sql) {
		return update(sql, null);
	}

	/**
	 * 单条修改记录
	 * 
	 * @param sql
	 *            sql语句
	 * @param param
	 *            参数
	 * @return 受影响的行数
	 */
	public static int update(String sql, Object param) {
		return update(sql, new Object[] { param });
	}

	/**
	 * 单条修改记录
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数数组
	 * @return 受影响的行数
	 */
	public static int update(String sql, Object[] params) {
		int affectedRows = 0;
		try {
			if (params == null) {
				affectedRows = queryRunner.update(sql);
			} else {
				affectedRows = queryRunner.update(sql, params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("update.单条修改记录错误：" + sql, e);
		}
		return affectedRows;
	}

	/**
	 * 批量修改记录
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            二维参数数组
	 * @return 受影响的行数的数组
	 */
	public static int[] batchUpdate(String sql, Object[][] params) {
		
		int[] affectedRows = new int[0];
		try {
			affectedRows = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("update.批量修改记录错误：" + sql, e);
		}
		return affectedRows;
	}

	/**
	 * 执行查询，将每行的结果保存到一个Map对象中，然后将所有Map对象保存到List中
	 * 
	 * @param sql
	 *            sql语句
	 * @return 查询结果
	 */
	public static List<Map<String, Object>> find(String sql) {
		return find(sql, null);
	}

	/**
	 * 执行查询，将每行的结果保存到一个Map对象中，然后将所有Map对象保存到List中
	 * 
	 * @param sql
	 *            sql语句
	 * @param param
	 *            参数
	 * @return 查询结果
	 */
	public static List<Map<String, Object>> find(String sql, Object param) {
		return find(sql, new Object[] { param });
	}


	/**
	 * 执行查询，将每行的结果保存到一个Map对象中，然后将所有Map对象保存到List中
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数数组
	 * @return 查询结果
	 */
	public static List<Map<String, Object>> find(String sql, Object[] params) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			if (params == null) {
				list = (List<Map<String, Object>>) queryRunner.query(sql, new MapListHandler());
			} else {
				list = (List<Map<String, Object>>) queryRunner.query(sql, new MapListHandler(), params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("map 数据查询错误", e);
		}
		return list;
	}

	/**
	 * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中
	 * 
	 * @param entityClass
	 *            类名
	 * @param sql
	 *            sql语句
	 * @return 查询结果
	 */
	public <T> List<T> find(Class<T> entityClass, String sql) {
		return find(entityClass, sql, null);
	}

	/**
	 * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中
	 * 
	 * @param entityClass
	 *            类名
	 * @param sql
	 *            sql语句
	 * @param param
	 *            参数
	 * @return 查询结果
	 */
	public <T> List<T> find(Class<T> entityClass, String sql, Object param) {
		return find(entityClass, sql, new Object[] { param });
	}

	/**
	 * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中
	 * 
	 * @param entityClass
	 *            类名
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数数组
	 * @return 查询结果
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> find(Class<T> entityClass, String sql, Object[] params) {
		
		List<T> list = new ArrayList<T>();
		try {
			if (params == null) {
				list = (List<T>) queryRunner.query(sql, new BeanListHandler(entityClass));
			} else {
				list = (List<T>) queryRunner.query(sql, new BeanListHandler(entityClass), params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("Error occured while attempting to query data", e);
		}
		return list;
	}

	/**
	 * 查询出结果集中的第一条记录，并封装成对象
	 * 
	 * @param entityClass
	 *            类名
	 * @param sql
	 *            sql语句
	 * @return 对象
	 */
	public <T> T findFirst(Class<T> entityClass, String sql) {
		return findFirst(entityClass, sql, null);
	}

	/**
	 * 查询出结果集中的第一条记录，并封装成对象
	 * 
	 * @param entityClass
	 *            类名
	 * @param sql
	 *            sql语句
	 * @param param
	 *            参数
	 * @return 对象
	 */
	public <T> T findFirst(Class<T> entityClass, String sql, Object param) {
		return findFirst(entityClass, sql, new Object[] { param });
	}

	/**
	 * 查询出结果集中的第一条记录，并封装成对象
	 * 
	 * @param entityClass
	 *            类名
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数数组
	 * @return 对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T findFirst(Class<T> entityClass, String sql, Object[] params) {
		
		Object object = null;
		try {
			if (params == null) {
				object = queryRunner.query(sql, new BeanHandler(entityClass));
			} else {
				object = queryRunner.query(sql, new BeanHandler(entityClass), params);
			}
		} catch (SQLException e) {
			LOG.error("返回一条记录错误：findFirst" + e.getMessage());
			e.printStackTrace();
		}
		return (T) object;
	}

	/**
	 * 查询出结果集中的第一条记录，并封装成Map对象
	 * 
	 * @param sql
	 *            sql语句
	 * @return 封装为Map的对象
	 */
	public static Map<String, Object> findFirst(String sql) {
		return findFirst(sql, null);
	}

	/**
	 * 查询出结果集中的第一条记录，并封装成Map对象
	 * 
	 * @param sql
	 *            sql语句
	 * @param param
	 *            参数
	 * @return 封装为Map的对象
	 */
	public static Map<String, Object> findFirst(String sql, Object param) {
		return findFirst(sql, new Object[] { param });
	}

	/**
	 * 查询出结果集中的第一条记录，并封装成Map对象
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数数组
	 * @return 封装为Map的对象
	 */
	public static Map<String, Object> findFirst(String sql, Object[] params) {
		
		Map<String, Object> map = null;
		try {
			if (params == null) {
				map = (Map<String, Object>) queryRunner.query(sql, new MapHandler());
			} else {
				map = (Map<String, Object>) queryRunner.query(sql, new MapHandler(), params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("findFirst.查询一条记录错误" + sql, e);
		}
		return map;
	}

	/**
	 * 查询某一条记录，并将指定列的数据转换为Object
	 * 
	 * @param sql
	 *            sql语句
	 * @param columnName
	 *            列名
	 * @return 结果对象
	 */
	public static Object findBy(String sql, String params) {
		return findBy(sql, params, null);
	}

	/**
	 * 查询某一条记录，并将指定列的数据转换为Object
	 * 
	 * @param sql
	 *            sql语句
	 * @param columnName
	 *            列名
	 * @param param
	 *            参数
	 * @return 结果对象
	 */
	public static Object findBy(String sql, String columnName, Object param) {
		return findBy(sql, columnName, new Object[] { param });
	}

	/**
	 * 查询某一条记录，并将指定列的数据转换为Object
	 * 
	 * @param sql
	 *            sql语句
	 * @param columnName
	 *            列名
	 * @param params
	 *            参数数组
	 * @return 结果对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object findBy(String sql, String columnName, Object[] params) {
		
		Object object = null;
		try {
			if (params == null) {
				object = queryRunner.query(sql, new ScalarHandler(columnName));
			} else {
				object = queryRunner.query(sql, new ScalarHandler(columnName), params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("findBy。错误" + sql, e);
		}
		return object;
	}

	/**
	 * 查询某一条记录，并将指定列的数据转换为Object
	 * 
	 * @param sql
	 *            sql语句
	 * @param columnIndex
	 *            列索引
	 * @return 结果对象
	 */
	public static Object findBy(String sql, int columnIndex) {
		return findBy(sql, columnIndex, null);
	}

	/**
	 * 查询某一条记录，并将指定列的数据转换为Object
	 * 
	 * @param sql
	 *            sql语句
	 * @param columnIndex
	 *            列索引
	 * @param param
	 *            参数
	 * @return 结果对象
	 */
	public static Object findBy(String sql, int columnIndex, Object param) {
		return findBy(sql, columnIndex, new Object[] { param });
	}

	/**
	 * 查询某一条记录，并将指定列的数据转换为Object
	 * 
	 * @param sql
	 *            sql语句
	 * @param columnIndex
	 *            列索引
	 * @param params
	 *            参数数组
	 * @return 结果对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object findBy(String sql, int columnIndex, Object[] params) {
		
		Object object = null;
		try {
			if (params == null) {
				object = queryRunner.query(sql, new ScalarHandler(columnIndex));
			} else {
				object = queryRunner.query(sql, new ScalarHandler(columnIndex), params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("findBy.错误" + sql, e);
		}
		return object;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> query(Class<T> beanClass, String sql, Object... params) {
		try {
			
			return (List<T>) queryRunner.query(sql, isPrimitive(beanClass) ? columnListHandler : new BeanListHandler(
					beanClass), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("serial")
	private List<Class<?>> PrimitiveClasses = new ArrayList<Class<?>>() {
		{
			add(Long.class);
			add(Integer.class);
			add(String.class);
			add(java.util.Date.class);
			add(java.sql.Date.class);
			add(java.sql.Timestamp.class);
		}
	};
	// 返回单一列时用到的handler
	@SuppressWarnings("rawtypes")
	private final static ColumnListHandler columnListHandler = new ColumnListHandler() {
		@Override
		protected Object handleRow(ResultSet rs) throws SQLException {
			Object obj = super.handleRow(rs);
			if (obj instanceof BigInteger)
				return ((BigInteger) obj).longValue();
			return obj;
		}

	};

	// 判断是否为原始类型
	private boolean isPrimitive(Class<?> cls) {
		return cls.isPrimitive() || PrimitiveClasses.contains(cls);
	}
	// map

}