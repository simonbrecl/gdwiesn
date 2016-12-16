/**
 * Class for storing the current state of the tent, including upgrades and additional areas.
 * Created by ericasolum on 12/11/16.
 */
public class TentState {

    private int kitchenLevel;
    private int securityLevel;
    private int bandLevel;
    private int decorationsLevel;
    private int barLevel;
    private int money;


    /* Initialized at the start of the game */
    public TentState() {
        kitchenLevel = 0;
        securityLevel = 0;
        bandLevel = 0;
        decorationsLevel = 0;
        barLevel = 1;
        money = 0;

    }

    public void updateMoney(int m) {
        money = m;
    }

    /** UPGRADE METHODS **/

    /* Upgrade the kitchen level.
    Returns true if successful, false otherwise. */
    public boolean upgradeKitchen() {
        if(kitchenLevel < 3) {
            kitchenLevel++;
            return true;
        }
        return false;
    }

    /* Upgrade the bar level */
    public boolean upgradeBar() {
        if(barLevel < 2) {
            barLevel++;
            return true;
        }
        return false;
    }

    /* Upgrade the decorations level */
    public boolean upgradeDecorations() {
        if(decorationsLevel < 3) {
            decorationsLevel++;
            return true;
        }
        return false;
    }

    public boolean upgradeBand() {
        if(bandLevel < 3) {
            bandLevel++;
            return true;
        }
        return false;
    }

    public boolean upgradeSecurity() {
        if(securityLevel < 1) {
            securityLevel++;
            return true;
        }
        return false;
    }

    /** GET METHODS **/

    /* Get the level of the kitchen.
        0 - no kitchen
        1 - kitchen only sells pretzels
        2 - kitchen sells sausages and pretzels
     */
    public int getKitchenLevel() {
        return kitchenLevel;
    }

    /* Get the level of the bar.
        1 - bar only has one tap
        2 - bar has two barrels
     */
    public int getBarLevel() {
        return barLevel;
    }

    /* Get the level of the decorations.
     */
    public int getDecorationsLevel() {
        return decorationsLevel;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    /* Get the level of the bar.
        0 - no band
        1 - band only plays annoying music?
        2 - band plays nice music and switches up the songs?
     */
    public int getBandLevel() {
        return bandLevel;
    }



}
