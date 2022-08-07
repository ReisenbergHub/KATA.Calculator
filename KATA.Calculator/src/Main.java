import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите Ваше выражение, используя пробелы:");
        String input = in.nextLine();          //получено выражение для дальнейшей обработки
        System.out.println("Начинаем обработку выражения " + input);
        System.out.println("Результат Вашего выражения: " + calc (input));
    }

    public static String calc(String input) throws Exception {
//здесь разбиваем выражение на массив строк. первая проверка на корректность ввода
        String[] inputButched = input.split(" ");
        System.out.println("Делим выражение на массив строк:");
        for (String d : inputButched){
            System.out.println(d);
        }
            if (inputButched.length !=3) {throw new Exception("Ошибка ввода. Требуется одна операция и два операнда");}

//массивы. собственный словарь
        String[] operations = new String[] {"+","-","*","/"};
        String[] arabianNumbers = new String[] {"1","2","3","4","5","6","7","8","9","10"};
        String[] romanNumbers = new String[] {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
//переменные для будущей конвертации
        int indexFirstRoman = 444;
        int indexSecondRoman = 444;
        int convertedFirst;
        int convertedSecond;

//проверка первого числа (арабское и от 1 до 10)
        boolean arabianTrue1 = false;
        for(int i=0; i<arabianNumbers.length; i++) {
            if (inputButched[0].equals(arabianNumbers[i]) == true) {
                arabianTrue1 = true;
                i = arabianNumbers.length;
                System.out.println("Первое число арабское и в пределах от 1 до 10");
            }
        }
//проверка второго числа (арабское и от 1 до 10)
        boolean arabianTrue2 = false;
        for(int i=0; i<arabianNumbers.length; i++) {
            if (inputButched[2].equals(arabianNumbers[i]) == true) {
                arabianTrue2 = true;
                i = arabianNumbers.length;
                System.out.println("Второе число арабское и в пределах от 1 до 10");
            }
        }
//проверка первого числа (римское и от I до X)
        boolean romanTrue1 = false;
        for(int i=0; i<romanNumbers.length; i++) {
            if (inputButched[0].equals(romanNumbers[i]) == true) {
                romanTrue1 = true;
                indexFirstRoman = i;    //запоминаем индекс для конвертации
                i = romanNumbers.length;
                System.out.println("Первое число римское и в пределах от I до X");
            }
        }
//проверка второго числа (римское и от I до X)
        boolean romanTrue2 = false;
        for(int i=0; i<romanNumbers.length; i++) {
            if (inputButched[2].equals(romanNumbers[i]) == true) {
                romanTrue2 = true;
                indexSecondRoman = i;    //запоминаем индекс для конвертации
                i = romanNumbers.length;
                System.out.println("Второе число римское и в пределах от I до X");
            }
        }
//итоговая проверка чисел. оба либо арабские либо римские. в пределах от 1 до 10
        if (((arabianTrue1 && arabianTrue2) || (romanTrue1 && romanTrue2)) == false){
            throw new Exception("Ошибка ввода. Неверный ввод числа или используются разные системы счисления");
        }
//проверка на корректность ввода арифметической операции
        boolean operationsTrue = false;
        for(int i=0; i<operations.length; i++){
            if (inputButched[1].equals(operations[i]) == true){
                operationsTrue = true;
                i = operations.length;
                System.out.println("Арифметическая операция введена верно");
            }
        }
        if (operationsTrue == false){throw new Exception("Ошибка ввода. Неверный ввод арифметической операции");}
//конвертация чисел для выполнения вычислений
        if ((arabianTrue1 && arabianTrue2) == true){    //если наши числа арабские
            convertedFirst = Integer.parseInt(inputButched[0]);
            convertedSecond = Integer.parseInt(inputButched[2]);
        } else {                                        //если наши числа римские
            convertedFirst = Integer.parseInt(arabianNumbers[indexFirstRoman]);
            convertedSecond = Integer.parseInt(arabianNumbers[indexSecondRoman]);
        }
        System.out.println("Ваши числа временно конвертированы в тип int, значения: " + convertedFirst + ", "+ convertedSecond);
//выполняем арифметическую операцию
        int result = 444;   //переменная для промежуточного результата
        switch (inputButched[1]){
            case "+" :
                result = convertedFirst + convertedSecond;
                break;
            case "-" :
                result = convertedFirst - convertedSecond;
                break;
            case "*" :
                result = convertedFirst * convertedSecond;
                break;
            case "/" :
                result = convertedFirst / convertedSecond;
                break;
        }
        System.out.println("Промежуточный результат Вашего выражения: " + result);
//обратная конвертация. Проверка на отрицательные числа в римской системе
        String[] romanUnits = new String[] {"","I","II","III","IV","V","VI","VII","VIII","IX"};
        String[] romanDozens = new String[] {"","X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] romanHundreds = new String[] {"","C"};
        String finalResult = "444";     //переменная для финального результата
        System.out.println("Обратная конвертация");
        if ((romanTrue1 && romanTrue2) == true) {
            if (result < 1) throw new Exception("Ошибка. В римской системе нет отрицательных чисел и нуля");
            int units = result % 10;
            int dozens = ((result % 100) - units) / 10;
            int hundreds = ((result % 1000) - dozens - units) / 100;
            finalResult = romanHundreds[hundreds] + romanDozens[dozens] + romanUnits[units];
        } else finalResult = Integer.toString(result); //финальный результат для арабских чисел

        return finalResult;
    }
}