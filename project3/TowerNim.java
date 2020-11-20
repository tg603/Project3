/**
 * Models a position in Tower Nim.  Tower Nim is the combinatorial game where sticks can only be removed from the top pile.  If that pile is ever zero, it is removed from the tower.
 *
 * @author Kyle Burke <paithanq@gmail.com>
 * 
 */
 
import java.lang.*;
import java.io.*;
import java.util.*;

public class TowerNim extends CombinatorialGame {

    //instance variables
    //the piles in this.
    private PureStack<Integer> piles; 

    /**
     * Class constructor.
     * 
     * @param piles     A PureStack of piles.  It will finish with the same elements as when this constructor is called.  All piles must have size greater than zero, or an IllegalArgumentException will be thrown.
     */
    public TowerNim(PureStack<Integer> piles) throws IllegalArgumentException {
        //need to put the piles in backwards, so first put them in one stack, then pull them out.
        PureStack<Integer> tempStack = new PureStack<Integer>();
        boolean illegalPileSize = false;
        while (!piles.isEmpty()) {
            tempStack.push(piles.pop());
        }
        
        //now put the piles from tempPiles into the stack field and into the parameter
        this.piles = new PureStack<Integer>();
        while (!tempStack.isEmpty()) {
            Integer pile = tempStack.pop();
            piles.push(pile); //put the pile back where it came from
            //now see if we should put it back into the field stack
            if (pile.intValue() > 0) {
                this.piles.push(pile);
            } else if (pile.intValue() <= 0) {
                illegalPileSize = true;
            }
        }
        if (illegalPileSize) {
            throw new IllegalArgumentException("Tried to use a non-positive pile size in TowerNim!");
        }
    }
    
    /**
     * Gets a String representation.
     *
     * @return  A String representation of this.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("A Stack Nim position: Next Pile --> ");
        PureStack<Integer> pileSizes = this.getPiles();
        while (!pileSizes.isEmpty()) {
            builder.append(pileSizes.pop() + " ");
        }
        builder.append("<-- Last Pile");
        return builder.toString();
    }
    
    /**
     * Gets the piles.
     *
     * @return  A copy of the piles, as a PureStack.
     */
    public PureStack<Integer> getPiles() {
        PureStack<Integer> pileCopy = new PureStack<Integer>(); //this is the one we'll return
        PureStack<Integer> tempPiles = new PureStack<Integer>(); //this is the one we'll use to reload the stack field
        while (!this.piles.isEmpty()) {
            tempPiles.push(this.piles.pop());
        }
        
        //now it's time to reload the stack field of this
        while (!tempPiles.isEmpty()) {
            Integer pile = tempPiles.pop();
            this.piles.push(pile);
            pileCopy.push(pile);
        }
        return pileCopy;
    }
    
    /**
     * Clones this.
     *
     * @return  A deep clone of this.
     */
    public TowerNim clone() {
        return new TowerNim(this.getPiles());
    }
    
    @Override
    public boolean equals(CombinatorialGame other) {
        try {
            return this.equals((TowerNim) other);
        } catch (ClassCastException cce) {
            return false;
        }
    }
    
    /**
     * Returns whether this equals another TowerNim object.
     *
     * @param other  The other TowerNim to compare to this.
     * @return  Whether this and other have exactly the same pile sizes in exactly the same places.
     */
    public boolean equals(TowerNim other) {
        return this.getPiles().equals(other.getPiles());
    }
    
    @Override
    public Collection<CombinatorialGame> getOptions(int player) {
        ArrayList<CombinatorialGame> options = new ArrayList<CombinatorialGame>();
        PureStack<Integer> piles = this.getPiles();
        if (piles.isEmpty()) {
            return options;
        }
        Integer topPile = piles.pop();
        //add the option where the entire top pile was taken
        options.add(new TowerNim(piles));
        for (int i = 1; i < topPile.intValue(); i++) {
            piles.push(new Integer(i));
            options.add(new TowerNim(piles));
            piles.pop();
        }
        return options;
    }
    
    /* Private methods */
    
    /**
     * A generator of TowerNim positions.
     */
    public static class PositionBuilder implements PositionFactory<TowerNim> {
    
        //maximum number of piles it will generate
        private int numPiles;
        
        //maximum piles size
        private int maxPileSize;
        
        /**
         * Class constructor.
         *
         * @param numPiles  The maximum number of piles.
         * @param maxPileSize  The maximum size of a pile.
         */
        public PositionBuilder(int numPiles, int maxPileSize) {
            this.numPiles = numPiles;
            this.maxPileSize = maxPileSize;
        }
        
        //@override
        public TowerNim getPosition() {
            PureStack<Integer> piles = new PureStack<Integer>();
            Random randomGenerator = new Random();
            for (int i = 0; i < numPiles; i++) {
                int pileSize = randomGenerator.nextInt(this.maxPileSize + 1);
                if (pileSize > 0) {
                    piles.push(new Integer(pileSize));
                }
            }
            return new TowerNim(piles);
        }
        
    } //end of PositionBuilder
    
    /**
     * Unit test for TowerNim.
     */
    public static void main(String[] args) {
        //test the constructor and toString
        PureStack<Integer> piles = new PureStack<Integer>();
        piles.push(new Integer(3));
        piles.push(new Integer(1));
        piles.push(new Integer(5));
        piles.push(new Integer(7));
        TowerNim nim = new TowerNim(piles);
        
        System.out.println("Position: " + nim);
        //test that the piles are still in working order
        System.out.println("Piles used to create the position: " + piles);
        
        //test the constructor again on an illegal pile size
        piles.push(new Integer(-8));
        try {
            TowerNim illegal = new TowerNim(piles);
        } catch (IllegalArgumentException iae) {
            System.out.println("Couldn't create a game!");
        }
        
        
        //test getPiles
        System.out.println("The piles: " + nim.getPiles());
        
        
        //test clone
        TowerNim clone = (TowerNim) nim.clone();
        System.out.println("Clone: " + clone);
        //make sure the original position is still intact
        System.out.println("Position again: " + nim);
        
        
        //test equals
        //easy true test
        System.out.println("position equals clone? (should be true): " + nim.equals(clone));
        System.out.println("clone equals position? (should be true): " + clone.equals(nim));
        //set up and test a complex false test
        piles.pop();
        piles.push(4);
        TowerNim bigger = new TowerNim(piles);
        System.out.println("position equals bigger? (should be false): " + nim.equals(bigger));
        System.out.println("bigger equals position? (should be false): " + bigger.equals(nim));
        //set up and test a complex true test
        PureStack<Integer> morePiles = new PureStack<Integer>();
        morePiles.push(new Integer(3));
        morePiles.push(new Integer(1));
        morePiles.push(new Integer(5));
        morePiles.push(new Integer(7));
        TowerNim nimCopy = new TowerNim(morePiles);
        System.out.println("position equals copy? (should be true): " + nim.equals(nimCopy));
        System.out.println("copy equals position? (should be true): " + nimCopy.equals(nim));
        
        
        //test getOptions
        System.out.println("Options:");
        for (CombinatorialGame option: nim.getOptions(CombinatorialGame.LEFT)) {
            System.out.println("    " + option);
        }
        
        piles = new PureStack<Integer>();
        piles.push(new Integer(3));
        piles.push(new Integer(1));
        piles.push(new Integer(5));
        piles.push(new Integer(5));
        TowerNim option = new TowerNim(piles);
        System.out.println("Has " + option + " as an option? (should be true): " + nim.hasOption(CombinatorialGame.LEFT, option));
    }

}  //end of TowerNim