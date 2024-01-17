package com.reports.hibernate.model.entity.collection.sorted.custom;

import java.util.Comparator;

public class CustomComparator implements Comparator<CustomComparatorSetPet> {

    @Override
    public int compare(CustomComparatorSetPet o1, CustomComparatorSetPet o2) {
        return -o1.getName().compareTo(o2.getName());
    }

}
