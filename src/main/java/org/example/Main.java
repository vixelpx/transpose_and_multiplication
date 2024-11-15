package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите матрицу (целые числа, разделенные пробелами). Завершите ввод, нажав Enter после последней строки:");

        StringBuilder matrixInput = new StringBuilder();
        String line;

        while (true) {
            line = scanner.nextLine();
            if (line.isEmpty()) {
                break; // Выход из цикла, если введена пустая строка
            }
            matrixInput.append(line).append("\n");
        }

        // Деление введенной матрицы на строки
        String[] rowsInput = matrixInput.toString().trim().split("\n");
        int rows = rowsInput.length;

        // Проверка на наличие введенных строк
        if (rows == 0) {
            System.out.println("Ошибка: матрица не была введена.");
            return;
        }

        // Количество столбцов определяется количеством элементов в первой строке
        int cols = rowsInput[0].split(" ").length;

        // Проверка на квадратность матрицы
        if (rows != cols) {
            System.out.println("Ошибка: матрица должна быть квадратной (количество строк должно совпадать с количеством столбцов).");
            return;
        }

        int[][] matrix = new int[rows][cols];

        // Заполнение матрицы
        for (int i = 0; i < rows; i++) {
            String[] rowElements = rowsInput[i].split(" ");
            // Проверка, что количество элементов каждой строки совпадает
            if (rowElements.length != cols) {
                System.out.println("Ошибка: количество элементов в строке " + (i + 1) + " должно быть равно " + cols);
                return;
            }

            // Проверка, что все числа являются целыми
            for (int j = 0; j < cols; j++) {
                try {
                    matrix[i][j] = Integer.parseInt(rowElements[j]);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: все элементы должны быть целыми числами!");
                    return;
                }
            }
        }

        // Создание транспонированной матрицы
        int[][] transposedMatrix = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }

        // Умножение исходной матрицы на транспонированную
        int[][] resultMatrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) { // Цикл проходит по каждой строке исходной матрицы matrix
            for (int j = 0; j < cols; j++) { // Цикл проходит по каждому столбцу результирующей матрицы
                for (int k = 0; k < cols; k++) { // Индекс k проходит по элементам текущей строки исходной матрицы и соответствующего столбца транспонированной матрицы
                    resultMatrix[i][j] += matrix[i][k] * transposedMatrix[k][j];
                }
            }
        }

        // Вывод исходной матрицы
        System.out.println("Исходная матрица:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // Вывод транспонированной матрицы на экран
        System.out.println("Транспонированная матрица:");
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print(transposedMatrix[i][j] + " ");
            }
            System.out.println();
        }

        // Вывод результирующей матрицы на экран
        System.out.println("Результат умножения исходной матрицы на транспонированную:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}