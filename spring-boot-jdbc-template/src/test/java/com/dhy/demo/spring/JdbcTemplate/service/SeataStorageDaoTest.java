package com.dhy.demo.spring.JdbcTemplate.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SeataStorageDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private SeataStorageDao seataStorageDao;

    private SeataStoragePo seataStoragePo;

    @BeforeEach
    public void setUp() {
        seataStoragePo = new SeataStoragePo();
        seataStoragePo.setId(1);
        seataStoragePo.setStock(100);
        seataStoragePo.setPrice(10.0);
    }

    @Test
    public void testProductUpdate() {
        // Arrange
        int expectedRowsAffected = 1;
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt(), anyInt())).thenReturn(expectedRowsAffected);

        // Act
        int result = seataStorageDao.productUpdate(seataStoragePo);

        // Assert
        assertEquals(expectedRowsAffected, result);
        verify(jdbcTemplate).update(anyString(), seataStoragePo.getStock(), seataStoragePo.getPrice(), seataStoragePo.getId());
    }
}
