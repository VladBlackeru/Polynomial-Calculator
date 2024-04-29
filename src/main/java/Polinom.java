import java.util.HashMap;
import java.util.Map;

public class Polinom {
    private final Map<Integer, Double> pol =  new HashMap<>();
    public Polinom(){}
    public void addTerm(int degree, double coefficient) {
        if(degree >= 0 ) {
            if (!pol.containsKey(degree) && coefficient != 0)
                pol.put(degree, coefficient);
            else {
                Double coef = pol.get(degree) + coefficient;
                pol.remove(degree);
                if(coef != 0)
                    pol.put(degree, coef);
            }
        }

    }

    public Map<Integer,Double> ret_pol(){
        return  pol;
    }

    public Double ret_coef(int degree){
        if(pol.containsKey(degree))
            return pol.get(degree);
        return null;
    }

}
