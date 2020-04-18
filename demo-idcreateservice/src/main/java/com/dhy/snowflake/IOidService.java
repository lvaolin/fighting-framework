package com.dhy.snowflake;

import java.util.List;

public interface IOidService {

	Long generateObjectID();
	List<Long> generateObjectID(int size);
}
