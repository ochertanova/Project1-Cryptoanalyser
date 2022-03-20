package ru.javarush;

import ru.javarush.cryptoanalyser.Cryptoanalyser;
import ru.javarush.dialog.Dialog;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // считываем имена файлов
        Scanner scanner = new Scanner(System.in);
        String fileReadName = scanner.nextLine();
        String fileWriteNameСrypt = scanner.nextLine();
        String fileWriteNameDecrypt = scanner.nextLine();

        // считываем данные для шифровки и записываем шифр в указанный файл
        Dialog dialogCipher = new Dialog(fileReadName, fileWriteNameСrypt);
        List<String> fileToEncrypt = dialogCipher.readFile();
        Cryptoanalyser cryptoanalyserCrypt = new Cryptoanalyser(16, fileToEncrypt);
        List<String> cipher = cryptoanalyserCrypt.encrypt();
        dialogCipher.writeFile(cipher);

        // считываем данные для расшифровки и записываем расшифрованный текст в указанный файл
        Dialog dialogDecipher = new Dialog(fileWriteNameСrypt, fileWriteNameDecrypt);
        List<String> fileToDecrypt = dialogDecipher.readFile();
        Cryptoanalyser cryptoanalyserDecrypt = new Cryptoanalyser(16, fileToDecrypt);
        List<String> decipher = cryptoanalyserDecrypt.decrypt();
        dialogDecipher.writeFile(decipher);
    }
}
