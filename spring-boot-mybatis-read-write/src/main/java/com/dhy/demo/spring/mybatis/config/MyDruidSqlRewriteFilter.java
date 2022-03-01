package com.dhy.demo.spring.mybatis.config;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.clause.MySqlSelectIntoStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlSelectIntoParser;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlSelectParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitorAdapter;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project
 * @Description 重写sql实现分表
 * @Author lvaolin
 * @Date 2022/2/22
 */
public class MyDruidSqlRewriteFilter extends FilterEventAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection, String sql) throws SQLException {
        //解决PrepareStatement执行sql问题
        String shardingTableColumnValue = TraceUtil.getShardingTableColumnValue();
        String newSQL = getNewSQL(sql,shardingTableColumnValue);
        return super.connection_prepareStatement(chain, connection, newSQL);
    }

    @Override
    public boolean statement_execute(FilterChain chain, StatementProxy statement, String sql) throws SQLException{
        //解决statement执行sql问题
        String shardingTableColumnValue = TraceUtil.getShardingTableColumnValue();
        String newSQL = getNewSQL(sql,shardingTableColumnValue);
        return super.statement_execute(chain, statement, newSQL);
    }

    private String getNewSQL(String sql,String suffix) {
        boolean rewrite = false;
        String newSQL = sql;
        List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        for (SQLStatement st : sqlStatements) {
            RewriteTableNameVisitor visitor = new RewriteTableNameVisitor(suffix);
            st.accept(visitor);
            if (visitor.isRewrite()) {
                rewrite = true;
            }
        }
        if (rewrite) {
            newSQL = SQLUtils.toSQLString(sqlStatements, JdbcConstants.MYSQL);
        }
        System.out.println(newSQL);
        return newSQL;
    }


    static class RewriteTableNameVisitor extends MySqlASTVisitorAdapter {

        //分表后缀
        private String suffix;

        //模拟下需要进行分表操作的表名单
        private static Map<String,Boolean> sharding = new HashMap<>();
        static {
            sharding.put("product",true);
        }

        /**
         * sql是否被重写
         */
        private boolean rewrite = false;
        /**
         * 是否insert语句？
         */
        private boolean insert = false;
        /**
         * 是否delete语句？
         */
        private boolean delete = false;
        /**
         * 是否update语句？
         */
        private boolean update = false;

        public RewriteTableNameVisitor(String suffix){
            this.suffix = suffix;
        }
        @Override
        public void endVisit(MySqlInsertStatement x) {
            this.insert = true;
        }
        @Override
        public void endVisit(MySqlDeleteStatement x) {
            this.delete = true;
        }
        @Override
        public void endVisit(MySqlUpdateStatement x) {
            this.update = true;
        }
        @Override
        public boolean visit(SQLExprTableSource tableSource){
            String tableName = tableSource.getTableName();
            //确认否需要分表
            if (sharding.containsKey(tableName.toLowerCase())) {
                //追加别名
                if (tableSource.getAlias()==null&&insert) {
                    tableSource.setAlias(tableName);
                }
                //追加分表后缀
                tableSource.setSimpleName(tableName+this.suffix);
                //已经被修改，需要替换SQL
                this.rewrite = true;
            }
            return true;
        }

        public boolean isRewrite() {
            return rewrite;
        }
    }


}
