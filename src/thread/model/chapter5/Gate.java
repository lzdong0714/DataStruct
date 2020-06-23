package thread.model.chapter5;

public class Gate {

    private int counter = 0;
    private String name = "Nobady";
    private String address = "Nowhere";

    public void pass(String name, String address){
        counter ++;
        this.name = name;
        this.address = address;
    }

    private void verify(){
        if(this.name.charAt(0) != this.address.charAt(0)){
            System.out.println("++++++++++broken++++++++ " + toString());
        }
    }

    @Override
    public String toString(){
        return "No." + counter + ":" + name + "," + address;
    }
}
