package com.dhy.mlife.common.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PageObject extends BaseDto {

    private static final long serialVersionUID = 855778573334816193L;

    /**
     * 分页大小
     */
    private int pageSize = 10;

    /**
     * 当前页
     */
    private int currentPage = 1;

    /**
     * 总页数
     */
    private int totalPage = 1;

    /**
     * 数据总数
     */
    private int totalCount = 0;

    /**
     * 分页时需要偏移的数据总量(MySQL、PgSQL 等特殊数据库)
     */
    @JsonIgnore
    private int offset = 0;

    /**
     * 分页合理化参数，默认 {@code true}
     * <p>当该参数设置为 {@code true} 时，方法 {@link #getCurrentPage()} 在 {@link #currentPage} > {@link #totalPage} 返回 {@link #totalPage}
     */
    @JsonIgnore
    private Boolean reasonable = true;

    /**
     * 获取分页大小
     *
     * @return 分页大小
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置分页大小，当 {@code pageSize} 小于 1 时，设置为 1
     *
     * @param pageSize 分页大小
     */
    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
    }

    /**
     * 获取当前页
     * <p>当参数 {@link #reasonable} 设置为 {@code true} 时， 如果 {@link #currentPage} > {@link #totalPage} 返回 {@link #totalPage}
     *
     * @return 当前页
     */
    public int getCurrentPage() {
        if (reasonable) {
            if (currentPage < 1) {
                return 1;
            } else if (currentPage > totalPage) {
                return totalPage;
            }
        }
        return currentPage;
    }

    /**
     * 设置当前页，当 {@code currentPage} 小于 1 时，设置为 1
     *
     * @param currentPage 当前页
     */
    public void setCurrentPage(int currentPage) {
        if (currentPage < 1) {
            this.currentPage = 1;
        } else {
            this.currentPage = currentPage;
        }
    }

    /**
     * 获取总页数
     *
     * @return 总页数
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 设置总页数
     * <p>总页数在设置 {@link #totalCount} 之后根据 {@link #pageSize} 自动计算
     *
     * @param totalPage 总页数
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 获取数据总数
     *
     * @return 数据总数
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置数据总数，当 {@code totalCount} 小于 0 时，设置为 0
     * <p>设置数据总数之后会根据 {@link #pageSize} 自动计算 {@link #totalPage}
     *
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        if (totalCount < 0) {
            totalCount = 0;
        }
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            totalPage++;
        }
        if (totalPage == 0) {
            totalPage = 1;
        }
        setTotalPage(totalPage);
    }

    /**
     * 获取分页时需要偏移的数据总量(MySQL、PgSQL 等特殊数据库)
     *
     * @return 分页时需要偏移的数据总量(MySQL 、 PgSQL 等特殊数据库)
     */
    public int getOffset() {
        setOffset((getCurrentPage() - 1) * getPageSize());
        return offset;
    }

    /**
     * 设置分页时需要偏移的数据总量(MySQL、PgSQL 等特殊数据库)
     *
     * @param offset 分页时需要偏移的数据总量(MySQL、PgSQL 等特殊数据库)
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * 获取分页合理化参数，默认true
     * <p>当该参数设置为 {@code true} 时，方法 {@link #getCurrentPage()} 在 {@link #currentPage} > {@link #totalPage} 返回 {@link #totalPage}
     *
     * @return reasonable 分页合理化参数
     */
    public Boolean getReasonable() {
        return reasonable;
    }

    /**
     * 设置分页合理化参数
     * <p>当该参数设置为 {@code true} 时，方法 {@link #getCurrentPage()} 在 {@link #currentPage} > {@link #totalPage} 返回 {@link #totalPage}
     *
     * @param reasonable 分页合理化参数
     */
    public void setReasonable(Boolean reasonable) {
        this.reasonable = reasonable;
    }

}
