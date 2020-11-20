package think.in.java.a.onjava8.my;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年10月20日 13:36:00
 */
public class ProcessModel {



    public void calculate (BaseDataModel dataModel ){


        Integer result_add =null;
        dataModel.setCal(this::calOverLoadAdd);
        result_add = dataModel.getResult();
        System.out.println("result_add"+ result_add);


        Integer result_mulit =null;
        dataModel.setCal(this::calOverLaodMutil);
        result_mulit = dataModel.getResult();
        System.out.println("result_add"+ result_mulit);

    }


    private Integer calOverLoadAdd(BaseDataModel data){
       return data.getX() + data.getY();
    }

    private Integer calOverLaodMutil(BaseDataModel data){
        return data.getX() * data.getX() + data.getY() * data.getY();

    }
}
