package ru.javarush.cryptoanalyser;

import java.util.ArrayList;
import java.util.List;

//Класс для шифрования\расшифрования
public class Cryptoanalyser {
    private int key;
    private List<String> file;
//    private List<String> fileToEncrypt;
//    private List<String> fileToDecipher;

    public Cryptoanalyser(int key, List<String> file) {
        this.key = key;
        this.file = file;
    }
//    public Cryptoanalyser(int key, List<String> fileToEncrypt) {
//        this.key = key;
//        this.fileToEncrypt = fileToEncrypt;
//    }
//
//    public Cryptoanalyser(int key, List<String> fileToEncrypt, List<String> fileToDecipher) {
//        this.key = key;
//        this.fileToEncrypt = fileToEncrypt;
//        this.fileToDecipher = fileToDecipher;
//    }

//    public Cryptoanalyser(int key, List<String> fileToDecipher) {
//        this.key = key;
//        this.fileToEncrypt = fileToEncrypt;
//        this.fileToDecipher = fileToDecipher;
//    }

//    private static final char[] ALPHABET_INGLISH = new char[]{'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'i', 'I', 'f', 'F',
//            'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q',
//            'r', 'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', '.', ',', '"', ':',
//            '-', '!', '?', ' '};
//
//    private static final char[] ALPHABET_RUSSIAN = new char[]{'а', 'А', 'б', 'Б', 'в', 'В', 'г', 'Г', 'д', 'Д', 'е', 'Е',
//            'ё', 'Ё', 'ж', 'Ж', 'з', 'З', 'и', 'И', 'й', 'Й', 'к', 'К', 'л', 'Л', 'м', 'М', 'н', 'Н', 'о', 'О', 'п', 'П',
//            'р', 'Р', 'с', 'С', 'т', 'Т', 'у', 'У', 'ф', 'Ф', 'х', 'Х', 'ц', 'Ц', 'ч', 'Ч', 'ш', 'Ш', 'щ', 'Щ', 'ъ', 'Ъ',
//            'ы', 'Ы', 'ь', 'Ь', 'э', 'Э', 'ю', 'Ю', 'я', 'Я', '.', ',', '"', ':', '-', '!', '?', ' '};

    private static final char[] ALPHABET = new char[]{'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'i', 'I', 'f', 'F',
            'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q',
            'r', 'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', 'а', 'А', 'б', 'Б', 'в', 'В', 'г', 'Г', 'д', 'Д', 'е', 'Е',
            'ё', 'Ё', 'ж', 'Ж', 'з', 'З', 'и', 'И', 'й', 'Й', 'к', 'К', 'л', 'Л', 'м', 'М', 'н', 'Н', 'о', 'О', 'п', 'П',
            'р', 'Р', 'с', 'С', 'т', 'Т', 'у', 'У', 'ф', 'Ф', 'х', 'Х', 'ц', 'Ц', 'ч', 'Ч', 'ш', 'Ш', 'щ', 'Щ', 'ъ', 'Ъ',
            'ы', 'Ы', 'ь', 'Ь', 'э', 'Э', 'ю', 'Ю', 'я', 'Я', '.', ',', '"', ':', '-', '!', '?', ' '};

    // Шифрование данных
    public List<String> encrypt() {
        checkKey(key);
        List<String> fileEncrypt = new ArrayList<>();
        for (String letter : file) {
            addToCrypt(letter, fileEncrypt);
        }
        return fileEncrypt;
    }

    // Расшифровка данных
    public List<String> decrypt() {
        checkKey(key);
        List<String> fileDecrypt = new ArrayList<>();
        for (String letter : file) {
            addToDecrypt(letter, fileDecrypt);
        }
        return fileDecrypt;
    }

    private void checkKey(int checkKey) {
        if (checkKey <= ALPHABET.length - 1 || checkKey % ALPHABET.length > 0) {
            // Если ключ больше длины алфавита, изменяем его, чтобы не делать лишние круги
            changeKey(checkKey);
        } else {
            throw new CryptoanalyserExeption("Введите, пожалуйста, ключ, отличный от кратного длине алфавита - " + ALPHABET.length);
        }
    }

    private void changeKey(int checkKey) {
        if (checkKey > ALPHABET.length) {
            key = checkKey % ALPHABET.length;
        }
    }

    private void addToCrypt(String letter, List<String> fileEncrypt) {
        String[] letters = letter.split("");
        for (int j = 0; j < letters.length; j++) {
            String newLetter = "";
            for (int i = 0; i < ALPHABET.length; i++) {
                if (letters[j].equals(String.valueOf(ALPHABET[i]))) {
                    if (i + key <= ALPHABET.length - 1) {
                        newLetter = String.valueOf(ALPHABET[i + key]);
                    } else {
                        int diff = (i + key) - ALPHABET.length;
                        newLetter = String.valueOf(ALPHABET[diff]);
                    }
                    fileEncrypt.add(newLetter);
                    break;
                }
            }
            // добавляем в файл символ без изменения, который отсутствует в алфавите
            if (newLetter.equals("")) {
                fileEncrypt.add(letters[j]);
            }
        }
    }

    private void addToDecrypt(String letter, List<String> fileDecrypt) {
        String[] letters = letter.split("");
        for (int j = 0; j < letters.length; j++) {
            String newLetter = "";
            for (int i = 0; i < ALPHABET.length; i++) {
                if (letters[j].equals(String.valueOf(ALPHABET[i]))) {
                    if (i - key >= 0) {
                        newLetter = String.valueOf(ALPHABET[i - key]);
                    } else {
                        int diff = (i - key) + ALPHABET.length;
                        newLetter = String.valueOf(ALPHABET[diff]);
                    }
                    fileDecrypt.add(newLetter);
                    break;
                }
            }
            // добавляем в файл символ без изменения, который отсутствует в алфавите
            if (newLetter.equals("")) {
                fileDecrypt.add(letters[j]);
            }
        }
    }
}

