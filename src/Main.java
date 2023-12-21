import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
        task4();


    }

    //Task1
    public static void task1() {
        System.out.println("Создание единичной (диагональной) матрицы");
        print(createDiagonalArr());
        System.out.println("=============================");
        System.out.println("Создание нулевой матрицы");
        print(createZeroArr());
        System.out.println("===============================");

        int[][] arr1 = new int[5][5];
        int[][] arr2 = new int[5][5];

        fillArray(arr1);
        fillArray(arr2);

        System.out.println("Cложение матриц");
        print(additionTwoArr(arr1, arr2));
        System.out.println("==============================");

        int[][] arr3 = new int[2][2];
        int[][] arr4 = new int[2][2];
//        fillArray(arr3);
//        fillArray(arr4);
//        print(arr3);
//        System.out.println("==============================");
//        print(arr4);
//        System.out.println("==============================");
        System.out.println("Умножение матриц");
        print(multiplyTwoArr(arr3, arr4));

        System.out.println("==============================");
        System.out.println("Умножение матрицы на скаляр");
        print(multiplyArrByScalar(arr1, 10));

        System.out.println("==============================");
        System.out.println("Определение детерминанта матрицы");

//        int[][] arr = { { 1, 0, 2, -1 },
//                { 3, 0, 0, 5 },
//                { 2, 1, 4, -3 },
//                { 1, 0, 5, 0 } };
//

        int[][] arr = {{1, 3, -2},
                {-1, 2, 1},
                {1, 0, -2}
        };
//        1(-4-0)-3(2-1)+(-2)(0-2) = -4-3+4 = -3


        System.out.printf("Детерминант матрицы = " +
                determinantOfArray(arr));

    }

    private static void fillArray(int[][] arr) {
        int count = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = count;
                count++;
            }
        }
    }

    //Создание единичной (диагональной) матрицы
    static int[][] createDiagonalArr() {
        int[][] arr = new int[5][5];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == j) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = 0;
                }
            }
        }
        return arr;
    }

    //Создание нулевой матрицы
    public static int[][] createZeroArr() {
        int[][] arr = new int[5][5];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = 0;
            }
        }
        return arr;
    }

    //Cложение матриц
    public static int[][] additionTwoArr(int[][] ar1, int[][] ar2) {
        int[][] result = new int[ar1.length][ar1.length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = ar1[i][j] + ar2[i][j];
            }
        }
        return result;
    }

    //Умножение матриц
    public static int[][] multiplyTwoArr(int[][] ar1, int[][] ar2) {
        if (ar1[0].length != ar2.length) {
            System.out.println("Эти матрицы нельзя перемножить");
            return null;
        }
        int[][] result = new int[ar1.length][ar1.length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = 0;
                for (int k = 0; k < ar1[0].length; k++) {
                    result[i][j] += ar1[i][k] * ar2[k][j];
                }
            }
        }
        return result;
    }

    //Умножение матрицы на скаляр
    public static int[][] multiplyArrByScalar(int[][] arr, int sc) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] *= sc;
            }
        }
        return arr;
    }

    //Определение детерминанта матрицы
    static int determinantOfArray(int[][] mat) {
        int num1;
        int num2;
        int index;
        int det = 1;
        int total = 1;

        // временный массив для хранения строк
        int[] temp = new int[mat.length + 1];

        // цикл для обхода диагональных элементов
        for (int i = 0; i < mat.length; i++) {
            index = i; //инит индекса

            // ищем индекс элемента с ненулевым значением
            while (mat[index][i] == 0 && index < mat.length) {
                index++;
            }
            if (index == mat.length) {
                continue;
            }
            if (index != i) {
                //цикл для замены диагонального элемента строки и индекса строки
                for (int j = 0; j < mat.length; j++) {
                    swap(mat, index, j, i, j);
                }
                //изменение знака детерминанта
                det = (int) (det * Math.pow(-1, index - i));
            }

            //сохраняем значение элементов диагональной строки
            for (int j = 0; j < mat.length; j++) {
                temp[j] = mat[i][j];
            }

            //обход каждой строки ниже диагонали
            for (int j = i + 1; j < mat.length; j++) {
                num1 = temp[i]; // значение диагонального элемента
                num2 = mat[j]
                        [i]; //значение следующего элемента

                //перемножаем столбцы со строками
                for (int k = 0; k < mat.length; k++) {
                    mat[j][k] = (num1 * mat[j][k]) - (num2 * temp[k]);
                }
                total = total * num1; // Det(kA)=kDet(A);
            }
        }
        //для получения детерминанта перемножаем диагональные элементы
        for (int i = 0; i < mat.length; i++) {
            det = det * mat[i][i];
        }
        return (det / total); // Det(kA)/k=Det(A);
    }

    //метод замены элементов в массиве
    static int[][] swap(int[][] arr, int i1, int j1, int i2, int j2) {
        int temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
        return arr;
    }

    //вывод матрицы на консоль
    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Task2
    public static void task2() {
        int[] arr1 = {1, 2, 3, 4, 5};
        String[] arr2 = {"one", "two", "three", "four", "five",};
        int[][] arr3 = {{1, 2, 3}, {4, 5, 6}};
        float[][] arr4 = {{2.7f, 10f, 0.33f, 3f, 1.4f}, {6f, 0.67f, 0.86f, 1.2f, 0.44f}};

        printToConsole(arr1);
        System.out.println("================");
        printToConsole(arr2);
        System.out.println("================");
        printToConsole(arr3);
        System.out.println("================");
        printToConsole(arr4);
    }

    //одномерный массив типа int
    public static void printToConsole(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    //одномерный массив типа String
    public static void printToConsole(String[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    //двухмерный массив типа int
    public static void printToConsole(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            printToConsole(arr[i]);
        }
    }

    //двухмерный массив типа float
    public static void printToConsole(float[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Task3
    public static void task3() {
        String[] strings1 = {"Ароза упала", "на лапу", "Азора"};
        String[] strings2 = {"1", "2", "3"};
        String[] strings3 = {"a", "b", "c", "d"};
        String[] strings4 = {"a", "c c c", "b b", "d d d d"};
        String[] strings5 = {"Привет мой друг", "Привет друг", "Привет", "Привет мой друг это я"};

        System.out.println("Вывод масссива в строку через пробел");
        print(strings2);
        System.out.println("====================================");
        System.out.println("Cортировка массива в обратном порядке");
        print(strings1);
        print(reverse(strings1));
        System.out.println("====================================");
        System.out.println("Сортировка массива по количеству слов в строке");
        System.out.println(Arrays.toString(strings4));
        System.out.println(Arrays.toString(sortByWordCount(strings4)));
        System.out.println(Arrays.toString(strings5));
        System.out.println(Arrays.toString(sortByWordCount(strings5)));

    }

    //вывод содержимого в строку через пробел
    public static void print(String[] strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
            if (i != strings.length - 1) {
                stringBuilder.append(" ");
            }
        }
        System.out.println(String.valueOf(stringBuilder));
    }

    //сортировка массива в обратном порядке
    public static String[] reverse(String[] strings) {

        String[] result = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            StringBuilder stringBuilder = new StringBuilder(strings[i]);
            String rev = stringBuilder.reverse().toString();
            result[strings.length - 1 - i] = rev;
        }
        return result;
    }

    //Сортировка массива по количеству слов в строке
    public static String[] sortByWordCount(String[] strings) {
        Arrays.sort(strings, (left, right) -> left.split(" ").length - right.split(" ").length);
        return strings;
    }

//    //метод генерации слов из английских прописных букв
//    public static String  generateRandomString() {
//
//        int leftLimit = 97; // буква 'a'
//        int rightLimit = 122; // буква 'z'
//        int targetStringLength = 10;
//        Random random = new Random();
//        StringBuilder builder = new StringBuilder(targetStringLength);
//        for (int i = 0; i < targetStringLength; i++) {
//            int randomLimitedInt = leftLimit + (int)
//                    (random.nextFloat() * (rightLimit - leftLimit + 1));
//            builder.append((char) randomLimitedInt);
//        }
//        String generatedString = builder.toString();
//
//        System.out.println(generatedString);
//
//        StringBuilder sentence = new StringBuilder();
//
//        for (int i = 0; i < random.nextInt(0,9); i++) {
//            sentence.append(generateRandomString());
//            sentence.append(" ");
//        }
//        sentence.append(generateRandomString());
//
//        return sentence.toString();
//    }

    //Task4

    public static void task4() {
        //Решаем с помощью рекурсии
        //результат - это сумма текущей позиции и максимального варианта (вниз налево или вниз направо)

        int size = 5;
        int[][] arr = createArr(size);
        printTriangleToConsole(arr);
        System.out.println("Наибольшая сумма чисел " + getMaxSum(arr, 0, 0));

    }
    //Создание треугольника
    public static int[][] createArr(int size) {

        //Проще считать, если треугольник хранить в прямоугольном массиаве,
        // тогда спускаемся вниз либо по тому же индексу, либо на 1 больше
        int[][] arr = {
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5,}};

//        int[][] arr = {
//                {1},
//                {2, 3},
//                {4, 5, 6},
//                {7, 8, 9, 10}};

        return arr;
    }

    //Считаем максимальную сумму
    public static int getMaxSum(int[][] arr, int col, int row) {
        int res = arr[row][col];
        if (row != arr.length - 1) {
            int left = getMaxSum(arr, col, row + 1);
            int right = getMaxSum(arr, col + 1, row + 1);
            res += Math.max(left, right);
        }
        return res;
    }

    //Вывод треугольника в консоль
    public static void printTriangleToConsole(int[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <(array.length - i); j++) {
                System.out.print(" ");
            }
            printArray(array[i]);
            for (int j = array[i].length/2; j < array.length; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}