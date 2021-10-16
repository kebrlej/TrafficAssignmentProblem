package cz.zcu.kiv.kebrlej;

import java.util.ArrayList;
import java.util.List;

public class OriginTrips {

    private int originId;
    private List<DestinationFlow> destFlows = new ArrayList<>();


    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public List<DestinationFlow> getDestFlows() {
        return destFlows;
    }

    public void setDestFlows(List<DestinationFlow> destFlows) {
        this.destFlows = destFlows;
    }
}
