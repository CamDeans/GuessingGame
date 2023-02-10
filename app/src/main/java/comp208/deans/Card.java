package comp208.deans;

/*
 * Find location - row/col
 * build a constructor for frontFace/ backFace
 * generate a boolean for isFaceUp to allow the user to flip cards if the card selection is not showing face value
 */
public class Card {

    public int row;
    public int col;
    public int frontFace;
    public int backFace;
    boolean faceUp = false;

    public Card(int backFace, int frontFace) {
        this.backFace = backFace;
        this.frontFace = frontFace;
    }

    public boolean isFaceUp() {
        return false;
    }
}
