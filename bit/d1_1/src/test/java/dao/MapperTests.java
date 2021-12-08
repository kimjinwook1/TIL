package dao;

import com.example.d1_1.domain.StoreVO;
import com.example.d1_1.dto.StoreDTO;
import com.example.d1_1.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
public class MapperTests {

    @Test
    void testMap() {
        StoreDTO storeDTO = StoreDTO.builder()
                .name("홍콩반점")
                .lat(37.1111)
                .lng(127.33333)
                .build();

        log.info("-------------------");
        log.info("storeDTO = " + storeDTO);

        StoreVO storeVO = MapperUtil.INSTANCE.get().map(storeDTO, StoreVO.class);
        log.info("====================");
        log.info("storeVO = " + storeVO);
    }
}