package ru.javarush.dialog;

import com.sun.source.doctree.SeeTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Struct;
import java.util.*;
import java.util.stream.Collectors;

//Класс ввода и проверки исходных данных

public class Dialog {
    private String fileReadName;
    private String fileWriteName;

    private static final Set<String> FORBBIDEN_FILES_NAME = Set.of(".bash_history", ".bash_logout", ".bash_profile", ".bashrc",
            ".gtkrc", ".login", ".logout", ".profile", ".viminfo", ".wm_style", ".Xdefaults & .Xresources", ".xinitrc",
            ".xsession", "/boot/vmlinuz", "/dev/fd0H1440", "/dev/fd0", "/dev/hda", "/dev/hdc", "/dev/null", "/etc/aliases",
            "/etc/bashrc", "/etc/crontab", "/etc/exports", "/etc/fstab", "/etc/group", "/etc/grub.conf", "/etc/hosts",
            "/etc/hosts.allow", "/etc/hosts.deny", "/etc/inittab", "/etc/issue", "/etc/lilo.conf", "/etc/modules.conf",
            "/etc/motd", "/etc/mtab", "/etc/passwd", "/etc/printcap", "/etc/profile", "/etc/resolv.conf", "/etc/securetty",
            "/etc/termcap", "/proc/cpuinfo", "/proc/filesystems", "/proc/interrupts", "/proc/ioports", "/proc/meminfo",
            "/proc/modules", "/proc/mounts", "/proc/stat", "/proc/swaps", "/proc/version", "/var/log/lastlog",
            "/var/log/messages", "/var/log/wtmp", "/bin", "/boot", "/dev", "/etc", "/etc/init.d", "/etc/profile.d",
            "/etc/rc.d", "/etc/rc.d/init.d", "/etc/rc.d/rc?.d", "/etc/skel", "/etc/X11", "/home", "/lib", "/mnt",
            "/proc", "/root", "/sbin", "/tmp", "/usr", "/usr/bin", "/usr/include", "/usr/share", "/usr/lib", "/usr/local/bin",
            "/usr/sbin", "/var");

    public Dialog(String fileReadName, String fileWriteName) {
        this.fileReadName = fileReadName;
        this.fileWriteName = fileWriteName;
        checkFileName(fileWriteName);//TODO: подумать! нехорошо завязывать логику в конструктор
    }

    //Проверка файла для записи
    private void checkFileName(String fileName) {
        for (String name : FORBBIDEN_FILES_NAME) {
            if (fileName.contains(name)) {
                throw new DialogUserException("Данный файл запрещен для записи.\n Просьба выбрать другой источник");
            }
        }
    }

    public List<String> readFile() {
        Path fileRead = Path.of(fileReadName);
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReadName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
                list.add("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Проверьте, пожалуйста, имя файла " + e.getMessage());
        } catch (IOException e) {
            System.out.println("При чтении данных из файла возникла ошибка " + e.getMessage());
        }
        return list;
    }

//    public List<String> readFile() {
//        Path fileRead = Path.of(fileReadName);
//        List<String> list = new ArrayList<>();
//        // TODO: посмотреть в доке закроется ли поток
//        try {
//            list = Files.readAllLines(fileRead);
//            System.out.println("Данные успешно получены из файла");
//        } catch (SecurityException e) {
//            System.out.println("Возникла ошибка доступа к чтению файла" + e.getMessage());
//        } catch (IOException e) {
//            System.out.println("При чтении данных из файла возникла ошибка" + e.getMessage());
//        }
//        return list;
//    }

    public void writeFile(List<String> dataWrite) {
        Path fileWrite = Path.of(fileWriteName);
        try {
            for (String str : dataWrite) {
                Files.writeString(fileWrite, str, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
            System.out.println("Данные успешно записаны в файл");
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректно заданы настройки записи в файл: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Возникла ошибка при записи данных в файл: " + e.getMessage());
        }
    }
}
