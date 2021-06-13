package com.company.server.id;

import java.util.ArrayList;
import java.util.List;

public class IdHandler {
    private final List<Long> created;
    private long idPointer = 0;

    public IdHandler(){
        created = new ArrayList<>();
    }

    public long getNewId(){
        boolean found = false;
        while (!found) {
            idPointer++;
            found = true;
            for (Long aLong : created) {
                if (aLong == idPointer) {
                    found = false;
                    break;
                }
            }
        }
        created.add(idPointer);
        return idPointer;
    }

    public boolean isAllowed(long id){ //предоставляет id, если такой еще не занят
        for (Long aLong : created) {
            if (aLong == id) {
                return false;
            }
        }
        return true;
    }

    public List<Long> getCreated() {
        return created;
    }
    public void put(long usedId){
        if (isAllowed(usedId))
        created.add(usedId);
    }

    public void putAll(List<Long> used){
        for (Long id: used){
            put(id);
        }
    }
}
