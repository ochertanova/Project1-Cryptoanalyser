package ru.javarush.dialog;

import com.sun.source.doctree.SeeTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Struct;
import java.util.*;
import java.util.stream.Collectors;

//Класс ввода и проверки исходных данных
public class Dialog {
    private Scanner scanner = new Scanner(System.in);
    private String fileReadName = scanner.nextLine();
    private String fileWriteName = scanner.nextLine();
    private HashSet<String> forbiddenFiles = new HashSet<>();
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

    // Проверка только для введенного файла для записи
    private boolean checkFileName(String fileName) {
        boolean result = false;
        for (String name : FORBBIDEN_FILES_NAME) {
//            TODO: Подумать, может нужо сделать исключение
            if (!fileName.contains(name)) {
                result = true;
            } else {
                System.out.println("В данный файл запрещен для записи. Просьба выбрать другой источник");
                result = false;
                break;
            }
        }
        return result;
    }

    //    Метод для чтения данных из файла
    private List<String> readFile(String fileReadName) {
        Path fileRead = Path.of(fileReadName);
        //  считываем все данные из указанного пользователем файла и результат записываем в лист
        List<String> list = new ArrayList<>();
        // TODO: посмотреть в доке закроется ли поток
        try {
//            list = Files.lines(fileRead).collect(Collectors.toList());
            list = Files.readAllLines(fileRead);
        } catch (IOException e) {
//            TODO: подумать над комметом
            System.out.println("При чтении данных из файла возникла ошибка" + e.getMessage());
        }
        return list;
    }

    //  Метод для записи данных из файла
    private void writeFile(String fileWriteName, List<String> file) {
        Path fileWrite = Path.of(fileWriteName);
        //  считываем все данные из указанного пользователем файла и результат записываем в лист
        List<String> list = new ArrayList<>();
        // TODO: посмотреть в доке закроется ли поток
        try {
            for (String str : file)
                Files.writeString(fileWrite, str, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("");
        }
    }
}
