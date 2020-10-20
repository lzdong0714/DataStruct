package think.in.java.a.onjava8.my;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年10月20日 13:45:00
 */
public class ClientModelMain {

    public static void main(String[] args) {
        BaseDataModel dataModel = new BaseDataModel();
        dataModel.setX(19);
        dataModel.setY(20);
        ProcessModel process = new ProcessModel();
        process.calculate(dataModel);
    }
}
