package ru.javarush.cryptoanalyser;

import java.util.ArrayList;
import java.util.List;

//Класс для шифрования\расшифрования
public class Cryptoanalyser {

    private static final char[] ALPHABET_INGLISH = new char[]{'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'i', 'I', 'f', 'F',
            'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q',
            'r', 'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', '.', ',', '"', ':',
            '-', '!', '?', ' '};

    private static final char[] ALPHABET_RUSSIAN = new char[]{'а', 'А', 'б', 'Б', 'в', 'В', 'г', 'Г', 'д', 'Д', 'е', 'Е',
            'ё', 'Ё', 'ж', 'Ж', 'з', 'З', 'и', 'И', 'й', 'Й', 'к', 'К', 'л', 'Л', 'м', 'М', 'н', 'Н', 'о', 'О', 'п', 'П',
            'р', 'Р', 'с', 'С', 'т', 'Т', 'у', 'У', 'ф', 'Ф', 'х', 'Х', 'ц', 'Ц', 'ч', 'Ч', 'ш', 'Ш', 'щ', 'Щ', 'ъ', 'Ъ',
            'ы', 'Ы', 'ь', 'Ь', 'э', 'Э', 'ю', 'Ю', 'я', 'Я', '.', ',', '"', ':', '-', '!', '?', ' '};

    private static final char[] ALPHABET = new char[]{'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'i', 'I', 'f', 'F',
            'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q',
            'r', 'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', 'а', 'А', 'б', 'Б', 'в', 'В', 'г', 'Г', 'д', 'Д', 'е', 'Е',
            'ё', 'Ё', 'ж', 'Ж', 'з', 'З', 'и', 'И', 'й', 'Й', 'к', 'К', 'л', 'Л', 'м', 'М', 'н', 'Н', 'о', 'О', 'п', 'П',
            'р', 'Р', 'с', 'С', 'т', 'Т', 'у', 'У', 'ф', 'Ф', 'х', 'Х', 'ц', 'Ц', 'ч', 'Ч', 'ш', 'Ш', 'щ', 'Щ', 'ъ', 'Ъ',
            'ы', 'Ы', 'ь', 'Ь', 'э', 'Э', 'ю', 'Ю', 'я', 'Я', '.', ',', '"', ':', '-', '!', '?', ' '};

//    TODO: вспомнить про передачу данных по ссылке и по значению (нужно ли создавать новый объект)
    private static List<String> encrypt(List<String> file, int key) {
        List<String> fileEncrypt = new ArrayList<>();
        String newLetter=null;
        // переопределяем ключ, если он больше длины алфавита, чтобы не делать лишние круги
        if (key > ALPHABET.length) {
            key = key % ALPHABET.length;
        }
        for (String letter : file) {
            for (int i = 0; i < ALPHABET.length; i++) {
                if (letter.equals(String.valueOf(ALPHABET[i]))) {
                    if (ALPHABET[i + key] <= ALPHABET.length) {
                        newLetter = String.valueOf(ALPHABET[i + key]);
                    } else {
                        int diff = (i + key) - ALPHABET.length;
                        newLetter = String.valueOf(diff - 1);
                    }
                }
                fileEncrypt.add(newLetter);
            }
        }
        return fileEncrypt;
    }

    private static void decipher(int key) {

    }

    private static void checkKey(int key) {
        if (key <= ALPHABET.length - 1 || key % ALPHABET.length > 0) {
//           TODO: Подумать , как лучше написать, скорее всего нужно сделать ряд исключений и пробрасывать их
            System.out.println("Ключ класс!");
        } else {
            System.out.println("Введите другой ключ");
        }


    }
}

