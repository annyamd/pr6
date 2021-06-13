package com.company.server.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvRecurse;

import java.io.Serializable;
import java.time.LocalDateTime;

////пока сортирует только по id

public class MusicBand implements Comparable<MusicBand>, Serializable {

    private static final long serialVersionUID = 1L;

    @CsvBindByName
    private long id; /**Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически*/
    @CsvBindByName
    private String name; /**Поле не может быть null, Строка не может быть пустой*/
    @CsvRecurse
    private Coordinates coordinates; /**Поле не может быть null*/
    @CsvBindByName(column = "creation_date")
    @CsvDate("yyyy-MM-dd'T'HH:mm")
    private java.time.LocalDateTime creationDate; /**Поле не может быть null, Значение этого поля должно генерироваться автоматически*/
    @CsvBindByName(column = "number_of_participants")
    private int numberOfParticipants; /**Значение поля должно быть больше 0*/
    @CsvBindByName
    private MusicGenre genre; /**Поле может быть null*/
    @CsvRecurse
    private Studio studio; /**Поле может быть null*/

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }

    public void setNumberOfParticipants(int numberOfParticipants){
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setGenre(MusicGenre genre){
        this.genre = genre;
    }

    public void setStudio(Studio studio){
        this.studio = studio;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public int compareTo(MusicBand o) {
        if (o == null) return 2;
        if ((id - o.id) > 0) return 1;
        else if (id == o.id) return 0;
        else return -1;
    }

    @Override
    public int hashCode() {
        return ("" + id + name + coordinates + creationDate + numberOfParticipants + genre + studio).hashCode();
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", genre=" + genre +
                ", studio=" + studio +
                '}';
    }
}
