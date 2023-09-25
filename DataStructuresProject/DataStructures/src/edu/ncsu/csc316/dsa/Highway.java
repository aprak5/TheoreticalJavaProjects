package edu.ncsu.csc316.dsa;

/**
 * Gives an example object for testing ShortestPathUtil and MinimumSpanningTreeUtil, as a weighted object.
 * @author Amit Prakash
 *
 */
public class Highway implements Weighted {
        @SuppressWarnings("unused")
        /**The name of the highway.*/
		private String name;
        /**The length of the highway.*/
        private int length;
        
        /**
         * The (all) field constructor for the Highway.
         * @param n The name of the highway.
         * @param l The length of the highway.
         */
        public Highway(String n, int l) {
            setName(n);
            setLength(l);
        }

        /**
         * This sets the name of the highway.
         * @param name The name to set.
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * This gets the length of the highway.
         * @return The length of the highway.
         */
        public int getLength() {
            return length;
        }

        /**
         * This sets the length of the highway.
         * @param length The length to set.
         */
        public void setLength(int length) {
            this.length = length;
        }

        /**
         * This gets the weight or length of the highway.
         * @return The length/weight of the highway.
         */
        @Override
        public int getWeight() {
            return getLength();
        }
}