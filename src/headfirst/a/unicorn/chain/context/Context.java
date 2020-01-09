package headfirst.a.unicorn.chain.context;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年01月09日 14:01:00
 */
public interface Context {

    DataScope getDataScope();

    void setDataScope(DataScope scope);
}
