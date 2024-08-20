import java.io.IOException;
import java.util.Scanner;

public class calculator {
    public static void main(String[] args) {
        /**
         * Требования:
         * 1.Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления
         * с двумя числами: a + b, a - b, a * b, a / b. Данные передаются в одну строку (смотри пример)!
         * Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.
         * 2.Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
         * 3.Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не
         * ограничиваются по величине и могут быть любыми.
         * 4.Калькулятор умеет работать только с целыми числами.
         * 5.Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе
         * пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
         * 6.При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно,
         * при вводе арабских - ответ ожидается арабскими.
         * 7.При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
         * 8.При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций,
         * приложение выбрасывает исключение и завершает свою работу.
         * 9.Результатом операции деления является целое число, остаток отбрасывается.
         * 10.Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль.
         * Результатом работы калькулятора с римскими числами могут быть только положительные числа, если результат работы меньше единицы, выбрасывается исключение
         */
        System.out.println("Введите выражение");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        String[] membersExpression = expression.split(" ");
        int a;
        int b;


        if ((membersExpression.length <= 3 && membersExpression.length != 1) &&
                (membersExpression[1].equals("+") || membersExpression[1].equals("-") ||
                        membersExpression[1].equals("*") || membersExpression[1].equals("/"))) {
            if (isArabic(membersExpression[0])) {
                if (isArabic(membersExpression[2])) {
                    a = Integer.parseInt(membersExpression[0]);
                    b = Integer.parseInt(membersExpression[2]);
                    System.out.println(calculat(a, b, membersExpression[1]));
                } else {
                    try {
                        throw new IOException("используются одновременно разные системы счисления");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }
            } else if (isRoman(membersExpression[0])) {
                if (isArabic(membersExpression[2])) {
                    try {
                        throw new IOException("используются одновременно разные системы счисления");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }
                if (isRoman(membersExpression[2]) &&
                        ((membersExpression[1].equals("+") || membersExpression[1].equals("*") || membersExpression[1].equals("/")) ||
                                (membersExpression[1].equals("-") && romanToInt(membersExpression[0]) > romanToInt(membersExpression[2])))) {
                    a = romanToInt(membersExpression[0]);
                    b = romanToInt(membersExpression[2]);
                    if (calculat(a, b, membersExpression[1]) < 1) {
                        try {
                            throw new IOException("результат меньше единицы");
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                            return;
                        }
                    }
                    intToRoman(calculat(a, b, membersExpression[1]));
                } else {
                    try {
                        throw new IOException("в римской системе нет отрицательных чисел");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }
            } else {
                try {
                    throw new IOException("строка не является математической операцией");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
        } else {
            try {
                throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *) или отсутствуют пробелы");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    public static int calculat(int a, int b, String znak) {
        if (znak.equals("-")) {
            return (a - b);
        } else if (znak.equals("+")) {
            return (a + b);
        } else if (znak.equals("*")) {
            return (a * b);
        } else {
            return ((int) (a / b));
        }
    }


    public static boolean isArabic(String stringMember) {
        if (stringMember.equals("1") ||
                stringMember.equals("2") ||
                stringMember.equals("3") ||
                stringMember.equals("4") ||
                stringMember.equals("5") ||
                stringMember.equals("6") ||
                stringMember.equals("7") ||
                stringMember.equals("8") ||
                stringMember.equals("9") ||
                stringMember.equals("10")) {
            return true;
        }
        return false;
    }

    public static boolean isRoman(String stringMember) {
        if (stringMember.equals("I") ||
                stringMember.equals("II") ||
                stringMember.equals("III") ||
                stringMember.equals("IV") ||
                stringMember.equals("V") ||
                stringMember.equals("VI") ||
                stringMember.equals("VII") ||
                stringMember.equals("VIII") ||
                stringMember.equals("IX") ||
                stringMember.equals("X")) {
            return true;
        }
        return false;
    }

    public static boolean isAriеhmeticSign(String stringMember) {
        if (stringMember.equals("+") ||
                stringMember.equals("-") ||
                stringMember.equals("*") ||
                stringMember.equals("/")) {
            return true;
        }
        return false;
    }

    public static int romanToInt(String stringMember) {
        if (stringMember.equals("I")) {
            return 1;
        } else if (stringMember.equals("II")) {
            return 2;
        } else if (stringMember.equals("III")) {
            return 3;
        } else if (stringMember.equals("IV")) {
            return 4;
        } else if (stringMember.equals("V")) {
            return 5;
        } else if (stringMember.equals("VI")) {
            return 6;
        } else if (stringMember.equals("VII")) {
            return 7;
        } else if (stringMember.equals("VIII")) {
            return 8;
        } else if (stringMember.equals("IX")) {
            return 9;
        } else {
            return 10;
        }
    }

    public static void intToRoman(int number) {
        if (number == 100) {
            System.out.print("C");
        } else if (number >= 90) {
            System.out.print("XC");
            number -= 90;
        } else if (number >= 80) {
            System.out.print("LXXX");
            number -= 80;
        } else if (number >= 70) {
            System.out.print("LXX");
            number -= 70;
        } else if (number >= 60) {
            System.out.print("LX");
            number -= 60;
        } else if (number >= 50) {
            System.out.print("l");
            number -= 50;
        } else if (number >= 40) {
            System.out.print("Xl");
            number -= 40;
        } else if (number >= 30) {
            System.out.print("XXX");
            number -= 30;
        } else if (number >= 20) {
            System.out.print("XX");
            number -= 20;
        } else if (number >= 10) {
            System.out.print("X");
            number -= 10;
        }

        if (number >= 9) {
            if (number == 9) {
                System.out.print("I");
            }
            System.out.print("X");
        } else if (number < 9) {
            if (number < 4 && number > 0) {
                System.out.print("I");
                if (number <= 3 && number > 1) {
                    System.out.print("I");
                    if (number == 3) {
                        System.out.print("I");
                    }
                }
            }
            if (number >= 4) {
                if (number == 4) {
                    System.out.print("I");
                }
                System.out.print("V");
                if (number >= 6) {
                    System.out.print("I");
                    if (number >= 7) {
                        System.out.print("I");
                        if (number == 8) {
                            System.out.print("I");
                        }
                    }
                }
            }

        }

    }

}
