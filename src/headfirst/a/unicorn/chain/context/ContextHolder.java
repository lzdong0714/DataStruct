package headfirst.a.unicorn.chain.context;

import java.util.Optional;

/**
 * @Author: Liu Zhendong
 * @Description  上下文持有器，静态类
 * @createTime 2020年01月09日 14:03:00
 */
public class ContextHolder {

    private static final ThreadLocal<Context> contextHolder = new ThreadLocal<>();

    public void clearContext(){ contextHolder.remove();}

    public Context getContext(){
        Context context = contextHolder.get();
        boolean isExist = Optional.ofNullable(context).isPresent();
        if( ! isExist ){
            context = createEmptyContext();
            contextHolder.set(context);
        }

        return context;
    }

    public void setContex(Context context){
        boolean isExist = Optional.ofNullable(context).isPresent();
        if(isExist){
            contextHolder.set(context);
        }
    }

    public Context createEmptyContext(){ return new Context() {
        // 这里实际调用Context接口的实现类
            DataScope scope;
            @Override
            public DataScope getDataScope() {
                return scope;
            }

            @Override
            public void setDataScope(DataScope scope) {
                this.scope = scope;
            }
        };
    }


}
