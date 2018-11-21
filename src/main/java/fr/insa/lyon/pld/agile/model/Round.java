/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.lyon.pld.agile.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author scheah
 */
public class Round {
    private List<Passage> itinerary;

    public Round() {
        this.itinerary = new ArrayList();
    }

    public List<Passage> getItinerary() {
        return Collections.unmodifiableList(itinerary);
    }
}
