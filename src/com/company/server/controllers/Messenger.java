package com.company.server.controllers;

import com.company.server.model.MusicGenre;

public class Messenger {
    public static String getRequestByName(String name) {
        switch (name) {
            case "id":
                return "Введите id музыкакльной группы:";
            case "name":
                return "Введите название музыкакльной группы:";
            case "coordinates": return "Укажите координаты расположения: ";
            case "coordinate_x":
                return "Введите x музыкакльной группы:";
            case "coordinate_y":
                return "Введите y музыкакльной группы:";
            case "creation date":
                return "Значение этого поля должно генерироваться автоматически";
            case "number of participants":
                return "Введите число участников группы: ";
            case "genre": return  "Какой из музыкальных жанров: ";
            case "music genre":

                StringBuilder s = new StringBuilder("\n");
                MusicGenre[] genres = MusicGenre.values();
                for (MusicGenre genre : genres){
                    s.append(genre.name()).append(", ");
                }
                return s.substring(0,s.length()-2) + "\n";

            case "studio": return "Укажите информацию о студии группы: ";
            case "studio_name": return "Введите название студии: ";
            case "studio_address": return "Введите адрес студии: ";
        }
        return "";
    }

    public static String getCommandsDescription(){
        return "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние 10 команд (без их аргументов)\n" +
                "max_by_studio : вывести любой объект из коллекции, значение поля studio которого является максимальным\n" +
                "filter_by_number_of_participants numberOfParticipants : вывести элементы, значение поля numberOfParticipants которых равно заданному\n" +
                "print_descending : вывести элементы коллекции в порядке убывания";
    }
}
