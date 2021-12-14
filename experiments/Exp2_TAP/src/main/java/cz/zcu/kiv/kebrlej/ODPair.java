package cz.zcu.kiv.kebrlej;

import java.util.Objects;

public class ODPair {

    public Integer origin;
    public Integer destination;

    public double flow;


    public ODPair(Integer origin, Integer destination, double flow) {
        this.origin = origin;
        this.destination = destination;
        this.flow = flow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ODPair)) return false;
        ODPair odPair = (ODPair) o;
        return origin.equals(odPair.origin) && destination.equals(odPair.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination);
    }
}
