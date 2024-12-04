package cards;

public enum Suit {
    DIAMOND,
    HEART,
    CLUBS,
    SPADES;

    /**
     * This method return corresponded suit symbol. These symbols are stored in char array in the Unicode format.
     * @return char
     */
    public char getSuit(){
        char[] suitsSymbols = {9830, 9829, 9827, 9824};
        return suitsSymbols[this.ordinal()];
    }
}
