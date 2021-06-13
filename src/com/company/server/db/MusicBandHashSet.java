package com.company.server.db;

import com.company.server.exceptions.IdAlreadyExistsException;
import com.company.server.id.IdHandler;
import com.company.server.model.MusicBand;
import com.company.server.commands.templer.Command;

import java.util.*;

/**
 * Expanded {@code HashSet}, which stores {@code MusicBand} Objects
 * Receiver for Commands
 * @see MusicBand
 * @see Command
 */

public class MusicBandHashSet {

    /**
     * The date of the initialization of {@code MusicBandHashSet}
     */

    private final Date initTime;
    private final HashSet<MusicBand> data;
    private final Comparator<MusicBand> nameComparator;
    private final IdHandler idHandler;

    public MusicBandHashSet(){
        initTime = new Date();
        data = new HashSet<>();
        idHandler = new IdHandler();
        nameComparator = Comparator.comparing(MusicBand::getName);
    }

    public MusicBandHashSet(List<MusicBand> list) throws IdAlreadyExistsException {
        this();
        addAll(list);
    }

    public Comparator<MusicBand> getNameComparator() {
        return nameComparator;
    }

    public void addWithNewId(MusicBand newElem){
        newElem.setId(idHandler.getNewId());
        data.add(newElem);
    }

    public void add(MusicBand newElem){
        data.add(newElem);
    }

    public void addAll(List<MusicBand> list) throws IdAlreadyExistsException{
        for (MusicBand musicBand : list){
            if (!idHandler.isAllowed(musicBand.getId())){
                throw new IdAlreadyExistsException();
            } else {
                idHandler.put(musicBand.getId());
            }
        }
        data.addAll(list);
    }

    /**
     * contains: collection type, count of elements, the date of the initialization
     * @return information of {@code MusicBandHashSet} object in a {@code String}
     */
    public String getInfo(){
        return "Collection type: " + data.getClass().toString() + ", count of elements: " + data.size()
                + ", init time: " + initTime;
    }

    public HashSet<MusicBand> getData(){
        return data;
    }

}
