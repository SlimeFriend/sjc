package com.sjc.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sjc.app.pr.mapper.PrdtMapper;

@SpringBootTest
public class PrMApperTest {

	@Autowired PrdtMapper prdtMapper;
	
	@Test
	void pstart() {
		Map<String, Object> map = new HashMap<>();
		map.put("pdCode", "PD02");
		map.put("ipt", 10);
		map.put("ldmng", Arrays.asList(106,101,102, 103));
		prdtMapper.pstart(map);
	}
	
}
