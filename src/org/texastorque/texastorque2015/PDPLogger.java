package org.texastorque.texastorque2015;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.texastorque.torquelib.util.Loggable;

public class PDPLogger implements Loggable {
    
    PowerDistributionPanel pdp;
    
    public PDPLogger() {
        pdp = new PowerDistributionPanel();
    }

    @Override
    public String getLogNames() {
        return "0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, ";
    }

    @Override
    public String getLogValues() {
        String currents = "";
        
        for (int i = 0; i < 16; i++) {
            currents += (pdp.getCurrent(i) + ", ");
        }
        
        return currents;
    }

}
