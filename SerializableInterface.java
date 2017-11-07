package com.nf.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.nf.entity.AssetQuota;


public class SerializableInterface { 
    public SerializableInterface(){ 
 
    } 
 
    public static <T> byte[] serialize(Map<String, T> map){ 
        try { 
        ByteArrayOutputStream mem_out = new ByteArrayOutputStream(); 
            ObjectOutputStream out = new ObjectOutputStream(mem_out); 
 
            out.writeObject(map); 
 
            out.close(); 
           mem_out.close(); 
 
           byte[] bytes =  mem_out.toByteArray(); 
           return bytes; 
        } catch (IOException e) { 
            return null; 
        } 
    } 
 
     @SuppressWarnings("unchecked")
	public static <T> Map<String, T> deserialize(byte[] bytes){ 
        try { 
            ByteArrayInputStream mem_in = new ByteArrayInputStream(bytes); 
            ObjectInputStream in = new ObjectInputStream(mem_in); 
 
            HashMap<String, T> hashMap = (HashMap<String, T>)in.readObject(); 
 
             in.close(); 
             mem_in.close(); 
 
             return hashMap; 
        } catch (StreamCorruptedException e) { 
            return null; 
        } catch (ClassNotFoundException e) { 
            return null; 
        }   catch (IOException e) { 
            return null; 
        } 
     } 
     
   public static void main(String[] args) throws UnsupportedEncodingException {
	   Map<String, AssetQuota> strss = new HashMap<String, AssetQuota>();
		AssetQuota assetQuota = new AssetQuota();
		assetQuota.setN_cash_ratio(11.1);
		strss.put("sa", assetQuota);
		byte[] bytes = serialize(strss);
		String str = new String(bytes,"UTF-8");
		System.out.println(str);

		Map<String, AssetQuota> map = deserialize(bytes);
		
		System.out.println(map);
   }
} 
