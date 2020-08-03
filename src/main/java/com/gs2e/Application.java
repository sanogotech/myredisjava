package com.gs2e;

import org.redisson.config.Config;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.api.RMap;

/**
 * Hello world!
 *
 */
public class Application 
{
  
	
	 public static void main( String[] args )
    {
	System.out.println( "Hello World!" );
        Config config = new Config();
        // use single Redis server
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
        // perform operations
        RBucket<String> bucket = redisson.getBucket("simpleObject");
        bucket.set("This is object value");
        RMap<String, String> map = redisson.getMap("simpleMap");
        map.put("mapKey", "This is map value");
        String objectValue = bucket.get();
        System.out.println("stored object value: " + objectValue);
	long startime = System.currentTimeMillis();
        String mapValue = map.get("mapKey");
	System.out.println("Total Time ms -- to map.get :"  + (System.currentTimeMillis() - startime));
        System.out.println("stored map value: " + mapValue);
        redisson.shutdown();
    }
}
