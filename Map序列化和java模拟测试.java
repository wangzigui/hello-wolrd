package com.nf.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nf.dao.TestDao;
import com.nf.entity.AssetQuota;
import com.nf.util.SerializableInterface;


//@RunWith(MockitoJUnitRunner.class)  
@RunWith(PowerMockRunner.class)
@PrepareForTest({ SerializableInterface.class }) // 2 
public class sdfsd {

    
    @Mock
    private TestDao testDao;
    
    @InjectMocks 
    private TestController personService;



    @Before
    public void setupMockMvc() {
    	PowerMockito.mockStatic(SerializableInterface.class);  
    }

   @Test
   public void contextLoads() {

	   AssetQuota actul =  new AssetQuota();
	   actul.setN_cash_ratio(1.0);
	   AssetQuota expect =  new AssetQuota();
	   expect.setN_cash_ratio(1.0);
	   when(testDao.getMsgById(Mockito.anyString())).thenReturn(actul);
//	   testDao.createMsg(Mockito.any()).doNothing();
	   doNothing().when(testDao).createMsg(Mockito.any());
	   AssetQuota  name =personService.createMsg("{\"id\":\"1\"}");
	   
	   System.out.println(name);
	   byte[] bytes = new byte[4];
	   Map<String, Object> map = new HashMap<>();
	   map.put("aa", "aa");
	   PowerMockito.when(SerializableInterface.deserialize(Mockito.any())).thenReturn(map);  
       Map result = SerializableInterface.deserialize(Mockito.any()); 
       System.out.println(result);
//       assertEquals(result, new HashMap<>());  
	   
	   assertEquals(expect.getN_cash_ratio(), testDao.getMsgById("aaa").getN_cash_ratio());
   }
}
