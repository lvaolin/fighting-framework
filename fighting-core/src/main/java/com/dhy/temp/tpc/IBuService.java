package com.dhy.temp.tpc;

public interface IBuService {
    boolean updateListPage(
            Long orgId,
            String inventoryAccountPageSize,
            String provisionalccountPageSize,
            String inventoryDetailPageSize,
            Token token
    );
}
