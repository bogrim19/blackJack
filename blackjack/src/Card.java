public class Card {
    public enum Suits {
        SPADES,
        HEARTS,
        DIAMONDS,
        CLUBS;
    }

    public enum Ranks {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE;
    }

    protected Suits suit;
    protected Ranks rank;


    public Card(Suits s, Ranks r) {
        this.suit = s;
        this.rank = r;
    }

    public Card getCard(){
        return this;
    }

    public int getScore(){
        for(Ranks r : Ranks.values()){
            if (rank == r) {
                if (r.ordinal()<8) return r.ordinal()+2;
                if (r.ordinal()>=8 && r.ordinal()<12) return 10;
                if (r == Ranks.ACE) return 11;
            }
        }
return 0;
    }
}
