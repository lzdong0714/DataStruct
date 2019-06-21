package thread.base.chapter2;

public class TaxCalculation {

     private final double salary;

     private final double bonus;

     private  CalculateStrategy calculateStrategy;

     public TaxCalculation(double salary,double bonus){
         this.bonus = bonus;
         this.salary = salary;
     }

     protected double calTax(){
         return 0.0d;
     }


     protected double calTaxWithStrategy(){
         double tax=calculateStrategy.calculateTax(salary,bonus);
         return tax;
     }

     public double calculate(){
         return this.calTax();
     }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setCalculateStrategy(CalculateStrategy calculateStrategy) {
        this.calculateStrategy = calculateStrategy;
    }
}
