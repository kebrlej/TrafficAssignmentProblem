package cz.zcu.kiv.kebrlej;

import java.util.ArrayList;
import java.util.List;

public class ODFlows {

    private int originId;
    private List<DestinationFlowPair> destFlows = new ArrayList<>();



    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public List<DestinationFlowPair> getDestFlows() {
        return destFlows;
    }

    public void setDestFlows(List<DestinationFlowPair> destFlows) {
        this.destFlows = destFlows;
    }
}
