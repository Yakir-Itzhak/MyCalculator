import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    String regex;

    public static void main(String[] args) {
        Main Calculator = new Main("(\\s*\\-*\\d+(\\.\\d*)*\\s*[\\+\\-\\*x\\/\\^]\\s*\\-*\\d+(\\.\\d*)*\\s*([\\+\\-\\*x\\/\\^]\\s*\\-*\\d+(\\.\\d*)*\\s*)*)*");
        String userInput =Calculator.userInput();
        List<String> List = Calculator.getList(userInput);
      //  double finalAnswer = Calculator.calculate(List);

    }

    public Main(String regex) {
        this.regex = regex;
    }
    public String userInput() {
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        String calc = scanner.nextLine();
        Matcher matcher = pattern.matcher(calc);
       /* while (!matcher.matches()) {
            System.out.println("Invalid, please enter the numbers you would like to calculate in the right format");
            calc = scanner.nextLine();
            matcher = pattern.matcher(calc);
        }*/
        calc = calc.replaceAll(" ", "");
        return calc;
    }


    public List<String> getList(String userInput){
        List<String> tokens = new ArrayList<>();
        if(userInput.contains("(")) {
            Matcher matcher = Pattern.compile("\\(|\\)|[+*x/^\\-]|-*\\d+(\\.\\d*)*").matcher(userInput);
            while (matcher.find()) {
                if (matcher.group().matches("-")) {
                    tokens.add("-1");
                    tokens.add("*");
                } else tokens.add(matcher.group());

            }

            while (tokens.contains("(")) {
                tokens = parenthesis(tokens);
            }
        }
        else {
            Matcher matcher = Pattern.compile("\\(|\\)|-*\\d+(\\.\\d*)*|[+*x/^\\-]").matcher(userInput);
            while (matcher.find()) {
                tokens.add(matcher.group());
            }
        }

        System.out.println(calculate(tokens));
        return tokens;
    }
    public List<String> parenthesis(List<String> tokens){
        List<String> Z = new ArrayList<>();
        for(int a=0; a<tokens.size();a++){
            if(tokens.get(a).equals("(")){
                for(int b=a+1;b<tokens.size();b++){
                    if(tokens.get(b).equals(")")){
                        while(!tokens.get(a).equals(")")){
                            tokens.remove(a);
                            if(!tokens.get(a).equals(")")) {
                                Z.add(tokens.get(a));
                            }
                        }

                        for(int i=0;i<Z.size()-1;i++){
                            if(Z.get(i).matches("-*\\d") && Z.get(i+1).matches("-*\\d")){
                                Z.add(i+1,"+");
                            }
                            if(Z.get(i).matches("-*\\d+(\\.\\d*)*") && Z.get(i+1).matches("-1")){
                                Z.add(i+1,"+");
                            }
                        }
                      //  System.out.println(Z);

                        String newValue= String.valueOf(calculate(Z));
                        //System.out.println("Inside the parenthesis "+newValue);
                        tokens.set(a,newValue);
                        a=0;
                        Z.clear();
                        break;
                    }
                    else if(tokens.get(b).equals("(")){
                        break;
                    }
                }
            }
        }
        return tokens;
    }

    public double calculate(List<String> tokens){
        String A="";
        for(int i=0;i<tokens.size()-1;i++){
            if(tokens.get(i).matches("-*\\d") && tokens.get(i+1).matches("-*\\d")){
                tokens.add(i+1,"+");
            }
            if(tokens.get(i).matches("-*\\d+(\\.\\d*)*") && tokens.get(i+1).matches("-1")){
                tokens.add(i+1,"+");
            }
        }

        for (int i = tokens.size()-1; i>=0; i--) {
            if (tokens.get(i).equals("^")) {
                A = String.valueOf(Math.pow(Double.parseDouble(tokens.get(i - 1)), Double.parseDouble(tokens.get(i + 1))));
                tokens.set(i - 1, A);
                tokens.remove(i);
                tokens.remove(i);
                i--;
            }
        }
        if(tokens.get(0).equals("+")){
            tokens.remove(0);
        }
       System.out.println(tokens);

        for (int i =0; i<tokens.size(); i++) {
            if (tokens.get(i).equals("x") || tokens.get(i).equals("*")) {
                A = String.valueOf((Double.parseDouble(tokens.get(i - 1)) * Double.parseDouble(tokens.get(i + 1))));
                tokens.set(i - 1, A);
                tokens.remove(i);
                tokens.remove(i);
                i--;
            }
            else if(tokens.get(i).equals("/")){
                A = String.valueOf((Double.parseDouble(tokens.get(i - 1))/Double.parseDouble(tokens.get(i + 1))));
                tokens.set(i - 1, A);
                tokens.remove(i);
                tokens.remove(i);
                i--;
            }
        }

        for (int i=0;i<tokens.size();i++) {
            if (tokens.get(i).equals("+")) {
                A = String.valueOf((Double.parseDouble(tokens.get(i - 1)) + Double.parseDouble(tokens.get(i + 1))));
                tokens.set(i - 1, A);
                tokens.remove(i);
                tokens.remove(i);
                i--;
            }
            if (tokens.get(i).equals("-")) {
                A = String.valueOf((Double.parseDouble(tokens.get(i - 1)) - Double.parseDouble(tokens.get(i + 1))));
                tokens.set(i - 1, A);
                tokens.remove(i);
                tokens.remove(i);
                i--;
            }
        }

        double finalAnswer =Double.parseDouble(tokens.get(0));
        int wholeAnswer = (int)finalAnswer;
        if(finalAnswer%1==0){
            return wholeAnswer;

        }
        else {
            return finalAnswer;
        }

    }
    int releaseAnswer(int A){
        System.out.println(A);
        return A;
    }
    double releaseAnswer(double A){
        System.out.println(A);
        return A;
    }

    }

