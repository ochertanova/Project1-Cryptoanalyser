package ru.javarush;

import ru.javarush.cryptoanalyser.Cryptoanalyser;
import ru.javarush.dialog.Dialog;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //исходные данные
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя файла, который необходимо зашифровать");
        String fileReadName = scanner.nextLine();
        System.out.println("Введите имя файла, в который необходимо записать зашифрованный текст");
        String fileWriteNameCrypt = scanner.nextLine();
        System.out.println("Введите имя файла,в который необходимо записать расшифрованный текст");
        String fileWriteNameDecrypt = scanner.nextLine();
        System.out.println("Введите ключ для шифра Цезаря");
        int key = scanner.nextInt();

        //считываем данные для шифровки и записываем шифр в указанный файл
        Dialog dialogCrypt = new Dialog(fileReadName, fileWriteNameCrypt);
        List<String> fileToEncrypt = dialogCrypt.readFile();
        Cryptoanalyser cryptoanalyserCrypt = new Cryptoanalyser(key, fileToEncrypt);
        List<String> crypt = cryptoanalyserCrypt.encrypt();
        dialogCrypt.writeFile(crypt);

        //считываем данные для расшифровки и записываем расшифрованный текст в указанный файл
        Dialog dialogDecrypt = new Dialog(fileWriteNameCrypt, fileWriteNameDecrypt);
        List<String> fileToDecrypt = dialogDecrypt.readFile();
        Cryptoanalyser cryptoanalyserDecrypt = new Cryptoanalyser(key, fileToDecrypt);
        List<String> decrypt = cryptoanalyserDecrypt.decrypt();
        dialogDecrypt.writeFile(decrypt);
    }
}
