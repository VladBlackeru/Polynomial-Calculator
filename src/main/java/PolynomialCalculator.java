import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

public class PolynomialCalculator {




    public Polinom add(Polinom p1, Polinom p2) {
        Polinom result = new Polinom();
        Map<Integer, Double> map1 = p1.ret_pol();
        Map<Integer, Double> map2 = p2.ret_pol();

        for (Integer degree : map1.keySet()) {
            result.addTerm(degree, map1.get(degree));
        }
        for (Integer degree : map2.keySet()) {
            result.addTerm(degree, map2.get(degree));
        }
        return result;
    }

    public Polinom sub(Polinom p1, Polinom p2) {
        Polinom result = new Polinom();
        Map<Integer, Double> map1 = p1.ret_pol();
        Map<Integer, Double> map2 = p2.ret_pol();

        for (Integer degree : map1.keySet()) {
            result.addTerm(degree, map1.get(degree));
        }

        for (Integer degree : map2.keySet()) {
            result.addTerm(degree, -map2.get(degree));
        }

        return result;
    }

    public Polinom multiply(Polinom p1, Polinom p2) {
        Polinom result = new Polinom();
        Map<Integer, Double> map1 = p1.ret_pol();
        Map<Integer, Double> map2 = p2.ret_pol();

        for (Integer degree1 : map1.keySet()) {
            double coefficient1 = map1.get(degree1);
            for (Integer degree2 : map2.keySet()) {
                double coefficient2 = map2.get(degree2);
                result.addTerm(degree1 + degree2, coefficient1 * coefficient2);
            }
        }

        return result;
    }

    public Polinom derivative(Polinom p) {
        Polinom result = new Polinom();
        Map<Integer, Double> map = p.ret_pol();

        for (Integer degree : map.keySet()) {
            double coefficient = map.get(degree);
            if (degree > 0) {
                result.addTerm(degree - 1, coefficient * degree);
            }
        }

        return result;
    }

    public Polinom integrate(Polinom p) {
        Polinom result = new Polinom();
        Map<Integer, Double> map = p.ret_pol();

        for (Integer degree : map.keySet()) {
            double coefficient = map.get(degree);
            result.addTerm(degree + 1, coefficient / (degree + 1));
        }

        return result;
    }

    public  Polinom divide(Polinom p1,Polinom p2) {
        if (p2.ret_pol().isEmpty() ) {
            throw new IllegalArgumentException("Cannot divide by zero polynomial.");
        }

        List<Integer> degrees_1 = new ArrayList<>(p1.ret_pol().keySet());
        List<Integer> degrees_2 = new ArrayList<>(p2.ret_pol().keySet());
        degrees_1.sort(Collections.reverseOrder());
        degrees_2.sort(Collections.reverseOrder());

        Polinom resultat = new Polinom();
        Map<Integer, Double> map_p1 = p1.ret_pol();

        while (!degrees_1.isEmpty() && degrees_1.get(0) >= degrees_2.get(0)) {
            Integer biggest_degree_p1 = degrees_1.get(0);
            Integer biggest_degree_p2 = degrees_2.get(0);

            double coeficient = map_p1.get(biggest_degree_p1) / p2.ret_coef(biggest_degree_p2);
            resultat.addTerm(biggest_degree_p1 - biggest_degree_p2, coeficient);

            for (Integer degree : degrees_2) {
                double coeff = p2.ret_coef(degree) * coeficient;
                int newDegree = degree + (biggest_degree_p1 - biggest_degree_p2);

                Double existingCoeff = map_p1.get(newDegree);
                if (existingCoeff != null) {
                    // Update coef in the divident polynomial
                    double updatedCoeff = existingCoeff - coeff;
                    if (updatedCoeff != 0) {
                        map_p1.put(newDegree, updatedCoeff);
                    } else {
                        map_p1.remove(newDegree);
                    }
                } else {
                    map_p1.put(newDegree, -coeff);
                }
            }

            degrees_1 = new ArrayList<>(map_p1.keySet());
            degrees_1.sort(Collections.reverseOrder());
        }

        return resultat;
    }


}
