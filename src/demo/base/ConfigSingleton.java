package demo.base;

import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年01月06日 13:39:00
 */
@Data
public class ConfigSingleton {
    public static final String Scope_Type_All = "{#ALL}";
    public static final String Scope_Type_Tenant = "{#TENANT}";
    public static final String Scope_Type_Self = "{#SELF}";
    public static final String Scope_Type_Assign = "{#ASSIGN}";

    public static final String MULTI_SCOPE_SEPERATER = ";";
    public static final String TENANT_VALUE_SEPERATER = ",";

    //默认Key值可由配置文件获取
    private static String Default_Scope_Key_Tenant ;
    private static String Default_Scope_Key_User ;

    public static void setDefault_Scope_Key_Tenant(String default_Scope_Key_Tenant){

            Default_Scope_Key_Tenant = default_Scope_Key_Tenant;
    }

    public static String getDefault_Scope_Key_Tenant(){
        return Default_Scope_Key_User;
    }

    public static void setDefault_Scope_Key_User(String default_Scope_Key_User){

            Default_Scope_Key_User = default_Scope_Key_User;

    }

    public static String getDefault_Scope_Key_User(){
        return Default_Scope_Key_Tenant;
    }

    /**
     * 数据范围的类型
     * {#ALL}：所有数据范围
     * {#ASSIGN}：指定租户范围
     * {#TENANT}：本租户数据范围类型
     * {#SELF}：访问者数据范围类型
     *
     */
    String scopeType = Scope_Type_Tenant;

    /**
     * 数据范围过滤的数据库字段名
     */
    String scopeKey = Default_Scope_Key_Tenant;

    /**
     * 数据范围过滤的字段值
     */
    Collection<String> scopeValues;

    private ConfigSingleton(){}

    public void bulidDataScope(String scopeValuesStr){
        handleScopeValues(scopeValuesStr);
    }

    @SuppressWarnings("unchecked")
    public void bulidDataScope(Map<String, Object> map){
        if(map != null){
            if(map.containsKey("scopeType")){
                this.scopeType = (String)map.get("scopeType");
            }
            if(map.containsKey("scopeKey")){
                this.scopeKey = (String)map.get("scopeKey");
            }
            if(map.containsKey("scopeValues")){
                this.scopeValues = (Collection<String>)map.get("scopeValues");
            }
        }
    }

    private static class Holder{
        private static ConfigSingleton instance= new ConfigSingleton();

    }
    public static ConfigSingleton getInstance(){
        return Holder.instance;
    }


    /**
     * 用于授权服务器转换处理DataScope的范围值scopeValues。
     *
     * 对scopeValues中包含的多个范围值进行处理。
     * 此时scopeValues可能形如：tenant1,tenant2;{#ALL};{TENANT};{#SELF}。
     * 从中找出";"分隔的各个范围的合集。
     *
     * @param scopeValues
     */
    private void handleScopeValues(String scopeValues){
        if(scopeValues != null){
            String[] scopeStrs = scopeValues.split(MULTI_SCOPE_SEPERATER);
            boolean scopeTypeIsSelf = true;
            for(String scopeStr : scopeStrs){
                if(!getScopeType().equals(Scope_Type_All)){
                    if(scopeStr == null || scopeStr.equals(Scope_Type_All)){
                        //只要有一个角色的范围是全部，则用户范围就是全部
                        setScopeType(Scope_Type_All);
                        setScopeValues(null);
                        scopeTypeIsSelf = false;
                        return;
                    } else if(!scopeStr.equals(Scope_Type_Self)){
                        //scopeStr为{#TENANT}或teanant_id值,非{#SELF}
                        setScopeKey(Default_Scope_Key_Tenant);
                        scopeTypeIsSelf = false;
                        if(!scopeStr.equals(Scope_Type_Tenant)){
                            setScopeType(Scope_Type_Assign);
                        }
                        addScopeValues(scopeStr);
                    }
                } else {
                    scopeTypeIsSelf = false;
                }
            }
            if(scopeTypeIsSelf){
                setScopeType(Scope_Type_Self);
                setScopeKey(Default_Scope_Key_User);
                setScopeValues(null);
            }
        } else {
            setScopeType(Scope_Type_All);
            setScopeValues(null);
        }
    }

    public void addScopeValues(String scopeValueStr){
        if(scopeValueStr == null || scopeValueStr.length() == 0) return;
        if(this.scopeValues == null){
            this.scopeValues = new ArrayList<String>();
        }
        String[] scopeArray = scopeValueStr.split(TENANT_VALUE_SEPERATER);
        for(String scope : scopeArray){
            if(!scopeValues.contains(scope)){
                scopeValues.add(scope);
            }
        }
    }

    public void addScopeValues(Collection<String> scopeValues){
        if(scopeValues == null)	return;
        if(this.scopeValues == null){
            this.scopeValues = new ArrayList<String>();
        }
        for(String scope : scopeValues){
            if(!scopeValues.contains(scope)){
                scopeValues.add(scope);
            }
        }
    }

    /**
     * 取现有scope与目标scope的合集
     * @param targetScope
     */
    public void unionScope(String targetScopeValue){

    }

    /**
     * 取现有scope与目标scope的交集
     * @param targetScope
     */
    public void intersectionScope(String targetScopeValue){

    }
}
