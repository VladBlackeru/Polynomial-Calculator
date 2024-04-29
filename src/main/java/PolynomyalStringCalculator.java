import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;
public class PolynomyalStringCalculator {
    private final PolynomialCalculator calc =  new PolynomialCalculator();

    public String  addPolynomials(String polynomial1, String polynomial2) {
        Polinom p1 = new Polinom();
        Polinom p2 = new Polinom();
        processString(polynomial1, p1);
        processString(polynomial2, p2);
        Polinom result = calc.add(p1, p2);
        return polynomialToString(result);
    }
    private void processString(String s, Polinom p) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len ;i++){
            sb.append(s.charAt(i));
            if( (s.charAt(i) == '-') || (s.charAt(i) == '+')){
                while(s.charAt(i+1) ==' ')
                    i++;
                if(s.charAt(i+1) =='x')
                    sb.append("1");
            }

            if(i+1 < len && s.charAt(i+1) == ' ')
                sb.append(' ');
        }
        s = sb.toString();

        String[] parts = s.split("\\s+");
        for (String term : parts) {
            int degree = 1;
            double coefficient = 1.0;

            if (isNumeric(term)) {
                degree = 0;
                coefficient = Double.parseDouble(term);
            } else {
                String[] spl = term.split("x(\\^)?");
                if (spl.length == 1) {
                    coefficient = Double.parseDouble(spl[0]);
                } else if (spl.length == 2) {
                    degree = Integer.parseInt(spl[1]);
                    if (!spl[0].isEmpty()) {
                        coefficient = Double.parseDouble(spl[0]);
                    }
                }
            }
            p.addTerm(degree, coefficient);
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public String subtractPolynomials(String polynomial1, String polynomial2) {
        Polinom p1 = new Polinom();
        Polinom p2 = new Polinom();
        processString(polynomial1, p1);
        processString(polynomial2, p2);
        Polinom result = calc.sub(p1, p2);
        return polynomialToString(result);
    }

    public String multiplyPolynomials(String polynomial1, String polynomial2) {
        Polinom p1 = new Polinom();
        Polinom p2 = new Polinom();
        processString(polynomial1, p1);
        processString(polynomial2, p2);
        Polinom result = calc.multiply(p1, p2);
        return polynomialToString(result);
    }

    public String computeDerivative(String polynomial) {
        Polinom p = new Polinom();
        processString(polynomial, p);
        Polinom result = calc.derivative(p);
        return polynomialToString(result);
    }

    public String computeIntegral(String polynomial) {
        Polinom p = new Polinom();
        processString(polynomial, p);
        Polinom result = calc.integrate(p);
        return polynomialToString(result);
    }


    public String computeDivision(String polynomial1, String polynomial2){
        Polinom p1 = new Polinom();
        Polinom p2 = new Polinom();
        processString(polynomial1, p1);
        processString(polynomial2, p2);
        Polinom result = calc.divide(p1, p2);
        return "quotient :" + polynomialToString(result) + "  remainder :" + polynomialToString(p1);
    }
    private String polynomialToString(Polinom p) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, Double> map = p.ret_pol();

// Sort the degrees in descending order
        List<Integer> degrees = new ArrayList<>(map.keySet());
        degrees.sort(Collections.reverseOrder());
        if(degrees.isEmpty())
            sb.append(" 0 ");
        for (Integer degree : degrees) {
            double coefficient = map.get(degree);
            if (coefficient != 0) {
                if(coefficient >= 0 )
                    sb.append(" + ");
                else
                    sb.append(" - ");
                if( (coefficient != 1.0 && coefficient != -1.0) || degree == 0) {
                    sb.append(abs(coefficient));
                }
                if (degree != 0) {
                    sb.append("x");
                    if (degree != 1) {
                        sb.append("^").append(degree);
                    }
                }
            }
        }
        return sb.toString();

    }



}
