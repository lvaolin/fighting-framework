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
import java.util.List;

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
        //1、从上下文获取  orgId  计算分表后缀 数字
        String shardingTableColumnValue = TraceUtil.getShardingTableColumnValue();

        //2、解析重写sql
        String newSQL = getNewSQL(sql);
        return super.connection_prepareStatement(chain, connection, newSQL);
    }

    @Override
    public boolean statement_execute(FilterChain chain, StatementProxy statement, String sql) throws SQLException{
        //解决statement执行sql问题
        String newSQL = getNewSQL(sql);
        return super.statement_execute(chain, statement, newSQL);
    }

    private String getNewSQL(String sql) {
        boolean rewrite = false;
        String newSQL = sql;
        List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        for (SQLStatement st : sqlStatements) {
            RewriteTableNameVisitor visitor = new RewriteTableNameVisitor();
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


    class RewriteTableNameVisitor extends MySqlASTVisitorAdapter {

        /**
         * sql是否被重写
         */
        private boolean rewrite = false;

        @Override
        public boolean visit(MySqlUpdateStatement x){
            //解析语法树，修改语法树
            SQLTableSource sqlTableSource = x.getFrom();

            //sqlTableSource.setAlias();
            return true;
        }

        @Override
        public boolean visit(MySqlDeleteStatement x){
            //解析语法树，修改语法树
            return true;
        }
        @Override
        public boolean visit(SQLUnionQuery x){

            //解析语法树，修改语法树
            List<SQLSelectQuery> children = x.getChildren();
            for (SQLSelectQuery child : children) {
                if (child instanceof MySqlSelectQueryBlock) {
                    MySqlSelectQueryBlock sqlSelectQuery = (MySqlSelectQueryBlock) child;
                    visit(sqlSelectQuery);
                }
            }
            this.rewrite = true;
            return true;
        }

        @Override
        public boolean visit(MySqlSelectQueryBlock x){

            //解析语法树，修改语法树
            SQLTableSource sqlTableSource = x.getFrom();
            updateTableSource(sqlTableSource);

            return true;
        }

        private void updateTableSource(SQLTableSource sqlTableSource) {
            if (sqlTableSource instanceof SQLExprTableSource) {
                SQLExprTableSource tableSource = (SQLExprTableSource)sqlTableSource;
                SQLExpr expr = tableSource.getExpr();
                //查询分表配置信息，确认此表是否需要分表
                if (true) {
                    tableSource.setExpr(expr.toString()+"_1");
                    this.rewrite = true;
                }

                return;
            }
            if (sqlTableSource instanceof SQLJoinTableSource) {
                SQLJoinTableSource tableSource = (SQLJoinTableSource) sqlTableSource;
                updateTableSource(tableSource.getLeft());
                updateTableSource(tableSource.getRight());
                return;
            }

            if (sqlTableSource instanceof SQLSubqueryTableSource) {
                SQLSubqueryTableSource tableSource = (SQLSubqueryTableSource)sqlTableSource;
                SQLSelect select = tableSource.getSelect();
                SQLSelectQueryBlock queryBlock = select.getQueryBlock();
                SQLTableSource from = queryBlock.getFrom();
                updateTableSource(from);
                return;
            }
            if (sqlTableSource instanceof SQLWithSubqueryClause.Entry) {
                SQLWithSubqueryClause.Entry tableSource = (SQLWithSubqueryClause.Entry)sqlTableSource;
                SQLSelect select = tableSource.getSubQuery();
                SQLSelectQueryBlock queryBlock = select.getQueryBlock();
                SQLTableSource from = queryBlock.getFrom();
                updateTableSource(from);
                return;
            }


        }


        @Override
        public boolean visit(MySqlSelectIntoStatement x){
            //解析语法树，修改语法树
            return true;
        }

        @Override
        public boolean visit(MySqlInsertStatement x) {
            boolean needRenameTable = false;

            List<SQLExpr> duplicateKeyUpdate = x.getDuplicateKeyUpdate();
            if (!CollectionUtils.isEmpty(duplicateKeyUpdate)) {
                for (SQLExpr sqlExpr : duplicateKeyUpdate) {
                    if (sqlExpr instanceof SQLBinaryOpExpr
                            && ((SQLBinaryOpExpr) sqlExpr).conditionContainsColumn("create_time")) {
                        needRenameTable = true;
                        break;
                    }
                }
                if (!needRenameTable) {
                    String tableAlias = x.getTableSource().getAlias();
                    StringBuilder setUpdateTimeBuilder = new StringBuilder();
                    if (!StringUtils.isEmpty(tableAlias)) {
                        setUpdateTimeBuilder.append(tableAlias).append('.');
                    }
                    setUpdateTimeBuilder.append("create_time").append(" = now()");
                    SQLExpr sqlExpr = SQLUtils.toMySqlExpr(setUpdateTimeBuilder.toString());
                    duplicateKeyUpdate.add(sqlExpr);
                    // 重写状态记录
                    rewrite = (Boolean.TRUE);
                }
            }
            return super.visit(x);
        }

         public boolean isRewrite() {
             return rewrite;
         }
     }


}
