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
        Main Calculator = new Main("\\s*\\-*\\d+(\\.\\d*)*\\s*[\\+\\-\\*x\\/\\^]\\s*\\-*\\d+(\\.\\d*)*\\s*([\\+\\-\\*x\\/\\^]\\s*\\-*\\d+(\\.\\d*)*\\s*)*");
        String userInput =Calculator.userInput();
        List<String> List = Calculator.getList(userInput);
        double finalAnswer = Calculator.calculate(List);

    }

    public Main(String regex) {
        this.regex = regex;
    }
    public String userInput() {
        System.out.println("Calculator is ready. Enter the numbers you would like to calculate:");
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        String calc = scanner.nextLine();
        Matcher matcher = pattern.matcher(calc);
        while (!matcher.matches()) {
            System.out.println("Invalid, please enter the numbers you would like to calculate in the right format");
            calc = scanner.nextLine();
            matcher = pattern.matcher(calc);
        }
        calc = calc.replaceAll(" ", "");
        System.out.println(calc);
        return calc;
    }
    public List<String> getList(String userInput){
        List<String> tokens = new ArrayList<>();
        Matcher matcher = Pattern.compile("-*\\d+(\\.\\d*)*|[+*x/^\\-]").matcher(userInput);
        while(matcher.find()){
            tokens.add(matcher.group());
        }
        return tokens;
    }

    public double calculate(List<String> tokens){
        String A="";
        for (int i = tokens.size()-1; i>=0; i--) {
            if (tokens.get(i).equals("^")) {
                A = String.valueOf(Math.pow(Double.parseDouble(tokens.get(i - 1)), Double.parseDouble(tokens.get(i + 1))));
                tokens.set(i - 1, A);
                tokens.remove(i);
                tokens.remove(i);
                i--;
            }
        }
   //     System.out.println(tokens);
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
    //    System.out.println(tokens);
        for (int i=0;i<tokens.size();i++) {
            if (tokens.get(i).equals("+")) {
                A = String.valueOf((Double.parseDouble(tokens.get(i - 1)) + Double.parseDouble(tokens.get(i + 1))));
                tokens.set(i - 1, A);
                tokens.remove(i);
                tokens.remove(i);
                i--;
            }
            else if(tokens.get(i).equals("-")){
                A = String.valueOf((Double.parseDouble(tokens.get(i - 1))-Double.parseDouble(tokens.get(i + 1))));
                tokens.set(i - 1, A);
                tokens.remove(i);
                tokens.remove(i);
                i--;
            }
        }
        double finalAnswer =Double.parseDouble(tokens.get(0));
        int wholeAnswer = (int)finalAnswer;
        int convert;
        if(finalAnswer%1==0){
            System.out.println(wholeAnswer);
            return wholeAnswer;

        }
        else {
            convert = (int)(finalAnswer*1000);
            finalAnswer = convert/1000.0;
            System.out.println(finalAnswer);
            return finalAnswer;
        }

    }

    }

