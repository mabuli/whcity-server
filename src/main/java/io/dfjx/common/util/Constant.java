/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjx.common.util;

/**
 * 常量
 *
 * @author Mark mazong@gmail.com
 */
public class Constant {
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;
    /** 数据权限过滤 */
	public static final String SQL_FILTER = "sql_filter";
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";

    public static final long LIMIT_MAX = 100;
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     *  升序
     */
    public static final String ASC = "asc";

    /**
     * 指标类型
     */
    public static final String SCHEMA = "schema";
    public static final String TABLE = "table";
    public static final String VIEW = "view";
    public static final String COLUMN = "column";
    public static final String SRC = "src";

    /**
     * sql函数类型
     */
    public static final String GROUP = "group";
    public static final String COUNT = "count";
    public static final String DISTINCT = "distinct";
    public static final String SUM = "sum";
    public static final String MAX = "max";
    public static final String MIN = "min";



    /**
	 * 菜单类型
	 *
	 * @author mazong
	 * @email mazong@gmail.com
	 * @date 2019年11月15日 下午1:24:29
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     *
     * @author mazong
     * @email mazong@gmail.com
     * @date 2019年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
