package demo.base;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年01月06日 13:49:00
 */
public class ConfigSingletonClient {


    public static void main(String[] args) throws Exception {
        ConfigSingleton.setDefault_Scope_Key_Tenant("id");
        ConfigSingleton.setDefault_Scope_Key_User("user");
        ConfigSingleton instance = ConfigSingleton.getInstance();
        instance.bulidDataScope("tenant1,tenant2;{#ALL};{TENANT};{#SELF}");
        System.out.println(instance.getScopeKey());
        System.out.println(instance.getScopeType());
        String tenant = ConfigSingleton.getDefault_Scope_Key_Tenant();
        String user = ConfigSingleton.getDefault_Scope_Key_User();
        System.out.println(tenant + "," + user);
        try {
            instance.getScopeValues().forEach(System.out::println);
        }catch (Exception e){

        }
        System.out.println("==================");
        ConfigSingleton.setDefault_Scope_Key_Tenant("id_2");
        ConfigSingleton.setDefault_Scope_Key_User("user_2");
        HashMap<String, Object> map = new HashMap<>();
        map.put("scopeType","{#ASSIGN}");
        map.put("scopeKey","iiii");
        String[] strings = {"11111","2222"};
        map.put("scopeValues", Arrays.asList(strings));
        instance.bulidDataScope(map);
        System.out.println(instance.getScopeKey());
        System.out.println(instance.getScopeType());
        String tenant_1 = ConfigSingleton.getDefault_Scope_Key_Tenant();
        String user_1 = ConfigSingleton.getDefault_Scope_Key_User();
        System.out.println(tenant_1 + "," + user_1);
        try {
            instance.getScopeValues().forEach(System.out::println);
        }catch (Exception e){

        }
    }
}
