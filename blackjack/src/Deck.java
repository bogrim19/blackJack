import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {

    private ArrayList<Card> mCards;
    private ArrayList<Card> mPulledCards;


    public Deck() {
        mPulledCards = new ArrayList<Card>();
        mCards = new ArrayList<Card>();
        reset();
    }

    public void reset() {
        mPulledCards.clear();
        mCards.clear();
        for (Card.Suits s : Card.Suits.values()) {
            for (Card.Ranks r : Card.Ranks.values()) {
                Card c = new Card(s, r);
                mCards.add(c);
            }
        }
    }

    public Card pullCard() {
        if (mCards.isEmpty())
            return null;
        Card pulled = mCards.get(ThreadLocalRandom.current().nextInt(0, mCards.size()-2));
        mCards.remove(pulled);
        mPulledCards.add(pulled);
        return pulled;
    }

    public void putBack(ArrayList<Card> hand) {
        mCards.addAll(hand);
        mPulledCards.removeAll(hand);
    }

    public boolean isEmpty(){
        return mCards.isEmpty();
    }
}