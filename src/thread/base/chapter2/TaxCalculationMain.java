package thread.base.chapter2;

public class TaxCalculationMain {
        //策略模式的类似runable的下的应用
    public static void main(String[] args){
        TaxCalculation taxCalculation = new TaxCalculation(10000d,2000d){
            @Override
            public double calTax() {
                return getSalary()*0.1 + getBonus()*0.15;
            }
        };

        double tax = taxCalculation.calculate();
        System.out.println("tax is : "+ tax);

        SampleCalculatorStrategy calculatorStrategy =
                new SampleCalculatorStrategy();
        taxCalculation.setCalculateStrategy(calculatorStrategy);
        double tax_1 = taxCalculation.calTaxWithStrategy();
        System.out.println("tax_1 is : "+ tax_1);

        taxCalculation.setCalculateStrategy((s,b)-> s*0.1+b*0.15);
        double tax_2 = taxCalculation.calTaxWithStrategy();
        System.out.println("tax_1 is : "+ tax_2);

    }
}
