package com.reports.hibernate.model.entity.collection.sorted.custom;

import java.util.Comparator;

public class CustomComparator implements Comparator<CustomComparatorSetReferencedEntity> {

    @Override
    public int compare(CustomComparatorSetReferencedEntity o1, CustomComparatorSetReferencedEntity o2) {
        return -o1.getName().compareTo(o2.getName());
    }

}
